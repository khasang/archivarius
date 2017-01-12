package io.khasang.archivarius.controller;

import io.khasang.archivarius.convertor.CaseInsensitiveConverter;
import io.khasang.archivarius.entity.Department;
import io.khasang.archivarius.entity.DocKey;
import io.khasang.archivarius.entity.DocType;
import io.khasang.archivarius.entity.Document;
import io.khasang.archivarius.service.DepartmentService;
import io.khasang.archivarius.service.DocTypeService;
import io.khasang.archivarius.service.DocumentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class DocumentController {

    @Autowired
    DocumentService documentService;
    @Autowired
    DocTypeService docTypeService;
    @Autowired
    DepartmentService departmentService;

    private static final Logger log = Logger.getLogger(CompanyController.class);

    @GetMapping(value = {"/{docKey}", "/{docKey}/"})
    public String docKeyList(@PathVariable("docKey") DocKey docKey, Model model) {
        model.addAttribute("documents", documentService.getDocKeyList(docKey));
        return "lists/documents";
    }

    @GetMapping("/control")
    public String controlList(Model model) {
        model.addAttribute("controlList", documentService.getControlList());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        model.addAttribute("dateNow", dateFormat.format(date));
        return "control";
    }

    @GetMapping(value = {"/document/{id}"})
    public String documentGetId(@PathVariable("id") Integer id, ModelMap model) {
        model.addAttribute("document", documentService.getDocumentById(id));
        return "lists/document";
    }

    @GetMapping(value = {"/document/{id}/edit"})
    public String documentForm(@PathVariable("id") Integer id, ModelMap model) {
        Document document = documentService.getDocumentById(id);
        model.addAttribute("docKey", document.getDocKey());
        model.addAttribute("doctypes", docTypeService.getDocTypeList());
        model.addAttribute("departs", departmentService.getDepartmentList());
        model.addAttribute("document", document);
        return "forms/document";
    }

    @GetMapping(value = "/document/{docKey}/add")
    public String showDocumentForm(@PathVariable("docKey") DocKey docKey, ModelMap model) {
        model.addAttribute("docKey", docKey);
        model.addAttribute("doctypes", docTypeService.getDocTypeList());
        model.addAttribute("departs", departmentService.getDepartmentList());
        model.addAttribute("document", new Document());
        return "forms/document";
    }

    @PostMapping(value = "/document/")
    public String submit(@ModelAttribute("document") Document document,
                         BindingResult result, ModelMap model,
                         @RequestParam("file") MultipartFile file,
                         RedirectAttributes redirectAttributes) {
        final String fileName = file.getOriginalFilename();
        Department department = departmentService.getDepartmentById((Integer.valueOf((String) (result.getFieldValue("department")))));
        DocType docType = docTypeService.getDocTypeById(Integer.valueOf((String) result.getFieldValue("documentType")));
        document.setDocumentType(docType);
        document.setDepartment(department);
        document.setFileName(fileName);
        documentService.updateDocument(document);

        String rootPath = System.getProperty("catalina.home");
        File dir = new File(rootPath + File.separator + "tmpFiles" + File.separator + fileName);
        try {
            file.transferTo(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }

        redirectAttributes.addFlashAttribute("message", "Документ " + document.getTitle() + " добавлен");

        return "redirect:/" + document.getDocKey().toString();
    }

    @PostMapping(value = "/document/", params = {"delete"})
    public String delete(final Document document, final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "Документ " + document.getTitle() + " удален");
        documentService.deleteDocument(document.getId());
        return "redirect:/" + document.getDocKey().toString();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(DocKey.class, new CaseInsensitiveConverter<>(DocKey.class));
    }
}

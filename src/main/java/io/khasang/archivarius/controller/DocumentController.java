package io.khasang.archivarius.controller;

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

//    @GetMapping(value = {"/{id}"})
//    public String documentGetId(@PathVariable("id") Integer id, ModelMap model) {
//        model.addAttribute("document", documentService.getDocumentById(id));
//        return "lists/document";
//    }

    @GetMapping(value = {"/document/{id}/edit"})
    public String documentForm(@PathVariable("id") Integer id, ModelMap model) {
        Document document = documentService.getDocumentById(id);
        model.addAttribute("doctypes", docTypeService.getDocTypeList());
        model.addAttribute("departs", departmentService.getDepartmentList());
        model.addAttribute("document", document);
        return "forms/document";
    }

    @GetMapping(value = {"/document/inbox/{id}/edit"})
    public String editInboxDocument(@PathVariable("id") Integer id, ModelMap model) {
        Document document = documentService.getDocumentById(id);
        model.addAttribute("doctypes", docTypeService.getDocTypeList());
        model.addAttribute("departs", departmentService.getDepartmentList());
        model.addAttribute("document", document);
        model.addAttribute("documentKey", 1);
        return "forms/editInbox";
    }

    @GetMapping(value = {"/document/outbox/{id}/edit"})
    public String editOutboxDocument(@PathVariable("id") Integer id, ModelMap model) {
        Document document = documentService.getDocumentById(id);
        model.addAttribute("doctypes", docTypeService.getDocTypeList());
        model.addAttribute("departs", departmentService.getDepartmentList());
        model.addAttribute("document", document);
        model.addAttribute("documentKey", 2);
        return "forms/editOutbox";
    }

    @GetMapping(value = {"/document/internal/{id}/edit"})
    public String editInternalDocument(@PathVariable("id") Integer id, ModelMap model) {
        Document document = documentService.getDocumentById(id);
        model.addAttribute("doctypes", docTypeService.getDocTypeList());
        model.addAttribute("departs", departmentService.getDepartmentList());
        model.addAttribute("document", document);
        model.addAttribute("documentKey", 3);
        return "forms/editInternal";
    }

    @GetMapping(value = "/document/add")
    public String showDocumentForm(ModelMap model) {
        model.addAttribute("doctypes", docTypeService.getDocTypeList());
        model.addAttribute("departs", departmentService.getDepartmentList());
        model.addAttribute("document", new Document());
        return "forms/document";
    }

    @GetMapping(value = "/document/inbox/add")
    public String showInboxDocumentForm(ModelMap model) {
        model.addAttribute("doctypes", docTypeService.getDocTypeList());
        model.addAttribute("departs", departmentService.getDepartmentList());
        model.addAttribute("document", new Document());
        model.addAttribute("documentKey", 1);
        return "forms/newInbox";
    }

    @GetMapping(value = "/document/outbox/add")
    public String showOutboxDocumentForm(ModelMap model) {
        model.addAttribute("doctypes", docTypeService.getDocTypeList());
        model.addAttribute("departs", departmentService.getDepartmentList());
        model.addAttribute("document", new Document());
        model.addAttribute("documentKey", 2);
        return "forms/newOutbox";
    }

    @GetMapping(value = "/document/internal/add")
    public String showInternalDocumentForm(ModelMap model) {
        model.addAttribute("doctypes", docTypeService.getDocTypeList());
        model.addAttribute("departs", departmentService.getDepartmentList());
        model.addAttribute("document", new Document());
        model.addAttribute("documentKey", 3);
        return "forms/newInternal";
    }

    @PostMapping(value = "/document/")
    public String submit(@ModelAttribute("document") Document document,
                         BindingResult result, ModelMap model,
                         @RequestParam("documentKey") Integer docKey,
                         @RequestParam("file") MultipartFile file,
                         RedirectAttributes redirectAttributes) {
        final String fileName = file.getOriginalFilename();
        Department department = departmentService.getDepartmentById((Integer.valueOf((String) (result.getFieldValue("department")))));
        DocType docType = docTypeService.getDocTypeById(Integer.valueOf((String) result.getFieldValue("documentType")));
        document.setDocumentType(docType);
        document.setDepartment(department);
        document.setFileName(fileName);
        document.setDocumentKey(docKey);
        documentService.updateDocument(document);

        String rootPath = System.getProperty("catalina.home");
        File dir = new File(rootPath + File.separator + "tmpFiles" + File.separator + fileName);
        try {
            file.transferTo(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }

        redirectAttributes.addFlashAttribute("message", "Документ " + document.getTitle() + " добавлен");

        switch (docKey) {
            case 1:
                return "redirect:/document/inbox";
            case 2:
                return "redirect:/document/outbox";
            case 3:
                return "redirect:/document/internal";
            default:
                return "redirect:/document/";
        }
    }

    @PostMapping(value = "/document/", params = {"delete"})
    public String delete(final Document document, final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "Документ " + document.getTitle() + " удален");
        documentService.deleteDocument(document.getId());
        return "redirect:/document/";
    }
}

package io.khasang.archivarius.controller;

import io.khasang.archivarius.convertor.CaseInsensitiveConverter;
import io.khasang.archivarius.entity.*;
import io.khasang.archivarius.service.DepartmentService;
import io.khasang.archivarius.service.DocTypeService;
import io.khasang.archivarius.service.DocumentService;
import io.khasang.archivarius.service.WorkerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;

@Controller
public class DocumentController {

    @Autowired
    DocumentService documentService;
    @Autowired
    DocTypeService docTypeService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    WorkerService workerService;

    private static final Logger log = Logger.getLogger(CompanyController.class);

    @GetMapping(value = {"/{docKey}", "/{docKey}/"})
    public String docKeyList(@PathVariable("docKey") DocKey docKey, Model model) {
        model.addAttribute("documents", documentService.getDocKeyList(docKey));
        model.addAttribute("docKey", docKey);
        return "lists/documents";
    }

    @GetMapping("/control")
    public String controlList(Model model) {
        model.addAttribute("documents", documentService.getDocumentList());
        model.addAttribute("docKey", null);
        return "lists/documents";
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
        model.addAttribute("workers", workerService.getWorkerList());
        model.addAttribute("departs", departmentService.getDepartmentList());
        model.addAttribute("document", document);
        return "forms/document";
    }

    @GetMapping(value = "/document/{docKey}/add")
    public String showDocumentForm(@PathVariable("docKey") DocKey docKey, ModelMap model) {
        model.addAttribute("docKey", docKey);
        model.addAttribute("doctypes", docTypeService.getDocTypeList());
        model.addAttribute("departs", departmentService.getDepartmentList());
        model.addAttribute("workers", workerService.getWorkerList());
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
        Worker worker = workerService.getWorkerById(Integer.valueOf((String) result.getFieldValue("worker")));
        document.setDocumentType(docType);
        document.setWorker(worker);
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

    @RequestMapping(value="/search/{searchTerm}")
    public ModelAndView Search(@PathVariable("searchTerm") String documentSearchTerm) {
        ModelAndView mav = new ModelAndView("search");

        mav.addObject("searchTerm", documentSearchTerm);
        mav.addObject("searchResult", documentService.searchDocument(documentSearchTerm));

        return mav;
    }
}

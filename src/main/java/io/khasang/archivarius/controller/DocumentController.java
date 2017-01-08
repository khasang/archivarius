package io.khasang.archivarius.controller;

import io.khasang.archivarius.entity.Department;
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
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @Autowired
    DocTypeService docTypeService;

    @Autowired
    DepartmentService departmentService;

    private static final Logger log = Logger.getLogger(CompanyController.class);

    @RequestMapping("/")
    public String documentList(@ModelAttribute("message") String message, Model model) {
        model.addAttribute("documents", documentService.getDocumentList());
        model.addAttribute("message", message);
        return "lists/documents";
    }

    @RequestMapping("/inbox")
    public String inboxList(Model model) {
        model.addAttribute("inboxList", documentService.getInboxList());
        model.addAttribute("deparment", docTypeService.get)
        return "inbox";
    }

    @RequestMapping("/outbox")
    public String outboxList(Model model) {
        model.addAttribute("outboxList", documentService.getOutboxList());
        return "outbox";
    }

    @RequestMapping("/internal")
    public String internalList(Model model) {
        model.addAttribute("internalList", documentService.getInternalList());
        return "internal";
    }

    @RequestMapping("/control")
    public String controlList(Model model) {
        model.addAttribute("controlList", documentService.getControlList());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        model.addAttribute("dateNow", dateFormat.format(date));
        return "control";
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
    public String documentGetId(@PathVariable("id") Integer id, ModelMap model) {
        model.addAttribute("document", documentService.getDocumentById(id));
        return "lists/document";
    }

    @RequestMapping(value = {"/{id}/edit"}, method = RequestMethod.GET)
    public String documentForm(@PathVariable("id") Integer id, ModelMap model) {
        Document document = documentService.getDocumentById(id);
        model.addAttribute("doctypes", docTypeService.getDocTypeList());
        model.addAttribute("departs", departmentService.getDepartmentList());
        model.addAttribute("document", document);
        return "forms/document";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showDocumentForm(ModelMap model) {
        model.addAttribute("doctypes", docTypeService.getDocTypeList());
        model.addAttribute("departs", departmentService.getDepartmentList());
        model.addAttribute("document", new Document());
        return "forms/document";
    }

    @RequestMapping(value = "/inbox/add", method = RequestMethod.GET)
    public String showInboxDocumentForm(ModelMap model) {
        model.addAttribute("doctypes", docTypeService.getDocTypeList());
        model.addAttribute("departs", departmentService.getDepartmentList());
        model.addAttribute("document", new Document());
        model.addAttribute("documentKey", 1);
        return "forms/newInbox";
    }

    @RequestMapping(value = "/outbox/add", method = RequestMethod.GET)
    public String showOutboxDocumentForm(ModelMap model) {
        model.addAttribute("doctypes", docTypeService.getDocTypeList());
        model.addAttribute("departs", departmentService.getDepartmentList());
        model.addAttribute("document", new Document());
        model.addAttribute("documentKey", 2);
        return "forms/newOutbox";
    }

    @RequestMapping(value = "/internal/add", method = RequestMethod.GET)
    public String showInternalDocumentForm(ModelMap model) {
        model.addAttribute("doctypes", docTypeService.getDocTypeList());
        model.addAttribute("departs", departmentService.getDepartmentList());
        model.addAttribute("document", new Document());
        model.addAttribute("documentKey", 3);
        return "forms/newInternal";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String submit(@ModelAttribute("document") Document document,
                         BindingResult result, ModelMap model,
                         @RequestParam("file") MultipartFile file,
                         RedirectAttributes redirectAttributes) {
        final String fileName = file.getOriginalFilename();
        Department department = departmentService.getDepartmentById((Integer.valueOf((String)(result.getFieldValue("department")))));
        DocType docType = docTypeService.getDocTypeById(Integer.valueOf((String) result.getFieldValue("documentType")));
        document.setDocumentType(docType);
        document.setDepartment(department);
        document.setFileName(fileName);
        document.setDocumentKey(Integer.parseInt(result.getFieldValue("documentKey").toString()));
        documentService.updateDocument(document);

        String rootPath = System.getProperty("catalina.home");
        File dir = new File(rootPath + File.separator + "tmpFiles" + File.separator + fileName);
        try {
            file.transferTo(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }

        redirectAttributes.addFlashAttribute("message", "Документ " + document.getTitle() + " добавлен");

        return "redirect:/document/";
    }

    @PostMapping(value="/", params = {"delete"})
    public String delete(final Document document, final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "Документ " + document.getTitle() + " удален");
        documentService.deleteDocument(document.getId());
        return "redirect:/document/";
    }
}

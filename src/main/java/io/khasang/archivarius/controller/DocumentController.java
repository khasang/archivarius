package io.khasang.archivarius.controller;

import io.khasang.archivarius.entity.DocType;
import io.khasang.archivarius.entity.Document;
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

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @Autowired
    DocTypeService docTypeService;

    private static final Logger log = Logger.getLogger(CompanyController.class);

    @RequestMapping("/")
    public String documentList(Model model) {
        model.addAttribute("documents", documentService.getDocumentList());
        return "lists/documents";
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
    public String documentGetId(@PathVariable("id") Integer id, ModelMap model) {
        model.addAttribute("document", documentService.getDocumentById(id));
        return "lists/document";
    }

    @RequestMapping(value = {"/{id}/edit"}, method = RequestMethod.GET)
    public String documentForm(@PathVariable("id") Integer id, ModelMap model) {
        Document document = documentService.getDocumentById(Integer.valueOf(id));
        model.addAttribute("doctypes", docTypeService.getDocTypeList());
        model.addAttribute("document", document);
        return "forms/document";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showDocumentForm(ModelMap model) {
        model.addAttribute("doctypes", docTypeService.getDocTypeList());
        model.addAttribute("document", new Document());
        return "forms/document";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String submit(@ModelAttribute("document") Document document,
                         BindingResult result, ModelMap model,
                         @RequestParam("file") MultipartFile file) {
        final String fileName = file.getOriginalFilename();
        DocType docType = docTypeService.getDocTypeById(Integer.valueOf((String) result.getFieldValue("documentType")));
        document.setDocumentType(docType);
        document.setFileName(fileName);
        documentService.updateDocument(document);
        String rootPath = System.getProperty("catalina.home");
        File dir = new File(rootPath + File.separator + "tmpFiles" + File.separator + fileName);
        try {
            file.transferTo(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "forms/success";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, params = {"delete"})
    public String deny(@RequestParam int id, @RequestParam String delete, Model model) {
        documentService.deleteDocument(id);
        return "redirect:/document/";
    }
}

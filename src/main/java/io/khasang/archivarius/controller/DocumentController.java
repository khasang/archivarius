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
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        model.addAttribute("documentList", documentService.getDocumentList());
        return "documentList";
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
    public String documentGetId(@PathVariable("id") String id, ModelMap model) {
        Integer intId = Integer.valueOf(id);
        model.addAttribute("documentGetId", documentService.getDocumentById(intId));
        return "documentGetId";
    }

    @RequestMapping(value = {"/{id}/edit"}, method = RequestMethod.GET)
    public String documentForm(@PathVariable("id") String id, ModelMap model) {
        Document document = documentService.getDocumentById(Integer.valueOf(id));
        model.addAttribute("docType", getDropboxList());
        model.addAttribute("document", document);
        return "documentForm";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showDocumentForm(ModelMap model) {
        model.addAttribute("docType", getDropboxList());
        model.addAttribute("document", new Document());
        return "documentForm";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String submit(@ModelAttribute("document")Document document,
                         BindingResult result, ModelMap model) {


        DocType docType;
        if("NONE".equals(result.getFieldValue("docType"))) {
            docType = null;
       } else {
            docType = docTypeService.getDocTypeById(Integer.valueOf((String)(result.getFieldValue("id"))));
       }
        document.setDocumentType(docType);
        documentService.updateDocument(document);
        model.addAttribute("title", document.getTitle());
      //  model.addAttribute("type", document.getDocumentType());
        model.addAttribute("author", document.getAuthor());
        model.addAttribute("deadline", document.getDeadline());
        model.addAttribute("status", document.getStatus());
        model.addAttribute("dateOfReceive", document.getDateOfReceive());
        model.addAttribute("destination", document.getDestination());
        return "resultNewDocument";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, params = {"delete"})
    public String deny(@RequestParam int id, @RequestParam String delete, Model model) {
        documentService.deleteDocument(id);
        return "redirect:/document/";
    }

    //выпадающий список документов из типов документов
    public Map<Integer, String> getDropboxList() {
        List<DocType> docTypeList = docTypeService.getDocTypeList();
        Map<Integer, String> documentTypes = new HashMap<>();
        for (DocType docType : docTypeList) {
            documentTypes.put(docType.getId(), docType.getDocumentType());
        }
        return documentTypes;
    }
}

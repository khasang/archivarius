package io.khasang.archivarius.controller;

import io.khasang.archivarius.entity.Document;
import io.khasang.archivarius.entity.DocumentLifeCycle;
import io.khasang.archivarius.service.DocumentLifeCicleService;
import io.khasang.archivarius.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/document/life_cycle")
public class DocumentLiveCycleController {
    @Autowired
    DocumentLifeCicleService documentLifeCicleService;
    @Autowired
    DocumentService documentService;

    @RequestMapping("/")
    public String documentList(Model model) {
        model.addAttribute("documentLifeCycleList", documentLifeCicleService.getDocumentLifeCycleList());
        return "documentLifeCycleList";
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
    public String documentGetId(@PathVariable("id") String id, ModelMap model) {
        Integer intId = Integer.valueOf(id);
        model.addAttribute("documentLifeCycleGetId", documentLifeCicleService.getDocumentLifeCycleById(intId));
        return "documentLifeCycleGetId";
    }

    @RequestMapping(value = {"/{id}/edit"}, method = RequestMethod.GET)
    public String departmentForm(@PathVariable("id") String id, ModelMap model) {
        Integer intId = Integer.valueOf(id);
        DocumentLifeCycle documentLifeCycle = documentLifeCicleService.getDocumentLifeCycleById(intId);
        model.addAttribute("documents", getDropboxList());
        model.addAttribute("lifecycle", documentLifeCycle);
        return "documentLifeCycleForm";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showDepartmentForm(ModelMap model) {
        model.addAttribute("documents", getDropboxList());
        model.addAttribute("lifeCycle", new DocumentLifeCycle());
        return "documentLifeCycleForm";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String submit(@ModelAttribute("lifecycle")DocumentLifeCycle documentLifeCycle,
                         BindingResult result, ModelMap model) {
        Document document;
        if("NONE".equals(result.getFieldValue("document"))) {
            document = null;
        } else {
            document = documentService.getDocumentById(Integer.valueOf((String)(result.getFieldValue("company"))));
        }
        documentLifeCycle.setDocument(document);
        documentLifeCicleService.updateDocumentLifeCycle(documentLifeCycle);
        model.addAttribute("name", documentLifeCycle.getLifeCycle());
        model.addAttribute("document", documentLifeCycle.getDocument());
        return "resultDepartmentFormEdit";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, params = { "delete" })
    public String deny(@RequestParam int id, @RequestParam String delete, Model model) {
        documentLifeCicleService.deleteDocumentLifeCycleById(id);
        return "redirect:/department/";
    }

    public Map<Integer, String> getDropboxList() {
        List<Document> documentList = documentService.getDocumentList();
        Map<Integer, String> documents = new HashMap<>();
        for(Document document: documentList) {
            documents.put(document.getId(), document.getName());
        }
        return documents;
    }
}

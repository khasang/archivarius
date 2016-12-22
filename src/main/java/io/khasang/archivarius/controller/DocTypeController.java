package io.khasang.archivarius.controller;

import io.khasang.archivarius.entity.DocType;
import io.khasang.archivarius.service.DocTypeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/doctype")
public class DocTypeController {
    @Autowired
    DocTypeService docTypeService;

    private static final Logger log = Logger.getLogger(DocTypeController.class);

    @RequestMapping("/")
    public String docTypeList(Model model) {
        model.addAttribute("docTypeList", docTypeService.getDocTypeList());
        return "docTypeList";
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
    public String docTypeGetId(@PathVariable("id") String id, ModelMap model) {
        Integer intId = Integer.valueOf(id);
        model.addAttribute("docTypeGetId", docTypeService.getDocTypeById(intId));
        return "docTypeGetId";
    }

    @RequestMapping(value = {"/{id}/edit"}, method = RequestMethod.GET)
    public ModelAndView docTypeForm(@PathVariable("id") String id) {
        Integer intId = Integer.valueOf(id);
        DocType docType = docTypeService.getDocTypeById(intId);
        docType.setId(docType.getId());
        docType.setDocumentType(docType.getDocumentType());
        docType.setDescription(docType.getDescription());
        return new ModelAndView("docTypeForm", "docType", docType);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("docTypeForm", "docType", new DocType());
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String submit(@ModelAttribute("docType")DocType docType,
                         BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        docTypeService.updateDocType(docType);
        model.addAttribute("documentType", docType.getDocumentType());
        model.addAttribute("description", docType.getDescription());
        return "resultDocTypeFormEdit";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, params = { "delete" })
    public String deny(@RequestParam int id, @RequestParam String delete, Model model) {
        docTypeService.deleteDocType(id);
        return "redirect:/doctype/";
    }
}

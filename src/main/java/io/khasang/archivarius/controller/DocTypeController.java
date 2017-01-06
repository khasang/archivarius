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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/doctype")
public class DocTypeController {
    @Autowired
    DocTypeService docTypeService;

    private static final Logger log = Logger.getLogger(DocTypeController.class);

    @RequestMapping("/")
    public String docTypeList(Model model) {
        model.addAttribute("docTypes", docTypeService.getDocTypeList());
        return "lists/docTypes";
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
    public String docTypeGetId(@PathVariable("id") String id, ModelMap model) {
        Integer intId = Integer.valueOf(id);
        model.addAttribute("docType", docTypeService.getDocTypeById(intId));
        return "lists/docType";
    }

    @RequestMapping(value = {"/{id}/edit"}, method = RequestMethod.GET)
    public ModelAndView docTypeForm(@PathVariable("id") Integer id) {
        DocType docType = docTypeService.getDocTypeById(id);
        return new ModelAndView("forms/docType", "docType", docType);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("forms/docType", "docType", new DocType());
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String submit(@ModelAttribute("docType")DocType docType,
                         BindingResult result, ModelMap model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "error";
        }
        docTypeService.updateDocType(docType);
        redirectAttributes.addFlashAttribute("message", "Успешно!");
        return "redirect:/doctype/";
    }

    @PostMapping(value = "/delete")
    public String delete(@RequestParam int id) {
        docTypeService.deleteDocType(id);
        return "redirect:/doctype/";
    }
}

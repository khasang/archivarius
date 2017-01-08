package io.khasang.archivarius.controller;

import io.khasang.archivarius.entity.DocType;
import io.khasang.archivarius.service.DocTypeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/")
    public String docTypeList(@ModelAttribute("message") String message, Model model) {
        model.addAttribute("docTypes", docTypeService.getDocTypeList());
        model.addAttribute("message", message);
        return "lists/docTypes";
    }

    @GetMapping(value = {"/{id}"})
    public String docTypeGetId(@PathVariable("id") Integer id, ModelMap model) {
        model.addAttribute("docType", docTypeService.getDocTypeById(id));
        return "lists/docType";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCUMENTOVED')")
    @GetMapping(value = {"/{id}/edit"})
    public ModelAndView docTypeForm(@PathVariable("id") Integer id) {
        DocType docType = docTypeService.getDocTypeById(id);
        return new ModelAndView("forms/docType", "docType", docType);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCUMENTOVED')")
    @GetMapping(value = "/add")
    public ModelAndView showForm() {
        return new ModelAndView("forms/docType", "docType", new DocType());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCUMENTOVED')")
    @PostMapping(value = "/", params = {"save"})
    public String submit(@ModelAttribute("docType")DocType docType,
                         BindingResult result, ModelMap model,
                         RedirectAttributes redirectAttributes) {
        docTypeService.updateDocType(docType);
        redirectAttributes.addFlashAttribute("message", "Тип документа " + docType.getName() + " создан");
        return "redirect:/doctype/";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCUMENTOVED')")
    @PostMapping(value="/", params = {"delete"})
    public String delete(final DocType docType, final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "Тип документа " + docType.getName() + " удален");
        docTypeService.deleteDocType(docType.getId());
        return "redirect:/doctype/";
    }
}

package io.khasang.archivarius.controller;

import io.khasang.archivarius.entity.Document;
import io.khasang.archivarius.service.DocumentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/document")
public class DocumentController {
    @Autowired
    DocumentService documentService;

    private static final Logger log = Logger.getLogger(CompanyController.class);

    //готов список документов
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
    public ModelAndView documentForm(@PathVariable("id") String id) {
        Integer intId = Integer.valueOf(id);
        Document document = documentService.getDocumentById(intId);
        document.setId(document.getId());
        document.setTitle(document.getTitle());
        document.setAuthor(document.getAuthor());
        document.setDestination(document.getDestination());
        document.setStatus(document.getStatus());
        document.setDateOfReceive(document.getDateOfReceive());
        document.setDocumentType(document.getDocumentType());
        document.setAuthor(document.getAuthor());
        document.setDeadline(document.getDeadline());
        return new ModelAndView("documentForm", "document", document);
    }

    //готов
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("documentForm", "document", new Document());
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String submit(@ModelAttribute("document") Document document,
                         BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        documentService.updateDocument(document);
        //можно обратится к полям объекта передав весь объект, не добавляя атрибуты к моделе
        //модель уже получается содержит все атрибуты (поля класса энтити) потому что передали
        //всю сущность в documentService.updateDocument(document)
        // может я и не прав, но все работает (с) Евгений

       // model.addAttribute("title", document.getTitle());
       // model.addAttribute("type", document.getDocumentType());
       // model.addAttribute("author", document.getAuthor());
       // model.addAttribute("deadline", document.getDeadline());
       // model.addAttribute("status", document.getStatus());
       // model.addAttribute("dateOfReceive", document.getDateOfReceive());
       // model.addAttribute("destination", document.getDestination());
        return "resultNewDocument";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, params = {"delete"})
    public String deny(@RequestParam int id, @RequestParam String delete, Model model) {
        documentService.deleteDocument(id);
        return "redirect:/document/";
    }
}

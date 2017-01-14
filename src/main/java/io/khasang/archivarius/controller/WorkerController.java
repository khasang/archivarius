package io.khasang.archivarius.controller;

import io.khasang.archivarius.entity.Department;
import io.khasang.archivarius.entity.Worker;
import io.khasang.archivarius.service.DepartmentService;
import io.khasang.archivarius.service.WorkerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/worker")
public class WorkerController {
    @Autowired
    WorkerService workerService;
    @Autowired
    DepartmentService departmentService;

    private static final Logger log = Logger.getLogger(WorkerController.class);

    @GetMapping("/")
    public String workerList(@ModelAttribute("message") String message, Model model) {
        model.addAttribute("workers", workerService.getWorkerList());
        model.addAttribute("message", message);
        return "lists/workers";
    }

    @GetMapping({"/{id}"})
    public String workerGetId(@PathVariable("id") Integer id, ModelMap model) {
        model.addAttribute("worker", workerService.getWorkerById(id));
        return "lists/worker";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCUMENTOVED')")
    @GetMapping({"/{id}/edit"})
    public String workerForm(@PathVariable("id") Integer id, ModelMap model) {
        Worker worker = workerService.getWorkerById(id);
        model.addAttribute("departments", departmentService.getDepartmentList());
        model.addAttribute("worker", worker);
        return "forms/worker";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCUMENTOVED')")
    @GetMapping("/add")
    public String showWorkerForm(ModelMap model) {
        model.addAttribute("departments", departmentService.getDepartmentList());
        model.addAttribute("worker", new Worker());
        return "forms/worker";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCUMENTOVED')")
    @PostMapping(value = "/", params = {"save"})
    public String submit(@ModelAttribute("worker")Worker worker,
                         BindingResult result, ModelMap model,
                         final RedirectAttributes redirectAttributes) {
        Department department = departmentService.getDepartmentById(Integer.valueOf((String)(result.getFieldValue("department"))));
        worker.setDepartment(department);
        workerService.updateWorker(worker);
        redirectAttributes.addFlashAttribute("message", "Сотрудник " + worker.getLastName() + " создан");
        return "redirect:/worker/";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCUMENTOVED')")
    @PostMapping(value="/", params = {"delete"})
    public String delete(final Worker worker, final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "Сотрудник " + worker.getLastName() + " удален");
        workerService.deleteWorker(worker);
        return "redirect:/worker/";
    }
}

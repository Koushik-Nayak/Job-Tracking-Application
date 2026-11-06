package com.jsp.job.tracking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsp.job.tracking.entity.Company;
import com.jsp.job.tracking.service.CompanyService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/companies")
    public String list(Model model) {
        model.addAttribute("companies", companyService.findAll());
        return "company-list";
    }

    @GetMapping("/companies/add")
    public String addPage() {
        return "company-form";
    }

    @PostMapping("/companies/save")
    public String save(@ModelAttribute Company company) {
        companyService.save(company);
        return "redirect:/companies";
    }

    @GetMapping("/companies/edit")
    public String edit(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("company", companyService.findById(id));
        return "company-form";
    }

    @GetMapping("/companies/delete")
    public String delete(@RequestParam("id") Integer id) {

        if (companyService.hasJobs(id)) {
            return "redirect:/companies?error=jobs";
        }

        companyService.delete(id);

        return "redirect:/companies";
    }
}

package com.jsp.job.tracking.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsp.job.tracking.entity.Company;
import com.jsp.job.tracking.entity.Job;
import com.jsp.job.tracking.entity.User;
import com.jsp.job.tracking.service.CompanyService;
import com.jsp.job.tracking.service.JobService;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class JobController {

	private final JobService jobService;
	private final CompanyService companyService;

	@GetMapping("/jobs/list")
	public String list(Model model) {
		model.addAttribute("jobs", jobService.findAllActive());
		return "job-list";
	}

	@GetMapping("/jobs/search")
	public String search(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
		model.addAttribute("jobs", jobService.search(keyword));
		model.addAttribute("keyword", keyword);
		return "job-list";
	}

	@GetMapping("/jobs/admin/list")
	public String adminJobList(HttpSession session, Model model) {

		User user = (User) session.getAttribute("loggedInUser");
		if (user == null)
			return "redirect:/login";

		if (!user.getRole().name().equals("ADMIN"))
			return "redirect:/dashboard";

		model.addAttribute("jobs", jobService.findAllActive());

		return "admin-job-list";
	}

	@GetMapping("/jobs/add")
	public String addPage(Model model) {
		model.addAttribute("companies", companyService.findAll());
		model.addAttribute("job", new Job());
		return "job-form";
	}

	@PostMapping("/jobs/save")
	public String save(@ModelAttribute Job job, @RequestParam("companyId") Integer companyId) {

		Company company = companyService.findById(companyId);
		job.setCompany(company);

		if (job.getPostedDate() == null) {
			job.setPostedDate(LocalDate.now());
		}

		jobService.save(job);
		return "redirect:/jobs/list";
	}

	@GetMapping("/jobs/edit")
	public String edit(@RequestParam("id") Integer id, Model model) {
		model.addAttribute("job", jobService.findById(id));
		model.addAttribute("companies", companyService.findAll());
		return "job-form";
	}

	@GetMapping("/jobs/delete")
	public String delete( @RequestParam("id") Integer id) {
		jobService.delete(id);
		return "redirect:/jobs/list";
	}

	@GetMapping("/jobs/view")
	public String view( @RequestParam("id") Integer id, Model model) {
		model.addAttribute("job", jobService.findById(id));
		return "job-details";
	}
}

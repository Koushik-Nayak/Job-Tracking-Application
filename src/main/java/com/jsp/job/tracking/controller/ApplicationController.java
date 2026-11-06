package com.jsp.job.tracking.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.jsp.job.tracking.entity.ApplicationStatus;
import com.jsp.job.tracking.entity.JobApplication;
import com.jsp.job.tracking.entity.User;
import com.jsp.job.tracking.service.JobApplicationService;
import com.jsp.job.tracking.service.JobService;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ApplicationController {
	
	private final JobApplicationService applicationService;

	@Autowired
	private JobService jobService;

	@GetMapping("/applications/apply")
	public String apply(@RequestParam("jobId") Integer jobId, HttpSession session, Model model) {

		User user = (User) session.getAttribute("loggedInUser");

		if (user == null) {
			return "redirect:/login";
		}

		if (user.getRole().name().equals("ADMIN")) {
			return "redirect:/dashboard/admin";
		}

		JobApplication application = applicationService.apply(user.getId(), jobId);

		if (application == null) {

			model.addAttribute("error", "You have already applied for this job or the job is unavailable.");

		} else {

			model.addAttribute("success", "Application submitted successfully.");
		}

		model.addAttribute("job", jobService.findById(jobId));

		return "job-details";
	}

	@GetMapping("/applications/my")
	public String myApplications(HttpSession session, Model model) {

		User user = (User) session.getAttribute("loggedInUser");

		model.addAttribute("applications", applicationService.findByUser(user.getId()));

		return "my-applications";
	}

	@GetMapping("/applications/filter")
	public String filter(@RequestParam("status") String status, HttpSession session, Model model) {

		User user = (User) session.getAttribute("loggedInUser");

		if (status == null || status.isBlank() || status.equals("ALL")) {
			model.addAttribute("applications", applicationService.findByUser(user.getId()));
		} else {
			ApplicationStatus applicationStatus = ApplicationStatus.valueOf(status);

			model.addAttribute("applications",
					applicationService.findByUserAndStatus(user.getId(), applicationStatus));
		}

		return "my-applications";
	}

	@GetMapping("/applications/job")
	public String viewJobApplications(@RequestParam("id") Integer jobId, Model model) {

		model.addAttribute("applications", applicationService.findByJob(jobId));
		model.addAttribute("job", jobService.findById(jobId));

		return "application-list";
	}

	@GetMapping("/applications/update")
	public String updatePage(@RequestParam("id") Integer id, Model model) {
		model.addAttribute("application", applicationService.findById(id));
		model.addAttribute("statuses", ApplicationStatus.values());
		return "application-update";
	}

	@PostMapping("/applications/update")
	public String update(@RequestParam("id") Integer id, @RequestParam("status") ApplicationStatus status,
			@RequestParam(value = "interviewDate", required = false) String interviewDate,
			@RequestParam(value = "interviewTime", required = false) String interviewTime,
			@RequestParam(value = "notes", required = false) String notes) {

		LocalDate date = null;

		if (interviewDate != null && !interviewDate.isBlank()) {
			date = LocalDate.parse(interviewDate);
		}

		applicationService.updateStatus(id, status, date, interviewTime, notes);

		return "redirect:/applications/job?id=" + applicationService.findById(id).getJob().getId();
	}
}

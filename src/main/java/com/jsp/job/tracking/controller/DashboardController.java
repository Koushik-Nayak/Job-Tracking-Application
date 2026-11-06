package com.jsp.job.tracking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jsp.job.tracking.entity.ApplicationStatus;
import com.jsp.job.tracking.entity.User;
import com.jsp.job.tracking.service.JobApplicationService;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class DashboardController {

    private final JobApplicationService applicationService;

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user.getRole().name().equals("ADMIN")) {
            return "redirect:/dashboard/admin";
        }

        Integer userId = user.getId();

        model.addAttribute("total",
                applicationService.countByUser(userId));
        model.addAttribute("applied",
                applicationService.countByUserAndStatus(
                        userId, ApplicationStatus.APPLIED));
        model.addAttribute("interview",
                applicationService.countByUserAndStatus(
                        userId, ApplicationStatus.INTERVIEW));
        model.addAttribute("selected",
                applicationService.countByUserAndStatus(
                        userId, ApplicationStatus.SELECTED));
        model.addAttribute("rejected",
                applicationService.countByUserAndStatus(
                        userId, ApplicationStatus.REJECTED));

        return "dashboard";
    }

    @GetMapping("/dashboard/admin")
    public String adminDashboard() {
        return "admin-dashboard";
    }
}

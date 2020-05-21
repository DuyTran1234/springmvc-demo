package com.project.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {
	@RequestMapping(value = "/dang-xuat")
	public String logoutController(HttpSession session) {
		if(session.getAttribute("userSession") == null) {
			return "LoginUser";
		}
		session.removeAttribute("userSession");
		return "HomeForward";
	}
}

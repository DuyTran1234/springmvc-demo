package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeForward {
	@RequestMapping(value = {"/trang-chu", "/"}, method = RequestMethod.GET)
	public String homeForward() {
		return "HomeForward";
	}
}

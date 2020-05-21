package com.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.model.UserDTO;
import com.project.service.UserService;
import com.project.validator.UserValidator;

@Controller
public class RegisterController {
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Autowired
	@Qualifier("userValidator")
	private UserValidator userValidator;
	
	@RequestMapping(value = "/dang-ky", method = RequestMethod.GET)
	public String registerForward(HttpServletRequest request, HttpSession session) {
		if(session.getAttribute("userSession") != null) {
			return "redirect:/trang-chu";
		}
		request.setAttribute("userRegister", new UserDTO());
		return "RegisterPage";
	}
	
	@RequestMapping(value = "/register-controller", method = RequestMethod.POST)
	public String registerController(HttpSession session, @ModelAttribute("userRegister") UserDTO userDTO,
			BindingResult result) {
		if(session.getAttribute("userSession") != null) {
			return "redirect:/trang-chu";
		}
		userValidator.validate(userDTO, result);
		if(result.hasErrors()) {
			return "RegisterPage";
		}
		userService.addUser(userDTO);
		session.setAttribute("userSession", userDTO);
		return "HomeForward";
	}
}

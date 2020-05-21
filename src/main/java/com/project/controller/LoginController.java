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
public class LoginController {
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Autowired
	@Qualifier("userValidator")
	private UserValidator userValidator;
	
	@RequestMapping(value = "/dang-nhap")
	public String loginForward(HttpServletRequest request, HttpSession session) {
		if(session.getAttribute("userSession") == null) {
			request.setAttribute("userLogin", new UserDTO());
			return "LoginUser";
		}
		else {
			return "redirect:/trang-chu";
		}
	}
	
	@RequestMapping(value = "/login-controller", method = RequestMethod.POST)
	public String loginController(HttpSession session, HttpServletRequest request,
			@ModelAttribute("userLogin") UserDTO userDTO, BindingResult result) {
		if(session.getAttribute("userSession") != null) {
			return "redirect:/trang-chu";
		}
		else {
			userValidator.validate(userDTO, result);
			if(result.hasErrors()) {
				return "LoginUser";
			}
			UserDTO loginUserDTO = userService.getUserByName(userDTO.getName(), userDTO.getPassword());
			if(loginUserDTO == null) {
				return "redirect:/dang-nhap";
			}
			session.setAttribute("userSession", loginUserDTO);
			return "redirect:/trang-chu";
		}
	}
}

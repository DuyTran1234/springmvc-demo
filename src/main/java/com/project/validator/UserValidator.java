package com.project.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.project.model.UserDTO;

@Component(value = "userValidator")
public class UserValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	public void validate(Object target, Errors errors) {
		UserDTO userDTO = (UserDTO) target;
		if(userDTO.getName().length() <= 0) {
			errors.rejectValue("name", "empty.name");
		}
		if(userDTO.getPassword().length() <= 0) {
			errors.rejectValue("password", "empty.password");
		}
	}

}

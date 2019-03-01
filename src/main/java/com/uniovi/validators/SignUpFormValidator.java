package com.uniovi.validators;

import com.uniovi.entities.User;
import com.uniovi.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.*;

@Component
public class SignUpFormValidator implements Validator {

	@Autowired
	private UsersService usersService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Error.empty");

		if (user.getUsername().length() < 4 || user.getUsername().length() > 28) {
			errors.rejectValue("username", "Error.signup.username.length");
		}

		if (usersService.getUserByUsername(user.getUsername()) != null) {
			errors.rejectValue("username", "Error.signup.username.duplicate");
		}
		if (user.getName().length() < 3 || user.getName().length() > 24) {
			errors.rejectValue("name", "Error.signup.name.length");
		}
		if (user.getLastName().length() < 3 || user.getLastName().length() > 24) {
			errors.rejectValue("lastName", "Error.signup.lastName.length");
		}

		if (user.getPassword().length() < 6 || user.getPassword().length() > 24) {
			errors.rejectValue("password", "Error.signup.password.length");
		}

		if (!user.getPasswordConfirm().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirm", "Error.signup.passwordConfirm.coincidence");
		}
	}
}
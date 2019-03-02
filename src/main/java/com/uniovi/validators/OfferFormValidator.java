package com.uniovi.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Offer;

@Component
public class OfferFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return Offer.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Offer offer = (Offer) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "Error.empty");
		
		if (offer.getTitle().length() < 2 || offer.getTitle().length() > 50) {
			errors.rejectValue("title", "Error.offer.title");
		}

		if (offer.getCost() < 0) {
			errors.rejectValue("cost", "Error.offer.cost");
		}

		if (offer.getDescription().length() < 2 || offer.getDescription().length() > 50) {
			errors.rejectValue("description", "Error.offer.description");
		}
	}
}
package org.tourGo.service.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

@Service
public class ValidateHandleService {
	
	public Map<String, String> validateHandling(Errors errors) {
		Map<String, String> validatorResult = new HashMap<>();
		
		for(FieldError error : errors.getFieldErrors()) {
			String validKeyName = String.format("%s", error.getField());
			validatorResult.put(validKeyName, error.getDefaultMessage());
		}
		
		return validatorResult;
	}
}

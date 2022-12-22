package org.tourGo.controller.plan;

import java.time.LocalDate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.tourGo.models.plan.PlannerRq;
@Component
public class PlanValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		
		return PlannerRq.class.isAssignableFrom(clazz);
	}
	

	@Override
	public void validate(Object target, Errors errors) {
		
		PlannerRq planner = (PlannerRq) target;
		
		//제목 입력했는지 체크
		/**
		String title = planner.getTitle();
		if(title ==null || title.isBlank()) {
			errors.rejectValue("title", "errors.title");
		}
		
		LocalDate sdate = planner.getSdate();
		
		if(sdate==null) {
			errors.rejectValue("sdate", "errors.sdate");
		}
		
		*/
	}

}

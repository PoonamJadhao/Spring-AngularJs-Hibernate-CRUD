package com.cruddemo.springmvc.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cruddemo.springmvc.model.Surveyor;
import com.cruddemo.springmvc.service.SurveyorService;

@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	SurveyorService service;
	
	@Autowired
	MessageSource messageSource;

	/*
	 * This method will list all existing surveyors.
	 */
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listSurveyors(ModelMap model) {

		List<Surveyor> surveyors = service.findAllSurveyors();
		model.addAttribute("surveyors", surveyors);
		return "allsurveyors";
	}

	/*
	 * This method will provide the medium to add a new surveyor.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newSurveyor(ModelMap model) {
		Surveyor surveyor = new Surveyor();
		model.addAttribute("surveyor", surveyor);
		model.addAttribute("edit", false);
		return "registration";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving surveyor in database. It also validates the user input
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveSurveyor(@Valid Surveyor surveyor, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [site] should be implementing custom @Unique annotation 
		 * and applying it on field [site] of Model class [Surveyor].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
		if(!service.isSurveyorSiteUnique(surveyor.getId(), surveyor.getSite())){
			FieldError siteError =new FieldError("surveyor","site",messageSource.getMessage("non.unique.site", new String[]{surveyor.getSite()}, Locale.getDefault()));
		    result.addError(siteError);
			return "registration";
		}
		
		service.saveSurveyor(surveyor);

		model.addAttribute("success", "Surveyor " + surveyor.getName() + " registered successfully");
		return "success";
	}


	/*
	 * This method will provide the medium to update an existing surveyor.
	 */
	@RequestMapping(value = { "/edit-{site}-surveyor" }, method = RequestMethod.GET)
	public String editSurveyor(@PathVariable String site, ModelMap model) {
		Surveyor surveyor = service.findSurveyorBySite(site);
		model.addAttribute("surveyor", surveyor);
		model.addAttribute("edit", true);
		return "registration";
	}
	
	/*
	 * This method will be called on form submission, handling POST request for
	 * updating surveyor in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-{site}-surveyor" }, method = RequestMethod.POST)
	public String updateSurveyor(@Valid Surveyor surveyor, BindingResult result,
			ModelMap model, @PathVariable String site) {

		if (result.hasErrors()) {
			return "registration";
		}

		if(!service.isSurveyorSiteUnique(surveyor.getId(), surveyor.getSite())){
			FieldError siteError =new FieldError("surveyor","site",messageSource.getMessage("non.unique.site", new String[]{surveyor.getSite()}, Locale.getDefault()));
		    result.addError(siteError);
			return "registration";
		}

		service.updateSurveyor(surveyor);

		model.addAttribute("success", "Surveyor " + surveyor.getName()	+ " updated successfully");
		return "success";
	}

	
	/*
	 * This method will delete an surveyor by it's Site value.
	 */
	@RequestMapping(value = { "/delete-{site}-surveyor" }, method = RequestMethod.GET)
	public String deleteSurveyor(@PathVariable String site) {
		service.deleteSurveyorBySite(site);
		return "redirect:/list";
	}

}

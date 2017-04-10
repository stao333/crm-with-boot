package com.crm.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UIController
{
	@RequestMapping(value = "/ui", method = RequestMethod.GET)
	public String getMovie( ModelMap model ) {
		return "crm_page";
	}

}
package com.crm.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UIController
{
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login( ModelMap model ) {
		return "login";
	}

	@RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
	public String loginerror( ModelMap model ) {
		model.addAttribute( "error", "true" );
		return "denied";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout( ModelMap model ) {
		return "logout";
	}

	@RequestMapping(value = "/ui", method = RequestMethod.GET)
	public String getMovie( ModelMap model ) {
		return "crm_page";
	}

}
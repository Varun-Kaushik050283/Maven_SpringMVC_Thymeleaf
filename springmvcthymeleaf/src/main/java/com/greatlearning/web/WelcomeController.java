package com.greatlearning.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

	@RequestMapping("/")
	public String giveWelcomeMessage(ModelMap modelMap) {
		modelMap.addAttribute("welcomeMessage","Welcome to our spring MVC and Thymeleaf website.");
		return "welcome";
	}
}

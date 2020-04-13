package com.stacksimplify.restservices.hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {

	@Autowired
	private ResourceBundleMessageSource messageSource;

	// Simple Method
	// URI -/helloworld
	// Get Method
	// @RequestMapping(method = RequestMethod.GET, path = "/helloworld")
	@GetMapping("/helloworld1")
	public String helloWorld() {
		return "Hello World1";
	}

	@GetMapping("/helloworld-bean")
	public UserDetails helloWorldBean() {
		return new UserDetails("Raj Kumar", "Gupta", "Bangalore");
	}

	@GetMapping("/hello-int")
	public String getMessageInI18NFormat(@RequestHeader(name = "Accept-Lunguage", required = false) String locale) {
		return messageSource.getMessage("lable.hello", null, new Locale(locale));
	}

	@GetMapping("/hello-int2")
	public String getMessageInI18NFormat2() {
		return messageSource.getMessage("lable.hello", null, LocaleContextHolder.getLocale());
	}
}

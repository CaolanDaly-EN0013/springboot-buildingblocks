package com.stacksimplify.restservices.Hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@RequestMapping(method = RequestMethod.GET, path = "/helloworld1")
	public String helloWorld1() {
		return "Hello World @RequestMapping";
	}
	
	@GetMapping("/helloworld2")
	public String helloWorld2() {
		return "Hello World @GetMapping";
	}
	
	@GetMapping("/helloworldbean")
	public UserDetails helloWorldBean() {
		return new UserDetails("Caolán", "Daly", "Belfast");
	}
}

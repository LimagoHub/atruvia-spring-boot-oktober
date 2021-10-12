package de.atruvia.webapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
//@RequestScope
//@SessionScope Nicht bei Rest
public class DemoController {
	
	@GetMapping(path="/gruss")
	public String herbert() {
		return "Hallo Welt";
	}

}

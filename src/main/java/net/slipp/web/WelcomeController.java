package net.slipp.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	@GetMapping("/helloworld")
//	public String welcome(Model model) {
//		System.out.println("name : " + name + " age : " + age);
/*
 		model.addAttribute("name", "javajigi");
		model.addAttribute("value", 10000);
		model.addAttribute("taxed_value", 30);
		model.addAttribute("in_ca", true);
*/
		public String welcom(Model model) {
		List<MyModel> repo = Arrays.asList(new MyModel("javajigi"), new MyModel("sanjigi"));
		model.addAttribute("repo", repo);
		return "welcome";
	}
}

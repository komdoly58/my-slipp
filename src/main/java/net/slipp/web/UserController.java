package net.slipp.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.slipp.domain.User;
import net.slipp.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {
//	private List<User> users = new ArrayList<User>();
	
	@Autowired
	private UserRepository userRepository;
	@GetMapping("/loginForm")
	public String longinForm() {
		
		return "/user/login";
	}
	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session) {
		User user = userRepository.findByUserId(userId);
		if(user==null) {
			System.out.println("Login Failure!");
			return "redirect:/users/loginform";
		}
		if(!password.equals(user.getPassword())) {
			System.out.println("Login Failure!");
			return "redirect:/users/loginform";
		}
		System.out.println("Login Success!");
		session.setAttribute("user", user);
		return "redirect:/";
	}
	@GetMapping("/form")
	public String form() {
		return "/user/form";
	}

	@PostMapping("")
//	public String create(String userId, String password, String name, String email) {
	public String create(User user) {
//		System.out.println("userId : " + userId + " name : " + name);
		System.out.println("user : " + user);
//		users.add(user);
		userRepository.save(user);
		return "redirect:/users";
	}
	@GetMapping("")
	public String list(Model model) {
//		model.addAttribute("users", users);
		model.addAttribute("users", userRepository.findAll());
		return "/user/list";
	}
	@GetMapping("/{id}/form")
	public String updateform(@PathVariable Long id, Model model) {
		User user = userRepository.findById(id).get();
		model.addAttribute("user", user);
		return "/user/updateform";
	}		
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, User newUser) {
		User user = userRepository.findById(id).get();
		user.update(newUser);
		userRepository.save(user);
		return"redirect:/users";
	}

}

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
			return "redirect:/users/loginForm";
		}
//		if(!password.equals(user.getPassword())) {
		if(!user.matchPassword(password)) {
			System.out.println("Login Failure!");
			return "redirect:/users/loginForm";
		}
		System.out.println("Login Success!");
		session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);
		return "redirect:/";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		//session.invalidate();
		/*session에 해당하는 이름을 매개변수로 넣어줘야 한다 */
		/* key값을 sessionUser로 변경 */
		session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);
		System.out.println("Logout Success!");
		return "redirect:/";
	}
	
	@GetMapping("/form")
	public String form() {
		return "/user/form";
	}
//	@GetMapping("/post")
//	public String post() {
//		return "redirect:/";
//	}
//user 정보를 db에 기록한다.
	@PostMapping("")
//	public String create(String userId, String password, String name, String email) {
	public String create(User user) {
//		System.out.println("userId : " + userId + " name : " + name);
		System.out.println("user : " + user);
//		users.add(user);
		userRepository.save(user);
		return "redirect:/users";
	}
//user 정보를 보여준다.	
	@GetMapping("")
	public String list(Model model) {
//		model.addAttribute("users", users);
		model.addAttribute("users", userRepository.findAll());
		return "/user/list";
	}
//user id에 맞는 정보를 url에서 받아와 sessionUser 정보와 비교후 맞으면 해당 정보(SessionUser)를 보여준다.	
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session){
//		Object tempUser = session.getAttribute("sessionUser");
//		if(tempUser == null) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/loginForm";
		}
//		User SessionUser = (User) tempUser;
		User SessionUser =HttpSessionUtils.getUserFromSession(session);
		
//		if(!id.equals(SessionUser.getId())) {
		if(!SessionUser.matchId(id)) {
			throw new IllegalStateException("자신의 정보만 수정할 수 있습니다.");
		}

		User user = userRepository.findById((Long)SessionUser.getId()).get();
		model.addAttribute("SessionUser", user);
		System.out.println("go to updateform!");
		System.out.println("user : " + user);
		return "/user/updateForm";
	}
//사용자 정보 수정시 id와 sessionUser의 id가 일치할 경우만 updatedUser 정보를 db에 적는다.	
	@PostMapping("/{id}")
	public String update(@PathVariable Long id, User updatedUser, HttpSession session) {
		System.out.println("updatedUser : " + updatedUser);
//		Object tempUser = session.getAttribute("sessionUser");
//		if(tempUser == null) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/loginForm";
		}
//		User sessionUser = (User) tempUser;
		User sessionUser = HttpSessionUtils.getUserFromSession(session);
//		if(!id.equals(sessionUser.getId())) {
		if(!sessionUser.matchId(id)) {
			throw new IllegalStateException("자신의 정보만 수정할 수 있습니다.");
		}	
		User user = userRepository.findById(id).get();
		user.update(updatedUser);
		userRepository.save(user);
		sessionUser = updatedUser;
		System.out.println("sessionUser : " + sessionUser);
		return"redirect:/users";
	}

}

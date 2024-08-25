package br.edu.ifsuldeminas.mch.webii.crudmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifsuldeminas.mch.webii.crudmanager.model.User;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repo.AddressRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repo.UserRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AddressRepository addressRepository;

	@GetMapping
	public String listUsers(Model model) {

		List<User> users = userRepository.findAll();

		model.addAttribute("users", users);

		return "users_page";
	}

	@GetMapping("/form")
	public String userForm(@ModelAttribute("user") User user) {
		// ESTUDAR BINDING DO SPRING

		return "users_form";
	}

	@PostMapping("/register")
	public String userNew(@Valid @ModelAttribute("user") User user, BindingResult err) {

		if (err.hasErrors()) {
			return "users_form";
		}

		addressRepository.save(user.getAddress());
		userRepository.save(user);

		return "redirect:/users";
	}

	@GetMapping("/update/{id}")
	public String userUpdate(@PathVariable("id") Integer id, Model model) {

		Optional<User> userOpt = userRepository.findById(id);
		User user;

		if (!userOpt.isPresent()) {
			user = new User();
		} else {
			user = userOpt.get();
		}

		model.addAttribute("user", user);

		return "users_form";
	}

	@GetMapping("/delete/{id}")
	public String userDelete(@PathVariable("id") Integer id) {

		userRepository.delete(new User(id));
		return "redirect:/users";
	}
}

package controller;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.User;
import services.UserServices;

@RestController
@RequestMapping("/api/users")
public class UserConteroller {
	@Autowired
	private UserServices userService;

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user) {
		return ResponseEntity.ok(userService.register(user));
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Map<String, String> body) {
		boolean success = userService.login(body.get("username"), body.get("password"));
		return ResponseEntity.ok(success ? "Login successful" : "Invalid credentials");
	}

	@GetMapping("/search")
	public Page<User> search(@RequestParam(required = false) String name,
			@RequestParam(required = false) String pinCode, @RequestParam LocalDate from, @RequestParam LocalDate to,
			@RequestParam int page, @RequestParam int size) {
		Pageable pageable = (Pageable) PageRequest.of(page, size);
		return userService.searchUsers(name, pinCode, from, to, pageable);
	}
}

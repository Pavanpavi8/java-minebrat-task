package services;

import java.awt.print.Pageable;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import model.Address;
import model.User;
import repository.UserRepository;

@Service
public class UserServices {
	@Autowired
	private UserRepository userRepository;

	public User register(User user) {
		user.setRegistrationDate(LocalDate.now());
		user.getAddress().setRegistrationDate(LocalDate.now());
		Address address = user.getAddress();
		userRepository.save(user);
		address.setUserId(user.getId());
		return user;
	}

	public boolean login(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password).isPresent();
	}

	public Page<User> searchUsers(String name, String pinCode, LocalDate from, LocalDate to, Pageable pageable) {
		return userRepository.search(name, pinCode, from, to, pageable);
	}
}

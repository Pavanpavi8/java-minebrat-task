package controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class Permutation {
	@GetMapping("/permutations")
	public ResponseEntity<Set<String>> getPermutations(@RequestParam String input) {
		Set<String> result = new HashSet<String>();
		permute("", input, result);
		return ResponseEntity.ok(result);
	}

	private void permute(String prefix, String remaining, Set<String> result) {
		if (remaining.isEmpty()) {
			result.add(prefix);
			return;
		}
		for (int i = 0; i < remaining.length(); i++) {
			permute(prefix + remaining.charAt(i), remaining.substring(0, i) + remaining.substring(i + 1), result);
		}
	}
}

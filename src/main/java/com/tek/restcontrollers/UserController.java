package com.tek.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tek.configuration.TokenUtil;
import com.tek.models.Login;
import com.tek.models.Register;
import com.tek.models.ResponseModel;
import com.tek.services.CustomUserDetailsService;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenUtil tokenUtil;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@GetMapping("/register")
	public String registerEmployee() {
		return "Employee Registered";
	}

	@PostMapping("/userregister")
	public ResponseEntity<?> registerUser(@RequestBody Register userObj) {
		System.out.println(">>>>>>>>>> method - UserController registerUser() >>>>>> " + userObj.getEmail());
		Register savedUserObj = customUserDetailsService.saveUser(userObj);
		return ResponseEntity.ok(savedUserObj);
	}

	@PostMapping("/userauthenticate")
	public ResponseEntity<?> userAuthenticate(@RequestBody Login login) throws Exception {
		System.out.println(">>>>>>>>>>>> method - UserController userAuthenticate() >>>>>>>>>>>>");
		authenticate(login.getUserName(), login.getPassword());

		final UserDetails userDetails = customUserDetailsService.loadUserByUsername(login.getUserName());
		final String token = tokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new ResponseModel("ok", "success", userDetails.getUsername(), token));
	}
	
	private void authenticate(String username, String password) throws Exception {
		System.out.println(">>>>>>>>>> method - UserController authenticate()  >>>>>>>>>>>>");
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}

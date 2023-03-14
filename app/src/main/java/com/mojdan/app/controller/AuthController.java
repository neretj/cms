package com.mojdan.app.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mojdan.app.jwt.security.JwtUtils;
import com.mojdan.app.model.address.Address;
import com.mojdan.app.model.address.AddressRepository;
import com.mojdan.app.model.shop.Shop;
import com.mojdan.app.model.shop.ShopRepository;
import com.mojdan.app.model.user.Customer;
import com.mojdan.app.model.user.CustomerRepository;
import com.mojdan.app.model.user.ERole;
import com.mojdan.app.model.user.Role;
import com.mojdan.app.model.user.RoleRepository;
import com.mojdan.app.model.user.Seller;
import com.mojdan.app.model.user.SellerRepository;
import com.mojdan.app.model.user.User;
import com.mojdan.app.model.user.UserRepository;
import com.mojdan.app.payload.request.LoginRequest;
import com.mojdan.app.payload.request.SignupCustomerRequest;
import com.mojdan.app.payload.request.SignupSellerRequest;
import com.mojdan.app.payload.response.JwtResponse;
import com.mojdan.app.payload.response.JwtSellerResponse;
import com.mojdan.app.payload.response.MessageResponse;
import com.mojdan.app.service.user.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	SellerRepository sellerRepository;

	@Autowired
	ShopRepository storeRpository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/admin/signin")
	public ResponseEntity<?> authenticateSeller(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		Optional<Seller> seller = sellerRepository.findSellerByUserId(userDetails.getId());

		JwtSellerResponse response = new JwtSellerResponse(jwt, userDetails.getId(), userDetails.getUsername(),
				userDetails.getEmail(), roles, seller.get().getFirstName(), seller.get().getLastName(),
				seller.get().getShop(), seller.get().getPrimaryAddress());

		return ResponseEntity.ok(response);
	}

	@PostMapping("/admin/signup")
	public ResponseEntity<?> registerSeller(@Valid @RequestBody SignupSellerRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);

		try {
			userRepository.save(user);

			Address primaryAddress = new Address(signUpRequest.getAddressLine(), signUpRequest.getCity(),
					signUpRequest.getCountry(), signUpRequest.getPostalCode());
			addressRepository.save(primaryAddress);

			Shop shop = new Shop(signUpRequest.getStoreName(), signUpRequest.getStoreAddress());
			storeRpository.save(shop);

			Seller seller = new Seller(user, signUpRequest.getFirstName(), signUpRequest.getLastName(), primaryAddress,
					shop);
			sellerRepository.save(seller);

		} catch (Exception e) {

		}

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@PostMapping("/user/signin")
	public ResponseEntity<?> authenticateCustomer(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}

	@PostMapping("/user/signup")
	public ResponseEntity<?> registerCustomer(@Valid @RequestBody SignupCustomerRequest signUpRequest) {

		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		// All customers has User role by default
		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findByName(ERole.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(userRole);
		user.setRoles(roles);

		try {
			userRepository.save(user);

			Address primaryAddress = new Address(signUpRequest.getAddressLine(), signUpRequest.getCity(),
					signUpRequest.getCountry(), signUpRequest.getPostalCode());
			addressRepository.save(primaryAddress);

			Customer customer = new Customer(user, signUpRequest.getFirstName(), signUpRequest.getLastName(),
					primaryAddress);
			customerRepository.save(customer);

		} catch (Exception e) {

		}

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
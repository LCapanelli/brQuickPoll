package com.brquickpoll.security;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails; 
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component; 
import com.brquickpoll.domain.User;
import com.brquickpoll.repository.UserRepository;

@Component
public class BrQuickPollUserDetailsService implements UserDetailsService {

	@Inject
	private UserRepository userRepository; 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username);
		
		if (user == null) {
			throw new UsernameNotFoundException(String.format("User with the username %s does not exist!"
					, username));
		}
		
		//Creating a granted auth based on users's role
		List<GrantedAuthority> authorities = new ArrayList<>();
		if (user.isAdmin()) {
			authorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN");
		}
		
		//Creating user details obj from the data
		UserDetails userDetails = new org.springframework.security.core.userdetails
				.User(user.getUsername(), user.getPassword(), authorities);
		
		return userDetails; 
	}
}

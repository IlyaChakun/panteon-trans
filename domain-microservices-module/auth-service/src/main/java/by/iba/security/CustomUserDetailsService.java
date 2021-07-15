package by.iba.security;

import by.iba.security.config.UserPrincipal;
import by.iba.domain.User;
import by.iba.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {


		

		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(email));

		return UserPrincipal.create(user);
	}
}

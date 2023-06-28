package KeyboardShop.Keytopia.auth.security;

import KeyboardShop.Keytopia.auth.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
	private final IUserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
		final var user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException(email);
		}
		return UserDetailsImpl.build(user);
	}

}

package com.cd.book.config.security;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cd.book.entity.User;;

@Service
public class CollegeDaysUserDetailsService  implements UserDetailsService {

	// @Autowired
	// private UserRepository userRepository;

	 @Override
	 public User loadUserByUsername(String email) throws UsernameNotFoundException {
		 if (org.apache.commons.lang3.StringUtils.isEmpty(email)) {
		    return null;
		 }
		 //System.out.println("userRepository.findByEmailId(email) "+userRepository.findByEmailId(email));
		 return null;//userRepository.findOne(1);
	 }

}

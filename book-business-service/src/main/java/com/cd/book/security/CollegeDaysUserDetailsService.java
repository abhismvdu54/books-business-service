package com.cd.book.security;
import static com.cd.book.util.CommonUtil.isEmpty;

/**
 * @author Mayank
 * 
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cd.book.dao.UserRepository;
import com.cd.book.entity.User;;

@Service
public class CollegeDaysUserDetailsService  implements UserDetailsService {

	 @Autowired
	 private UserRepository userRepository;

	 @Override
	 public User loadUserByUsername(String email) throws UsernameNotFoundException {
		 if (isEmpty(email)) {
		    return null;
		 }
		 System.out.println("userRepository.findByEmail(email) "+userRepository.findByEmail(email));
		 return userRepository.findByEmail(email);
	 }

}

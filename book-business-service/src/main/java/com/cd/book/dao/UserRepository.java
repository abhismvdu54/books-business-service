/**
 * 
 */
package com.cd.book.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cd.book.entity.User;

/**
 * @author Mayank
 * 
 */
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByEmail(@Param("email") String email);
}

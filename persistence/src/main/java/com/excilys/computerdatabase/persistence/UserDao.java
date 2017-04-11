package com.excilys.computerdatabase.persistence;

import org.springframework.data.repository.CrudRepository;

import com.excilys.computerdatabase.entity.User;

/**
 * @author Guillon Julien
 *
 * 10 avr. 2017
 */
public interface UserDao extends CrudRepository<User, Long>{

	User findByUsername(String username);

}
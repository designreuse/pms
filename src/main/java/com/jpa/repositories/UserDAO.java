/* Copyright (C) 2013 , Inc. All rights reserved */
package com.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jpa.entities.User;

public interface UserDAO extends JpaRepository<User, Long> {

	public User findByEmailId(String username);

	@Query("from User u where u.company.companyId=:companyId")
	public List<User> findUserListByCompany(@Param(value="companyId") Long companyId);

	@Query("from User u where u.emailId = :email")
	public User findUserByEmail(@Param(value="email") String email);
	
	@Query(value="select count(*) from pm_users where email_id =:email",nativeQuery=true)
	public int checkUserAvailibity(@Param(value="email") String email);
}

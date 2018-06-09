package com.cjk.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cjk.domain.Account;

public interface UserDao extends JpaRepository<Account, Integer>{

	//通过用户名查询
	public List<Account> findByUserName(String userName);

}

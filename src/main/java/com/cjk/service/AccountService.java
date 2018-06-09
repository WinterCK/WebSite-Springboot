package com.cjk.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjk.dao.UserDao;
import com.cjk.domain.Account;

@Service
public class AccountService {

	@Autowired
	private UserDao userDao;
	
	@Transactional                   //事务注解，如果操作一条数据失败，则其他数据也不插入
	public void insertTwo() {
		Account acc1 = new Account();
		acc1.setUserName("asd3");
		acc1.setMoney("4000");
		userDao.save(acc1);
		
		Account acc2 = new Account();
		acc2.setUserName("qwe4");
		acc2.setMoney("2000");
		userDao.save(acc2);
		
	}
	public Account findOne(Integer id) {
		return userDao.findOne(id);
	}
}

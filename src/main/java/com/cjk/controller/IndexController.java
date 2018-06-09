package com.cjk.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.cjk.dao.UserDao;
import com.cjk.domain.Account;
import com.cjk.domain.Result;
import com.cjk.service.AccountService;

@RestController
@RequestMapping("/index")
public class IndexController {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AccountService accService;
	
//	@RequestMapping(value = "index", method = RequestMethod.GET)
//	public ModelAndView index1(){
//		ModelAndView modelAndView = new ModelAndView("/index/t");
//		return modelAndView;
//	}
	
	@RequestMapping("/t")
    public ModelAndView index(@SessionAttribute(WebSecurityConfig.SESSION_KEY) String account, Model model){
		model.addAttribute("name", account);
		
		List<Account> userList =new ArrayList<Account>();
        Account bean =new Account("官方参考文档","Spring Boot Reference Guide", 1);
        userList.add(bean);
        bean =new Account("官方SpriongBoot例子","官方SpriongBoot例子",2);
        userList.add(bean);
        bean =new Account("BootStrap中文网","BootStrap全局CSS样式",3);
        userList.add(bean);
        bean =new Account("嘟嘟MD独立博客","Spring Boot干货系列 ",4);
        userList.add(bean);
        bean =new Account("后端编程嘟","Spring Boot教程和视频 ",5);
        userList.add(bean);
        bean =new Account("程序猿DD","Spring Boot系列",6);
        userList.add(bean);
        bean =new Account("纯洁的微笑","Sping Boot系列文章",7);
        userList.add(bean);
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("userList", userList);
        return modelAndView;
    }
	
	
	/**
	 * 查询所有数据
	 * @return
	 */
	@RequestMapping(value = "/index1", method = RequestMethod.GET)
	public List<Account> accList() {
		return userDao.findAll();
	}
	
	/**
	 * 添加一个子用户
	 * @param userName
	 * @param money
	 * @return
	 */
	@RequestMapping(value = "/index",method = RequestMethod.POST)
	public Result<Account> addAcc(@Valid Account account, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			Result result = new Result();
			result.setCode(1);
			result.setMsg(bindingResult.getFieldError().getDefaultMessage());
			result.setData(null);
			return result;
		}
		
		account.setMoney(account.getMoney());
		account.setUserName(account.getUserName());
		
		userDao.save(account);
		return null;
	}
	
	
	@RequestMapping(value = "/index/{id}",method = RequestMethod.GET)
	public Account findAcc(@PathVariable("id") Integer id) {
		return userDao.findOne(id);
	}
	
	/**
	 * 更新一条数据
	 * @param id
	 * @param userName
	 * @param money
	 */
	@RequestMapping(value = "/index/{id}", method = RequestMethod.PUT)
	public Account accUpdate(@PathVariable("id") Integer id,
			@RequestParam(value ="userName", required = false) String userName,
			@RequestParam(value ="money",required = false) String money) {
		Account account = new Account();
		account.setId(id);
		account.setUserName(userName);
		account.setMoney(money);
		
		return userDao.save(account);
	}
	
	/**
	 * 删除一数据
	 * @param id
	 */
	@RequestMapping(value = "/index/{id}",method = RequestMethod.DELETE)
	public void accDelete(@PathVariable("id") Integer id) {
		userDao.delete(id);
	}
	
	/**
	 * 插入两条数据
	 */
	@RequestMapping(value = "/index/two",method = RequestMethod.POST)
	public void accTwo() {
		accService.insertTwo();
	}
}

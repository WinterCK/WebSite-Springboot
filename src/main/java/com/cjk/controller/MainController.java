package com.cjk.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class MainController {

	@GetMapping("/index")
	public String index() {
//			@SessionAttribute(WebSecurityConfig.SESSION_KEY) String account, Model model) {
//		model.addAttribute("name", account);
		return "redirect:/index/t";
	}

	
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/loginPost")
	public @ResponseBody Map<String, Object> loginPost(String account, String password, HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		if (!"123456".equals(password) || "".equals(account)) {
			map.put("success", false);
			map.put("message", "密码错误或用户名为空");
			return map;
		}

		// 设置session
		session.setAttribute(WebSecurityConfig.SESSION_KEY, account);

		map.put("success", true);
		map.put("message", "登录成功");
		return map;
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// 移除session
		session.removeAttribute(WebSecurityConfig.SESSION_KEY);
		return "redirect:/login";
	}

}

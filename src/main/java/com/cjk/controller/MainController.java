package com.cjk.controller;

import com.alibaba.fastjson.JSON;
import com.cjk.common.exception.AesException;
import com.cjk.common.utils.WxUtils;
import com.cjk.domain.TestVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class MainController {

	@GetMapping("/testWechat")
	public String testWechat(HttpServletRequest request, HttpServletResponse resp) throws AesException {
		String msgSignature = request.getParameter("signature");
		String msgTimestamp = request.getParameter("timestamp");
		String msgNonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		if (WxUtils.verifyUrl(msgSignature, msgTimestamp, msgNonce)) {
			return echostr;
		}
		return null;
	}

	@GetMapping("/index")
	@ResponseBody
	public String index() {
		return JSON.toJSONString(new TestVO("chk", "male"));
	}


}

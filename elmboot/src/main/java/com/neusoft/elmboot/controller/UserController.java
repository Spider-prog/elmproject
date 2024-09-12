package com.neusoft.elmboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.elmboot.po.User;
import com.neusoft.elmboot.service.UserService;
import com.neusoft.elmboot.util.TokenUtil;

import static com.neusoft.elmboot.util.MD5forDBUtil.formPassToDBPass;
import static com.neusoft.elmboot.util.RamUtil.getRandomNumber;

@RestController
@RequestMapping("/userController")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	TokenUtil tokenUtil;

	//先用userId获取盐值，在进行单向加密后对比数据库
	@RequestMapping("/getUserByIdByPass")
	public HttpEntity<User> getUserByIdByPass(User user) throws Exception{
		HttpHeaders headers = new HttpHeaders( );
		headers.set( "Authorization", tokenUtil.getToken(user.getUserId()));
		String salt = userService.getUserSaltById(user.getUserId());

		user.setSalt(salt);
		user.setPassword(formPassToDBPass(user.getPassword(),user.getSalt()));

		HttpEntity<User> entity = new HttpEntity<>(userService.getUserByIdByPass(user),headers);
		return entity;
	}
	
	@RequestMapping("/getUserById")
	public int getUserById(User user) throws Exception{
		return userService.getUserById(user.getUserId());
	}
	
	@RequestMapping("/saveUser")
	public HttpEntity saveUser(User user) throws Exception{
		HttpHeaders headers = new HttpHeaders( );
		headers.set( "Authorization", tokenUtil.getToken(user.getUserId()));

		String salt = getRandomNumber(8);
		user.setSalt(salt);
		user.setPassword(formPassToDBPass(user.getPassword(),user.getSalt()));

		HttpEntity<Integer> entity = new HttpEntity<>(userService.saveUser(user),headers);
		return entity;
	}
}

package com.neusoft.elmboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.elmboot.po.User;
import com.neusoft.elmboot.service.UserService;

import static com.neusoft.elmboot.util.MD5Utils.formPassToDBPass;
import static com.neusoft.elmboot.util.RamUtil.getRandomNumber;

@RestController
@RequestMapping("/saveUser")
public class UserController {
	
	@Autowired
	private UserService userService;

	//先用userId获取盐值，在进行单向加密后对比数据库
	@RequestMapping("/getUserByIdByPass")
	public User getUserByIdByPass(User user) throws Exception{
		String salt = userService.getUserSaltById(user.getUserId());
		user.setSalt(salt);
		user.setPassword(formPassToDBPass(user.getPassword(),user.getSalt()));
		return userService.getUserByIdByPass(user);
	}
	
	@RequestMapping("/getUserById")
	public int getUserById(User user) throws Exception{
		return userService.getUserById(user.getUserId());
	}
	
	@RequestMapping("/saveUser")
	public int saveUser(User user) throws Exception{
		String salt = getRandomNumber();
		user.setSalt(salt);
		user.setPassword(formPassToDBPass(user.getPassword(),user.getSalt()));
		return userService.saveUser(user);
	}
}

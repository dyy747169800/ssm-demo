package com.uban.controller;

import com.uban.entity.User;
import com.uban.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ShowController {


	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/show")
	public String show(){
		return "success";
	}

	@ResponseBody
	@RequestMapping("/showUsers")
	public List<User> showUsers(){
		List<User> users = userService.selectUsers();
		return users;
	}

}

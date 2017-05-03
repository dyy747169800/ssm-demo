package com.uban.controller;

import com.uban.common.CommonInterface;
import com.uban.entity.User;
import com.uban.service.UserService;
import com.uban.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class UserController {


	@Autowired
	private UserService userService;
	@Autowired
	private HttpServletRequest request;

	@ResponseBody
	@RequestMapping("/show")
	public String show(){
		return "success";
	}

	@RequestMapping("/showUsers")
	public ModelAndView showUsers(ModelAndView model){
		model.setViewName("user/user_list");
		model.addObject(userService.selectUsers());
		return model;
	}

	@ResponseBody
	@RequestMapping("/getUsers")
	public List<User> getUsers(ModelAndView model){
		return userService.selectUsers();
	}


	@ResponseBody
	@RequestMapping("/selectByPrimaryKey")
	public User selectByPrimaryKey(@RequestParam("id")Integer id){
		return userService.selectUserById(id);
	}

	@ResponseBody
	@RequestMapping(value = "/userLogin",method = RequestMethod.POST)
	public User userLogin(User user,HttpServletResponse response){
		if(!StringUtils.isEmpty(user.getPassword()) && !StringUtils.isEmpty(user.getUsername())){
			CookieUtil.setCookie(CommonInterface.LOGIN_COOKIE_NAME,user.getUsername(),1,response);
			return user;
		}else {
			return null;
		}

	}
}

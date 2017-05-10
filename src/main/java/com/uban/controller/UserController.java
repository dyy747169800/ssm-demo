package com.uban.controller;

import com.uban.common.CommonInterface;
import com.uban.entity.User;
import com.uban.service.UserService;
import com.uban.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {


	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping("/show")
	public String show(){
		return "success";
	}

	@RequestMapping("/showUsers")
	public ModelAndView showUsers(ModelAndView model){
		model.setViewName("user/user_list_angular");
		model.addObject("userList",userService.selectUsers());
		return model;
	}

	@RequestMapping("/toAddUserPage")
	public ModelAndView toAddUserPage(ModelAndView model){
		model.setViewName("user/user_add");
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

    @ResponseBody
    @RequestMapping(value = "/addOrEditUser",method = RequestMethod.POST)
	public String addOrEditUser(@RequestBody User user){
       if(user != null){
           if(user.getId() != null){
               //edit
               user.setCreateDate(new Date().getTime());
               userService.editUserById(user);
           }else {
               //add
               user.setCreateDate(new Date().getTime());
               userService.saveUser(user);
           }
       }
       return "{\"status\":\"success\"}";
    }

    @ResponseBody
    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    public String deleteUser(@RequestBody User user){
     if(null != user && user.getId() != null){
         userService.deleteUserById(user.getId());
     }
        return "{\"status\":\"success\"}";
    }
}

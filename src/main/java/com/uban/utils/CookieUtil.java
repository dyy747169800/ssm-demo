package com.uban.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	
	public static Cookie getCookie(HttpServletRequest request, String cookieName) {
		Cookie[] cookies =  request.getCookies();
		if(cookies != null && cookies.length>0){
			for (Cookie cookie : cookies) {
				if(cookieName.equals(cookie.getName())){
					return cookie;
				}
			}
		}
		return null;
	}
	
	/**
	 * 保存COOKIE
	 * @version 2016年1月28日  下午8:12:38
	 * @param cookieName
	 * @param cookieValue
	 * @param hour
	 * @param response
	 */
	public static void setCookie(String cookieName,String cookieValue, int hour, HttpServletResponse response){
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setDomain("dyy.com");
		cookie.setPath("/");
		cookie.setMaxAge(hour*3600);//设置cookie的有效时间
		response.addCookie(cookie);
	}

}

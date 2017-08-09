package com.uban.intercepter;

import com.alibaba.fastjson.JSON;
import com.uban.common.CommonInterface;
import com.uban.entity.User;
import com.uban.redis.RedisCacheClient;
import com.uban.utils.CookieUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by DuanYangYu on 2017/5/3 0003.
 */
public class LoginIntercepter extends HandlerInterceptorAdapter {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private RedisCacheClient redisCacheClient;

     /**
     * 在业务处理器处理请求之前被调用
     * 如果返回false
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     * 如果返回true
     *    执行下一个拦截器,直到所有的拦截器都执行完毕
     *    再执行被拦截的Controller
     *    然后进入拦截器链,
     *    从最后一个拦截器往回执行所有的postHandle()
     *    接着再从最后一个拦截器往回执行所有的afterCompletion()
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("====================执行顺序 1.preHandle ===============");
        Cookie cookie = CookieUtil.getCookie(request,CommonInterface.LOGIN_TOKEN);
        if(cookie != null){
            String user = redisCacheClient.get(cookie.getValue());
            if(user != null){
                CookieUtil.setCookie(CommonInterface.LOGIN_USER_NAME, JSON.parseObject(user, User.class).getUsername(),2,response);
                redisCacheClient.setExpire(cookie.getValue(),600l);
            }else {
                request.setAttribute("requestUrl",request.getRequestURI());
                request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request,response);
                return false;
            }
            return super.preHandle(request, response, handler);
        }else {
             request.setAttribute("requestUrl",request.getRequestURI());
             request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request,response);
             return false;
        }
    }


    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
     * 可在modelAndView中加入数据，比如当前时间
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("====================执行顺序 2.preHandle ===============");
        super.postHandle(request, response, handler, modelAndView);
    }


    /**
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
     *
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("====================执行顺序 3.afterCompletion ===============");
        super.afterCompletion(request, response, handler, ex);
    }
}

package com.huaxianvwa.school.controller;
/**
 * @author zsj
 * @date 2020/3
 */
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import com.huaxianvwa.school.entity.User;
import com.huaxianvwa.school.result.Result;
import com.huaxianvwa.school.result.ResultFactory;
import com.huaxianvwa.school.service.UserService;

@Controller
@RestController
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping("/api/login")
    public Result login(@RequestBody User requestUser,HttpSession session) {
    	
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);

        Subject subject = SecurityUtils.getSubject();
//        subject.getSession().setTimeout(10000);
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, requestUser.getPassword());
        usernamePasswordToken.setRememberMe(true);
        try {
            User user = userService.findByUsername(username);
            if (!user.isEnabled()) {
                return ResultFactory.buildFailResult("该用户已被禁用");
            }
            subject.login(usernamePasswordToken);
            session.setAttribute("name", username);
            User u = userService.findByUsername(username);
            session.setAttribute("id", u.getId());
            return ResultFactory.buildSuccessResult(usernamePasswordToken);

        } catch (IncorrectCredentialsException e) {
            return ResultFactory.buildFailResult("密码错误");
        } catch (UnknownAccountException e) {
            return ResultFactory.buildFailResult("账号不存在");
        } catch (NullPointerException e) {
        	return ResultFactory.buildFailResult("账号不存在");
    	}
    }

    @PostMapping("/api/register")
    public Result register(@RequestBody User user) {
        int status = userService.register(user);
        switch (status) {
            case 0:
                return ResultFactory.buildFailResult("用户名和密码不能为空");
            case 1:
                return ResultFactory.buildSuccessResult("注册成功");
            case 2:
                return ResultFactory.buildFailResult("用户已存在");
        }
        return ResultFactory.buildFailResult("未知错误");
    }

    @GetMapping("/api/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResultFactory.buildSuccessResult("成功登出");
    }

    @GetMapping("/api/authentication")
    public String authentication() {
        return "身份认证成功";
    }
}


package com.fengyangcai.shiro.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

	
	@RequestMapping("/login")
	public String login(HttpServletRequest request,HttpServletResponse response,@RequestParam("name")String name,@RequestParam("password")String password) {
		
		//获取subject
		Subject subject = SecurityUtils.getSubject();
		
		ModelAndView view = new ModelAndView("msg");
		
		//获取封装的用户数据
		System.out.println("前端传来的用户名和密码为："+name+":"+password);
		AuthenticationToken token = new UsernamePasswordToken(name, password);
		
		//2.执行登录操作
		
		try {
			subject.login(token);
			//保存用户信息到会话，用于在页面展示，而不用于认证操作
			//request.getSession().setAttribute("user", name+","+password);
			
			//一、基于资源授权
			//System.out.println("当前角色是否拥有用户增加权限："+subject.isPermitted("userAdd"));
			//System.out.println("当前角色是否拥有用户增加和修改权限："+Arrays.toString(subject.isPermitted("userAdd","userEdit")));
			System.out.println("当前角色是否拥有编辑增加权限："+subject.isPermitted("edit:add"));
			System.out.println("当前角色是否拥有测试更新和编辑的添加权限："+Arrays.toString(subject.isPermitted("test:update","edit:add")));
			//通用授权符
			//System.out.println("当前角色是否拥有用户增加和修改权限："+ Arrays.toString( subject.isPermitted("user:add","user:edit")  ));

			//二、基于角色授权
			//System.out.println("当前用户是否是超级管理员："+subject.hasRole("admin"));

			
			
			return "redirect:/index.jsp";
			
		} catch (UnknownAccountException e) {
			
			//e.printStackTrace();
			request.setAttribute("msg", "用户名不存在");
			//view.addObject("用户名不存在");
			
			System.out.println("用户名不存在-------------");
			return "error";
			
		}catch (IncorrectCredentialsException e) {
			// TODO: handle exception
			request.setAttribute("msg", "密码错误");
			return "error";
		}catch (Exception e) {
			view.addObject(e.getMessage());
			return  "error";
		}
		

	}

}

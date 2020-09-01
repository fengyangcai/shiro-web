package com.fengyangcai.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;


public class LoginRealm extends AuthorizingRealm {

	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		//授权
		System.out.println("执行授权操作 :");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		/*这里可以访问数据库的资源类。获取资源类的资源码如资源码"user:edit,edit:*等"*/
		//info.addStringPermission("user:*");
		info.addStringPermission("user:edit");
		info.addStringPermission("edit:add");
		info.addStringPermission("test:update");
		info.addStringPermission("btn:*");
		info.addStringPermission("user_add_btn");
		//这里不添加user_add_btn1这个授权码导致在页面上使用标签不会显示
		//info.addStringPermission("user_add_btn1");

		return info;
	}

	//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
	
		System.out.println("执行认证操作");
		
		//模拟数据数据库
		String name="root";
		String password="123";
		//判断用户名称是否正确
		UsernamePasswordToken usernamePasswordToken =(UsernamePasswordToken) token;
		if (!usernamePasswordToken.getUsername().equals(name)) {
			//用户名不存在
			return  null;
		}
		
		//判断密码
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, password, name);
		return simpleAuthenticationInfo;
		

	
	}

}

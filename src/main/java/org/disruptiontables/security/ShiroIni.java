package org.disruptiontables.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.util.Factory;
import org.codehaus.jackson.map.DeserializerFactory.Config;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.IniSecurityManagerFactory;

public class ShiroIni {

	//programatic instance of Shiro Ini

	Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
    SecurityManager securityManager = factory.getInstance();
   // SecurityUtils.setSecurityManager(securityManager);
    
    UsernamePasswordToken token = new  UsernamePasswordToken("username", "password");
    
    
	
	
}

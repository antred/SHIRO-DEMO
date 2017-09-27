package src;



import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

public class Test1 {
	
	@Test
	public void testHelloWorld() {
		
		// 1.使用Shiro.ini文件初始化SecurityManagerg工厂
		Factory<SecurityManager>factoty = new IniSecurityManagerFactory("classpath:shiro.ini");
		
		// 2.获取SecurityManager实例并绑定到SecurityUtils
		SecurityManager securityManager = factoty.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		
		// 3.获取Subject对象并创建用户名密码Token
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
		
		try {
			// 4.用户登录
			subject.login(token);
		} catch (Exception e) {
			// 5.身份验证失败
			//e.printStackTrace();
		}
		
		// 断言用户是否登陆
		Assert.assertEquals(true, subject.isAuthenticated());
		
		// 6.用户推出
		subject.logout();
		
	}

}

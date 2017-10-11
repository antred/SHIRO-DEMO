package src;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class AuthenticatorTest {
	
	/**
	 * 测试AllSuccessStrategyWithSuccess即所有认证通过
	 */
	@Test
	public void testAllSuccessStrategyWithSuccess() {
		
		// 加载AllSuccessfulStrategy的INI配置文件进行登陆测试
		login("classpath:shiro-authenticator-all-success.ini");
		Subject subject = SecurityUtils.getSubject();
		
		// 得到一个身份集合,其中包含了Realm验证成功的身份信息
		PrincipalCollection principal =  subject.getPrincipals();
		Assert.assertEquals(2, principal.asList().size());
		System.out.println(principal);
	}
	
	/**
	 * 测试AllSuccessStrategyWithFail即认证失败
	 */
	@Test(expected = UnknownAccountException.class)
	public void testAllSuccessStrategyWithFail() {
		
		// 加载AllSuccessfulStrategy的INI配置文件进行登陆测试
		login("classpath:shiro-authenticator-all-failed.ini");
		Subject subject = SecurityUtils.getSubject();
		
		// 得到一个身份集合,其中包含了Realm验证成功的身份信息
		PrincipalCollection principal =  subject.getPrincipals();
		Assert.assertEquals(2, principal.asList().size());
		System.out.println(principal);		
		
	}

	/**
	 * 测试用登陆方法
	 * 
	 * @param configFile
	 */
	private void login(String configFile) {

		// 1.使用配置文件初始化SecurityManager工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFile);

		// 2.获取securityManager实例绑定到securityutil
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		// 3.获取subject主体并创建usernamepasswordtoken对象
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

		// 4.使用subject和token登陆
		subject.login(token);

	}
	
	/**
	 * 每次测试完成解除SecurityUtils的subject绑定
	 */
	@After
	public void teardown() {
		ThreadContext.unbindSubject();
	}

}

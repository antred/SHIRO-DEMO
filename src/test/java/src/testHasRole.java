package src;

import org.junit.Assert;
import org.junit.Test;

/**
 * shiro权限管理测试	
 * @author Xulf
 *
 */
public class testHasRole extends BaseTest{
	
	/**
	 * 检查用户是否有对应的角色
	 */
	@Test
	public void test1() {
		
		// 使用shiro-role配置文件测试登陆		
		login("classpath:shiro-role.ini","zhang","123");
		
		// 判断用户是否拥有角色
		Assert.assertTrue(subject().hasRole("role1"));
		Assert.assertTrue(subject().hasRole("role2"));
	}
	
	/**
	 * 检查用户是否有对应的权限
	 */
	@Test
	public void test2() {
		
		// 使用shiro-role配置文件测试登陆		
		login("classpath:shiro-role.ini","zhang","123");
		
		// 判断用户是否拥有权限
		subject().checkPermission("user:save");	
		
	}

}

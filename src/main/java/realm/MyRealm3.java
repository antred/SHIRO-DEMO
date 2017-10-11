package realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

public class MyRealm3 implements Realm {

	/**
	 * 获取Realm名称
	 */
	public String getName() {
		return "MyRealm3";
	}

	/**
	 * 判定是否支持指定的token
	 */
	public boolean supports(AuthenticationToken token) {
		// 仅支持UsernamePasswordToken类型
		return token instanceof UsernamePasswordToken;
	}

	/**
	 * 具体的身份认证实现
	 */
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 从token中获取用户名
		String username = (String) token.getPrincipal();
		// 从token中获取密码
		String password = new String((char[]) token.getCredentials());
		// 如果用户名认证错误
		if (!"zhang".equals(username)) {
			throw new UnknownAccountException();
		}
		// 如果密码认证失败
		if (!"123".equals(password)) {
			throw new IncorrectCredentialsException();
		}

		// 如果身份认证成功,返回一个新的AuthenticationInfo实例
		return new SimpleAuthenticationInfo(username+"@163.com", password, getName());
	}

}

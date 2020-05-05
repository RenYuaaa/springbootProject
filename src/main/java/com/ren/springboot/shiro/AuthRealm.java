package com.ren.springboot.shiro;

import com.ren.springboot.common.AuthConstant;
import com.ren.springboot.entity.vo.UserVo;
import com.ren.springboot.service.UserService;
import com.ren.springboot.utils.JWTUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * 自定义安全数据Realm
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 重写，绕过身份令牌异常导致的shiro报错
     *
     * @param authenticationToken
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof JWTToken;
    }

    /**
     * 执行授权逻辑
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("用户角色权限认证");


        //添加角色和权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        //获取用户登录信息
        String username = JWTUtil.getUsername(principals.toString());
        List<UserVo> users = userService.login(username);
        for (UserVo userVo : users) {
            authorizationInfo.addRole(userVo.getRoleName());
            authorizationInfo.addStringPermission(userVo.getPermissionName());
        }
        return authorizationInfo;

    }

    /**
     * 执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        //获得token
        String token = (String) authenticationToken.getCredentials();
        //获得token中的用户信息
        String userName = JWTUtil.getUsername(token);
        //判空
        if (StringUtils.isBlank(userName)) {
            throw new AuthenticationException(AuthConstant.TOKEN_BLANK);
        }
        try {
            //查询用户是否存在
            List<UserVo> userVos = userService.login(userName);
            if (userVos.size() <= 0) {
                throw new AuthenticationException(AuthConstant.TOKEN_INVALID);
                //token过期
            } else if (!(JWTUtil.verifyToken(token, userName, userVos.get(0).getPassword()))) {
                throw new AuthenticationException(AuthConstant.TOKEN_EXPIRE);
            }
        } catch (Exception e) {
            throw e;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                token, token, "auth_realm");
        return authenticationInfo;
    }
}

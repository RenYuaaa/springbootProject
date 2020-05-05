package com.ren.springboot.shiro;

import com.ren.springboot.common.CommonStatus;
import com.ren.springboot.utils.JWTUtil;
import com.ren.springboot.utils.JsonConvertUtil;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 实现自定义的认证拦截器,接收传过来的token,实现前后端分离的权限认证
 */
public class AuthFilter extends BasicHttpAuthenticationFilter {

    /**
     * 判断用户是否想要登入。
     * 检测header里面是否包含Authorization字段即可
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("token");
        return authorization != null;
    }

    /**
     * 在这里拦截所有请求
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {

        if (isLoginAttempt(request, response)) {
            try {
                this.executeLogin(request, response);
            } catch (Exception e) {
                // 应用异常
                System.out.println(e.getMessage());
                return false;
            }
        } else {
            // cookie中未检查到token或token为空
            HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
            String httpMethod = httpServletRequest.getMethod();
            String requestURI = httpServletRequest.getRequestURI();
            System.out.println("请求的" + requestURI + "的token为空 请求类型为：" + httpMethod);
            return false;
        }

        return true;
    }

    /**
     * 请求失败拦截,请求终止，不进行转发直接返回客户端拦截结果
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception{
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        httpServletResponse.setContentType("application/json; charset=utf-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        String result = JsonConvertUtil.objectToJson(CommonStatus.UNKNOWN_ERROR.getText());
        httpServletResponse.getWriter().print(result);
        return false;
    }

    /**
     * 用户存在，执行登录认证
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        String token = JWTUtil.getRequestToken((HttpServletRequest)request);
        JWTToken jwtToken = new JWTToken(token);
        // 提交给AuthRealm进行登录认证
        getSubject(request, response).login(jwtToken);
        return true;
    }
}

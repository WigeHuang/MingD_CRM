package com.mindao.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindao.entity.TokenEntity;
import com.mindao.service.TokenService;
import com.mindao.service.UserService;
import com.mindao.utils.R;
import com.mindao.utils.annotation.IgnoreAuth;
import com.mindao.utils.validator.Assert;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * API登录授权
 *
 * @author ligc
 * @email 153277817@qq.com
 * @date 2017-03-23 15:31
 */
@RestController
@RequestMapping("/api")
@Api("登录登出接口")
public class ApiLoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    /**
     * 登录
     */
    @IgnoreAuth
    @PostMapping("login")
    @ApiOperation(value = "登录",notes = "登录")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "query", dataType="string", name = "account", value = "账号", required = true),
        @ApiImplicitParam(paramType = "query", dataType="string", name = "password", value = "密码", required = true)
    })
    public R login(String account, String password){
        Assert.isBlank(account, "账号不能为空");
        Assert.isBlank(password, "密码不能为空");

        //用户登录
        String userId = userService.login(account, password);

        //生成token
        Map<String, Object> map = tokenService.createToken(userId);

        return R.ok(map);
    }
    
    /**
     * 登录
     */
    @IgnoreAuth
    @PostMapping("logout")
    @ApiOperation(value = "登出",notes = "登出")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "header", dataType="string", name = "token", value = "访问凭证", required = true)
    })
    public R logout(HttpServletRequest request){
    	String token = request.getHeader("token");
        tokenService.deleteByToken(token);

        return R.ok();
    }

}

package com.mindao.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindao.service.UserService;
import com.mindao.utils.R;
import com.mindao.utils.annotation.IgnoreAuth;
import com.mindao.utils.validator.Assert;

/**
 * 注册
 * @author ligc
 * @email 153277817@qq.com
 * @date 2017-03-26 17:27
 */
@RestController
@RequestMapping("/api")
@Api("注册接口")
public class ApiRegisterController {
    @Autowired
    private UserService userService;

    /**
     * 注册
     */
    @IgnoreAuth
    @PostMapping("register")
    @ApiOperation(value = "注册")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType="string", name = "acocunt", value = "账号：手机号或邮箱", required = true),
            @ApiImplicitParam(paramType = "query", dataType="string", name = "password", value = "密码", required = true),
            @ApiImplicitParam(paramType = "query", dataType="string", name = "nickname", value = "昵称", required = true)
    })
    public R register(String acocunt, String password, String nickname){
        Assert.isBlank(acocunt, "账号不能为空");
        Assert.isBlank(password, "密码不能为空");
        Assert.isBlank(password, "昵称不能为空");
        userService.save(acocunt, password);

        return R.ok();
    }
}

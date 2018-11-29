package com.mindao.api;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindao.entity.SysMenuEntity;
import com.mindao.entity.TokenEntity;
import com.mindao.entity.UserEntity;
import com.mindao.service.SysMenuService;
import com.mindao.service.TokenService;
import com.mindao.service.UserService;
import com.mindao.utils.MD5Util;
import com.mindao.utils.R;
import com.mindao.utils.RRException;
import com.mindao.utils.annotation.IgnoreAuth;
import com.mindao.utils.annotation.LoginUser;
import com.mindao.utils.validator.Assert;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * API登录授权
 *
 * @author ligc
 * @email 153277817@qq.com
 * @date 2017-03-23 15:31
 */
@RestController
@RequestMapping("/api/user")
@Api("用户接口")
public class ApiUserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private SysMenuService sysMenuService;
    /**
     * 加载菜单
     */
    @PostMapping("menu")
    @ApiOperation(value = "菜单",notes = "加载菜单")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "header", dataType="string", name = "token", value = "访问凭证", required = true) 
    })
    public R menu(HttpServletRequest request,@LoginUser UserEntity user ){
//    	String token = request.getHeader("token");
//        Assert.isBlank(token, "访问凭证不能为空");
//   
//        TokenEntity tokenEntity  = tokenService.queryByToken(token);
//        boolean b=tokenService.isExpired(tokenEntity.getExpireTime());
//        if (b){
//        	throw new RRException("会话已过期，请重新登录");  
//        } 
        //用户登录
		//查询列表数据
		List<SysMenuEntity> menuList = new ArrayList<>();
		
		//添加顶级菜单
//		SysMenuEntity root = new SysMenuEntity();
//		root.setMenuId(0L);
//		root.setName("一级菜单");
//		root.setParentId(-1L);
//		root.setOpen(true);
//		menuList.add(root);
		
		SysMenuEntity fdMemu = sysMenuService.queryObject(41L);
		menuList.add(fdMemu);
		
        return R.ok().put("menuList", menuList);
    }
    /**
     * 加载菜单
     */
    @PostMapping("info")
    @ApiOperation(value = "用户信息",notes = "加载用户信息")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "header", dataType="string", name = "token", value = "访问凭证", required = true) 
    })
    public R info(HttpServletRequest request,@LoginUser UserEntity user  ){
//    	String token = request.getHeader("token");
//        Assert.isBlank(token, "访问凭证不能为空");
//        TokenEntity tokenEntity  = tokenService.queryByToken(token);
//        boolean b=tokenService.isExpired(tokenEntity.getExpireTime());
//        if (b){
//        	throw new RRException("会话已过期，请重新登录");  
//        } 
//        UserEntity user=userService.queryObject(tokenEntity.getUserId());
        
        return R.ok().put("user", user);
    }
    /**
     * 修改用户密码
     */
    @PostMapping("updatePassword")
    @ApiOperation(value = "修改用户密码",notes = "修改用户密码")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "header", dataType="string", name = "token", value = "访问凭证", required = true),
        @ApiImplicitParam(paramType = "query", dataType="string", name = "password", value = "旧密码", required = true),
        @ApiImplicitParam(paramType = "query", dataType="string", name = "newPassword", value = "新密码", required = true)
    })
    public R updatePassword(HttpServletRequest request,@LoginUser UserEntity user,String password ,String newPassword){
    	String token = request.getHeader("token");
        //Assert.isBlank(token, "访问凭证不能为空");
        Assert.isBlank(password, "旧密码不能为空");
        Assert.isBlank(password, "新密码不能为空");
        TokenEntity tokenEntity  = tokenService.queryByToken(token);
        boolean b=tokenService.isExpired(tokenEntity.getExpireTime());
        if (b){
        	throw new RRException("会话已过期，请重新登录");  
        } 
        
        //UserEntity user=userService.queryObject(tokenEntity.getUserId());
        //检查旧密码
        userService.login(StringUtils.isBlank(user.getEmail())?user.getMobile():user.getEmail(), password);
        //更新新密码
        user.setPassword(MD5Util.stringMD5(newPassword));
        userService.update(user);
        return R.ok();
    } 

}

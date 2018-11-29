package com.mindao.crm;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindao.config.CrmConfigInfo;
import com.mindao.entity.UserDeviceEntity;
import com.mindao.entity.UserEntity;
import com.mindao.service.UserDeviceService;
import com.mindao.service.UserService;
import com.mindao.utils.PageUtils;
import com.mindao.utils.Query;
import com.mindao.utils.R;


/**
 * 
 * 
 * @author liguocai
 * @email 153277817@qq.com
 * @date 2017-06-14 14:02:08
 */
@RestController
@RequestMapping("/crm/user")
public class UserController {
	@Autowired
	private CrmConfigInfo crmConfigInfo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDeviceService userDeviceService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("crm:user:list")
	public R list(@RequestParam Map<String, Object> params){
		//String baseDir=crmConfigInfo.getSaveAvatarDir();
		if (params.get("sidx")==null || ((String) params.get("sidx")).trim().length()==0 ){
			params.put("sidx", "register_time");
			params.put("order", "desc");
		}
		String baseDir=crmConfigInfo.getAuthServer()+"/userAvatar/download/";
		//查询列表数据
        Query query = new Query(params);

		List<UserEntity> userList = userService.queryList(query);
		File f=null;
		for (UserEntity userEntity : userList) {
			if (!StringUtils.isBlank(userEntity.getAvatar())){
				String avatarPath=baseDir +userEntity.getAvatar();
				userEntity.setAvatarPath(avatarPath);
			}else{
				//userEntity.setAvatarPath({auth_server}/userAvatar/download/);
			}
		}
		int total = userService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());
		
 
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{uid:.*}")
	@RequiresPermissions("crm:user:info")
	public R info(@PathVariable("uid") String uid){
		String baseDir=crmConfigInfo.getAuthServer()+"/userAvatar/download/";
		UserEntity user = userService.queryObject(uid);
		if (!StringUtils.isBlank(user.getAvatar())){
			String avatarPath=baseDir +user.getAvatar();
			user.setAvatarPath(avatarPath);
		}else{
			//userEntity.setAvatarPath({auth_server}/userAvatar/download/);
		}
		return R.ok().put("user", user);
	}
	
	/**
	 * 信息
	 */
	@RequestMapping("/detail/{uid:.*}")
	@RequiresPermissions("crm:user:info")
	public R detail(@PathVariable("uid") String uid){
		String baseDir=crmConfigInfo.getAuthServer()+"/userAvatar/download/";
		UserEntity user = userService.queryObject(uid);
		if (!StringUtils.isBlank(user.getAvatar())){
			String avatarPath=baseDir +user.getAvatar();
			user.setAvatarPath(avatarPath);
		}else{
			//userEntity.setAvatarPath({auth_server}/userAvatar/download/);
		}
		
		Map<String, Object> map =new HashMap<>();
		map.put("userId", uid);
		 List<UserDeviceEntity> devices=userDeviceService.queryByUserId(map);
		 user.setDevices(devices);
		return R.ok().put("user", user);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("crm:user:save")
	public R save(@RequestBody UserEntity user){
		userService.save(user);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("crm:user:update")
	public R update(@RequestBody UserEntity user){
		userService.update(user);
		
		return R.ok();
	}
	/**
	 * 修改
	 */
	@RequestMapping("/updateStatus")
	@RequiresPermissions("crm:user:update")
	//@ResponseBody
	//public R updateStatus(@RequestParam String uid,@RequestParam String status){
	public R updateStatus(UserEntity user){		
		//UserEntity user=userService.queryObject(uid);
		//user.setStatus(status);
		userService.update(user);
		
		R r= R.ok();
		return r;
	}	
	
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("crm:user:delete")
	public R delete(@RequestBody String[] uids){
		userService.deleteBatch(uids);
		
		return R.ok();
	}
	
}

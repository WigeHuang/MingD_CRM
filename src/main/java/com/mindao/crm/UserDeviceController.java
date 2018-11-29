package com.mindao.crm;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindao.entity.UserDeviceEntity;
import com.mindao.service.UserDeviceService;
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
@RequestMapping("/crm/userdevice")
public class UserDeviceController {
	@Autowired
	private UserDeviceService userDeviceService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("crm:userdevice:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		if (params.get("sidx")==null || ((String) params.get("sidx")).trim().length()==0 ){
			params.put("sidx", "bind_time");
			params.put("order", "desc");
		}
        Query query = new Query(params);

		List<UserDeviceEntity> userDeviceList = userDeviceService.queryList(query);
		int total = userDeviceService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(userDeviceList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{pid}")
	@RequiresPermissions("crm:userdevice:info")
	public R info(@PathVariable("pid") String pid){
		UserDeviceEntity userDevice = userDeviceService.queryObject(pid);
		
		return R.ok().put("userDevice", userDevice);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("crm:userdevice:save")
	public R save(@RequestBody UserDeviceEntity userDevice){
		userDeviceService.save(userDevice);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("crm:userdevice:update")
	public R update(@RequestBody UserDeviceEntity userDevice){
		userDeviceService.update(userDevice);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("crm:userdevice:delete")
	public R delete(@RequestBody String[] pids){
		userDeviceService.deleteBatch(pids);
		
		return R.ok();
	}
	
}

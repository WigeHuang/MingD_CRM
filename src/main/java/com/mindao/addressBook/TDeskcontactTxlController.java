package com.mindao.addressBook;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindao.entity.TDeskcontactTxlEntity;
import com.mindao.service.TDeskcontactTxlService;
import com.mindao.utils.PageUtils;
import com.mindao.utils.Query;
import com.mindao.utils.R;

@RestController
@RequestMapping("crm/addressBook")
public class TDeskcontactTxlController {
	@Autowired
	private TDeskcontactTxlService tDeskcontactTxlService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("crm:addressBook:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TDeskcontactTxlEntity> tDeskcontactTxlList = tDeskcontactTxlService.queryList(query);
		int total = tDeskcontactTxlService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tDeskcontactTxlList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("crm:addressBook:info")
	public R info(@PathVariable("id") String id){
		TDeskcontactTxlEntity tDeskcontactTxl = tDeskcontactTxlService.queryObject(id);
		
		return R.ok().put("tDeskcontactTxl", tDeskcontactTxl);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("crm:addressBook:save")
	public R save(@RequestBody TDeskcontactTxlEntity tDeskcontactTxl){
		tDeskcontactTxlService.save(tDeskcontactTxl);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("crm:addressBook:update")
	public R update(@RequestBody TDeskcontactTxlEntity tDeskcontactTxl){
		tDeskcontactTxlService.update(tDeskcontactTxl);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("crm:addressBook:delete")
	public R delete(@RequestBody String[] ids){
		tDeskcontactTxlService.deleteBatch(ids);
		
		return R.ok();
	}
	
}

package com.mindao.crm;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindao.admin.AbstractController;
import com.mindao.entity.FeedbackEntity;
import com.mindao.service.FeedbackService;
import com.mindao.utils.PageUtils;
import com.mindao.utils.Query;
import com.mindao.utils.R;


/**
 * 
 * 
 * @author liguocai
 * @email 153277817@qq.com
 * @date 2017-06-27 14:47:10
 */
@RestController
@RequestMapping("/crm/feedback")
public class FeedbackController  extends AbstractController {
	@Autowired
	private FeedbackService feedbackService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("feedback:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		if (params.get("sidx")==null || ((String) params.get("sidx")).trim().length()==0 ){
			params.put("sidx", "submit_date");
			params.put("order", "desc");
		}		
        Query query = new Query(params);

		List<FeedbackEntity> feedbackList = feedbackService.queryList(query);
		int total = feedbackService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(feedbackList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{pid}")
	@RequiresPermissions("feedback:info")
	public R info(@PathVariable("pid") String pid){
		FeedbackEntity feedback = feedbackService.queryObject(pid);
		
		return R.ok().put("feedback", feedback);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("feedback:save")
	public R save(@RequestBody FeedbackEntity feedback){
		feedbackService.save(feedback);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("feedback:update")
	public R update(@RequestBody FeedbackEntity feedback){
		feedback.setHandleDate(new Date());
		feedback.setHandler(super.getUserId());
		feedbackService.update(feedback);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("feedback:delete")
	public R delete(@RequestBody String[] pids){
		feedbackService.deleteBatch(pids);
		
		return R.ok();
	}
	
}

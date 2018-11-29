package com.mindao.crm;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindao.admin.AbstractController;
import com.mindao.service.StatService;
import com.mindao.utils.R;

@RestController
@RequestMapping("/crm/dashboard")
public class DashboardController  extends AbstractController {
	@Autowired
	private StatService statService;
	/**
	 * 列表
	 */
	@RequestMapping("/stat")
	public R stat(@RequestParam Map<String, Object> params){
		Map<String,Object> data=statService.dashboardStat();
		return R.ok().put("data", data);
	}
}

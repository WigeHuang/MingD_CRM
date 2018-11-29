package com.mindao.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindao.entity.FeedbackEntity;
import com.mindao.entity.UserEntity;
import com.mindao.service.FeedbackService;
import com.mindao.service.TokenService;
import com.mindao.service.UserService;
import com.mindao.utils.PageUtils;
import com.mindao.utils.Query;
import com.mindao.utils.R;
import com.mindao.utils.annotation.IgnoreAuth;
import com.mindao.utils.annotation.LoginUser;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * API登录授权
 *
 * @author ligc
 * @email 153277817@qq.com
 * @date 2017-03-23 15:31
 */
@RestController
@RequestMapping("/api/feedback")
@Api("反馈接口")
public class ApiFeedbackController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private FeedbackService feedbackService;
    /**
     * 加载列表
     */
 
    @RequestMapping(value="list",method={RequestMethod.GET })
    @ApiOperation(value = "加载反馈列表",notes = "加载反馈列表")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "query", dataType="string", name = "token", value = "访问凭证", required = true) 
        //@ApiImplicitParam( name = "params", value = "查询参数Map", required = true, dataType = "ListParam")
    })
	public R list(HttpServletRequest request ,@LoginUser UserEntity user, @RequestParam Map<String, Object> params){
//    	String token =(String) params.get("token");
//        Assert.isBlank(token, "访问凭证不能为空");
//   
//        TokenEntity tokenEntity  = tokenService.queryByToken(token);
//        boolean b=tokenService.isExpired(tokenEntity.getExpireTime());
//        if (b){
//        	throw new RRException("会话已过期，请重新登录");  
//        }     	
		//查询列表数据
		if (params.get("sidx")==null || ((String) params.get("sidx")).trim().length()==0 ){
			params.put("sidx", "submit_date");
			params.put("order", "desc");
		}		
		params.put("uid", user.getUid());
        Query query = new Query(params);

		List<FeedbackEntity> feedbackList = feedbackService.queryList(query);
		int total = feedbackService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(feedbackList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
    @IgnoreAuth
	@RequestMapping(value="/info/{pid}",method={RequestMethod.GET })
    @ApiOperation(value = "加载反馈信息",notes = "加载反馈信息")
    @ApiImplicitParams({
        //@ApiImplicitParam(paramType = "header", dataType="string", name = "token", value = "访问凭证", required = true),
        @ApiImplicitParam(paramType = "path", dataType="string", name = "pid", value = "主键ID", required = true)
    })    
	public R info(@PathVariable("pid") String pid){
		FeedbackEntity feedback = feedbackService.queryObject(pid);
		
		return R.ok().put("feedback", feedback);
	}
	
	/**
	 * 保存
	 */
 
	@RequestMapping(value="/save",method={RequestMethod.POST })
    @ApiOperation(value = "加载反馈信息",notes = "加载反馈信息")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "header", dataType="string", name = "token", value = "访问凭证", required = true),
        @ApiImplicitParam( dataType="FeedbackEntity", name = "feedback", value = "反馈对象实体类", required = true)
    }) 
	public R save(HttpServletRequest request ,@LoginUser UserEntity user,@RequestBody FeedbackEntity feedback){
//    	String token = request.getHeader("token");
//        Assert.isBlank(token, "访问凭证不能为空");
//   
//        TokenEntity tokenEntity  = tokenService.queryByToken(token);
//        boolean b=tokenService.isExpired(tokenEntity.getExpireTime());
//        if (b){
//        	throw new RRException("会话已过期，请重新登录");  
//        } 		
        feedback.setUid(user.getUid());
        feedback.setSubmitDate(new Date());
        feedback.setUploadLog(0);
        feedback.setState(FeedbackEntity.STATE_HANDLING);
		feedbackService.save(feedback);
		
		return R.ok();
	}
}

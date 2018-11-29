package com.mindao.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mindao.entity.SysUserEntity;
import com.mindao.utils.ShiroUtils;

/**
 * Controller公共组件
 * 
 * @author ligc
 * @email 153277817@qq.com
 * @date 2016年11月9日 下午9:42:26
 */
public abstract class AbstractController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected SysUserEntity getUser() {
		return ShiroUtils.getUserEntity();
	}

	protected Long getUserId() {
		return getUser().getUserId();
	}
}

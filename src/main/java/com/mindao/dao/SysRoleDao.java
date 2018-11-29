package com.mindao.dao;

import java.util.List;

import com.mindao.entity.SysRoleEntity;

/**
 * 角色管理
 * 
 * @author ligc
 * @email 153277817@qq.com
 * @date 2016年9月18日 上午9:33:33
 */
public interface SysRoleDao extends BaseDao<SysRoleEntity> {
	
	/**
	 * 查询用户创建的角色ID列表
	 */
	List<Long> queryRoleIdList(Long createUserId);
}

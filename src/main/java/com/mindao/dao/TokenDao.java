package com.mindao.dao;

import com.mindao.entity.TokenEntity;

/**
 * 用户Token
 * 
 * @author ligc
 * @email 153277817@qq.com
 * @date 2017-03-23 15:22:07
 */
public interface TokenDao extends BaseDao<TokenEntity> {
    
    TokenEntity queryByUserId(String userId);

    TokenEntity queryByToken(String token);

	void deleteByToken(String token);
	
}

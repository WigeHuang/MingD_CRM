package com.mindao.service;

import java.util.Date;
import java.util.Map;

import com.mindao.entity.TokenEntity;

/**
 * 用户Token
 * 
 * @author ligc
 * @email 153277817@qq.com
 * @date 2017-03-23 15:22:07
 */
public interface TokenService {

	TokenEntity queryByUserId(String userId);
    /**
     * 根据token找到用户id
     * @param token
     * @return
     */
	TokenEntity queryByToken(String token);
    /**
     * 根据token找到用户id
     * @param token
     * @return
     */
	void deleteByToken(String token);
	
	void save(TokenEntity token);
	
	void update(TokenEntity token);

	/**
	 * 生成token
	 * @param userId  用户ID
	 * @return        返回token相关信息
	 */
	Map<String, Object> createToken(String userId);
	/**
	 * 判断token是否过期
	 * @param expireTime
	 * @return
	 */
	public boolean isExpired(Date expireTime);

}

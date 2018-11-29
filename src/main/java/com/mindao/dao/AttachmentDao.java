package com.mindao.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mindao.entity.AttachmentEntity;

@Repository
public interface AttachmentDao extends  BaseDao<AttachmentEntity>{

	List<AttachmentEntity> findByIds(Map<String,Object> map);
 
	
}

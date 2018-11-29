package com.mindao.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.mindao.entity.AttachmentEntity;

public interface AttachmentService {

//	public List<AttachmentEntity> queryListByPage(Map<String, Object> parameter);

	//public AttachmentEntity findByName(String accountName);
	
	public int insert(AttachmentEntity attachmentEntity);
	
	public AttachmentEntity findByIdStr(String id);

//	public int update(AttachmentEntity attachmentEntity);
	
	//public int updateOnly(AttachmentEntity attachmentEntity, String password) throws ServiceException;
    
    public int deleteBatchById(List<String> userIds);
    
    //public List<AttachmentEntity> queryListAll();
    
    /**
     * 上传文件
     * @param fileInputStream
     * @param fileName
     * @param uploader 账号
     * @return
     */
    public AttachmentEntity uploadFile(InputStream fileInputStream,String fileName,String uploader)throws Exception;
    
	/**根据文档对象获取文件输入流
	 * @param doc
	 * @return
	 */
	public InputStream openFileInputStreamByDocument(AttachmentEntity doc);
	
	
	public List<AttachmentEntity> findByIds(String ids);
    
}
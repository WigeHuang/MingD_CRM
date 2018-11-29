package com.mindao.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyun.oss.ServiceException;
import com.mindao.config.CrmConfigInfo;
import com.mindao.dao.AttachmentDao;
import com.mindao.entity.AttachmentEntity;
import com.mindao.service.AttachmentService;

@Service("attachmentService")
public class AttachmentServiceImpl   implements AttachmentService{
	public Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private AttachmentDao attachmentMapper;
	
	@Autowired
	private CrmConfigInfo systemConfig;


 
	
	/**
	 * 重写用户插入，逻辑：
	 * 1、插入用户
	 * 2、插入用户和角色的对应关系
	 * 3、插入用户的个人资料信息
	 */
	public int insert(AttachmentEntity attachmentEntity){
		try
		{
			 attachmentMapper.save(attachmentEntity) ;
			 return 1;
		}catch(Exception e)
		{
			throw new ServiceException(e);
		}
		 
	}
	

//	/**
//	 * 重写用户更新逻辑：
//	 * 1、更新用户
//	 * 2、更新用户和角色的对应关系
//	 * 3、更新用户个人资料信息
//	 */
//	public int update(AttachmentEntity attachmentEntity){
//		try
//		{
//			if(attachmentMapper.update(attachmentEntity) == 1)
//			{
//				return 1;
////				if(uuserMapper.updateUserRole(attachmentEntity) == 1)
////				{
////					return uuserMapper.updateUserInfo(attachmentEntity);
////				}else
////				{
////					return 0;
////				}
//			}else
//			{
//				return 0;
//			}
//		}catch(Exception e)
//		{
//			throw new ServiceException(e);
//		}
//	}
	
	/**
	 * 重写用户删除逻辑：
	 * 1、删除用户和角色的对应关系
	 * 2、删除用户
	 */
	public int deleteBatchById(List<String> userIds){
		try
		{
			String[] ids = new String[userIds.size()];
			userIds.toArray(ids);
			return attachmentMapper.deleteBatch(ids);
		}catch(Exception e)
		{
			throw new ServiceException(e);
		}
	}
//
//	@Override
//	public int updateOnly(AttachmentEntity attachmentEntity, String password) throws ServiceException{
//		try
//		{ 
//			int cnt = attachmentMapper.update(attachmentEntity);
// 
//			return cnt;
//		}catch(Exception e)
//		{
//			throw new ServiceException(e);
//		}
//	}
//
//	@Override
//	public List<AttachmentEntity> queryListAll() {
//		// TODO Auto-generated method stub
//		return attachmentMapper.queryListAll(new HashMap());
//	}

	@Override
	public AttachmentEntity uploadFile(InputStream fileInputStream, String fileName,String uploader) throws Exception {
		int fileSize = 0;
		InputStream filein = null;
		FileOutputStream fileOutput = null;
		String fileprefix;
		String fileAbsoluteDir;
		SimpleDateFormat prefixFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat filepathFormat = new SimpleDateFormat("yyyyMM");
		Date ntime = new Date();
		fileprefix = prefixFormat.format(ntime);

		// 例子: d:/temp/
		String attahmentMainHomeDir = systemConfig.getAttachmentRootPath();
		attahmentMainHomeDir=attahmentMainHomeDir+(attahmentMainHomeDir.endsWith("/")?"":"/");
		// 例子: d:/temp/files/ ( = d:/temp/ + files/)
		//String attahmentSubHomeDir = attahmentMainHomeDir + getAttachmentSubHomeDirectory();

		// 例子: 20101011
		String monthPath = filepathFormat.format(ntime);

		// !! 小心处理这个字段filePath 是 相对于 Attachment Main Home Directory的那部分
		// 例子: files/20101011 ( = files/ + 20101011 )
		//String filePath =monthPath; //attahmentMainHomeDir + monthPath;

		// 例子: d:/temp/files/20101011
		//fileAbsoluteDir = attahmentMainHomeDir + monthPath;
		String filePath=monthPath+ "/" + fileprefix + fileName;
		fileAbsoluteDir=attahmentMainHomeDir+monthPath+ "/" ;
		String writeFileAbsolutePath = attahmentMainHomeDir + filePath;
		// Document doc = new Document();
		try {
			mkDir(attahmentMainHomeDir);
			//mkDir(attahmentSubHomeDir);
			mkDir(fileAbsoluteDir); // 创建目录
			log.debug("实际保存路径 : " + writeFileAbsolutePath);
			filein = fileInputStream;// new FileInputStream(file);
			File fileOut = new File(writeFileAbsolutePath);
			fileOutput = new FileOutputStream(fileOut);
			byte[] bytes = new byte[1024 * 10];
			int c = 0;
			fileSize = filein.available();
			while ((c = filein.read(bytes)) != -1) {
				fileOutput.write(bytes, 0, c);
			}
			fileOutput.flush();
			
			
			AttachmentEntity ae=new AttachmentEntity();
			ae.setPid(UUID.randomUUID().toString().replaceAll("-", ""));
			ae.setFileName(fileName);
			ae.setFilePath(filePath);
			ae.setFileSize(fileSize);
			ae.setUploadDate(new Date());
			ae.setUploader(uploader);
			//String checksum=ChecksumUtil.Md5Checksum(writeFileAbsolutePath);
			//ae.setChecksum(checksum);
			this.insert(ae);
			return ae;
		} catch (IOException e) {
			log.error(e.getMessage(),e);
			throw e;
		} finally {
			if (filein != null) {
				fileOutput.close();
			}
			if (filein != null) {
				filein.close();
			}
		}
		
	}

	/**
	 * 创建目录
	 * 
	 * @param dir
	 *            目录名称
	 */
	public void mkDir(String dir) {
		try {
			String filePath = dir;
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.mkdir();
			}
		} catch (Exception e) {
			log.debug("新建目录操作出错");
			log.error(e.getMessage(),e);
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param filePath
	 *            文件路径
	 */
	public void delFile(String filePath) {
		try {
			File DelFile = new File(filePath);
			DelFile.delete();

		} catch (Exception e) {
			log.debug("删除文件操作出错");
			log.error(e.getMessage(),e);

		}

	}

	@Override
	public InputStream openFileInputStreamByDocument(AttachmentEntity doc) {
		// TODO Auto-generated method stub
		if (doc == null) {
			return null;
		}
		String attahmentMainHomeDir = systemConfig.getAttachmentRootPath();
		attahmentMainHomeDir=attahmentMainHomeDir+(attahmentMainHomeDir.endsWith("/")?"":"/");
		String path = attahmentMainHomeDir+doc.getFilePath();
		System.out.println(path);
		File file = new File(path);
		InputStream fis = null;
		try {
			if (file.exists()) {
				fis = new FileInputStream(file);
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage(),e);
		}
		return fis;
	}

	@Override
	public List<AttachmentEntity> findByIds(String ids) {
		if (StringUtils.isBlank(ids)){
			return new ArrayList<AttachmentEntity>();
		}else{
			Map<String,Object> map=new HashMap<>();
			List attIdsArray=Arrays.asList(ids.split(","));
			map.put("attIdsArray", attIdsArray);
			return attachmentMapper.findByIds(map);
		}
	}

//
//	@Override
//	public List<AttachmentEntity> queryListByPage(Map<String, Object> parameter) {
//		// TODO Auto-generated method stub
//		return null;
//	}


	@Override
	public AttachmentEntity findByIdStr(String id) {
		return attachmentMapper.queryObject(id);
	}


//	@Override
//	public int update(AttachmentEntity attachmentEntity) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
	
}

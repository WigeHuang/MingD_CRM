package com.mindao.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * 产品信息
 * @author ligc
 *
 */
@Alias("attachmentEntity")
public class AttachmentEntity implements Serializable {
	private String pid;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    /**
     * 原始文件名称
     */
	private String fileName;
	/**
	 * 实际文件路径，相对于附件根目录的路径，自动生成年月文件夹
	 */
	private String filePath;
	/**
	 * 上传日期
	 */
	private Date uploadDate;
	/**
	 * 上传人
	 */
	private String uploader;
	/**
	 * 文件大小
	 */
	private Integer fileSize;
	/**
	 * 核对码
	 */
	private String checksum;	
 
	public AttachmentEntity() {

	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getUploader() {
		return uploader;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	public Integer getFileSize() {
		return fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	 
	 
 

}

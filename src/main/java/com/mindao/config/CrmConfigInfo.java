package com.mindao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class CrmConfigInfo {

	@Value("${crm.saveAvatarDir}")
	private String saveAvatarDir;
	
	@Value("${crm.authServer}")
	private String authServer;
	
	/**
	 * 附件保存路径
	 */
	@Value("${crm.attachmentRootPath}")
	private String attachmentRootPath;

	public String getSaveAvatarDir() {
		return saveAvatarDir;
	}

	public void setSaveAvatarDir(String saveAvatarDir) {
		this.saveAvatarDir = saveAvatarDir;
	}

	public String getAuthServer() {
		return authServer;
	}

	public void setAuthServer(String authServer) {
		this.authServer = authServer;
	}

	public String getAttachmentRootPath() {
		return attachmentRootPath;
	}

	public void setAttachmentRootPath(String attachmentRootPath) {
		this.attachmentRootPath = attachmentRootPath;
	}
	
	
}

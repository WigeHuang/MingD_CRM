package com.mindao.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mindao.entity.AttachmentEntity;
import com.mindao.entity.SysUserEntity;
import com.mindao.service.AttachmentService;
import com.mindao.utils.DownloadFileUtil;

@Controller
@Scope("prototype")
@RequestMapping("/attachment/")
public class AttachmentController    extends AbstractController {
 
	@Autowired
	private AttachmentService attachmentService;
 
 
 
	@RequestMapping("download/{attachmentId}")
	public void download(HttpServletRequest request,HttpServletResponse response,HttpSession session,Model rhs, @PathVariable(value = "attachmentId", required = true) String attachmentId) throws Exception {
 
		AttachmentEntity doc = attachmentService.findByIdStr(attachmentId);
		InputStream inputStream=attachmentService.openFileInputStreamByDocument(doc);
	 
		String s=request.getSession().getServletContext().getRealPath("/")  ;
		int fileSize=0;
		if (doc!=null && inputStream!=null){
			fileSize=doc.getFileSize();
//		}else{
//			inputStream=new FileInputStream(new File(s+"nopic.png"));
//			fileSize=1197;
			DownloadFileUtil.downloadFile(response,request,doc.getFileName(),inputStream,fileSize);
		}else{
			response.getWriter().write("<script>alert(\"File not found!\");</script>");
			response.getWriter().flush();
		}
		
		

	}
 
	@RequestMapping("upload")
	@ResponseBody
	public  Object upload(HttpServletResponse response,HttpServletRequest request,String fileToDownload,InputStream inputStream,long fileLength,@RequestParam(value = "upload", required = true) MultipartFile  file) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
//		Enumeration enums=request.getSession().getAttributeNames();
//		while (enums.hasMoreElements()){
//			String key = (String) enums.nextElement();
//			System.out.println(key+":"+request.getSession().getAttribute(key).toString());
//			
//		}
		
		SysUserEntity userEntity = getUser();
		String userAccount=userEntity.getEmail();
		//String userAccount="";
		//保存附件
		AttachmentEntity att = attachmentService.uploadFile(file.getInputStream(), file.getOriginalFilename() , userAccount);
		map.put("attachmentId", att.getPid());
		//versionEntity.setAttachmentId(att.getPid());
		return map;
		
	}
	
	
	@RequestMapping("show")
	public String show(HttpServletRequest request,HttpServletResponse response,HttpSession session,Model rhs, @RequestParam(value = "attIds", required = true) String attIds, @RequestParam(value = "readOnly", required = false) String readOnly) throws Exception {
 
		List<AttachmentEntity> docs = attachmentService.findByIds(attIds);
		rhs.addAttribute("attachments", docs);
		rhs.addAttribute("readOnly",StringUtils.isBlank(readOnly)?"true":readOnly);
		return  "/attachment/show";
	}
	
	@RequestMapping("withoutAuth/view")
	public String view(HttpServletRequest request,HttpServletResponse response,HttpSession session,Model rhs, @RequestParam(value = "attIds", required = true) String attIds ) throws Exception {
 
		List<AttachmentEntity> docs = attachmentService.findByIds(attIds);
		rhs.addAttribute("attachments", docs);
		return "/attachment/view";
	}
	
}

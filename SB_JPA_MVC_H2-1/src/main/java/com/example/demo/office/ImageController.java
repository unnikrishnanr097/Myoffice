package com.example.demo.office;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.ImageControllerRepo;
import com.example.demo.model.FileUploadUtil;
import com.example.demo.officemodel.Image;
import com.example.demo.officemodel.Leavecount;


@RestController
public class ImageController {

	    @Autowired
	    private ImageControllerRepo imagerepo;
	     
		@PutMapping(path="/office/image/upload",produces= {"application/json"})
	    public String saveUser(String userid,  @RequestParam("image") MultipartFile multipartFile) throws IOException {
	         
  
	    	Image img=new Image();
	        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
	        img.setUserid(userid);
	        img.setPhotos(fileName);
	        Image savedUser = imagerepo.save(img);
	        String uploadDir = "user-photos/" + savedUser.getUserid();
	        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
	         
	        return "Success";
	        
	        
	    }
	

		@RequestMapping(path="/office/image/getall",produces= {"application/json"})
		public List<Image> getall() {
			
			
			return imagerepo.findAll();
			
		}
		
		@DeleteMapping("/office/image/delete")  
		public String deletesuggestions( )  
		{  
			imagerepo.deleteAll();
			return "Success";
			
		}
		
//		@RequestMapping(path = "/office/image/get",produces= {"application/json"})
//		public  byte[] getImage() throws IOException {
//		    InputStream in = getClass().getResourceAsStream(new Image().getPhotosImagePath());
//		    return in.readAllBytes();
//		}
	
}

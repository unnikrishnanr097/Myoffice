package com.example.demo.office;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.LeavecountRepo;
import com.example.demo.dao.MailmodelRepo;
//import com.example.demo.dao.LeavecountRepo;
import com.example.demo.dao.UsersRepo;
import com.example.demo.model.EmailService;
import com.example.demo.officemodel.LeaveRequest;
import com.example.demo.officemodel.Leavecount;
import com.example.demo.officemodel.Mailmodel;
import com.example.demo.officemodel.Users;

@RestController

public class Officecontroller {
	
	
	 @Autowired
	 private JavaMailSender mailSender;
	
	@Autowired
	UsersRepo repos;
	@Autowired
	LeavecountRepo leavecountrepo;
	
	@Autowired
    private MailmodelRepo mailrepo;
	
	//error Check
	
	@RequestMapping(path="/",produces= {"application/json"})
	public String gethome() {

		return "Success";
	}
	
//users creation
@PostMapping(path="/office/admin/create",produces= {"application/json"})
	
	public String admincreate(Users users) throws AddressException, MessagingException, IOException {
		repos.save(users);

		Leavecount lc=new Leavecount();
		lc.setUserid(users.getUserid());
		lc.setLeavecount(0);
		lc.setDate(users.getJoiningdate());
		
		leavecountrepo.save(lc);
	
		return sendmail(users);
	}
	
//fetching users values
@RequestMapping(path="/office/admin/get",produces= {"application/json"})
public List<Users> getadmin() {

	return repos.findAll();
}

//user login authentication

@PostMapping(path="/office/user/login",produces= {"application/json"})

public Users userlogin(String userid,String password) {
	 Users user=repos.findByUserid(userid);
	 if(user.getPassword().equals(password))
	 {
		 return user;
	 }
	// user.getUserid()==userid&&
	
	 return null ; //user not found
	 
}
//Return single user

@PostMapping(path="/office/user/findbyid",produces= {"application/json"})

public Users userlogin(int id) {
	
	 Optional<Users> user=repos.findById(id);
	 Users user1=user.get();
	 user1.setPassword("******");
	return user1;
	
}
//update Employee 


@PutMapping(path="/office/user/update",produces= {"application/json"})

public Users userupdate(Users user) {
	
	return repos.save(user);
	
	
}

//fetching employees

@RequestMapping(path="/office/user/getall/empl",produces= {"application/json"})

public List<Users> getemployees() {
	
	 List<Users> user=repos.findAll();
	 user.remove(0);
	return user; 
}

//delete Employee 

@DeleteMapping("/office/user/delete/{id}")  
public void deleteUser( @PathVariable("id") int id)  
{
	
	repos.deleteById(id);
	
}


//sending email
private String sendmail(Users users) throws AddressException, MessagingException, IOException {
	
	 String content = "Dear [[name]],<br>"+" your login password is : [[password]] "
	            + "Please click the link below to verify your registration: <br>"
	            + "<h3><a href=https://myofficerest.herokuapp.com/office/users/verify/[[userid]]>VERIFY</a></h3>"
	            + " Thank you,<br>"
	            + " Inet Infotech";
	
	
	   Properties props = new Properties();
	   props.put("mail.smtp.auth", "true");
	   props.put("mail.smtp.starttls.enable", "true");
	   props.put("mail.smtp.host", "smtp.gmail.com");
	   props.put("mail.smtp.port", "587");
	   
	   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	      protected PasswordAuthentication getPasswordAuthentication() {
	         return new PasswordAuthentication("unnikrishnanr097@gmail.com", "rmvgwijajucwlabl");
	      }
	   });
	   Message msg = new MimeMessage(session);
	   msg.setFrom(new InternetAddress("unnikrishnanr097@gmail.com", false));

	   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(users.getEmail()));
	   msg.setSubject("Tutorials point email");
	   content = content.replace("[[name]]", users.getName());
	    content = content.replace("[[userid]]", users.getUserid());
	    content = content.replace("[[password]]", users.getPassword());

	   msg.setContent(content, "text/html");
	   msg.setSentDate(new Date());

	   MimeBodyPart messageBodyPart = new MimeBodyPart();
	   messageBodyPart.setContent(content, "text/html");
	   
	   
	   Multipart multipart = new MimeMultipart();
	   multipart.addBodyPart(messageBodyPart);

	   msg.setContent(multipart);
	   Transport.send(msg);   
	   return "success";
	}





@RequestMapping(path="/office/users/verify/{userid}",produces= {"application/json"})

private String verify(@PathVariable("userid") String userid) {
	
	Mailmodel email=mailrepo.findByUserid(userid);
	email.setEnabled(true);
	mailrepo.save(email);
	return "Registration Successfull!!! Thank you...";
}
	
@RequestMapping(path="/office/users/verify/getall",produces= {"application/json"})

private List<Mailmodel> getmail() {
	
	return mailrepo.findAll();
}


@DeleteMapping("/office/mail/delete")  
public void deletemail( )  
{
	
	mailrepo.deleteAll();
	
}

}

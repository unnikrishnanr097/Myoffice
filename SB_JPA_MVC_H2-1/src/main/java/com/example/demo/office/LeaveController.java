package com.example.demo.office;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.LeaveRequestRepo;
import com.example.demo.dao.LeavecountRepo;
import com.example.demo.dao.UsersRepo;
import com.example.demo.officemodel.LeaveFinal;
import com.example.demo.officemodel.LeaveRequest;
import com.example.demo.officemodel.Leavecount;
import com.example.demo.officemodel.Mailmodel;
import com.example.demo.officemodel.Suggestion;
import com.example.demo.officemodel.Users;
import com.example.demo.officemodel.UserwithLeave;

@RestController
public class LeaveController {
	@Autowired
	LeaveRequestRepo leaveReqrepo;
	
	@Autowired
	UsersRepo usersrepo;
	
	@Autowired
	LeavecountRepo leavecountrepo;
	@PostMapping(path="/office/leave/create",produces= {"application/json"})
	 
	public LeaveRequest leaveCreate(LeaveRequest leaveReq) {
		
		 Leavecount lc=new Leavecount();
		 Leavecount lc1=new Leavecount();
		 
		 
		 lc=leavecountrepo.findByUserid1(leaveReq.getUserid());
		 int a=lc.getLeavecount();
		 lc.setUserid(leaveReq.getUserid());
		  lc1=leavecountrepo.findByUserid1(leaveReq.getUserid());
		 String lastdate=lc1.getDate();
		 String newdate=leaveReq.getLeavedate();
		 String[] arrlast = lastdate.split("/", 0);  
		 String[] arrnew = newdate.split("/", 0);  
		 if(arrlast[1].equals(arrnew[1]))
		 {
			
			 lc1.setLeavecount(++a);
			 lc1.setDate(newdate);

		 }
		 else
		 {
			 a=0;
			 lc1.setLeavecount(0);

			 lc1.setDate(newdate);
			
		 }
		 lc1.setUserid(leaveReq.getUserid());
		 leavecountrepo.save(lc1);
		leaveReq.setLeavecount(a);
		 return leaveReqrepo.save(leaveReq);
		  
		
	}

	
	

	
	
	@RequestMapping(path="/office/leave/get/{status}",produces= {"application/json"})
	public ArrayList<LeaveFinal> getAllLeaves(@PathVariable("status") int status) {
		
		ArrayList<LeaveFinal> arrayleavefinal = new ArrayList<LeaveFinal>(); ;
		//LeaveFinal leavefinal=new LeaveFinal();
		ArrayList<LeaveRequest> arrayleaves =new ArrayList<LeaveRequest> () ;
		
		  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
		    Date date = new Date();  
		    String str = formatter.format(date);
		    try {
				Date  currentdate= new SimpleDateFormat("dd/MM/yyyy").parse(str);
			
		    if(status==0)
		    {
		    	 arrayleaves =leaveReqrepo.findByStatus(0);
		    for(LeaveRequest lv :arrayleaves)
		    {
		    	
					Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(lv.getLeavedate());
					if(date1.after(currentdate)&& lv.getStatus()==status)
					{
						LeaveFinal leavefinal=new LeaveFinal();
						leavefinal.setLeaverequest(lv);
						leavefinal.setUsers(usersrepo.findByUserid(lv.getUserid()));
						arrayleavefinal.add(leavefinal);
					}
		    }
		    return arrayleavefinal;
		    }
		    
		    
		    else if(status==1 )
		    {
		    	arrayleaves =leaveReqrepo.findByStatus(1);
		    	 for(LeaveRequest lv :arrayleaves)
				    {
						LeaveFinal leavefinal=new LeaveFinal();

				    	
							if(lv.getStatus()==1)
							{
								 
								 leavefinal.setLeaverequest(lv);
									leavefinal.setUsers(usersrepo.findByUserid(lv.getUserid()));
									arrayleavefinal.add(leavefinal);
							}
							
				    }
		    	 return arrayleavefinal;
		    	 
		    }
		    
		    else if(status==2)
		    {
		    	arrayleaves =leaveReqrepo.findByStatus(2);
		    	 for(LeaveRequest lv :arrayleaves)
				    {
						LeaveFinal leavefinal=new LeaveFinal();
				
					 leavefinal.setLeaverequest(lv);
						leavefinal.setUsers(usersrepo.findByUserid(lv.getUserid()));
						arrayleavefinal.add(leavefinal);

		    }
		    	 return arrayleavefinal;
		    }
		    
		    }
		    catch (ParseException e) {
					e.printStackTrace();
				}
		    
		return arrayleavefinal;
	}
	
	@PutMapping("/office/leave/status")
	public void a(int id,int status) {
		LeaveRequest lr=leaveReqrepo.findById(id);
		lr.setStatus(status);
		String userid =lr.getUserid();
		Users user=usersrepo.findByUserid(userid);
		String email=user.getEmail();
		
		leaveReqrepo.save(lr);
		try {
			sendmail(email,status);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private String sendmail(String email,int status) throws AddressException, MessagingException, IOException {
		
		 String content = "Dear [[name]],<br>"
		            + "Notification from Myoffice...<br>"
		            + "Your Leave Request has been [[leavestatus]]"
		            + "Thank you,<br>"
		            + "Inet Infotech";
		
		
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

		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("unnikrishnanr096@gmail.com"));
		   msg.setSubject("Tutorials point email");
		   content = content.replace("[[name]]", email);
		   if(status==1)
		   {
			   content = content.replace("[[leavestatus]]", "Accepted");
		   }
		   else
		   {
			    content = content.replace("[[leavestatus]]", "Rejected");

		   }
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

	
	@DeleteMapping("/office/leave/delete")  
	public String deletesuggestions( )  
	{  
		leaveReqrepo.deleteAll();
		return "Success";
		
	}
	
	@RequestMapping(path="/office/leave/user/{userid}",produces= {"application/json"})
	public ArrayList<LeaveRequest> getleave_userid(@PathVariable("userid") String userid) {
		
		return leaveReqrepo.findByUserid(userid);
		
		 	}
	
	
	
	
	
	
	
	
	@RequestMapping(path="/office/leave/getcount ",produces= {"application/json"})
	public List<Leavecount> getAllLeavescount() {
		return leavecountrepo.findAll();
	}
	
	@RequestMapping(path="/office/leave/findall",produces= {"application/json"})
	public List<LeaveRequest> getAllLeavess() {
		return leaveReqrepo.findAll();
	}
}

package com.example.demo.office;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.SuggestionRepo;
import com.example.demo.dao.UserNoticeModelRepo;
import com.example.demo.dao.WorkRepo;
import com.example.demo.officemodel.Notice;
import com.example.demo.officemodel.Suggestion;
import com.example.demo.officemodel.UserNoticeModel;

@RestController
public class WorkController {
	@Autowired
	WorkRepo workrepo;
	@Autowired
	SuggestionRepo suggrepo;
	@Autowired
	UserNoticeModelRepo unmr;
	//adding notice to database
	@PostMapping(path="/office/notice/add",produces= {"application/json"})
	
	public void addnotice(Notice notice) {
		workrepo.save(notice);
		
	}
	
	//fetching all notice
	@RequestMapping(path="/office/notice/get",produces= {"application/json"})

	
	  public List<Notice> getallnotices() {
	  
		List<Notice> list=workrepo.findAll(); 
		return list; 
	  }
	
	//deleting notice
	@DeleteMapping("/office/notice/{id}")  
	public void deleteNotice(@PathVariable("id") int id)  
	{  
		workrepo.deleteById(id);  
		
	}
	
	
	 	
	//adding suggestions
	@PostMapping(path="/office/suggestion/add",produces= {"application/json"})
	
	public Suggestion addsuggestion(Suggestion sugg,int userid) {
		
		UserNoticeModel unm=new UserNoticeModel();

		if(sugg.getUpvote()==1)
		{
			sugg.setUpvote(1);
		}
		else if(sugg.getDownvote()==1)
		{
			sugg.setDownvote(1);
		}
		suggrepo.save(sugg);
		 unm.setSuggestionid(sugg.getId());
		  unm.setUserid(userid);
		  unmr.save(unm);
		return sugg;
	}
	
	//fetching suggestions
	@RequestMapping(path="/office/suggestion/get/{userid}",produces= {"application/json"})

	
	  public ArrayList<Suggestion> getsuggestionsbyid(@PathVariable("userid") int userid) {
		ArrayList<Integer> newlistvotebank=new ArrayList<Integer>();
		ArrayList<Integer> newlistsuggestion=new ArrayList<Integer>();

		List<UserNoticeModel> listVotebank=unmr.findByUserid(userid);
		List<Suggestion> listSuggestion=suggrepo.findAll();
		  ArrayList<Suggestion> arraylist=new ArrayList<Suggestion>();

		if(!listVotebank.isEmpty())
		{
			for(int i=0;i<listVotebank.size();i++)
			{
				newlistvotebank.add(listVotebank.get(i).getSuggestionid());
			
				
				
			}
			for(int i=0;i<listSuggestion.size();i++)
			{
				newlistsuggestion.add(listSuggestion.get(i).getId());
			}
			
			Collections.sort(newlistvotebank);
			Collections.sort(newlistsuggestion);
			
			if(newlistvotebank.containsAll(newlistsuggestion))
			{
				return new ArrayList<Suggestion>();
			}
			else
			{
				newlistsuggestion.removeAll(newlistvotebank);
				int i=0;
				for(int a:newlistsuggestion)
				{
					for(int j=0;j<listSuggestion.size();j++) {
						if(a==listSuggestion.get(j).getId())
						{
							arraylist.add(listSuggestion.get(i));
						}
					}
					
				}
				return arraylist;
				
				
			}
			
		

		}
		
		return (ArrayList<Suggestion>) listSuggestion;
	
		 
	}
		
	
	//updating suggestion like/dislike
	
	@PatchMapping(path="/office/suggestion/update",produces= {"application/json"})
	  
	  public Suggestion updatesuggestion(int id,int vote,int userid)
	  {
		UserNoticeModel unm=new UserNoticeModel();

		  Optional<Suggestion> sugg=suggrepo.findById(id); 
		  Suggestion s=sugg.get(); 
		  if(vote==0) {
			  int a=s.getDownvote();
			  s.setDownvote(++a);
			  
		  }
		  else if(vote==1) {
			  int a=s.getUpvote();
			  s.setUpvote(++a);
		  }
		  unm.setSuggestionid(id);
		  unm.setUserid(userid);
		  unmr.save(unm);
		  
		   return suggrepo.save(s);
		  
	  }
	
	//delete all suggestions
	 
	@DeleteMapping("/office/suggestions")  
	public String deletesuggestions( )  
	{  
		suggrepo.deleteAll();
		return "Success";
		
	}
	
	//delete all notices

	@DeleteMapping("/office/usernotice")  
	public String deleteusernotice( )  
	{  
		unmr.deleteAll();
		return "Success";

	}
	
	//fetching all suggestions
	
	@RequestMapping(path="/office/suggestion/get",produces= {"application/json"})
	  public List<Suggestion> getallsuggestion() {
		List<Suggestion> list1=suggrepo.findAll();
		
		
		
		return list1;
		
	}
	
	//
	@RequestMapping(path="/office/usersugg/get",produces= {"application/json"})
	  public List<UserNoticeModel> getallusersuggestion() {
		List<UserNoticeModel> list1=unmr.findAll();
		
		
		
		return list1;
		
	}
	
	
	
	
	
	
	
	
				 
}

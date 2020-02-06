package com.ust.mail_simulation.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ust.mail_simulation.model.MailDTO;
import com.ust.mail_simulation.model.ModelDTO;
import com.ust.mail_simulation.service.MyService;

@RequestMapping("/")
@Component
public class MyController {
	@Autowired
	MyService ms;
	
	@RequestMapping("/registration")
	public String registerlink() {
		return "registration";
	}
	
	@RequestMapping(value="/registrationdata" ,method=RequestMethod.POST)
	public ModelAndView registerdata(@ModelAttribute ModelDTO dto,HttpServletRequest request) {
		boolean b=ms.register(dto,request);
		
		if(b) {
		return new ModelAndView("login","msg","Please Login");
		}else {
			return new ModelAndView("registration","msg","Registration Failed!");
		}
	}
	@RequestMapping("/login")
	public String loginlink() {
		return "login";
	}
	@RequestMapping(value="/logindata" ,method=RequestMethod.POST)
	public ModelAndView logindata(@ModelAttribute ModelDTO dto,HttpServletResponse response,HttpServletRequest request) {
		HttpSession session=request.getSession(false);
		String email=(String) session.getAttribute("email");
		boolean b=ms.login(dto,request);
		if(b) {
		return new ModelAndView("home","email",email);
		}else {
			return new ModelAndView("login","em","Login Failed");
			
		}
	
		
	
		
	}
	
	@RequestMapping("/compose")
	public ModelAndView composelink(HttpServletRequest request,MailDTO mail) {
		HttpSession session=request.getSession(false);
		String email=(String) session.getAttribute("email");
		if(email==null) {
		
		return new ModelAndView("login","msg","Login First");
		
		}else {
		String id=request.getParameter("id");
		 MailDTO mail1=null;
		if(id!=null) {
	    mail1=ms.draftdetail(request,mail);
		}
		return new ModelAndView("compose","m",mail1);
		}
		
	}
	@RequestMapping(value="/composedata",method=RequestMethod.POST)
	public ModelAndView composedata(MailDTO mail,HttpServletRequest request,ModelDTO dto,HttpSession session) {
		
		boolean b=ms.composedata(request,mail,dto,session);
		if(b) {
		return new ModelAndView("home","msg","Mail Sent!!");
		}else {
			return new ModelAndView("home","msg","Mail Delivery Failed!!");
		}
	}
	@RequestMapping("/draft")
	public ModelAndView draftlink(MailDTO mail,HttpServletResponse response,HttpSession session,HttpServletRequest request) {
		session=request.getSession(false);
		String email=(String) session.getAttribute("email");
		if(email==null) {
		
		return new ModelAndView("login","msg","Login First");
		
		}else {
		List<MailDTO> mlist=ms.draft(mail,response,session,request);
		return new ModelAndView("draft","mlist",mlist);
	   }
	}
	
	
	@RequestMapping("/sent")
	public ModelAndView sentlink(MailDTO mail,HttpServletResponse response,HttpSession session,HttpServletRequest request) {
		session=request.getSession(false);
		String email=(String) session.getAttribute("email");
	 List<MailDTO> mlist=ms.sent(mail,response,session,request);
	 if(email==null) {
			
			return new ModelAndView("login","msg","Login First");
			
			}else {
	 return new ModelAndView("sent","mlist",mlist);
			}
		
	}
	@RequestMapping("/inbox")
	public ModelAndView inboxlink(MailDTO mail,HttpServletResponse response,HttpSession session,HttpServletRequest request) {
		session=request.getSession(false);
		String email=(String) session.getAttribute("email");
		if(email==null) {
		
		return new ModelAndView("login","msg","Login First");
		
		}else {
			List<MailDTO> mlist=ms.inbox(mail,response,session,request);
			 return new ModelAndView("inbox","mlist",mlist);
			
		}
	}
	
	@RequestMapping("/logout")
	public ModelAndView logoutlink(HttpServletResponse response,HttpSession session,HttpServletRequest request) {
		boolean b=ms.logout(response,session,request);
		if(b) {
			return new ModelAndView("login","msg","Login First");
		}else {
			return new ModelAndView("login","msg","Login First");
		}
	}
	
	@RequestMapping("/change")
	public ModelAndView changelink(HttpServletRequest request) {
		HttpSession session=request.getSession(false);
		String email=(String) session.getAttribute("email");
		if(email==null) {
		
		return new ModelAndView("login","msg","Login First");
		
		}else {
		return new ModelAndView("change");
		}
		
	}
	
	@RequestMapping(value="/changedata" ,method=RequestMethod.POST)
	public ModelAndView changedata(ModelDTO dto,HttpServletResponse response,HttpSession session,HttpServletRequest request) {
		boolean b=ms.change(dto,response,session,request);
		if(b) {
			return new ModelAndView("login","msg","Password Set Successfully!");
		}else
			return new ModelAndView("change","msg","Enter Valid Email & Password!");
	}
	
	@RequestMapping("/forget")
	public String forgetlink() {
		return "forget";
		
	}
	
	@RequestMapping(value="/forgetdata" ,method=RequestMethod.POST)
	public ModelAndView forgetdata(ModelDTO dto,HttpServletResponse response,HttpSession session,HttpServletRequest request) {
		boolean b=ms.forget(dto,response,session,request);
		if(b) {
			return new ModelAndView("login","ms","Password Set Successfully!");
			
		}else
			return new ModelAndView("forget","msg","Enter Valid Answer!");
	}
	
	@RequestMapping("/delete")
	public ModelAndView deletelink(MailDTO mail,HttpServletRequest request) {
		
		boolean b=ms.delete(mail,request);
		if(b) {
			return new ModelAndView("home","msg","Deleted Successfully!!");
		}else {
			return new ModelAndView("home","msg","Deleted Successfully!!");
		}
		
	}
	
	@RequestMapping("/deleteitem")
	public ModelAndView deleteitemlink(MailDTO mail,HttpServletRequest request) {
		HttpSession session=request.getSession(false);
		String email=(String) session.getAttribute("email");
		if(email==null) {
		
		return new ModelAndView("login","msg","Login First");
		
		}else {
		List<MailDTO> mlist=ms.deleteitem(mail,request);
		return new ModelAndView("deleteitem","mlist",mlist);
		}
		
	}
	
	@RequestMapping("/permanentdelete")
	public ModelAndView permanentdeletelink(MailDTO mail,HttpServletRequest request) {
		
	     ms.permanentdelete(mail,request);
	
			return new ModelAndView("home","msg","Permanent Deleted!!");
		
		
	}


	@RequestMapping("/message")
	public ModelAndView messagelink(MailDTO mail,HttpServletRequest request) {
		
	     List<MailDTO> mlist=ms.message(mail,request);
	
			return new ModelAndView("message","mlist",mlist);
		
		
	}
	
	@RequestMapping("/composenormal")
	public ModelAndView composenormallink(HttpServletRequest request,MailDTO mail) {
		HttpSession session=request.getSession(false);
		String email=(String) session.getAttribute("email");
		if(email==null) {
		
		return new ModelAndView("login","msg","Login First");
		
		}else {
		
		return new ModelAndView("composenormal");
		}
	}
	
	@RequestMapping(value="/composedetail",method=RequestMethod.POST)
	public ModelAndView composedetail(MailDTO mail,HttpServletRequest request,ModelDTO dto,HttpSession session) {
		
		boolean b=ms.composedetail(request,mail,dto,session);
		if(b) {
		return new ModelAndView("home","msg","Mail Sent!!");
		}else {
			return new ModelAndView("home","msg","Mail Delivery Failed!!");
		}
	}
	

}

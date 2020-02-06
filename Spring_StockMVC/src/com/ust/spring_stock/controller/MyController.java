package com.ust.spring_stock.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ust.spring_stock.model.ModelDTO;
import com.ust.spring_stock.model.RegisterDTO;
import com.ust.spring_stock.service.MyService;

@RequestMapping("/")
@Component
public class MyController {
	@Autowired
	MyService ms;
	
	@RequestMapping("/addproduct")
	public ModelAndView addproduct(HttpServletRequest request) {
		HttpSession session=request.getSession(false);
		String email=(String) session.getAttribute("email");
		if(email==null) {
		
		return new ModelAndView("login","msg","Login First");
		
		}else {
		return new ModelAndView("addproduct");
		}
	}
	
	@RequestMapping(value="/stockdata",method=RequestMethod.POST)
	public ModelAndView stockdata(@ModelAttribute ModelDTO dto,HttpServletRequest request) {
		String pn=request.getParameter("productname");
		String c=request.getParameter("category");
		String com=request.getParameter("company");
		HttpSession session=request.getSession(false);
		String email=(String) session.getAttribute("email");
		if(email==null||pn.isEmpty()||c.isEmpty()||com.isEmpty()) {
		
		return new ModelAndView("login","msg","Login First");
		
		}else {
		boolean b=ms.stockdata(dto);
		if(b) {
			return new ModelAndView("addproduct");
		}else {
		return new ModelAndView("login","msg","Login First");
		}
		}
		
	}
	
	@RequestMapping("/fetchproduct")
	public ModelAndView fetchproduct(HttpServletRequest request) {
		HttpSession session=request.getSession(false);
		String email=(String) session.getAttribute("email");
		if(email==null) {
		
		return new ModelAndView("login","msg","Login First");
		
		}else {
		return new ModelAndView("searchproduct");
		}
	}
	
	@RequestMapping("/searchdata")
	public ModelAndView fetch(ModelDTO dto,HttpServletRequest request) {
	 List<ModelDTO> mlist=ms.fetchdata(dto,request);
	 return new ModelAndView("searchpage","mlist",mlist);
	}
	
	@RequestMapping("/registration")
	public String registerlink() {
		return "registration";
	}
	
	@RequestMapping(value="/registrationdata" ,method=RequestMethod.POST)
	public ModelAndView registerdata(@ModelAttribute RegisterDTO dto,HttpServletRequest request) {
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
	public ModelAndView logindata(@ModelAttribute RegisterDTO dto,HttpServletResponse response,HttpServletRequest request) {
		HttpSession session=request.getSession(false);
		String email=(String) session.getAttribute("email");
		boolean b=ms.login(dto,request);
		if(b) {
		return new ModelAndView("home","email",email);
		}else {
			return new ModelAndView("login","em","Login Failed");
			
		}
	}
	
	@RequestMapping("/modifyprod")
	public ModelAndView modifylink(ModelDTO dto,HttpServletRequest request) {
		HttpSession session=request.getSession(false);
		String email=(String) session.getAttribute("email");
		if(email==null) {
		
		return new ModelAndView("login","msg","Login First");
		
		}else {
			ModelDTO dto1=ms.modify(dto,request);
		return new ModelAndView("modifyproduct","m",dto1);
		}
	}
	
	@RequestMapping("/modifydata")
	public ModelAndView modifydata(ModelDTO dto,HttpServletRequest request) {
	 boolean b=ms.modifydata(dto,request);
	 if(b) {
	 return new ModelAndView("searchproduct","msg","Modify Successfully!!");
	 }else {
		 return new ModelAndView("searchproduct","msg","Modify Failed!!");
	 }
	}
	
	@RequestMapping("/addcart")
	public ModelAndView addcartlink(ModelDTO dto,HttpServletRequest request) {
		HttpSession session=request.getSession(false);
		String email=(String) session.getAttribute("email");
		if(email==null) {
		
		return new ModelAndView("login","msg","Login First");
		
		}else {
			ModelDTO dto1=ms.addcart(dto,request);
		return new ModelAndView("addcartpage","m",dto1);
		}
	}
	
	@RequestMapping("/addcartdata")
	public ModelAndView addcartdata(ModelDTO dto,HttpServletRequest request) {
	 List<ModelDTO> mlist=ms.addcartdata(dto,request);
	 return new ModelAndView("showcart","mlist",mlist);
	}
	
	@RequestMapping("/billdata")
	public ModelAndView billdata(ModelDTO dto,HttpServletRequest request) {
		HttpSession session=request.getSession(false);
		String email=(String) session.getAttribute("email");
		if(email==null) {
		
		return new ModelAndView("login","msg","Login First");
		
		}else {
	List<ModelDTO> mlist=ms.billdata(dto,request);
	 return new ModelAndView("bill","mlist",mlist);
		}
	}
	
	@RequestMapping("/removecart")
	public ModelAndView removecart(ModelDTO dto,HttpServletRequest request) {
		HttpSession session=request.getSession(false);
		String email=(String) session.getAttribute("email");
		if(email==null) {
		
		return new ModelAndView("login","msg","Login First");
		
		}else {
	 List<ModelDTO> mlist=ms.removecart(dto,request);
	 return new ModelAndView("showcart","mlist",mlist);
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
	

}

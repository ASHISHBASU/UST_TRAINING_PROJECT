package com.ust.mail_simulation.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ust.mail_simulation.DAO.MyDAO;
import com.ust.mail_simulation.model.MailDTO;
import com.ust.mail_simulation.model.ModelDTO;
@Component
public class MyServiceIMP implements MyService{
	
	@Autowired
	MyDAO mdao;

	@Override
	public boolean register(ModelDTO dto,HttpServletRequest request) {
		boolean b=mdao.register(dto,request);
		return b;
	}

	@Override
	public boolean login(ModelDTO dto, HttpServletRequest request) {
		return mdao.login(dto,request);
	}
	
	@Override
	public boolean composedata(HttpServletRequest request, MailDTO mail,ModelDTO dto,HttpSession session) {
		return mdao.composedata(request,mail,dto,session);
		
	}

	@Override
	public List<MailDTO> draft(MailDTO mail,HttpServletResponse response,HttpSession session,HttpServletRequest request) {
		return mdao.draft(mail,response,session,request);
		
	}

	@Override
	public List<MailDTO> sent(MailDTO mail, HttpServletResponse response, HttpSession session, HttpServletRequest request) {
		return mdao.sent(mail,response,session,request);
		
	}

	@Override
	public List<MailDTO> inbox(MailDTO mail, HttpServletResponse response, HttpSession session, HttpServletRequest request) {
		return mdao.inbox(mail,response,session,request);
		
	}

	@Override
	public boolean logout(HttpServletResponse response, HttpSession session, HttpServletRequest request) {
		return mdao.logout(response,session,request);
		
	}

	@Override
	public boolean change(ModelDTO dto, HttpServletResponse response, HttpSession session, HttpServletRequest request) {
		return mdao.change(dto,response,session,request);
		
	}

	@Override
	public boolean forget(ModelDTO dto, HttpServletResponse response, HttpSession session, HttpServletRequest request) {
		return mdao.forget(dto,response,session,request);
	}

	@Override
	public boolean delete(MailDTO mail, HttpServletRequest request) {
		return mdao.delete(mail,request);
		
	}

	@Override
	public List<MailDTO> deleteitem(MailDTO mail, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return mdao.deleteitem(mail, request);
	}

	@Override
	public void permanentdelete(MailDTO mail, HttpServletRequest request) {
		mdao.permanentdelete(mail,request);
		
	}

	@Override
	public List<MailDTO> message(MailDTO mail, HttpServletRequest request) {
		return mdao.message(mail,request);
		
	}

	@Override
	public MailDTO draftdetail(HttpServletRequest request, MailDTO mail) {
		return mdao.draftdetail(request,mail);
		
	}

	@Override
	public boolean composedetail(HttpServletRequest request, MailDTO mail, ModelDTO dto, HttpSession session) {
		return mdao.composedetail(request,mail,dto,session);
		
	}

	


}

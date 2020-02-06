package com.ust.mail_simulation.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ust.mail_simulation.model.MailDTO;
import com.ust.mail_simulation.model.ModelDTO;

public interface MyService {

	boolean register(ModelDTO dto,HttpServletRequest request);

	boolean login(ModelDTO dto, HttpServletRequest request);

	boolean composedata(HttpServletRequest request, MailDTO mail, ModelDTO dto, HttpSession session);

	List<MailDTO> draft(MailDTO mail, HttpServletResponse response, HttpSession session, HttpServletRequest request);

	List<MailDTO> sent(MailDTO mail, HttpServletResponse response, HttpSession session, HttpServletRequest request);

	List<MailDTO> inbox(MailDTO mail, HttpServletResponse response, HttpSession session, HttpServletRequest request);

	boolean logout(HttpServletResponse response, HttpSession session, HttpServletRequest request);

	boolean change(ModelDTO dto, HttpServletResponse response, HttpSession session, HttpServletRequest request);

	boolean forget(ModelDTO dto, HttpServletResponse response, HttpSession session, HttpServletRequest request);

	boolean delete(MailDTO mail, HttpServletRequest request);

	List<MailDTO> deleteitem(MailDTO mail, HttpServletRequest request);

	void permanentdelete(MailDTO mail, HttpServletRequest request);

	List<MailDTO> message(MailDTO mail, HttpServletRequest request);

	MailDTO draftdetail(HttpServletRequest request, MailDTO mail);

	boolean composedetail(HttpServletRequest request, MailDTO mail, ModelDTO dto, HttpSession session);

	


}

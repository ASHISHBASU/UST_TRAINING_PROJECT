package com.ust.mail_simulation.DAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ust.mail_simulation.model.MailDTO;
import com.ust.mail_simulation.model.ModelDTO;
@Component
public class MyDAOIMP implements MyDAO{
	
	@Autowired
	SessionFactory sf;
	
	@Override
	public boolean register(ModelDTO dto,HttpServletRequest request) {
		String pass=request.getParameter("password");
		String email=request.getParameter("email");
		Session ss=sf.openSession();
		Criteria cr=ss.createCriteria(ModelDTO.class);
		cr.add(Restrictions.eq("email", dto.getEmail()));
		ModelDTO mdto=(ModelDTO) cr.uniqueResult();
		if(mdto!=null||pass.isEmpty()||!email.contains("@gmail.com")) {
			return false;
		}
		else {
		ss.save(dto);        
		ss.beginTransaction().commit();
		ss.close();
		return true;
		}
	}

	@Override
	public boolean login(ModelDTO dto,HttpServletRequest request) {
		Session ss=sf.openSession();
		Criteria cr=ss.createCriteria(ModelDTO.class);
		cr.add(Restrictions.eq("email", dto.getEmail()));
		cr.add(Restrictions.eq("password", dto.getPassword()));
		ModelDTO mdto=(ModelDTO) cr.uniqueResult();
		ss.close();
		if(mdto!=null) {
			HttpSession session=request.getSession();
			session.setAttribute("email", dto.getEmail());
			return true;
		}
		return false;
		
	}
	
	@Override
	public boolean composedata(HttpServletRequest request, MailDTO mail,ModelDTO dto,HttpSession session) {
		Session ss=sf.openSession();
		String id=request.getParameter("id");
		int mid=Integer.parseInt(id);
		String tomail=request.getParameter("tomail");
		String subject=request.getParameter("subject");
		String text=request.getParameter("text");
		System.out.println(tomail);
		session=request.getSession(false);
		String frommail=(String) session.getAttribute("email");
			mail=new MailDTO();
			mail.setFrommail(frommail);
			mail.setSubject(subject);
			mail.setText(text);
			mail.setTomail(tomail);
			
			
			if(frommail!=null) {
			Criteria cr=ss.createCriteria(ModelDTO.class);
			cr.add(Restrictions.eq("email", tomail));
			ModelDTO dto1=(ModelDTO) cr.uniqueResult();
		
			
			if(tomail.isEmpty()||subject.isEmpty()||text.isEmpty()||dto1==null) {
				
				mail.setDid(1);
			}else {
				Query q=ss.createQuery("delete from MailDTO where mid=?");
				q.setParameter(0, mid);
				q.executeUpdate();
				mail.setDid(0);
			}
			
				
			
			
		    
			List<MailDTO> list=new LinkedList<>();
			list.add(mail);
			
			
			
			dto=new ModelDTO();
			Query qry=ss.createQuery("from ModelDTO where email=?");
			qry.setParameter(0, new String(frommail));
			dto=(ModelDTO) qry.uniqueResult();
			dto.setMlist(list);
			ss.save(dto);
			ss.beginTransaction().commit();
			
			Query qry1=ss.createQuery("update MailDTO set mlist_id=? where frommail=?");
			qry1.setParameter(0, new Integer(dto.getId()));
			qry1.setParameter(1, new String(dto.getEmail()));
			int row=qry1.executeUpdate();
			
			ss.close();
			
			if(dto1!=null) {
			return true;
			}else {
				return false;
			}
			}
			return false;
			
	}

	@Override
	public List<MailDTO> draft(MailDTO mail,HttpServletResponse response,HttpSession session,HttpServletRequest request) {
		response.setContentType("text/html");
			session=request.getSession(false);
			String frommail=(String) session.getAttribute("email");
		
		Session ss=sf.openSession();
		mail=new MailDTO();
		Criteria cr=ss.createCriteria(MailDTO.class);
		cr.add(Restrictions.eq("did", 1));
		cr.add(Restrictions.eq("frommail", frommail));
		List<MailDTO> mlist=cr.list();
		
		return mlist;
		
		
		
	}

	@Override
	public List<MailDTO> sent(MailDTO mail, HttpServletResponse response, HttpSession session, HttpServletRequest request) {
		response.setContentType("text/html");
		
		
			session=request.getSession(false);
			String frommail=(String) session.getAttribute("email");
		
		Session ss=sf.openSession();
		mail=new MailDTO();
		Criteria cr=ss.createCriteria(MailDTO.class);
		cr.add(Restrictions.eq("did", 0));
		cr.add(Restrictions.eq("frommail", frommail));
		List<MailDTO> mlist=cr.list();
		
		return mlist;
	
		
		
	}

	@Override
	public List<MailDTO> inbox(MailDTO mail, HttpServletResponse response, HttpSession session, HttpServletRequest request) {
		response.setContentType("text/html");
		
			session=request.getSession(false);
			String frommail=(String) session.getAttribute("email");
		
		Session ss=sf.openSession();
		mail=new MailDTO();
		Criteria cr=ss.createCriteria(MailDTO.class);
		cr.add(Restrictions.eq("did", 0));
		cr.add(Restrictions.eq("tomail", frommail));
		List<MailDTO> mlist=cr.list();
		return mlist;
		
		
		
		
	}

	@Override
	public boolean logout(HttpServletResponse response, HttpSession session, HttpServletRequest request) {
		PrintWriter pw;
		try {
			pw = response.getWriter();
		
		session=request.getSession(false);
		if(session!=null) {
			pw.print("LOGOUT SUCCESSFULLY   "+session.getAttribute("email"));
			session.invalidate();
			return true;
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}

	@Override
	public boolean change(ModelDTO dto, HttpServletResponse response, HttpSession session,
			HttpServletRequest request) {
		Session ss=sf.openSession();
		String email=request.getParameter("email");
		String oldpass=request.getParameter("oldpassword");
		String newpass=request.getParameter("newpassword");
		
		Criteria cr=ss.createCriteria(ModelDTO.class);
		cr.add(Restrictions.eq("email", email));
		cr.add(Restrictions.eq("password", oldpass));
		ModelDTO dto1=(ModelDTO) cr.uniqueResult();
		if(dto1!=null) {
		dto1.setPassword(newpass);
		ss.save(dto1);
		ss.beginTransaction().commit();
		ss.close();
		return true;
		}else {
			return false;
		}
		
		
		
	}

	@Override
	public boolean forget(ModelDTO dto, HttpServletResponse response, HttpSession session, HttpServletRequest request) {
		Session ss=sf.openSession();
		String email=request.getParameter("email");
		String fb=request.getParameter("fb");
		String newpass=request.getParameter("newpass");
		
		Criteria cr=ss.createCriteria(ModelDTO.class);
		cr.add(Restrictions.eq("email", email));
		cr.add(Restrictions.eq("fb", fb));
		ModelDTO dto1=(ModelDTO) cr.uniqueResult();
		if(dto1!=null) {
		dto1.setPassword(newpass);
		ss.save(dto1);
		ss.beginTransaction().commit();
		ss.close();
		return true;
		}else {
			return false;
		}
		
	}

	@Override
	public boolean delete(MailDTO mail, HttpServletRequest request) {
		Session ss=sf.openSession();
		String id=request.getParameter("id");
		int mid=Integer.parseInt(id);
		
		Criteria cr=ss.createCriteria(MailDTO.class);
		cr.add(Restrictions.eq("mid", mid));
		MailDTO dto1=(MailDTO) cr.uniqueResult();
		if(dto1!=null) {
	        dto1.setDid(2);
	        ss.save(dto1);
	        ss.beginTransaction().commit();
	        ss.close();
	        return true;
		}else {
			return false;
		}
	}

	@Override
	public List<MailDTO> deleteitem(MailDTO mail, HttpServletRequest request) {
		HttpSession session=request.getSession(false);
		String frommail=(String) session.getAttribute("email");
		Session ss=sf.openSession();
		mail=new MailDTO();
		Query qry=ss.createQuery("from MailDTO where frommail=? and did=? or tomail=? and did=?");
		qry.setParameter(0,new String(frommail));
		qry.setParameter(1,new Integer(2));
		qry.setParameter(2,new String(frommail));
		qry.setParameter(3,new Integer(2));
		List<MailDTO> mlist=qry.list();
		return mlist;
	}

	@Override
	public void permanentdelete(MailDTO mail, HttpServletRequest request) {
		String id=request.getParameter("id");
		int mid=Integer.parseInt(id);
		HttpSession session=request.getSession(false);
		String frommail=(String) session.getAttribute("email");
		Session ss=sf.openSession();
		mail=new MailDTO();
		Query qry=ss.createQuery("delete from MailDTO where frommail=? and mid=?");
		qry.setParameter(0, new String(frommail));
		qry.setParameter(1, new Integer(mid));
		int row=qry.executeUpdate();
		System.out.println(row);
	     ss.beginTransaction().commit();
		ss.close();
		
	}

	@Override
	public List<MailDTO> message(MailDTO mail, HttpServletRequest request) {
		String id=request.getParameter("id");
		int mid=Integer.parseInt(id);
		Session ss=sf.openSession();
		mail=new MailDTO();
		Criteria cr=ss.createCriteria(MailDTO.class);
		cr.add(Restrictions.eq("mid", mid));
		List<MailDTO> mlist=cr.list();
		ss.close();
		return mlist;	
	}

	@Override
	public MailDTO draftdetail(HttpServletRequest request, MailDTO mail) {
		String id=request.getParameter("id");
		int mid=Integer.parseInt(id);
		Session ss=sf.openSession();
		mail=new MailDTO();
		Criteria cr=ss.createCriteria(MailDTO.class);
		cr.add(Restrictions.eq("mid", mid));
		MailDTO mail1=(MailDTO) cr.uniqueResult();
		return mail1;
		
	}

	@Override
	public boolean composedetail(HttpServletRequest request, MailDTO mail,ModelDTO dto,HttpSession session) {
		Session ss=sf.openSession();
		String tomail=request.getParameter("tomail");
		String subject=request.getParameter("subject");
		String text=request.getParameter("text");
		System.out.println(tomail);
		session=request.getSession(false);
		String frommail=(String) session.getAttribute("email");
			mail=new MailDTO();
			mail.setFrommail(frommail);
			mail.setSubject(subject);
			mail.setText(text);
			mail.setTomail(tomail);
			
			
			if(frommail!=null) {
			Criteria cr=ss.createCriteria(ModelDTO.class);
			cr.add(Restrictions.eq("email", tomail));
			ModelDTO dto1=(ModelDTO) cr.uniqueResult();
		
			
			if(tomail.isEmpty()||subject.isEmpty()||text.isEmpty()||dto1==null) {
				
				mail.setDid(1);
			}
			 
			List<MailDTO> list=new LinkedList<>();
			list.add(mail);
			
			dto=new ModelDTO();
			Query qry=ss.createQuery("from ModelDTO where email=?");
			qry.setParameter(0, new String(frommail));
			dto=(ModelDTO) qry.uniqueResult();
			dto.setMlist(list);
			ss.save(dto);
			ss.beginTransaction().commit();
			
			Query qry1=ss.createQuery("update MailDTO set mlist_id=? where frommail=?");
			qry1.setParameter(0, new Integer(dto.getId()));
			qry1.setParameter(1, new String(dto.getEmail()));
			int row=qry1.executeUpdate();
			
			ss.close();
			
			if(dto1!=null) {
			return true;
			}else {
				return false;
			}
			}
			return false;
		
	}

	
		
	

	

}

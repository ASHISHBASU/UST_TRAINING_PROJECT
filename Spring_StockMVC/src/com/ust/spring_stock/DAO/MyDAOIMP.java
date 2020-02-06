package com.ust.spring_stock.DAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

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
import com.ust.spring_stock.model.ModelDTO;
import com.ust.spring_stock.model.RegisterDTO;
@Component
public class MyDAOIMP implements MyDAO{
    @Autowired
    SessionFactory sf;
	
	public boolean stockdata(ModelDTO dto) {
		
		Session ss=sf.openSession();
		
		if(dto!=null) {
		ss.save(dto);
		ss.beginTransaction().commit();
		ss.close();
		return true;
		}else {
			return false;
		}
		
	}
	
	@Override
	public boolean register(RegisterDTO dto,HttpServletRequest request) {
		String pass=request.getParameter("password");
		String email=request.getParameter("email");
		Session ss=sf.openSession();
		Criteria cr=ss.createCriteria(RegisterDTO.class);
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
	public boolean login(RegisterDTO dto,HttpServletRequest request) {
		Session ss=sf.openSession();
		Criteria cr=ss.createCriteria(RegisterDTO.class);
		cr.add(Restrictions.eq("email", dto.getEmail()));
		cr.add(Restrictions.eq("password", dto.getPassword()));
		RegisterDTO mdto=(RegisterDTO) cr.uniqueResult();
		ss.close();
		if(mdto!=null) {
			HttpSession session=request.getSession();
			session.setAttribute("email", dto.getEmail());
			return true;
		}
		return false;
		
	}

	@Override
	public List<ModelDTO> fetchdata(ModelDTO dto,HttpServletRequest request)  {
			
		String search=request.getParameter("searchproduct");
		
		Session ss=sf.openSession();
		Query qry=ss.createQuery("from ModelDTO where productname=? or category=? or company=?");
		qry.setParameter(0, new String(search));
		qry.setParameter(1, new String(search));
		qry.setParameter(2, new String(search));
		List<ModelDTO> mlist=qry.list();
		return mlist;
		
	}

	@Override
	public ModelDTO modify(ModelDTO dto, HttpServletRequest request) {
		String pid=request.getParameter("id");
		int id=Integer.parseInt(pid);
		Session ss=sf.openSession();
		Criteria cr=ss.createCriteria(ModelDTO.class);
		cr.add(Restrictions.eq("id", new Integer(id)));
		ModelDTO dto1=(ModelDTO) cr.uniqueResult();
		return dto1;
	}

	@Override
	public boolean modifydata(ModelDTO dto, HttpServletRequest request) {
		String pid=request.getParameter("id");
		int id=Integer.parseInt(pid);
		String productname=request.getParameter("productname");
		String category=request.getParameter("category");
		String company=request.getParameter("company");
		String price1=request.getParameter("price");
		double price=Double.parseDouble(price1);
		String quantity1=request.getParameter("quantity");
		int quantity=Integer.parseInt(quantity1);
		Session ss=sf.openSession();
		Criteria cr=ss.createCriteria(ModelDTO.class);
		cr.add(Restrictions.eq("id", new Integer(id)));
		ModelDTO dto1=(ModelDTO) cr.uniqueResult();
		if(dto1!=null) {
		dto1.setCategory(category);
		dto1.setCompany(company);
		dto1.setPrice(price);
		dto1.setProductname(productname);
		dto1.setQuantity(quantity);
		ss.save(dto1);
		ss.beginTransaction().commit();
		ss.close();
		return true;
		}else {
		return false;
		}
	}

	@Override
	public ModelDTO addcart(ModelDTO dto, HttpServletRequest request) {
		String pid=request.getParameter("id");
		int id=Integer.parseInt(pid);
		Session ss=sf.openSession();
		Criteria cr=ss.createCriteria(ModelDTO.class);
		cr.add(Restrictions.eq("id", new Integer(id)));
		ModelDTO dto1=(ModelDTO) cr.uniqueResult();
		return dto1;
	}

	@Override
	public List<ModelDTO> addcartdata(ModelDTO dto, HttpServletRequest request) {
		String pid=request.getParameter("id");
		int id=Integer.parseInt(pid);
		String addquantity1=request.getParameter("quantity");
		int addquantity=Integer.parseInt(addquantity1);
		Session ss=sf.openSession();
		Criteria cr=ss.createCriteria(ModelDTO.class);
		cr.add(Restrictions.eq("id", id));
		ModelDTO dto1=(ModelDTO) cr.uniqueResult();
		dto1.setAddquantity(addquantity);
		dto1.setAddcart(1);
		ss.save(dto1);
		ss.beginTransaction().commit();
		Criteria cr1=ss.createCriteria(ModelDTO.class);
		cr1.add(Restrictions.eq("addcart", 1));
		List<ModelDTO> mlist=cr1.list();
		return mlist;
	}

	@Override
	public List<ModelDTO> billdata(ModelDTO dto, HttpServletRequest request) {
		String[] bill=request.getParameterValues("bill");
		Session ss=sf.openSession();
		
		for(int i=0;i<bill.length;i++)
		{
			int a=Integer.parseInt(bill[i]);
		Criteria cr1=ss.createCriteria(ModelDTO.class);
		cr1.add(Restrictions.eq("id", a));
		ModelDTO dt=(ModelDTO) cr1.uniqueResult();
		dt.setQuantity(dt.getQuantity()-dt.getAddquantity());
		ss.save(dt);
		ss.beginTransaction().commit();
		}
		
		Criteria cr=ss.createCriteria(ModelDTO.class);
		cr.add(Restrictions.eq("addcart", 1));
		List<ModelDTO> mlist=cr.list();

		
		return mlist;
	}

	@Override
	public List<ModelDTO> removecart(ModelDTO dto, HttpServletRequest request) {
		Session ss=sf.openSession();
		String pid=request.getParameter("id");
		int id=Integer.parseInt(pid);
		Criteria cr=ss.createCriteria(ModelDTO.class);
		cr.add(Restrictions.eq("id", id));
		ModelDTO dto1=(ModelDTO) cr.uniqueResult();
		if(dto1!=null) {
			dto1.setAddcart(0);
			ss.save(dto1);
			ss.beginTransaction().commit();
		}
			Criteria cr1=ss.createCriteria(ModelDTO.class);
			cr1.add(Restrictions.eq("addcart", 1));
			List<ModelDTO> mlist=cr1.list();	
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
			
			e.printStackTrace();
		}
		return false;
		
	}

}

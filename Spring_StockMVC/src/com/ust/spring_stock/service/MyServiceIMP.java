package com.ust.spring_stock.service;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ust.spring_stock.DAO.MyDAO;
import com.ust.spring_stock.model.ModelDTO;
import com.ust.spring_stock.model.RegisterDTO;
@Component
public class MyServiceIMP implements MyService{
     @Autowired
     MyDAO mdao;
     
	@Override
	public boolean stockdata(ModelDTO dto) {
		return mdao.stockdata(dto);
		
	}

	@Override
	public List<ModelDTO> fetchdata(ModelDTO dto,HttpServletRequest request) {
		return mdao.fetchdata(dto,request);
		
	}

	@Override
	public boolean register(RegisterDTO dto, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return mdao.register(dto, request);
	}

	@Override
	public boolean login(RegisterDTO dto, HttpServletRequest request) {
	
		return mdao.login(dto, request);
	}

	@Override
	public ModelDTO modify(ModelDTO dto, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return mdao.modify(dto,request);
	}

	@Override
	public boolean modifydata(ModelDTO dto, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return mdao.modifydata(dto,request);
	}

	@Override
	public ModelDTO addcart(ModelDTO dto, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return mdao.addcart(dto,request);
	}

	@Override
	public List<ModelDTO> addcartdata(ModelDTO dto, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return mdao.addcartdata(dto,request);
	}

	@Override
	public List<ModelDTO> billdata(ModelDTO dto, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return mdao.billdata(dto,request);
	}

	@Override
	public List<ModelDTO> removecart(ModelDTO dto, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return mdao.removecart(dto,request);
	}

	@Override
	public boolean logout(HttpServletResponse response, HttpSession session, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return mdao.logout(response, session, request);
	}

}

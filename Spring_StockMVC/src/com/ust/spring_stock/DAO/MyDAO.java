package com.ust.spring_stock.DAO;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ust.spring_stock.model.ModelDTO;
import com.ust.spring_stock.model.RegisterDTO;

public interface MyDAO {
	boolean stockdata(ModelDTO dto);

	boolean register(RegisterDTO dto, HttpServletRequest request);

	boolean login(RegisterDTO dto, HttpServletRequest request);

	List<ModelDTO> fetchdata(ModelDTO dto, HttpServletRequest request);

	ModelDTO modify(ModelDTO dto, HttpServletRequest request);

	boolean modifydata(ModelDTO dto, HttpServletRequest request);

	ModelDTO addcart(ModelDTO dto, HttpServletRequest request);

	List<ModelDTO> addcartdata(ModelDTO dto, HttpServletRequest request);

	List<ModelDTO> billdata(ModelDTO dto, HttpServletRequest request);

	List<ModelDTO> removecart(ModelDTO dto, HttpServletRequest request);

	boolean logout(HttpServletResponse response, HttpSession session, HttpServletRequest request);

}

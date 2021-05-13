package com.Servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.dao.PurchaseDao;

/**
 * Servlet implementation class ProductPurchaseAPI
 */
@WebServlet("/ProductPurchaseAPI")
public class ProductPurchaseAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static PurchaseDao daoObj;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductPurchaseAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		daoObj = new PurchaseDao();
		
		String output = daoObj.insertProductPurchaseItems(
				Date.valueOf(request.getParameter("date")),
				Double.parseDouble(request.getParameter("total")));
				response.getWriter().write(output);
				
	}
	
	
	
	private static Map getParasMap(HttpServletRequest request)
	{
	 Map<String, String> map = new HashMap<String, String>();
	try
	 {
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
	 String queryString = scanner.hasNext() ?
	 scanner.useDelimiter("\\A").next() : "";
	 scanner.close();
	 String[] params = queryString.split("&");
	 for (String param : params)
	 { 
	
	String[] p = param.split("=");
	 map.put(p[0], p[1]);
	 }
	 }
	catch (Exception e)
	 {
	 }
	return map;
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		daoObj = new PurchaseDao();
		Map paras = getParasMap(request);
		 String output = daoObj.updateProductPurchaseItems(
	Integer.parseInt(paras.get("hidItemIDSave").toString())			 ,
		 Date.valueOf(paras.get("date").toString()),
		 Double.parseDouble(paras.get("total").toString())
		);
		response.getWriter().write(output);
		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		daoObj = new PurchaseDao();
		Map paras = getParasMap(request);
		 String output = daoObj.deleteProductPurchaseItems(Integer.parseInt(paras.get("id").toString()));
		response.getWriter().write(output);
	}

}

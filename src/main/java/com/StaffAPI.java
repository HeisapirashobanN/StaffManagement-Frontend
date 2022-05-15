package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/StaffAPI")
public class StaffAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	staff staffObj = new staff();

	//convert request parameters to a map
	private static Map<String, String> getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();

		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();

			String[] params = queryString.split("&");

			for (String param : params) {
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {

		}

		return map;
	}
  
    public StaffAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output = staffObj.insertStaff(	request.getParameter("name"),
				request.getParameter("title"),
				request.getParameter("mail"),
				request.getParameter("contact"),
				request.getParameter("gender"));
				
//sending the output to client
response.getWriter().write(output);
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<?, ?> paras = getParasMap(request);

		//getting values from the map and sending to update function
		String output = staffObj.updateStaff(	paras.get("hidStaffIDSave").toString(),
											paras.get("name").toString(),
											paras.get("title").toString(),
											paras.get("mail").toString(),
											paras.get("contact").toString(),
											paras.get("gender").toString());
		
		//sending the output to client
		response.getWriter().write(output);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String, String> paras = getParasMap(request);

		//getting values from the map and sending to delete function
		String output = staffObj.deleteStaff(paras.get("StaffId").toString());
		
		//sending the output to client
		response.getWriter().write(output);
	}

}

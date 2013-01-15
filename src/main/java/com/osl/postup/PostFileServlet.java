package com.osl.postup;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class PostFileServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		postfile(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		postfile(request, response);
	}
	
	private void postfile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		String pathInfo = request.getPathInfo().substring(1);
		
		if (pathInfo.isEmpty()) {
			pathInfo = "readme";
		}
		
		String data = request.getParameter("data");
		if (data != null) {
			FileWriter fileWriter = new FileWriter(getPath(pathInfo));
			try {
				fileWriter.write(data);
			} finally {
				fileWriter.close();
			}
		}
		
		File file = new File(getPath(pathInfo));
		if (file.isFile()) {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			try {
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					writer.println(line);
				}
			} finally {
				bufferedReader.close();
			}
		} else {
			response.sendError(404, "Post '" + pathInfo + "' does not exist");
		}
	}

	private String getPath(String pathInfo) {
		return getServletContext().getRealPath("posts/" + pathInfo + ".txt");
	}
	
}

package com.wrox;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class HelloServlet extends HttpServlet
{
	private static final String DEFAULT_USER = "Guest";

	@Override
	public void init() throws ServletException
	{
		String resource = "mybatis-config.xml";
		InputStream inputStream = null;
		try
		{
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String user = request.getParameter("user");
		if (user == null)
			user = HelloServlet.DEFAULT_USER;
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		writer.append("<!DOCTYPE html>\r\n").append("<html>\r\n").append(" <head>\r\n")
				.append(" <title>Hello User Application</title>\r\n").append(" </head>\r\n").append(" <body>\r\n")
				.append(" Hello, ").append(user).append("!<br/><br/>\r\n")
				.append(" <form action=\"greeting\" method=\"POST\">\r\n").append(" Enter your name:<br/>\r\n")
				.append(" <input type=\"text\" name=\"user\"/><br/>\r\n")
				.append(" <input type=\"submit\" value=\"Submit\"/>\r\n").append(" </form>\r\n").append(" </body>\r\n")
				.append("</html>\r\n");
	}
}
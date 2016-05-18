package com.rest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class JettyDefaultHandler extends AbstractHandler{
public static void main(String[] args) throws Exception {
	Server server = new Server(9080);
	
	server.setHandler(new JettyDefaultHandler());
	server.start();
	server.join();
}

@Override
public void handle(String arg0, Request arg1, HttpServletRequest arg2, HttpServletResponse arg3)
		throws IOException, ServletException {
	arg3.setContentType(MediaType.TEXT_HTML);
	arg3.setStatus(HttpServletResponse.SC_OK);;
	arg1.setHandled(true);
	arg3.getWriter().println("HelloWorld");
}
}

package com.rest;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class JettyServletHandler {
	public static void main(String[] args) throws Exception {
		Server server = new Server(7080);

		ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		servletContextHandler.setContextPath("/test");
		servletContextHandler.addServlet(new ServletHolder(new HelloServlet()), "/*");
		servletContextHandler.addServlet(new ServletHolder(new HelloServlet("Italy")), "/it/*");
		
		server.setHandler(servletContextHandler);
		
		
		server.start();
		server.join();
	}
}

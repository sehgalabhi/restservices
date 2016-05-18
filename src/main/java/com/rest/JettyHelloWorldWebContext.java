package com.rest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class JettyHelloWorldWebContext {
	public static void main(String[] args) throws Exception {
		JettyHelloWorldWebContext helloWorld = new JettyHelloWorldWebContext();
		helloWorld.run();
	}

	private void run() throws Exception {
		Server server = new Server(8080);

		WebAppContext webAppContext = new WebAppContext();
		webAppContext.setContextPath("/rest");
		webAppContext.setDescriptor(webAppContext + "/WEB-INF/web.xml");
		webAppContext.setResourceBase("../simplerest/src/main/webapp");
		webAppContext.setParentLoaderPriority(true);
		server.setHandler(webAppContext);

		server.start();
		server.join();

	}

	
}

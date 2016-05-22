package com.rest;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.glassfish.jersey.servlet.ServletContainer;

public class JettyRestHandler {

	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);

		ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
		contextHandler.setContextPath("/rest");

		ServletHolder servletHolder = contextHandler.addServlet(ServletContainer.class, "/*");
		
		servletHolder.setInitParameter("javax.ws.rs.Application", DepartmentConfig.class.getCanonicalName());

	//	servletHolder.setInitParameter(		"jersey.config.server.provider.classnames",	DepartmentService.class.getCanonicalName());

		
		WebAppContext appContext = new WebAppContext();
		appContext.setContextPath("/angular");
		appContext.setResourceBase("../simplerest/src/main/webapp");
		
		
		ContextHandlerCollection collection =new ContextHandlerCollection();
		collection.addHandler(contextHandler);
		collection.addHandler(appContext);
		
		server.setHandler(collection);
		server.start();
		server.join();
	}
}

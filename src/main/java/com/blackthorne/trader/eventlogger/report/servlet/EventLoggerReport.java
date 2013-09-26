package com.blackthorne.trader.eventlogger.report.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.core.framework.IPlatformContext;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.core.framework.PlatformServletContext;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.EngineConstants;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.HTMLServerImageHandler;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;

/**
 * Servlet implementation class EventLoggerReport
 */
public class EventLoggerReport extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(EventLoggerReport.class);
	private static IReportEngine birtReportEngine = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EventLoggerReport() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void destroy() {
		if (birtReportEngine != null) {
			birtReportEngine.destroy();
			Platform.shutdown();
			birtReportEngine = null;
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc = request.getSession().getServletContext();
		String reportName = request.getParameter("report");
		String format = request.getParameter("format");
		
		
		
		getBirtEngine();
		
		IReportRunnable design = null;
		try {
			//Open report design
			design = birtReportEngine.openReportDesign( sc.getRealPath("/reports") + "/" + reportName + ".rptdesign");
			//create task to run and render report
			IRunAndRenderTask task = birtReportEngine.createRunAndRenderTask( design );		
			//set output options
			HTMLRenderOption options = new HTMLRenderOption();
	        //set the image handler to a HTMLServerImageHandler if you plan on using the base image url.
	        options.setImageHandler(new HTMLServerImageHandler());
	        if (format.equalsIgnoreCase("html")) {
				response.setContentType("text/html");
				options.setOutputFormat(HTMLRenderOption.OUTPUT_FORMAT_HTML);
			} else {
				response.setContentType("application/pdf");
				options.setOutputFormat(HTMLRenderOption.OUTPUT_FORMAT_PDF);
			}
			
			//options.setOutputFormat(HTMLRenderOption.OUTPUT_FORMAT_PDF);
			options.setOutputStream(response.getOutputStream());
			options.setBaseImageURL(request.getContextPath()+"/images");
			options.setImageDirectory(sc.getRealPath("/images"));
			task.setRenderOption(options);
			
			//run report
			task.run();
			task.close();
		} catch (Exception e) {
			LOG.error("Error while running the report", e);
			throw new ServletException(e);
		}
	}

	private void getBirtEngine() {
		if (birtReportEngine == null) {
			EngineConfig config = new EngineConfig();
			IPlatformContext context = new PlatformServletContext(
					getServletContext());

			config.getAppContext().put(
					EngineConstants.APPCONTEXT_CLASSLOADER_KEY,
					Thread.currentThread().getContextClassLoader());
			config.setBIRTHome("");
			config.setPlatformContext(context);

			try {
				Platform.startup(config);
			} catch (BirtException e) {
				LOG.error("Error starting up BIRT Platform", e);
				return;
			}

			IReportEngineFactory factory = (IReportEngineFactory) Platform
					.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);

			birtReportEngine = factory.createReportEngine(config);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

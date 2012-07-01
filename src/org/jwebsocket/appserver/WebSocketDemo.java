//	---------------------------------------------------------------------------
//	jWebSocket - Copyright (c) 2010 Innotrade GmbH
//	---------------------------------------------------------------------------
//	This program is free software; you can redistribute it and/or modify it
//	under the terms of the GNU Lesser General Public License as published by the
//	Free Software Foundation; either version 3 of the License, or (at your
//	option) any later version.
//	This program is distributed in the hope that it will be useful, but WITHOUT
//	ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
//	FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for
//	more details.
//	You should have received a copy of the GNU Lesser General Public License along
//	with this program; if not, see <http://www.gnu.org/licenses/lgpl.html>.
//	---------------------------------------------------------------------------
package org.jwebsocket.appserver;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jwebsocket.api.WebSocketPacket;
import org.jwebsocket.factory.JWebSocketFactory;
import org.jwebsocket.kit.WebSocketServerEvent;
import org.jwebsocket.listener.WebSocketServerTokenEvent;
import org.jwebsocket.listener.WebSocketServerTokenListener;
import org.jwebsocket.logging.Logging;
import org.jwebsocket.server.TokenServer;
import org.jwebsocket.token.Token;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.games.catan.framework.RequestProcess;

/**
 *
 * @author aschulze
 */
public class WebSocketDemo extends HttpServlet implements WebSocketServerTokenListener {
	private static WebApplicationContext springContext;
	private static final long serialVersionUID = 1L;
	private static Logger mLog = null;
	public static WebApplicationContext getStringContext(){
		return springContext;
	}
	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 * @param aRequest servlet request
	 * @param aResponse servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws ServletException, IOException {
		aResponse.setContentType("text/plain;charset=UTF-8");
		PrintWriter lOut = aResponse.getWriter();

		try {
			lOut.println("This session: " + aRequest.getSession().getId());
			lOut.println("Http sessions: " + WebSocketHttpSessionMerger.getHttpSessionsCSV());
			lOut.println("WebSocket sessions: " + WebSocketHttpSessionMerger.getWebSocketSessionsCSV());
		} finally {
			lOut.close();
		}
	}

	@Override
	public void init() {
		mLog = Logging.getLogger(WebSocketDemo.class);
		mLog.info("Adding servlet '" + getClass().getSimpleName() + "' to WebSocket listeners...");
		TokenServer lServer = JWebSocketFactory.getTokenServer();
		if (lServer != null) {
			lServer.addListener(this);
		}
	}

	@Override
	public void processOpened(WebSocketServerEvent aEvent) {
		mLog.info("Opened WebSocket session: " + aEvent.getSession().getSessionId());
		// if a new web socket connection has been started,
		// update the session tables accordingly
		WebSocketHttpSessionMerger.addWebSocketSession(aEvent.getSession());
	}

	@Override
	public void processPacket(WebSocketServerEvent aEvent, WebSocketPacket aPacket) {
		mLog.info("Received WebSocket packet: " + aPacket.getASCII());
	}

	@Override
	public void processToken(WebSocketServerTokenEvent aEvent, Token aToken) {
		mLog.info("Received WebSocket token: " + aToken.toString());
		String type = aToken.getType();
		if(type.equals("catan")){
			//new RequestProcess().catanProcessRequest(aEvent, aToken);
			RequestProcess requestProcess = (RequestProcess) springContext.getBean("OperateProcess");
			requestProcess.catanProcessRequest(aEvent, aToken);
		}
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext sc = config.getServletContext();
		springContext = WebApplicationContextUtils.getWebApplicationContext(sc);
		super.init(config);
	}

	@Override
	public void processClosed(WebSocketServerEvent aEvent) {
		mLog.info("Closed WebSocket session: " + aEvent.getSession().getSessionId());
		// if a web socket connection has been terminated,
		// update the session tables accordingly
		WebSocketHttpSessionMerger.removeWebSocketSession(aEvent.getSession());
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 * @param aRequest servlet request
	 * @param aResponse servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws ServletException, IOException {
		processRequest(aRequest, aResponse);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 * @param aRequest servlet request
	 * @param aResponse servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws ServletException, IOException {
		processRequest(aRequest, aResponse);
	}

	/**
	 * Returns a short description of the servlet.
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>
}

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

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.apache.log4j.Logger;
import org.jwebsocket.logging.Logging;

/**
 * Web application lifecycle listener.
 * Here the http session is added or removed respectively from the
 * global WebSocketHttpSessionMerger.
 * @author aschulze
 */
public class SessionListener implements HttpSessionListener {

	private static Logger mLog = null;

	private void mCheckLogs() {
		if (mLog == null) {
			mLog = Logging.getLogger(SessionListener.class);
		}
	}

	@Override
	public void sessionCreated(HttpSessionEvent aHSE) {
		// when a new session is created by the servlet engine
		// add this session to the global WebSockethttpSessionMerger.
		WebSocketHttpSessionMerger.addHttpSession(aHSE.getSession());
		mCheckLogs();
		mLog.info("Created Http session: '" + aHSE.getSession().getId() + "'");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent aHSE) {
		// when an existing session is destroyed by the servlet engine
		// remove this session from the global WebSockethttpSessionMerger.
		WebSocketHttpSessionMerger.removeHttpSession(aHSE.getSession());
		mCheckLogs();
		mLog.info("Destroyed Http session: '" + aHSE.getSession().getId() + "'");
	}
}

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Language" content="en">
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252">
<title>Welcome To Catan Game</title>
<meta http-equiv="X-UA-Compatible" content="chrome=1">

<link rel="stylesheet" type="text/css" href="style/catan.css"
	media="all" />
</head>
<body onload="catan.jws.initPage();" onunload="catan.jws.exitPage();" >
	<div id="loginAndRoomDIV"
		style="margin-left: auto; margin-right: auto; text-align: center;">
		<div id="pageInfo"
			style="margin-left: auto; margin-right: auto; text-align: center;">

		</div>
		<div
			style="margin-left: auto; margin-right: auto; text-align: center;">
			<div id="loginDIV">
				<table border="1" align="center">
					<tr>
						<th colspan="2">CATAN Login</th>
					</tr>
					<tr>
						<td>name</td>
						<td><input type="text" name="userName" id="userName" />
						</td>
					</tr>
					<tr>
						<td>password</td>
						<td><input type="password" name="password" id="password" />
						</td>
					</tr>
					<tr>
						<td></td>
						<td><input type="button" value="Logon"
							onclick="catan.userLoginout.catanLogon();" />
						</td>
					</tr>
				</table>
			</div>
			<div id="roomListDIV" style="display: none;"></div>
			<hr />
			<div id="receiveInfo"
				style="margin-left: auto; margin-right: auto; text-align: left;height:300px; background-color:burlywood; overflow:auto;">
			</div>
		</div>
	</div>
	<script type="text/javascript" src="jsWebsocket/framework/jWebSocket.js"></script>
	<script type="text/javascript" src="jsWebsocket/framework/jwsSamplesPlugIn.js"></script>
	<script type="text/javascript" src="jsWebsocket/framework/catan.js"></script>
	<%@ include file="index.html" %>
	<script type="text/javascript" src="jsWebsocket/waitingRoom.js"></script>
	<script type="text/javascript" src="jsWebsocket/userLoginout.js"></script>
	<script type="text/javascript" src="jsWebsocket/gameRoom.js"></script>
	<script type="text/javascript" src="jsWebsocket/gameRoom.show.js"></script>
	<script type="text/javascript" src="jsWebsocket/noticeConfig.js"></script>
</body>
</html>
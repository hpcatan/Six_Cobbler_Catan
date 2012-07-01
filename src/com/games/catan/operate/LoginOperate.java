package com.games.catan.operate;

import java.util.Iterator;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import com.games.catan.Const.ConstConfigFileData;
import com.games.catan.Const.ConstConnectorData;
import com.games.catan.exception.CatanLogicException;
import com.games.catan.framework.ClientStatus;
import com.games.catan.pojo.User;
import com.games.catan.service.ClientStatusService;
import com.games.catan.service.UserService;
import com.games.catan.service.WaitingRoomService;

public class LoginOperate extends BaseOperate {
	private UserService userService;
	private ClientStatusService clientStatusService;
	private WaitingRoomService waitingRoomService;
	
	public void setWaitingRoomService(WaitingRoomService waitingRoomService) {
		this.waitingRoomService = waitingRoomService;
	}
	public UserService getUserService() {
		return userService;
	}
	public ClientStatusService getClientStatusService() {
		return clientStatusService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setClientStatusService(ClientStatusService clientStatusService) {
		this.clientStatusService = clientStatusService;
	}
	@Override
	public void process()throws JSONException, CatanLogicException {
		JSONObject jsonObject = websocketTool.getRequestJSONObject();
		String loginName = jsonObject.getString("loginName");
		String loginPassword = jsonObject.getString("loginPassword");

		if (loginName == null || loginName.length() == 0) {
			throw new CatanLogicException(
					"Exception when doLogin: loginName is invalid. loginName = "
							+ loginName + ".");
		}

		Set<User> userSet = userService.getAllUsers();
		Iterator<User> userIterator = userSet.iterator();
		while (userIterator.hasNext()) {
			User user = userIterator.next();
			if (user.getName().equals(loginName)) {
				String message = "The LoginName=" + loginName + " is already in Catan Game.";
				throw new CatanLogicException(message);
			}
		}

		String currentConnectorId = websocketTool.getCurrentConnectorId();
		ClientStatus clientStatus = clientStatusService.getClientStatusByConnectorId(currentConnectorId);
		User user = new User();
		user.setId(currentConnectorId);
		user.setName(loginName);
		user.setPassword(loginPassword);
		clientStatus.setUser(user);
		this.waitingRoomService.addUserInWaitingRoom(user);
		JSONObject responseJsonObject = websocketTool.getResponseJSONObject();
		responseJsonObject.put(ConstConnectorData.RESPONSE_TYPE_NAME, ConstConnectorData.RESPONSE_TYPE_VALUE_SUCCESS);
		responseJsonObject.put(ConstConnectorData.RESPONSE_MESSAGE_NAME, user.getName()+" login successfully.");
		websocketTool.sendJsonObject(responseJsonObject);
		this.addNextStatusIdByCurrentUser(ConstConfigFileData.OPERATE_SUCCESS_TO);
	}

}

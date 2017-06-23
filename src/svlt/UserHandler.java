package svlt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class UserHandler {
	Map<String, String> users;
	Map<String, String> usersInfo;
	List<User> usersInfos;

	public UserHandler() {
		// 给users赋初值
		users = new HashMap<String, String>();
		users.put("qbq", "123456");
		users.put("bqb", "123");
		users.put("bbq", "1234");
		usersInfo = new HashMap<String, String>();
		usersInfo.put("钱兵桥", "24岁,软件工程师");
		usersInfo.put("qbq", "22岁,Enigneer");
		usersInfo.put("bqb", "26岁,Doctor");
		usersInfos = new ArrayList<User>();
		usersInfos.add(new User("钱兵桥", "24岁", "软件工程师"));
		usersInfos.add(new User("qbq", "22岁", "Enigneer"));
		usersInfos.add(new User("bqb", "26岁", "Doctor"));
	}

	public Map<String, String> getUsers() {
		return users;
	}

	public void setUsers(Map<String, String> users) {
		this.users = users;
	}

	/**
	 * 
	 * @param user
	 * @param password
	 * @return
	 */
	public boolean doLogin(String user, String password) {
		if (user == null || password == null) {
			return false;
		}
		Set<Entry<String, String>> map = users.entrySet();
		for (Map.Entry<String, String> entrys : map) {
			if (user.equals(entrys.getKey()) && password.equals(entrys.getValue())) {
				return true;
			}
		}
		// if(users.containsKey(user) && password.equals(users.get(user))){
		// return true;
		// }
		return false;
	}

	public boolean queryUserInfo(String userName) {
		if (usersInfo.containsKey(userName)) {
			return true;
		}
		return false;
	}

	/**
	 * 根据Map<K,V> 针对K来查询
	 * 
	 * @param userName
	 * @return
	 */
	public String getUserInfo(String userName) {
		String info = "";
		info = userName + "," + usersInfo.get(userName);
		return info;
	}

	/**
	 * 查询全部
	 * 
	 * @return
	 */
	public List<User> getAllUser() {
		List<User> list = new ArrayList<User>();
		User users = null;
		for (User user : usersInfos) {
			users = new User();
			users.setName(user.getName());
			users.setAge(user.getAge());
			user.setGander(user.getGander());
			list.add(user);
		}
		return list;
	}
}

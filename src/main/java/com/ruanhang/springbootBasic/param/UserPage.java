package com.ruanhang.springbootBasic.param;
/** 
 * @author  阮航  
 * @date 创建时间：2017年11月24日 下午4:32:34 
 * @version 1.0 
*/
public class UserPage extends PageParam{

	private String userName;
    private String userSex;
	
    public String getUserName() {
		return userName;
	}
	
    public void setUserName(String userName) {
		this.userName = userName;
	}
	
    public String getUserSex() {
		return userSex;
	}
	
    public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
    
    
}

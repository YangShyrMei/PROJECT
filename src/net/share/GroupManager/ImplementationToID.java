package net.share.GroupManager;

import java.util.HashMap;
import java.util.Map.Entry;

public class ImplementationToID extends IDandPasswords{

    public void addIDPassword(String ID, String password){
        logininfo.put(ID,password);
    }

    protected HashMap getLoginInfo() {
		return logininfo;
	}
	public void showMap(){
		for (Entry<String, String> entry : logininfo.entrySet()){
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
	}
    
    public boolean isPresent(String k){
        if(logininfo.containsKey(k))
        {
            return true;
        }
        return false;
    }
    
}

package net.share.loginSystem;

import java.util.HashMap;
import java.util.Map.Entry;

public class IDandPasswords {
	public static HashMap<String,String> logininfo= new HashMap<String,String>();
	IDandPasswords(){
		logininfo.put("shyrmei","12345");
		logininfo.put("pragati","12345");
		logininfo.put("meghana","12345");
		logininfo.put("geetha","12345"); 
		logininfo.put("dipika","12345");
		}
	protected HashMap getLoginInfo() {
		return logininfo;
	}
	public void showMap(){
		for (Entry<String, String> entry : logininfo.entrySet()){
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
	}

}

package net.share.GroupManager;

import java.util.HashMap;
import java.util.Map.Entry;

public class IPAddressOfClients extends HashMapOfIP{
    
   
    public void addIPAddress(String clientID,String IP){
        IPAddress.put(clientID,IP);
    }

    public HashMap getIP(){
        return IPAddress;
    }
    public static void showMap(){
		for (Entry<String, String> entry : IPAddress.entrySet()){
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
	}
 public static void main(String args[]){
    IPAddressOfClients cip = new IPAddressOfClients();
    cip.showMap();
 }
     
}

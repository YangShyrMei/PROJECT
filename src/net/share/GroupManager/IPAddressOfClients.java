package net.share.GroupManager;

import java.util.HashMap;

public class IPAddressOfClients extends HashMapOfIP{
    
   
    public void addIPAddress(String clientID,String IP){
        IPAddress.put(clientID,IP);
    }

    public HashMap getIP(){
        return IPAddress;
    }

     
}

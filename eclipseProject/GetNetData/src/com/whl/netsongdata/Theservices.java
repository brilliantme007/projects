package com.whl.netsongdata;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService
public class Theservices {
	
	public String  getMessage(String msg){
		return msg;
	}
	
	 public static void  main(String[] args){  
	        Endpoint.publish("http://localhost:9001/Service/getMessage",new Theservices());  
	        System.out.println("Publish Success~");  
	    }
}

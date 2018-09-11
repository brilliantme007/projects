package com.whl.netsongdata.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;

public class ChatServer {
	private final static int PORT=123456;
	
	public static void main(String[] args) {
		try {
			DatagramSocket socket = new DatagramSocket(PORT);
			while(true){
				DatagramPacket request = new DatagramPacket(new byte[1024], 1024);
				socket.receive(request);
				
				String daytime=new  Date().toString();
				byte[] data = daytime.getBytes("ASCII");
				DatagramPacket response = new DatagramPacket(data, data.length);
				
				socket.send(response);
				System.out.println(daytime+" "+request.getAddress());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

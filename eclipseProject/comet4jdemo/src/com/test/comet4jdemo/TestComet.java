package com.test.comet4jdemo;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.comet4j.core.CometContext;
import org.comet4j.core.CometEngine;

public class TestComet implements ServletContextListener {
	
	
	 // 频道1  
    private static final String CHANNEL1 = "res1";  
    // 频道2  
    private static final String CHANNEL2 = "res2";  
  
    // 通过频道1推送给前台的变量1  
    private static int number1 = 0;  
    // 通过频道2推送给前台的变量2  
    private static int number2 = 100; 

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		 // CometContext ： Comet4J上下文，负责初始化配置、引擎对象、连接器对象、消息缓存等。
		CometContext cometContext=CometContext.getInstance();
		//注册频道,即标识那些字段可用到当前的频道,用来作为向前台传送数据的通道
		cometContext.registChannel(CHANNEL1);
		cometContext.registChannel(CHANNEL2);
		Thread myThread=new Thread(new SendToClientThread(),"SendToClientThread");
		myThread.setDaemon(true);
		myThread.start();
	}
	class SendToClientThread implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//CometEngine: 引擎,负责管理和维持链接,并能够必要的发送服务
				CometEngine cometEngine=CometContext.getInstance().getEngine();
				//参数意思,通过你什么频道发送什么数据,前台可用频道的值获取到发送的数据
				cometEngine.sendToAll(CHANNEL1,number1++);
				cometEngine.sendToAll(CHANNEL2,number2++);
			}
		}
		
	}
}

package com.example.websocket;

import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@ServerEndpoint("/websocket/{name}")
public class WebSocketService {
	
	private static final Logger log = LoggerFactory.getLogger(WebSocketService.class);
	
	//concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketService> webSocketSet = new CopyOnWriteArraySet<WebSocketService>();

	private Session session;
	
	private String name;
	
	@OnOpen
	public void onOpen(@PathParam("name") String name, Session session) {
		this.session = session;
		this.name = name;
		webSocketSet.add(this);
		try {
			sendHelloMessage("欢迎你：" + name);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}
	
	@OnClose
	public void onClose() {
		webSocketSet.remove(this);
	}
	
	@OnMessage
    public void onMessage(String message, Session session) {
		
		try {
			
			for (WebSocketService service : webSocketSet) {
				if (this != service)
					sendMessage(service.getSession(), message);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
    }
	
	private void sendMessage(Session session, String message) throws Exception {
		session.getBasicRemote().sendText("[" + this.name + "]：" + message);
	}
	
	private void sendHelloMessage(String message) throws Exception {
		this.session.getBasicRemote().sendText(message);
	}
	
	private Session getSession() {
		return session;
	}
}

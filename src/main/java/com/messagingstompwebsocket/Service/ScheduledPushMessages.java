package com.messagingstompwebsocket.Service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.messagingstompwebsocket.Vo.HelloMessage;


@Service
public class ScheduledPushMessages {
	@Autowired
	public SimpMessagingTemplate template;

	//排程推送訊息(server to all user)
//	@Scheduled(fixedRate = 5000) //依需求改變排程時間
//	public void scheduleToAll() {
//
//		System.out.println("進入pushToAll方法");
//		template.convertAndSend("/topic/greetings", new Greeting("推送廣播: 這是每5秒推送一次的訊息!!" ));
//	}

	private void SendTo() {
		// TODO Auto-generated method stub

	}


	public void testValid(@Valid HelloMessage hm) {
		System.out.println("測試bean valid");
		System.out.println(hm.getName() + ";");

		System.out.println(hm.getUserId().length());


	}

}

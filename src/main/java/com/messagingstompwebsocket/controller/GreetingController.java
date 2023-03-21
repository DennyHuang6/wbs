package com.messagingstompwebsocket.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import com.messagingstompwebsocket.Vo.Greeting;
import com.messagingstompwebsocket.Vo.HelloMessage;


@Controller
/****************************************************
 * 訊息發送前邏輯處理的部分或者處理需要發送給user的訊息			*
 ****************************************************/
public class GreetingController {

	@Autowired
	private SimpMessagingTemplate template;




	/**
	 * 一對多廣播 (user to all user)
	 * 對象:所有註冊"/topic/greetings"的user
	 * 使用@SendTo
	 * @param message
	 * @return
	 * @throws Exception
	 */
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) {

		System.out.println("進入greeting方法");
        String responseString = "Hello, " + message.getName() + "!";
        return new Greeting(responseString);
    }


    /**
     * 點對點傳送訊息(user to user)
     * 對象: 註冊"/user/(userId)/message"的user
     * @param message
     */
	@MessageMapping("/messageToOne")
	public void messageToOne(@RequestBody HelloMessage message) {
		System.out.println("進入messageToOne方法");
		template.convertAndSendToUser(message.getTo(), "/message", message.getUserId() + "向你傳送訊息: " + message.getContent());
	}


	/**
	 * 推送訊息(server to user)
	 * 對象: 註冊"/user/(userId)/message"的user
	 * @param message
	 */
	@ResponseBody
	@PostMapping("/pushToOne")
	public void pushToOne(@RequestBody HelloMessage message) {

		System.out.println("進入pushToOne方法");
		template.convertAndSendToUser(message.getTo(), "/message", message.getUserId() + "向你傳送訊息: " + message.getContent());
	}


	/**
	 * 推送訊息(server to all user)
	 * 對象: 註冊"/topic/greetings"的user
	 * @param message
	 */
	@ResponseBody
	@PostMapping("/pushToAll")
	public void pushToAll(@RequestBody HelloMessage message) {

		System.out.println("進入pushToAll方法");
		template.convertAndSend("/topic/greetings", new Greeting("推送廣播: " + message.getContent()));
	}


	//==========================================================
	@PostMapping(value = "/insertDataModify")
	@ResponseBody
	public String testVaild(@RequestBody @Valid HelloMessage hm, BindingResult result) {
		System.out.println("enter testVaild method...");
		if(result.hasErrors()) {
			int errorCount = result.getErrorCount();
			System.out.println("check field count: " + errorCount);
			List<ObjectError> errors = result.getAllErrors();

			int count = 0;
			for(ObjectError error : errors) {
				count++;




				System.out.println("faillMessage: "+ error.getDefaultMessage());

			}
			return "Fail.....";
		}else {
			return "Pass......";
		}
	}

}

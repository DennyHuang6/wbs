package com.messagingstompwebsocket.Vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;


@Validated
@Component
public class HelloMessage {
	//自定義所需屬性
    private String name;

    @NotBlank(message = "userId未輸入或空白")
    @Size(min = 14, max = 17, message = "userID長度應在14~17之內")
    private String userId; //user ID或名稱

    @NotBlank(message = "to未輸入或空白")
    private String to;//傳送對象的ID或名稱

    @NotBlank(message = "content未輸入或空白")
    private String content;//傳送訊息內容


    public HelloMessage() {
    }

    public HelloMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}



}

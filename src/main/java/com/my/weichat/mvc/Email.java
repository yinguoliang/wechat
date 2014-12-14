package com.my.weichat.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class Email {
	@Autowired
	private MailSender mailSender;
	@Autowired
	private SimpleMailMessage simpleMailMessage;
	public void sendMail(String subject, String content, String to) {
		simpleMailMessage.setSubject(subject); //设置邮件主题
		simpleMailMessage.setTo(to);             //设定收件人
		simpleMailMessage.setText(content);  //设置邮件主题内容
		mailSender.send(simpleMailMessage); //发送邮件
	}
	public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
		 this.simpleMailMessage = simpleMailMessage;
	}
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
}

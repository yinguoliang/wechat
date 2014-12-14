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
		simpleMailMessage.setSubject(subject); //�����ʼ�����
		simpleMailMessage.setTo(to);             //�趨�ռ���
		simpleMailMessage.setText(content);  //�����ʼ���������
		mailSender.send(simpleMailMessage); //�����ʼ�
	}
	public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
		 this.simpleMailMessage = simpleMailMessage;
	}
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
}

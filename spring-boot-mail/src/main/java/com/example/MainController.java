package com.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

@RestController
public class MainController {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private MailProperties mailProperties;
	
	@Autowired
	private Configuration freemarkerConfiguration;

	
	@GetMapping("simple")
	public String simple() {
		SimpleMailMessage message = new SimpleMailMessage();
		// 发件人
		message.setFrom(mailProperties.getUsername());
		// 收件人
		message.setTo("1061787720@qq.com");
		// 标题
		message.setSubject("网络并非法外之地");
		// 内容
		message.setText("请跟我们走一趟");
		javaMailSender.send(message);
		
		return "success";
	}
	
	@GetMapping("inline-attach")
	public String inlineAttach() throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
		messageHelper.setFrom(mailProperties.getUsername());
		messageHelper.setTo("1061787720@qq.com");
		messageHelper.setSubject("网络并非法外之地");
		messageHelper.setText("<h1>请跟我们走一趟..<img src=\"cid:attach\"/></h1>", true);
	    messageHelper.addInline("attach", new ClassPathResource("4.jpg"));
	    
	    javaMailSender.send(message);
		return "success";
	}
	
	@GetMapping("freemarker")
	public String freemarker() throws MessagingException, IOException, TemplateException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
		messageHelper.setFrom(mailProperties.getUsername());
		messageHelper.setTo("1061787720@qq.com");
		messageHelper.setSubject("网络并非法外之地");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", "Prime Yun");
		map.put("event", "英雄联盟");
		
		String content = FreeMarkerTemplateUtils.processTemplateIntoString(
                this.freemarkerConfiguration.getTemplate("mail.ftl"), map);
		
		messageHelper.setText(content, true);
		javaMailSender.send(message);
		return "success";
	}
	
}

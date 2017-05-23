package com.likun.util.mail;
/**
 * 发送邮件
 */
import java.util.Calendar;
import java.util.Properties;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	String smtpHost; 
	String from;	
	String fromUserPassword;
	String to;
	String subject;
	String messageText;
	String messageType;
	/**
	 * 
	 * @param smtpHost
	 *            代理发送地址
	 * @param from
	 *            发送者邮箱
	 * @param fromUserPassword
	 *            授权码
	 * @param to
	 *            接受者邮箱
	 * @param subject
	 *            邮件标题
	 * @param messageText
	 *            邮件内容
	 * @param messageType
	 *            邮件
	 * @throws MessagingException
	 */
	public SendMail(String smtpHost, String from, String fromUserPassword, String to, String subject,
			String messageText, String messageType) {
		super();
		this.smtpHost = smtpHost;
		this.from = from;
		this.fromUserPassword = fromUserPassword;
		this.to = to;
		this.subject = subject;
		this.messageText = messageText;
		this.messageType = messageType;
	}
	@SuppressWarnings("static-access")
	public void sendMessage(){
		// 第一步：配置javax.mail.Session对象
		System.out.println("为" + smtpHost + "配置mail session对象");

		Properties props = new Properties();
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.starttls.enable", "true");// 使用 STARTTLS安全连接
		props.put("mail.smtp.port", "25"); // google使用465或587端口
		props.put("mail.smtp.auth", "true"); // 使用验证
		// props.put("mail.debug", "true");
		Session mailSession = Session.getInstance(props, new MyAuthenticator(from, fromUserPassword));

		// 第二步：编写消息
		System.out.println("编写消息from——to:" + from + "——" + to);

		InternetAddress fromAddress;
		try {
			fromAddress = new InternetAddress(from);
			InternetAddress toAddress = new InternetAddress(to);

			MimeMessage message = new MimeMessage(mailSession);

			message.setFrom(fromAddress);
			message.addRecipient(RecipientType.TO, toAddress);

			message.setSentDate(Calendar.getInstance().getTime());
			message.setSubject(subject);
			message.setContent(messageText, messageType);

			// 第三步：发送消息
			Transport transport = mailSession.getTransport("smtp");
			transport.connect(smtpHost, from, fromUserPassword);
			transport.send(message, message.getRecipients(RecipientType.TO));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("已发送！");
	}
//	public static void main(String[] args) {
//			SendMail sendMail=new SendMail("smtp.qq.com", "1617455243@qq.com", "icncjjelkslxdigi", "930017197@qq.com", "测试邮件",
//					"<h1>这是一封验证邮箱</h1>http://www.baidu.com", "text/html;charset=utf-8");;
//			sendMail.sendMessage();
//			}
}


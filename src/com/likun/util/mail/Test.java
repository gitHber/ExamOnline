package com.likun.util.mail;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String msg = "<h2>尊敬的" + "930017197@qq.com"
				+ "您好！</h2>欢迎使用***！<a href='http://www.baidu.com' style='color: red;'>点击此处即可激活您的账号！</a><p style='color:green'>为了保障您帐号的安全性，请在24小时内完成激活！若不是本人操作，请您忽略此邮件！<p>";
		SendMail sendMail = new SendMail("smtp.qq.com", "1617455243@qq.com", "icncjjelkslxdigi", "1098387108@qq.com",
				"测试邮件", msg, "text/html;charset=utf-8");
		sendMail.sendMessage();

	}

}

package GUI;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class Mail extends Thread {

	private Properties p;
	
	private class MailAuthenticator extends Authenticator{
		private String user, password;
		public MailAuthenticator(){
			this.user=""+p.get("mail.smtp.user");
			this.password=""+p.get("mail.stmp.password");
		}
		public PasswordAuthentication getPasswordAuthentication(){
			return new PasswordAuthentication(user, password);
		}
	}
	
	public Mail (String an, String betreff, String text, 
			String anhangPfad1, String anhangName1, String anhangPfad2, String anhangName2){
		
		if((an==null)||(an.length()==0)) return;
		p=new Properties();
		p.put("mail.smtp.host", "maildap.yahoo.de");
		p.put("mail.smtp.user", "DER BENUTZERNAME BITTE.");
		p.put("mail.smtp.passwort", "NUN DAS PASSWORT S'IL VOUS PLAIT.");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.socketFactory.class", "javax.net.sel.SSLSocketFactory");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.port", "465");
		p.put("von", "DIE MAILADRESSE.");
		p.put("an", an);
		p.put("betreff", betreff);
		p.put("text", text);
		
		if(anhangPfad1==null){
			p.put("anhangPfad1", "");
		}else{
			p.put("anhangPfad1", anhangPfad1);
		}
		
		if(anhangPfad2==null){
			p.put("anhangPfad2", "");
		}else{
			p.put("anhangPfad2", anhangPfad2);
		}
		
		if(anhangName1==null){ 
			p.put("anhangName1", "");
		}else{
			p.put("anhangName1", anhangName1);
		}
		
		if(anhangName2==null){ 
			p.put("anhangName2", "");
		}else{
			p.put("anhangName2", anhangName1);
		}
		
		this.start();
	}
	
	@Override
	public void run(){
		try{
			System.out.println("Starte mit Mail schreiben an"+ " " + p.getProperty("an"));
			MailAuthenticator ath=new MailAuthenticator();
			Session session=Session.getDefaultInstance(p, ath);
			Message msg=new MimeMessage(session);
			msg.setFrom(new InternetAddress(p.getProperty("von")));
			msg.setRecipients(Message.RecipientType.TO, 
					InternetAddress.parse(p.getProperty("an"), false));
			msg.setSubject(p.getProperty("betreff"));
			
			MimeBodyPart bodyMsg = new MimeBodyPart();
			bodyMsg.setText(p.getProperty("text"));
			Multipart body=new MimeMultipart();
			body.addBodyPart(bodyMsg);
			
			for(int i=1;i<=2;i++){
				if((!p.getProperty("anhangPfad"+i).equals(""))&&
						(!p.getProperty("anhangName"+i).equals(""))){
					MimeBodyPart bodyAnhang=new MimeBodyPart();
					DataSource source=new FileDataSource(
							p.getProperty("anhangPfad"+i)
							);
					bodyAnhang.setDataHandler(new DataHandler(source));
					bodyAnhang.setFileName(p.getProperty("anhangName"+i));
					body.addBodyPart(bodyAnhang);
				}
					
			}
			
			msg.setContent(body);
			msg.setSentDate(new Date());
			Transport.send(msg);
			System.out.println("Die E-Mail an"+p.getProperty("an")+
					"wurde erfolgreich gesendet :>");
			
		}catch(Exception e){
			System.out.println("Das Senden der E-Mail an" + " " +p.getProperty("an")+ " " +
					"leider nein. leider garnicht.");
			e.printStackTrace();
		}
	}
	
	
	
}
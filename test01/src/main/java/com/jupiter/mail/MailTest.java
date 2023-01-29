package com.jupiter.mail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Properties;

public class MailTest {
        public static void main(String[] args) throws IOException, MessagingException {

                // Test to send an email
                // 1.import mail.jar

                // 2.connect to Email server
                Properties prop = new Properties();
                File file = new File("test01/target/classes/mail.properties");
                InputStream in = new FileInputStream(file);
                prop.load(in);
                Session session = Session.getInstance(prop);

                System.out.println(prop.getProperty("mail.transport.protocol"));
                System.out.println(prop.getProperty("user_Name"));
                System.out.println(prop.getProperty("authorization_key"));
//                System.exit(0);

                // 3.new a email instance
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(prop.getProperty("sender_address")));
                message.setRecipients(Message.RecipientType.TO,prop.getProperty("send_address"));
                message.setSubject("主题：拜年邮件");
                message.setText("Happy New Year!");
                message.saveChanges(); // must save

                // 4.send by Transporter.
                Transport transport = session.getTransport();
                transport.connect(prop.getProperty("user_Name"),prop.getProperty("authorization_key"));
                transport.sendMessage(message, message.getAllRecipients());

                // 5. close
                transport.close();

                // 99. authorization key: MIRKXTCTQEMIKXIN
        }


}

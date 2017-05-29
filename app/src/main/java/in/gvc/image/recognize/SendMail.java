package in.gvc.image.recognize;


import net.sargue.mailgun.Configuration;
import net.sargue.mailgun.Mail;
import net.sargue.mailgun.MailBuilder;
import net.sargue.mailgun.MultipartBuilder;

import org.glassfish.jersey.client.ClientResponse;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;

/**
 * Created by arpit on 27/5/17.
 */

public class SendMail {

    public static void SendSimpleMessage(byte[] bytes,String str) {
        Configuration configuration = new Configuration()
                .domain("sandbox172360c0ad414c06b3a188dd6d5b3208.mailgun.org")
                .apiKey("key-d2ac76e5c35729885c1d56daeba7bd25")
                .from("Test account", "mailgun@sandbox172360c0ad414c06b3a188dd6d5b3208.mailgun.org");

        MailBuilder mailBuilder = Mail.using(configuration);
        mailBuilder.to("ocr@gvc.in");
        mailBuilder.subject("Image Recognizer for ArcMedia");
        mailBuilder.text("This mail is sent from Image Recognizer App made by GVC Systems\nText: "+str);

        MultipartBuilder multipartBuilder = mailBuilder.multipart();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        multipartBuilder.attachment(new ByteArrayInputStream(bytes),df.format(date)+".jpeg");


        multipartBuilder.build().send();

    }
}

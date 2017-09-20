package me.grace.springbootsendemail;

import com.google.common.collect.Lists;
import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;
import it.ozimov.springboot.mail.service.exception.CannotSendEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    public EmailService emailService;

    public void sendEmailWithTemplating(String recipient) {
        final Email email;
        try {
            email = DefaultEmail.builder()
                    .from(new InternetAddress("lll", "999"))
                    .to(Lists.newArrayList(new InternetAddress("luningfu1010@gmail.com", "Luning")))
                    .subject("Email from Spring")
                    .body("Sending email from Gmail")
                    .encoding("UTF-8").build();
            final Map<String, Object> modelObject = new HashMap<>();
            modelObject.put("recipient", recipient);
            try {
                emailService.send(email, "emailtemp", modelObject);
            } catch (CannotSendEmailException e) {
                e.printStackTrace();
                System.out.println("!!!!!!!!!!!");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("?????????????");
        }

    }


    public void sendEmailWithoutTemplating(String recipient) {
        final Email email;
        try {
            email = DefaultEmail.builder()
                    .from(new InternetAddress("doesnotmatter", "Grace"))
                    .to(Lists.newArrayList(new InternetAddress("luningfu1010@gmail.com", "Luning")))
                    .subject("Email from Spring")
                    .body("Firmamentum")
                    .encoding("UTF-8").build();
            final Map<String, Object> modelObject = new HashMap<>();
            modelObject.put("recipient", recipient);
            emailService.send(email);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }


    @GetMapping("/")
    public @ResponseBody String sendEmail(){

        String rect = "luningfu1010@gmail.com" ;
        sendEmailWithoutTemplating(rect);
        return "email with out templating sent!";
    }


    //this is not working
    @GetMapping("/templ")
    public @ResponseBody String sendEmailt(){

        String rect = "luningfu1010@gmail.com" ;
        sendEmailWithTemplating(rect);
        return "email with templating sent!";
    }




}

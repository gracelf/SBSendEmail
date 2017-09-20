package me.grace.springbootsendemail;

import com.google.common.collect.Lists;
import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;
import it.ozimov.springboot.mail.service.exception.CannotSendEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

            System.out.println("here0=====");
            email = DefaultEmail.builder()
                    .from(new InternetAddress("testforspring123@gmail.com", "spring"))
                    .to(Lists.newArrayList(new InternetAddress("luningfu1010@gmail.com", "Luning")))
                    .subject("Email from Spring")
                    .body("")//empty body
                    .encoding("UTF-8").build();

            final Map<String, Object> modelObject = new HashMap<>();
            modelObject.put("recipient", recipient);
            System.out.println("here1=====");

            // can not go on from here, message:  java.lang.NullPointerException: null
            //email may be null
            emailService.send(email, "templates/emailtemp.html", modelObject);
            System.out.println("here2=====");


        } catch (CannotSendEmailException e) {
                e.printStackTrace();
                System.out.println("!!!!!!!!!!!");
            } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("2222!!!!!!!!!!!");
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
    @RequestMapping("/templ")
    public @ResponseBody String sendEmailt(){

        String rect2 = "luningfu1010@gmail.com" ;
        sendEmailWithTemplating(rect2);

        System.out.println("here4=====");
        return "email with templating sent!";
    }




}

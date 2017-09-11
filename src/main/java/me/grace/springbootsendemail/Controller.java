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

    public void sendEmailWithTemplating(String recipient) throws UnsupportedEncodingException, CannotSendEmailException {
        final Email email = DefaultEmail.builder()
                .from(new InternetAddress("grace.luning.fu@gmail.com", "Grace"))
                .to(Lists.newArrayList(new InternetAddress("luningfu1010@gmail.com", "Luning")))
                .subject("Email from Spring")
                .body("Firmamentum")
                .encoding("UTF-8").build();
        final Map<String, Object> modelObject = new HashMap<>();
        modelObject.put("recipient", recipient);
        emailService.send(email, "emailtemp", modelObject);
    }

    @GetMapping("/")
    public @ResponseBody String sendEmail(){
        return "email sent!";
    }

}

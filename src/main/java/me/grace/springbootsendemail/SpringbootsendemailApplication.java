package me.grace.springbootsendemail;

import com.google.common.collect.Lists;
import it.ozimov.springboot.mail.configuration.EnableEmailTools;
import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;
import it.ozimov.springboot.mail.service.exception.CannotSendEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.thymeleaf.expression.Lists;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


@SpringBootApplication
@EnableEmailTools
public class SpringbootsendemailApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootsendemailApplication.class, args);
	}

}

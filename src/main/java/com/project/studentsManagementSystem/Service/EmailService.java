package com.project.studentsManagementSystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.project.studentsManagementSystem.Model.Student;

@Service
public class EmailService {
    @Autowired
    private  JavaMailSender mailSender;
    
    public  void sendEmail(Student obj) {
    	String from = "gokulkrishhh0504@gmail.com";
        String to = obj.getGmail();

        String subject = "Student Credentials";

        String content = "Hi " + obj.getFirstname() + " " + obj.getLastname() + ",\n\n"
                + "Here are your login details for the student portal — please do not share them.\n\n"
                + "NET ID: " + obj.getNetid() + "\n"
                + "Password: " + obj.getPassword() + "\n\n"
                + "Use these to login to the student portal.\n"
                + "If you can’t login, reply to this email.\n"
                + "\nThanks,\nAdmin";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        try {
			mailSender.send(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        System.out.println("Email sent successfully!");
    }
}

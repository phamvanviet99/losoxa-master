package com.phamvanviet.losoxa.service.impl;

import com.phamvanviet.losoxa.entity.User;
import com.phamvanviet.losoxa.repository.UserRepository;
import com.phamvanviet.losoxa.service.MailService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl  implements MailService {
    @Autowired
    JavaMailSender mailSender;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void sendEmail(String toEmail, String subject) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        User user = userRepository.findUserByEmail(toEmail);
        String password = RandomStringUtils.randomNumeric(6);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);

        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText("Mật khẩu mới của bạn là: "+password);

        mailMessage.setFrom("losoxaofficial@gmail.com");

        mailSender.send(mailMessage);
    }
}

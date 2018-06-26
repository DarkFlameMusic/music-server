package com.musicweb.music.service;

import com.musicweb.music.entity.MailSendTo;

public interface MailService {

    void sendMail(String sendTo,String title,String msg);
}

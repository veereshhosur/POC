package com.lbg.integrator.api.service;

import org.springframework.web.multipart.MultipartFile;

public interface EmailService {
    void sendEmail(String[] paramArrayOfString1, String[] paramArrayOfString2, String paramString1, String paramString2, MultipartFile[] paramArrayOfMultipartFile);
}

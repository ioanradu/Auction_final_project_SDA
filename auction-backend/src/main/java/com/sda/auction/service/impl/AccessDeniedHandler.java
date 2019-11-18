package com.sda.auction.service.impl;

import com.sda.auction.dto.ErrorResponseDto;
import org.springframework.stereotype.Component;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandler {

    public void reply(ServletResponse servletResponse) throws IOException {
        ErrorResponseDto responseDto = new ErrorResponseDto(401, "Unauthorized access");
        byte[] response = responseDto.getBytes(); // initial a fost pus =deny.getBytes()
        servletResponse.getOutputStream().write(response);

        ((HttpServletResponse) servletResponse).setHeader("Content-Type", "application/json");
        ((HttpServletResponse) servletResponse).setStatus(401);
    }
}

package com.zhang.app1.config.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CustomizeAuthExceptionEntryPoint implements AuthenticationEntryPoint, AccessDeniedHandler {
    private static final Logger log = LoggerFactory.getLogger(CustomizeAuthExceptionEntryPoint.class);

    public CustomizeAuthExceptionEntryPoint() {
    }

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Throwable cause = authException.getCause();
        response.setStatus(401);
        BaseResponseDTO baseResponseDTO = null;
        if (cause instanceof InvalidTokenException) {
            log.error("InvalidTokenException : {}", cause.getMessage());
            baseResponseDTO = BaseResponseDTO.builder().code("500").message("ACCESS_TOKEN不合法").build();
        } else {
            log.error("AuthenticationException : NoAuthentication, {}" + cause);
            baseResponseDTO = BaseResponseDTO.builder().code(String.valueOf("500")).message("用户未登录").build();
        }

        writerError(baseResponseDTO, response);
    }

    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(401);
        log.error("AccessDeniedException : {}", accessDeniedException.getMessage());
        writerError(BaseResponseDTO.builder().code("500").message("用户权限不足").build(), response);
    }

    public static void writerError(BaseResponseDTO<?> baseResponseDTO, HttpServletResponse response) {
        try {
            response.setContentType("application/json;charset=UTF-8");
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Cache-Control", "no-cache");
            response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
            response.addHeader("Access-Control-Max-Age", "1800");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(response.getOutputStream(), baseResponseDTO);
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }
}

package com.dengjk.springsecuritydemo.config;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Dengjk
 * @create 2018-08-11 22:07
 * @desc  针对session并发处理
 **/
@Component
public class SessionInfoExpiredStrategy implements SessionInformationExpiredStrategy{


    /**
     *
     * @param event
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        HttpServletResponse response = event.getResponse();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("当前用户在多处登入,请退出后,重新登入!!!");
    }
}

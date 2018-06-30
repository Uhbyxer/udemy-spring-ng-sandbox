package com.rfb.security;

import com.rfb.service.LoginAttemptService;
import com.rfb.service.impl.RfbEventAttendanceServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFailureEventListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    private final Logger log = LoggerFactory.getLogger(AuthenticationFailureEventListener.class);

    @Autowired
    private LoginAttemptService loginAttemptService;


    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        WebAuthenticationDetails webAuthenticationDetails = (WebAuthenticationDetails) event.getAuthentication().getDetails();
        log.debug("Failed login at {}", webAuthenticationDetails.getRemoteAddress());
        loginAttemptService.loginFailed(webAuthenticationDetails.getRemoteAddress());
    }
}

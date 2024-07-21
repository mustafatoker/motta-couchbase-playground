package com.mustafatoker.motta.auth.filter;

import jakarta.servlet.http.HttpServletRequest;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import java.io.Serial;

@Getter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {

    @Serial
    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    private final String userId;

    public CustomWebAuthenticationDetails(HttpServletRequest request, String userId) {
        super(request);
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CustomWebAuthenticationDetails{" +
                "userId='" + userId + '\'' +
                ", remoteAddress='" + getRemoteAddress() + '\'' +
                ", sessionId='" + getSessionId() + '\'' +
                '}';
    }
}
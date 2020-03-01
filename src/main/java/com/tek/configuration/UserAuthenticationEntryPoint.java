package com.tek.configuration;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable{

	private static final long serialVersionUID = -7400489406002292705L;
	
	public UserAuthenticationEntryPoint() {
		System.out.println(">>>>>>> UserAuthenticationEntryPoint constructor >>>>>>>>>>");
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		System.out.println(">>>>>>>>>> method -- UserAuthenticationEntryPoint - commence() ");
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}

}

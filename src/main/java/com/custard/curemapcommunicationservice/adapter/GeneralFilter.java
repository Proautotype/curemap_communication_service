package com.custard.curemapcommunicationservice.adapter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
public class GeneralFilter extends OncePerRequestFilter {
    private final Logger filterLogger = LoggerFactory.getLogger(GeneralFilter.class);
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        filterLogger.info("Request received on path {} \n", request.getPathInfo());
        filterChain.doFilter(request, response);
    }
}

package com.honoka.web.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter {


    private String characterEncoding;
    private boolean enabled;

    @Override
    public void destroy() {
        characterEncoding = null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        if (enabled || characterEncoding != null) {
            request.setCharacterEncoding(characterEncoding);
            response.setCharacterEncoding(characterEncoding);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        characterEncoding = config.getInitParameter("characterEncoding");
        enabled = "true".equalsIgnoreCase(config.getInitParameter("enabled")
                .trim());
    }

}

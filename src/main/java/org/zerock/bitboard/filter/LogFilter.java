package org.zerock.bitboard.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Log4j2
@WebFilter(urlPatterns ={"/*"} ) // urlPatterns ={"/*"}. 모든 jsp에 결합
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException { // In . 한번
        log.info("--------------------------------");
        log.info("--------------------------------");
        log.info("init filter");
        log.info("--------------------------------");
        log.info("--------------------------------");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            log.info("doFilter--------------------------------");
            log.info("doFilter--------------------------------");
            log.info("doFilter--------------------------------");
            log.info("doFilter--------------------------------");

            request.setCharacterEncoding("UTF-8");

            chain.doFilter(request,response);  //필터를 통해서 가서 한글이 안깨진다.?
    }

    @Override
    public void destroy() {  // OUT 나온다.들어가는거랑 나가는거는.한번
        Filter.super.destroy();
    }
}

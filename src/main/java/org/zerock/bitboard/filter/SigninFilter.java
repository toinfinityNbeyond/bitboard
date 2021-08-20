package org.zerock.bitboard.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
@WebFilter(filterName = "signin", urlPatterns = {}) // "/board/register", "/board/read"
//urlPatterns = {"/board/register", "/board/read"}
//필터는 여러가지를 만들 수 있는데 한 가지 필터를 여러 jsp에 적용할 수 있다.(urlPatterns)

public class SigninFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {  //IN. 한번만
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("Signin Filter..................run........");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        //HttpServletRequest 브라우저가 보낸 모든 정보를 처리
        //ServletRequest > HttpServletRequest  다운캐스팅을 해야함.
        //HttpServletRequest 브라우저가 보내는 모든 정보를 담아낸다.


        HttpSession session = req.getSession();
        // HttpSession 서버가 갖고 있는 장부. 락커룸
        //서버가 발행한 세션값을 갖고 있다.
        //세션저장소에 있는 데이터 값
        // Session 로그인 기능.



        //not yet login
        if(session.getAttribute("member") == null) {
            res.sendRedirect("/login");
            return;
        }
        chain.doFilter(request,response);
     }

    @Override
    public void destroy() {  //OUT. 한번만
        Filter.super.destroy();
    }
}

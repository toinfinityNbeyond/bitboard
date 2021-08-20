package org.zerock.bitboard.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.bitboard.service.BoardService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static javax.swing.UIManager.getInt;

@Log4j2
@WebServlet(name = "RemoveController", value = "/board/remove")
public class RemoveController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("do post.....................");

        Integer bno = Integer.parseInt(request.getParameter("bno")); //request string이라서 Integer.parseInt 로 감싸줌.

        log.info("bno" + bno);

        //삭제 쿼리
        BoardService.INSTANCE.remove(bno);

        //리다이렉트
        response.sendRedirect("/board/list?bno=" + bno);
    }
}

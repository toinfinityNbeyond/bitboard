package org.zerock.bitboard.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.bitboard.dto.BoardDTO;
import org.zerock.bitboard.service.BoardService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@Log4j2
@WebServlet(name = "ModifyController", value = "/board/modify")
public class ModifyController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/board/modify.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(Integer.parseInt(request.getParameter("bno")))
                .title(request.getParameter("title"))
                .content(request.getParameter("content"))
                .build();

         BoardService.INSTANCE.modify(boardDTO);

        response.sendRedirect("/board/list?bno=");

    }
}

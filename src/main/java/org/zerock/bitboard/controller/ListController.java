package org.zerock.bitboard.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.bitboard.dto.BoardDTO;
import org.zerock.bitboard.dto.PageDTO;
import org.zerock.bitboard.dto.PageMaker;
import org.zerock.bitboard.service.BoardService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@Log4j2
@WebServlet(name = "ListController", value = "/board/list")
public class ListController extends HttpServlet {

    private Integer getInt(String str) {
        try {
            int value = Integer.parseInt(str);
            if (value <= 0) {
                return null;
            }
            return value;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer page = getInt(request.getParameter("page"));
        Integer size = getInt(request.getParameter("size"));   // 두 개 수집해서 PageDTO 만든다.

        PageDTO pageDTO = PageDTO.builder().build();

        if (page != null) {
            pageDTO.setPage(page);
        }
        ;
        if (size != null) {
            pageDTO.setSize(size);
        }

        log.info("==============================1");
        log.info(pageDTO);

        List<BoardDTO> dtoList = BoardService.INSTANCE.getList(pageDTO);

        log.info("==============================2");
        log.info(dtoList);

        request.setAttribute("dtoList", dtoList);

        PageMaker pageMaker = new PageMaker(pageDTO.getPage(),pageDTO.getSize(),1230);

        request.setAttribute("pageMaker",pageMaker);


        request.getRequestDispatcher("/WEB-INF/board/list.jsp").forward(request, response);
    }

}

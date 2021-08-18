package org.zerock.bitboard.service;

import lombok.extern.log4j.Log4j2;
import org.zerock.bitboard.dao.BoardDAO;
import org.zerock.bitboard.dto.BoardDTO;
import org.zerock.bitboard.dto.PageDTO;

import java.util.List;

@Log4j2
public enum BoardService {
    INSTANCE;

    public List<BoardDTO> getList(PageDTO pageDTO)throws RuntimeException{ //PageDTO 가 파라미터가 되는 이유가 페이지번호와 사이즈를 같이 가지고 있기 때문에.

        log.info("BoardService getList....................");
        log.info(pageDTO);

        return BoardDAO.INSTANCE.list(pageDTO);
    }
}

package org.zerock.bitboard.dao;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.zerock.bitboard.dto.BoardDTO;
import org.zerock.bitboard.dto.PageDTO;

import java.util.List;

@Log4j2
public enum BoardDAO {
    INSTANCE;

    private static final String PREFIX = "org.zerock.bitboard.dao.BoardMapper";

    public void insert(BoardDTO boardDTO) throws RuntimeException {
        try(SqlSession session = MyBatisLoader.INSTANCE.getFactory().openSession(true)) {  // openSession 에 true를 넣지면 항상 session.commit이 필요
        session.insert(PREFIX+ ".insert",boardDTO);
    }catch(Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public BoardDTO select(Integer bno) throws RuntimeException {
        BoardDTO dto = null;
        try(SqlSession session = MyBatisLoader.INSTANCE.getFactory().openSession(true)) {
            dto = session.selectOne(PREFIX+ ".select",bno);  // 한개는 selectOne 통채로는 select
        }catch(Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return dto;
    }
    public List<BoardDTO> list(PageDTO pageDTO) throws RuntimeException {
        List<BoardDTO> list = null;
        try(SqlSession session = MyBatisLoader.INSTANCE.getFactory().openSession(true)) {
            list = session.selectList(PREFIX+ ".list", pageDTO);  // 파라미터가 없으면 생략
        }catch(Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return list;
    }

    public void delete(Integer bno) throws RuntimeException{

        try(SqlSession session = MyBatisLoader.INSTANCE.getFactory().openSession(true)) {
            session.delete(PREFIX+ ".list", bno);  // 파라미터가 없으면 생략
        }catch(Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public void update(BoardDTO boardDTO) throws RuntimeException{

        try(SqlSession session = MyBatisLoader.INSTANCE.getFactory().openSession(true)) {
            session.update(PREFIX+ ".list", boardDTO );  // 파라미터가 없으면 생략
        }catch(Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public int selectCount(Integer bno) throws RuntimeException {
        int count = 0;
        try(SqlSession session = MyBatisLoader.INSTANCE.getFactory().openSession(true)) {
           count = session.selectOne(PREFIX+ ".selectCount",bno);  // 한개는 selectOne 통채로는 select
        }catch(Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return count;
    }
}


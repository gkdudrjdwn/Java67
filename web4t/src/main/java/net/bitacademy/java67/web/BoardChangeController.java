package net.bitacademy.java67.web;

import net.bitacademy.java67.dao.BoardDao;
import net.bitacademy.java67.domain.BoardVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/* 실습 목표: 스프링 애노테이션 적용 */

@Controller("/board/change.do")
public class BoardChangeController {
  @Autowired
  BoardDao boardDao;
  
  @RequestMapping
  public String change(BoardVo board) throws Exception {
    boardDao.update(board);
    
    return "redirect:list.do";
  }
}














package net.bitacademy.java67.step06;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* 실습 목표: 컨텍스트 초기화 파라미터 사용
 * - 먼저, ServletContext 객체를 얻는다.
 * - 그리고, getInitParameter("파라미터이름") 호출한다.        
 */
@WebServlet("/step06/list")
public class BoardListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void service(
      HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    // ServletContext : 웹 애플리케이션 정보를 다루는 도구
    ServletContext ctx = this.getServletContext();
    
    BoardDao boardDao = new BoardDao();
    DBConnectionPool dbPool = new DBConnectionPool(
        ctx.getInitParameter("driver"), // web.xml의 컨텍스트 초기화 파라미터 가져오기
        ctx.getInitParameter("url"),
        ctx.getInitParameter("user"),
        ctx.getInitParameter("password"));
    boardDao.setDBConnectionPool(dbPool);
    
    List<BoardVo> list = boardDao.selectList();
    
    response.setContentType("text/html;charset=UTF-8");
    
    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("  <head>");
    out.println("    <title>게시판</title>");
    out.println("  </head>");
    out.println("  <body>");
    out.println("  <h1>게시물 목록</h1>");
    out.println("  <p><a href='form.html'>새 글</a></p>");
    out.println("  <table border='1'>");
    out.println("  <tr> "
        + "<th>번호</th> <th>제목</th> <th>작성일</th> <th>조회수</th>"
        + "</tr>");
    
    for (BoardVo board : list) {
      out.println("  <tr> <td>"
          + board.getNo() + "</td> <td><a href='detail?no=" 
          + board.getNo() + "'>"
          + board.getTitle() + "</a></td> <td>"
          + board.getCreateDate() + "</td> <td>"
          + board.getViews() + "</td> </tr>");
    }
    
    out.println("  </table>");
    out.println("  </body>");
    out.println("</html>");
    
  }

}











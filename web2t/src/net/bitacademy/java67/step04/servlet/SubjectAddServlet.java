package net.bitacademy.java67.step04.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bitacademy.java67.step04.DBConnectionPool;
import net.bitacademy.java67.step04.dao.SubjectDao;
import net.bitacademy.java67.step04.vo.SubjectVo;

/* 실습 목표: 게시물 등록후 목록 화면으로 이동하기3
 *          단, 등록 성공 결과 화면을 출력하지 말고 바로 목록 화면으로 이동하라. 
 * 
 */

@WebServlet("/step04/subject/add")
public class SubjectAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(
      HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    request.setCharacterEncoding("UTF-8");
    
    SubjectVo subject = new SubjectVo();
    subject.setTitle(request.getParameter("title"));
    
    SubjectDao subjectDao = new SubjectDao();
    subjectDao.setDBConnectionPool(new DBConnectionPool());
    subjectDao.insert(subject);
    
    response.sendRedirect("list");
    
    // 리다이렉트는 서버에서 클라이언트로 응답할 때 본문을 보내지 않는다.
    // 따라서 다음의 출력은 무시된다.
    //
    // 이미 출력했는데 어떻게 클라이언트로 보내지 않나요?
    // 보낸 것을 취소하는 기능이 있나요?
    // 
    // 답변: 
    // - response.getWriter()가 리턴한 출력스트림에는 8KB 버퍼가 내장되어 있다.
    // - 출력하면 이 버퍼에 임시 보관된다.
    // - 버퍼가 모두 차거나 service() 호출이 끝날 때 버퍼의 내용을 웹브라우저로 보낸다.
    // - out.println()을 호출한다고 해서 즉시 웹 브라우저로 보내는 것이 아니다.
    // - 리다이렉트는 버퍼에 보관된 출력 내용을 버린다.
    //   본문을 웹 브라우저로 보내지 않는다.
    //
    // 결론: 리다이렉트 할 경우 웹 브라우저로 출력할 필요가 없다.
    //      다음 출력을 없애라!
    /*
    response.setContentType("text/html;charset=UTF-8");
    
    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("  <head>");
    out.println("    <title>게시판</title>");
    out.println("  </head>");
    out.println("  <body>");
    out.println("  <p>등록 성공입니다.</p>");
    out.println("  </body>");
    out.println("</html>");
    */
    
    /* 리다이렉트의 HTTP 응답 
HTTP/1.1 302 Found
Server: Apache-Coyote/1.1
Location: http://localhost:9999/web2t/step03/list
Content-Length: 0
Date: Fri, 08 May 2015 05:18:34 GMT

      확인할 사항
      1) 응답 코드 값이 200이 아니라 302이다. 
      2) Location 헤더에 리다이렉트할 주소가 있다.
      웹 브라우저는 이 응답을 받는 즉시 Location 헤더에 정의한 주소로 다시 요청한다.
     */
  }
}

/* Refresh와 Redirect
 * 1) 작업 결과를 잠시나마 출력할 필요가 있다면 refresh를 사용하라!
 * 2) 작업 결과를 출력하지 않고 즉시 다른 페이지로 이동시키고 싶다면 redirect를 사용하라!
 */











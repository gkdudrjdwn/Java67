<%@ page  language="java" 
          contentType="text/plain; charset=UTF-8"
          pageEncoding="UTF-8"%>
JSP란?
- 출력문을 자동 생성하는 기술이다.
- JSP 파일을 읽어서 서블릿 클래스를 생성한다. 
- 템플리 데이터는 out.println(), out.write() 등의 출력문을 생성한다.
- 톰캣 서버의 경우, 임시 배치 폴더의 work 디렉토리에 자동으로 생성된 서블릿 파일을 둔다.
  서블릿 파일이 놓이는 폴더 ==> ...tmp0/work/...
- 클래스 이름은 서블릿 컨테이너가 임의로 짓는다.
- JSP 파일은 직접 실행되지 않는다. 단지 서블릿 클래스를 만들기 위한 재료로 사용될 뿐이다.

언제 JSP 파일을 가지고 서블릿 클래스를 생성하는가?
- JSP를 최초로 요청할 때,
- JSP 파일이 변경되었을 때,

[JSP 구동원리]
1) 클라이언트에서 JSP 실행을 요청

2) 서블릿 컨테이너는 JSP로 만든 서블릿 클래스를 찾는다.

3-1) 만약 없다면,
   - JSP 파일을 분석하여 서블릿 클래스 파일을 만든다.(JSP 엔진)
   - 컴파일 한다.

3-2) 만약 JSP 파일이 변경되었다면,
   - JSP 파일을 분석하여 서블릿 클래스 파일을 만든다.(JSP 엔진)
   - 컴파일 한다.
    
4) 해당 서블릿의 service() 메서드를 호출한다.
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
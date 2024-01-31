<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSTL Test - Show List</title>
</head>

<body>
        <h1>JSTL Test</h1>
 
        <p>
            Show List:
            <ul>
                <c:forEach items="${list}" var="animal">
                    <li>${animal}</li>
                </c:forEach>        
            </ul>
        </p>

</body>
</html>

<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<html>
    <body>
        <h1>JSTL Test</h1>
 
        <p>
            The games we play:
            <ul>
                <c:forEach items="${list}" var="game">
                    <li>${game}</li>
                </c:forEach>        
            </ul>
        </p>
    </body>
</html> --%>
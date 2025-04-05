<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
    <%@ include file="/WEB-INF/pages/header.jsp" %>

    <main>
        <div class="login-header">
            <h2>Login</h2>
        </div>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <input type="text" name="username" placeholder="Username" required>
            <input type="password" name="password" placeholder="Password" required>
            <button type="submit">Login</button>
            <% if (request.getAttribute("error") != null) { %>
                <p class="error"><%= request.getAttribute("error") %></p>
            <% } %>
        </form>
        <p class="signup-text">Don't have an account? <a href="${pageContext.request.contextPath}/register">Sign up</a></p>
    </main>

    <%@ include file="/WEB-INF/pages/footer.jsp" %>
</body>
</html>
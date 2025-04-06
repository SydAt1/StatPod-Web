<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>

<div class="page-wrapper">
    
    <main class="main-content">
        <div class="login-box">
            <h2>Login</h2>
            <form action="LoginServlet" method="post">
                <input type="text" name="username" placeholder="Username" required>
                <input type="password" name="password" placeholder="Password" required>
                <input type="submit" value="Login">
            </form>
            <p class="signup-link">
                Don't have an account? <a href="register.jsp">Sign up</a>
            </p>
        </div>
    </main>
    
    <%@ include file="footer.jsp" %>

</div>

</body>
</html>

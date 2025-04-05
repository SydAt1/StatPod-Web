<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>
    <header>
        <div class="navbar">
            <a href="${pageContext.request.contextPath}/home" class="logo-link">
                <div class="logo-container">
                    <img src="${pageContext.request.contextPath}/images/logo.png" alt="Website Logo" class="logo-img">
                </div>
            </a>
            <nav>
                <a href="${pageContext.request.contextPath}/pages/about.jsp">About</a>
                <a href="${pageContext.request.contextPath}/pages/contact.jsp">Contact</a>
                <a href="${pageContext.request.contextPath}/login" class="login-btn">Login</a>
            </nav>
        </div>
    </header>
</body>
</html>
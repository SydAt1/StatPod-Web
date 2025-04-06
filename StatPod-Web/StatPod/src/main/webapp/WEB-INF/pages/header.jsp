<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.statpod.util.SessionUtil" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
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

            <%
            boolean loggedIn = SessionUtil.isLoggedIn(request);
            if (loggedIn) {
                String username = SessionUtil.getCurrentUser(request);
            %>
                <div class="user-profile">
                    <a href="${pageContext.request.contextPath}/portfolio" class="profile-pic-btn">
                        <i class="fas fa-user"></i>
                    </a>
                    <span class="user-name"><%= username %></span>
                    <a href="${pageContext.request.contextPath}/logout" class="logout-link">Logout</a>
                </div>
            <%
            } else {
                // Only show login/register links if not on those pages
                String currentPage = request.getRequestURI();
                if (!currentPage.endsWith("/login") && !currentPage.endsWith("/register")) {
            %>
                    <a href="${pageContext.request.contextPath}/login" class="login-btn">Login</a>
            <%
                }
            }
            %>
        </nav>
    </div>
</header>
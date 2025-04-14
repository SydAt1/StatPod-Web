<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.statpod.model.PodcastUserModel" %>
<%
    PodcastUserModel user = (PodcastUserModel) request.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>User Profile</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profile.css">
</head>
<body>
    <%@ include file="header.jsp" %>

    <div class="profile-container">
        <div class="profile-header"></div>
        <div class="profile-user-info">
            <div class="profile-picture">
			    <img src="<%= request.getContextPath() + "/images/users/" + user.getImageUrl() %>" alt="Profile Picture">
			</div>
            <div class="profile-name"><%= user.getDisplayName() %></div>
            <button class="edit-profile-btn">Edit User Profile</button>
        </div>
        
        <div class="profile-details">
            <div class="detail-row">
                <div>
                    <div class="detail-label">Display Name</div>
                    <div class="detail-value"><%= user.getDisplayName() %></div>
                </div>
                <button class="edit-btn">Edit</button>
            </div>

            <div class="detail-row">
                <div>
                    <div class="detail-label">Username</div>
                    <div class="detail-value"><%= user.getUsername() %></div>
                </div>
                <button class="edit-btn">Edit</button>
            </div>

            <div class="detail-row">
                <div>
                    <div class="detail-label">Email</div>
                    <div class="detail-value">
                        ************.com
                        <button class="reveal-btn" onclick="this.previousSibling.textContent='<%= user.getEmail() %>';">Reveal</button>
                    </div>
                </div>
                <button class="edit-btn">Edit</button>
            </div>
            
            <div class="detail-row">
                <div>
                    <div class="detail-label">Favorite Genre</div>
                    <div class="detail-value"><%= user.getFavoriteGenre() %></div>
                </div>
                <button class="edit-btn">Edit</button>
            </div>     
        </div>
    </div>
    <%@ include file="footer.jsp" %>
</body>
</html>

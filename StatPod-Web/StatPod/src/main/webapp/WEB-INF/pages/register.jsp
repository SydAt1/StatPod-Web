<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Register</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css">
</head>
<body>

<%@ include file="/WEB-INF/pages/header.jsp" %>

<main class="registration-container">
    <div class="register-header">
        <h2>Create Account</h2>
        <p>Join our community today</p>
    </div>

    <!-- Success message display -->
    <%
        String success = (String) request.getAttribute("success");
        if (success != null && !success.isEmpty()) {
    %>
        <div class="success-message">
            <p><%= success %></p>
        </div>
    <%
        }
    %>

    <!-- Error message display -->
    <%
        String error = (String) request.getAttribute("error");
        if (error != null && !error.isEmpty()) {
    %>
        <div class="error-message">
            <p><%= error %></p>
        </div>
    <%
        }
    %>

    <form action="<%= request.getContextPath() %>/register" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <input type="text" name="username" placeholder="Username" value="<%= request.getAttribute("username") != null ? request.getAttribute("username") : "" %>" required>
        </div>
        <div class="form-group">
            <input type="email" name="email" placeholder="Email Address" value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>" required>
        </div>
        <div class="form-group">
            <input type="text" name="displayName" placeholder="Display Name" value="<%= request.getAttribute("displayName") != null ? request.getAttribute("displayName") : "" %>" required>
        </div>
        <div class="form-group">
            <select name="favoriteGenre">
                <option value="">Select Favorite Genre</option>
			    <option value="1" <%= "1".equals(request.getAttribute("favoriteGenre")) ? "selected" : "" %>>Comedy</option>
			    <option value="2" <%= "2".equals(request.getAttribute("favoriteGenre")) ? "selected" : "" %>>Technology</option>
			    <option value="3" <%= "3".equals(request.getAttribute("favoriteGenre")) ? "selected" : "" %>>Music</option>
			    <option value="4" <%= "4".equals(request.getAttribute("favoriteGenre")) ? "selected" : "" %>>Business</option>
			    <option value="5" <%= "5".equals(request.getAttribute("favoriteGenre")) ? "selected" : "" %>>Education</option>
			    <option value="6" <%= "6".equals(request.getAttribute("favoriteGenre")) ? "selected" : "" %>>Horror</option>
			    <option value="7" <%= "7".equals(request.getAttribute("favoriteGenre")) ? "selected" : "" %>>True Crime</option>
			    <option value="8" <%= "8".equals(request.getAttribute("favoriteGenre")) ? "selected" : "" %>>History</option>
			    <option value="9" <%= "9".equals(request.getAttribute("favoriteGenre")) ? "selected" : "" %>>Science</option>
			    <option value="10" <%= "10".equals(request.getAttribute("favoriteGenre")) ? "selected" : "" %>>Health And Fitness</option>
			    <option value="11" <%= "11".equals(request.getAttribute("favoriteGenre")) ? "selected" : "" %>>Fiction</option>
			    <option value="12" <%= "12".equals(request.getAttribute("favoriteGenre")) ? "selected" : "" %>>Lifestyle</option>
                <%
                    Object genreList = request.getAttribute("genres");
                    Integer selectedGenre = null;
                    if (request.getAttribute("favoriteGenre") != null) {
                        try {
                            selectedGenre = Integer.parseInt(request.getAttribute("favoriteGenre").toString());
                        } catch (NumberFormatException ignored) {}
                    }
                    if (genreList != null && genreList instanceof java.util.List) {
                        java.util.List<com.statpod.model.GenreModel> genres = (java.util.List<com.statpod.model.GenreModel>) genreList;
                        for (com.statpod.model.GenreModel genre : genres) {
                %>
                    <option value="<%= genre.getGenreId() %>" <%= (selectedGenre != null && selectedGenre == genre.getGenreId()) ? "selected" : "" %>>
                        <%= genre.getGenreName() %>
                    </option>
                <%
                        }
                    }
                %>
            </select>
        </div>
        <div class="form-group">
            <input type="password" name="password" placeholder="Password" required>
        </div>
        <div class="form-group">
            <input type="password" name="confirmPassword" placeholder="Confirm Password" required>
        </div>
        <div class="form-group">
            <label for="imageUrl">Upload Profile Picture:</label>
            <input type="file" name="imageUrl" id="imageUrl" accept="image/*">
        </div>

        <div class="button-group">
            <button type="submit" class="register-btn">Register</button>
            <a href="<%= request.getContextPath() %>/login" class="login-link">Already have an account? Login</a>
        </div>
    </form>
</main>

<%@ include file="/WEB-INF/pages/footer.jsp" %>

</body>
</html>

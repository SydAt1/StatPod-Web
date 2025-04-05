<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Contact Us</title>
    <link rel="stylesheet" type="text/css" href="contact.css">
</head>
<body>

    <%@ include file="header.jsp" %>

    <main>
        <h2>Contact Us</h2>
        <form action="contactServlet" method="post">
            <input type="text" name="name" placeholder="Your Name" required><br>
            <input type="email" name="email" placeholder="Your Email" required><br>
            <textarea name="message" placeholder="Your Message" required></textarea><br>
            <button type="submit">Send Message</button>
        </form>
    </main>

    <%@ include file="footer.jsp" %>

</body>
</html>

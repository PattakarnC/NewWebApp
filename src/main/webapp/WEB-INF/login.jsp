<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>Home Page</title>
<body>
<p>
    ${error}
</p>
<p>
<form action="/login" method="post">
    <input type="text" placeholder="Enter Username" name="username" required><br>
    <input type="password" placeholder="Enter Password" name="password" required><br>
    <button type="submit">Login</button>
</form>
</p>
</body>
</html>
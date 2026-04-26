<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Read My Config File - scriptlets</title>
</head>
<body>
<%
    java.io.InputStream is = this.getClass().getClassLoader().getResourceAsStream("../myConf.ini");
    java.util.Properties p = new java.util.Properties();
    try {
        p.load(is);
        out.println("<p>Test1 = " + p.getProperty("Test1") + "</p>");
        out.println("<p>Test2 = " + p.getProperty("Test2") + "</p>");
        out.println("<p>Test3 = " + p.getProperty("Test3") + "</p>");
    } catch (Exception e) {
        out.println("<p>Błąd: " + e + "</p>");
    }
%>
</body>
</html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>JSP and JavaBean</title>
</head>
<body>
<jsp:useBean id="dateBean" scope="application" class="zti.beans.DateBean"/>
<h1>Odczyt danych z JavaBean</h1>
<br/>
<p>Dzisiaj jest: ${dateBean.currentDate}!</p>
</body>
</html>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="x" uri="jakarta.tags.xml" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSP JSTL XML</title>
</head>
<body>
<h1>JSP - Read xml file with JSTL</h1>
<c:import var="file" url="http://localhost:8080/ZTI_Lab01/Data/data.xml" charEncoding="UTF-8"/>
<x:parse var="doc" doc="${file}"/>
<table border="1">
    <tr>
        <th>Firstname</th>
        <th>Lastname</th>
        <th>Grade</th>
        <th>Type</th>
    </tr>
    <x:forEach select="$doc/students/student">
        <tr>
            <td><x:out select="data/fname"/></td>
            <td><x:out select="data/lname"/></td>
            <td><x:out select="results/grade"/></td>
            <td><x:out select="results/type"/></td>
        </tr>
    </x:forEach>
</table>
</body>
</html>

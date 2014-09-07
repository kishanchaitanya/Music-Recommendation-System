<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Insert title here</title>
</head>
<body>
<form:form method="POST" action="/MusicRecommendation/display" commandName="input">

<table>
    <tr>
        <td><form:label path="userId">UserId</form:label></td>
        <td><form:input path="userId" /></td>
    </tr>
    <tr>
        <td><form:label path="songId">SongId</form:label></td>
        <td><form:input path="songId" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
    
    </table>

</form:form>

<h3>Song Name : ${music}</h3>
</body>
</html>
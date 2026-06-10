<%@ page import ="java.net.*" %>

<html>
<head>
    <title>Get IP address </title>
</head>

<body>
<h2> Your IP address:</h2>
<%
    String ipaddress= request.getRemoteAddr();
    out.println(ipaddress);
%>
</body>
</html>
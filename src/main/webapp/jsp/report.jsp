<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@taglib uri="/birt.tld" prefix="birt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<script type="text/javascript">
	function resizeViewer() {
		document.getElementById("birtViewer").height=screen.height*0.8;
		document.getElementById("birtViewer").width=screen.width*0.8;
	}
</script>
<title>Insert title here</title>
</head>
<% String format = request.getParameter("format") == null ? "pdf" : request.getParameterValues("format")[0]; %>
<body onload="resizeViewer();">
	<birt:viewer 	id="birtViewer" 
					reportDesign="reports/EventLogger.rptdesign"
					pattern="frameset"
					isHostPage="true"
					title="Event Logger Report"
					height="0"
					width="0"
					format="<%= format %>"/>
</body>
</html>
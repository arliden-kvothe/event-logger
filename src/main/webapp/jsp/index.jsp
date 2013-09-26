<%@page import="java.util.List"%>
<%@page import="com.blackthorne.trader.eventlogger.Main"%>
<%@page import="com.blackthorne.trader.eventlogger.db.Event"%>
<%@page import="com.blackthorne.trader.eventlogger.db.System"%>
<form action="jsp/save.jsp" method="get">
	Event: <select name="eventId" size="1">
		<%
			List<Event> events = Main.getEvents();
			for (Event event : events) {
		%>
		<option value="<%=event.getId()%>"><%=event.getDescription()%></option>
		<%
			}
		%>
	</select><br /> System: <select name="systemId" size="1">
		<%
			List<System> systems = Main.getSystems();
			for (System system : systems) {
		%>
		<option value="<%=system.getId()%>"><%=system.getDescription()%></option>
		<%
			}
		%>
	</select><br />

	Description of Event:<input type="text" name="comments" /><br/>
 
	<input type="submit" value="Save Log" />
</form>

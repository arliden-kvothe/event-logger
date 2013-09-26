<%@page import="com.blackthorne.trader.eventlogger.Main"%>
<jsp:useBean id="row" class="com.blackthorne.trader.eventlogger.db.Log"/>
<jsp:setProperty property="*" name="row"/>

<% 
int i=Main.save(row);
if(i>0)
out.print("Log successfully saved");
%>
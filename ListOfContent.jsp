<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>一覧画面</title>
<link rel="stylesheet" type="text/css" href="./css/listofcontent.css">
</head>
<body>
<h1 id="header_list">一覧画面</h1>

<div id="wholestyle">

<%@ page import="java.util.ArrayList"%>
<%@ page import="controller.MemoData" %>
<% ArrayList<MemoData> list = (ArrayList<MemoData>)request.getAttribute("all_memo"); %>
<% for(MemoData d : list){ %>

	<div id="testcontent">
	
	<p id="content">タイトル：<%= d.getTitle() %></p>
	
	<form method="post" action="./DetailOfContent">
	<input type="hidden" name="memo_no" value="<%= d.getId() %>">
	<button id="detail" type="submit">詳細</button>
	</form>
	
	</div>
	
	<br> <% } %>
	

<form method="post" action="./NewEntry"> 
<button type="submit">新規登録</button>
</form>

</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>編集画面</title>
<link rel="stylesheet" type="text/css" href="./css/edit.css">
</head>
<body>

<%@ page import="java.util.ArrayList"%>
<%@ page import="controller.MemoData" %>
<% String id_of_memo = (String)request.getAttribute("memo_id"); %>
<% String title_of_memo = (String)request.getAttribute("memo_title"); %>
<% String content_of_memo = (String)request.getAttribute("memo_content"); %>

<h1 id="header_edit">編集画面</h1>

<div id=wholestyle>
	<!-- タイトルと内容の入力 -->
	<form method="post" action="./ListOfContent">
		<input name="update" type="hidden" value="true">
		<input name="edited_id" type="hidden" value="<%= id_of_memo %>">
		<label>タイトル<br><input id="title" type="text" name="edited_title" style="width:95%" maxlength="50" value="<%= title_of_memo%>"><br></label>
		<label>内容<br><textarea id="content" name="edited_content" style="width:95%"><%= content_of_memo%></textarea><br></label>
		<button type="submit" id="tourokubotan">登録する</button>
	</form>	
		
	<form method="post" action="./ListOfContent">
		<button type="submit">やめる</button> 	
	</form>
	
</div>

</body>
</html>
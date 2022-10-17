<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>詳細画面</title>
<link rel="stylesheet" type="text/css" href="./css/detailofcontent.css">
</head>
<body>

<%@ page import="java.util.ArrayList"%>
<%@ page import="controller.MemoData" %>
<% String id_of_memo = (String)request.getAttribute("memo_id"); %>
<% String title_of_memo = (String)request.getAttribute("memo_title"); %>
<% String content_of_memo = (String)request.getAttribute("memo_content"); %>
<h1 id="header_detail">詳細画面</h1>

<div id="wholestyle">

<dl id="contentdetail">

	<dt id="title">タイトル</dt>
	<dd id="titledetail"><%= title_of_memo %></dd>
	
	<dt>　</dt>
	<dd>　</dd>
	
	<dt id="content">内容</dt>
	<dd id="nakami"><%= content_of_memo %></dd>

</dl>

	<form method="post" action="./Edit">
		<label><input type="hidden" name="memo_no" value="<%= id_of_memo %>"></label>
		<button type="submit" id="hensyubotan">編集する</button>
	</form>
	
	<form method="post" action="./ListOfContent">
		<label><input type="hidden" name="delete_id" value="<%= id_of_memo %>"></label>
		<label><input type="hidden" name="delete" value="true"></label>
		<button type="submit" id="sakujobotan">削除する</button>
	</form>
	
	<form method="post" action="./ListOfContent">
		<button type="submit">戻る</button>
	</form>

</div>


</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録画面</title>
<link rel="stylesheet" type="text/css" href="./css/newentry.css">
</head>
<body>

<h1 id="header_newentry">新規登録画面</h1>

<div id=wholestyle>
	<!-- タイトルと内容の入力 -->
	<form method="post" action="./ListOfContent">
		<input type="hidden" name="new_entry" value="true">
		<label>タイトル<br><input id="title" type="text" name="title" style="width:95%" maxlength="50"><br></label>
		<label>内容<br><textarea id="content" name="content" style="width:95%" maxlength="1000"></textarea><br></label>
		<button type="submit" id="tourokubotan">登録する</button>
	</form>
	
	<form method="post" action="./ListOfContent">
		<button type="submit">やめる</button>
	</form>
	
</div>

</body>
</html>

<!-- button type="reset" は押すと内容がリセットされる-->
<!-- br は改行 -->
<!-- projectファイル起点は暗に了解されているのでfinalproject/,,,ではなく./で書く -->
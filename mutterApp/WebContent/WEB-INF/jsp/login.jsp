<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ツブヤイター</title>
<link rel="stylesheet" href="css/index.css">
</head>
<body>
	<div style="text-align: center">
		<div class="form-wrapper">
			<h1>hello</h1>
			<c:if test="${not empty errorMsg}">
				<div><c:out value="${errorMsg }" /></div>
			</c:if>
			<form action="/mutterApp/Login" method="post">
				<div class="form-item">
					<label for="email"></label>
					<input type="email" name="mailAddress" required="required" placeholder="メールアドレス">
				</div>
				<div class="form-item">
					<label for="password"></label>
					 <input type="password" name="password" required="required" placeholder="パスワード">
				</div>
				<div class="button-panel">
					<input type="submit" class="button" title="ログイン" value="ログイン">
				</div>
			</form>
			<div class="form-footer">
				<p>
					<a href="/mutterApp/Register">新規登録</a>
				</p>
			</div>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員登録完了</title>
<link rel="stylesheet" href="css/registerResult.css">
</head>
<body>
	<div style="text-align: center">
		<h2>会員登録が完了しました</h2>

		<table>
			<tr>
				<th>名前</th>
				<td>${user.userName}</td>
			<tr>
				<th>性別</th>
				<td>
					<c:if test="${'1' == user.genderKbn}">
						男性
					</c:if>
					<c:if test="${'2' == user.genderKbn}">
						女性
					</c:if>
					<c:if test="${'3' == user.genderKbn}">
						その他
					</c:if>
				</td>
			<tr>
				<th>生年月日</th>
				<td>${user.birthDate}</td>
			<tr>
				<th>メールアドレス</th>
				<td>${user.mailAddress}</td>
			<tr>
				<th>パスワード</th>
				<td>${user.password}</td>
		</table>
			<form action="/mutterApp/Login" method="get">
				<button type="submit">ログイン画面へ</button>
			</form>

	</div>
</body>
</html>
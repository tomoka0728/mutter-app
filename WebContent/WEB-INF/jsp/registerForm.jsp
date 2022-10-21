<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create an account</title>
<link rel="stylesheet" href="css/index.css">
</head>
<body>
	<div style="text-align: center">
		<div class="form-wrapper">
			<h1>新規登録</h1>
			<c:if test="${not empty errorMsg2}">
				<div>
					<c:out value="${errorMsg2 }" />
				</div>
			</c:if>
			<form action="/mutterApp/Register" method="post">
				<div class="form-item">
					<label for="name"></label>
					<input type="text" name="userName" required="required" placeholder="名前">
				</div>
				<div class="visually-hidden">
					<label for="sex"></label> <input type="radio" name="genderKbn"
						value="1">男性 <input type="radio" name="genderKbn"
						value="2">女性 <input type="radio" name="genderKbn"
						value="3">その他
				</div>
				<div class="birth">
					<label for="date"></label> <input type="date" name="birthDate">
				</div>
				<div class="form-item">
					<label for="email"></label> <input type="email" name="mailAddress"
						required="required" placeholder="メールアドレス">
				</div>
				<div class="form-item">
					<label for="password"></label> <input type="password"
						name="password" required="required"
						placeholder="パスワード(半角英数字/4文字以上)">
					<c:if test="${not empty errorMsg}">
						<div>
							<c:out value="${errorMsg }" />
						</div>
					</c:if>
				</div>

				<div class="button-panel">
					<input type="submit" class="button" title="登録" value="登録">
				</div>
			</form>
		</div>
	</div>
</body>
</html>
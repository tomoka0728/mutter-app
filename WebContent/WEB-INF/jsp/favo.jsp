<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MutterAppfavo</title>
<link rel="stylesheet" href="css/top.css">
</head>


<body>
	<div id="container" style="text-align: center">
		<header id="header">
			<img src="img/6123.png" width="5%"> <br>
			<b><c:out value="${user.userName}" /></b> <br>
			<br>

			<div style="text-align: center">
				<a href ="/mutterApp/Top"><img src="img/3533.png" width="20"></a>&emsp;
				<a href ="/mutterApp/Favo"><img src="img/8757.png"width="20"></a>&emsp;
				<a href ="/mutterApp/Login"><img src="img/4398.png" width="20"></a>
			</div>
		</header>

		<div id="pickup" class="wrapper">
			<div id="mutter">
				<div id="contents">
					<div class="main"></div>
					<form action="Top" method="post">
						<input type="text" name="mutter"> <input type="submit"
							name="button" value="つぶやく">
					</form>
					<c:if test="${not empty errorMsg}">
						<p>${errorMsg}</p>
					</c:if>
				</div>

				<c:forEach var="mutter" items="${mutterList }">
				<c:if test="${mutter.favOnFlg==1}">
					<div
						style="border: 1px solid #e9e9e9; border-radius: 5px; margin: 1em 0; padding: 2em;">

						<h5>
							<c:out value="${mutter.userName }" />
							&emsp;&emsp;
							<c:out value="${mutter.mutterYMDHM}" />
						</h5>
						<p>
							<c:out value="${mutter.mutter}" />
						<p>
							♡
							<c:out value="${mutter.likeCount}" />
							&emsp; 💭
							<c:out value="${mutter.commentCount}" />
						</p>
						<c:if test="${mutter.delVisFlg==1}">
							<form action="Top" method="post">
								<input type="hidden" name="mutterId" value="${mutter.mutterId }">
								<input type="hidden" name="likeCount"
									value="${mutter.likeCount }"> <input type="hidden"
									name="commentCount" value="${mutter.commentCount }"> <input
									type="submit" name="button" value="削除">
							</form>
						</c:if>
						<form action="Top" method="post">
							<input type="hidden" name="mutterId" value="${mutter.mutterId }">
							<input type="hidden" name="favOnFlg" value="${mutter.favOnFlg }">
							<c:if test="${mutter.favOnFlg==1}">
								<input type="submit" name="button" value="♡"
									style="color: #ffffff; background-color: #0000ff;">
							</c:if>
							<c:if test="${mutter.favOnFlg==0}">
								<input type="submit" name="button" value="♡">
							</c:if>
						</form>
						<form action="Top" method="post">
							<input type="hidden" name="mutterId" value="${mutter.mutterId }">
							<input type="text" name="comment"> <input type="submit"
								name="button" value="コメント">
						</form>
						<c:if test="${mutter.commentCount>0}">
							<div>
								<hr class="cp_hr02" />
								<h4>コメント</h4>
								<c:forEach var="comment" items="${mutter.commentList }">
									<div>
										<h5>
											<c:out value="${comment.userName }" />
											&emsp;&emsp;
											<c:out value="${comment.commentYMDHM }" />
										</h5>
										<p>
											<c:out value="${comment.comment }" />
										</p>

									</div>
									<hr class="cp_hr02" />
								</c:forEach>
							</div>
						</c:if>
					</div>
					</c:if>
				</c:forEach>
				<!-- メインコンテンツの内容を入力 -->
			</div>
		</div>
	</div>
</body>
</html>
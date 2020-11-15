<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" />
<fmt:bundle basename="properties.pagecontent" prefix="main.">
	<!DOCTYPE html>
	<html>
<head>
<title><fmt:message key="title" /></title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body id="main"
	style="background-image: url(${pageContext.request.contextPath}/img/back1.jpg);">
	<c:import url="common/header.jsp" />
	<c:choose>
		<c:when test="${ sessionScope.role=='admin' }">
			<c:import url="common/menu_admin.jsp" />
		</c:when>
		<c:when test="${ sessionScope.role=='doctor'}">
			<c:import url="common/menu_doctor.jsp" />
		</c:when>
		<c:when test="${ sessionScope.role=='nurse'}">
			<c:import url="common/menu_nurse.jsp" />
		</c:when>
	</c:choose>
	<main>
		<div class="container content" style="padding-top: 100px;">
			<div class="row">
				<div class="col-lg-12 center">
					<div>
						<h2 class="shadowed">
							<fmt:message key="greeting" />
							, ${sessionScope.login}!
						</h2>
					</div>
				</div>
			</div>
		</div>
	</main>
	<c:import url="common/footer.jsp" />
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
	</html>
</fmt:bundle>

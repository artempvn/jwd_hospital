<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" />
<fmt:bundle basename="properties.pagecontent" prefix="login.">
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
	<main class="content">
		<div class="container center ">
			<div class="row justify-content-lg-center">
				<div class="col-lg-4 ">
					<div>
						<h2 class="shadowed">
							<fmt:message key="header" />
						</h2>

						<form class="form center needs-validation" novalidate
							name="LoginForm" method="POST" action="controller">
							<input type="hidden" name="command" value="login" />
							<h4>
								<fmt:message key="login" />
							</h4>
							<input class="form-control form__input" type="text" name="login" class="form__input" required
								pattern="[_\w]{4,10}" />
							<div class="invalid-feedback">
								<fmt:message key="error.login.incorrect" />
							</div>
							<h4>
								<fmt:message key="password" />
							</h4>
							<input class="form-control form__input" type="password" name="password" class="form__input"
								required pattern="[!-~]{4,10}" />
							<div class="invalid-feedback">
								<fmt:message key="error.password.incorrect" />
							</div>
							<c:choose>
								<c:when test="${ error_login=='registered_status' }">
									<h5 class="text-danger">
										<fmt:message key="error.registered_status" />
									</h5>
								</c:when>
								<c:when test="${ error_login=='banned_status'}">
									<h5 class="text-danger">
										<fmt:message key="error.banned_status" />
									</h5>
								</c:when>
								<c:when test="${ error_login=='invalid'}">
									<h5 class="text-danger">
										<fmt:message key="error.invalid" />
									</h5>
								</c:when>
								<c:when test="${ error_login=='connection_error'}">
									<h5 class="text-danger">
										<fmt:message key="status.connection_error" />
									</h5>
								</c:when>
							</c:choose>
							<button class="form__btn button" type="submit">
								<fmt:message key="log_in" />
							</button>
						</form>
						<c:choose>
							<c:when test="${ status_message=='true' }">
								<h5 class="text-success shadowed">
									<fmt:message key="status.changed" />
								</h5>
							</c:when>
							<c:when test="${ status_message=='false'}">
								<h5 class="text-danger shadowed">
									<fmt:message key="status.not_changed" />
								</h5>
							</c:when>
							<c:when test="${ status_message=='connection_error'}">
								<h5 class="text-danger shadowed">
									<fmt:message key="status.connection_error" />
								</h5>
							</c:when>
						</c:choose>
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
<script type="text/javascript">
	<c:import url="../js/validation.js" />
</script>

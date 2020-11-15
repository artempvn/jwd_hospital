<%@ page isErrorPage="true" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" />
<fmt:bundle basename="properties.pagecontent" prefix="error.">
	<!DOCTYPE html>
	<html>
<head>
<title><fmt:message key="header" /></title>
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
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-6 form">
					<h2>
						<fmt:message key="header" />
					</h2>
					<fmt:message key="request" />
					: ${pageContext.errorData.requestURI} <br />
					<fmt:message key="servlet" />
					: ${pageContext.errorData.servletName} <br />
					<fmt:message key="status_code" />
					: ${pageContext.errorData.statusCode} <br />
					<fmt:message key="exception" />
					: ${pageContext.errorData.throwable} <br />
					<form name="Main" method="POST" action="controller">
						<input type="hidden" name="command" value="main" />
						<button class="form__btn" type="submit">
							<fmt:message key="main" />
						</button>
					</form>
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
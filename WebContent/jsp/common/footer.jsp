<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" />
<fmt:bundle basename="properties.pagecontent" prefix="footer.">
	<!DOCTYPE html>
	<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/main.css"
	rel="stylesheet">
</head>
<footer>
	<div class="container-fluid footer">
		<div class="row">
			<div class="col-lg-12">
				<div class="credits">
					<fmt:message key="author" />
					<br>
					<fmt:message key="project" />
					<br>
					<fmt:message key="location" />
				</div>

			</div>
		</div>
	</div>
</footer>
	</html>
</fmt:bundle>
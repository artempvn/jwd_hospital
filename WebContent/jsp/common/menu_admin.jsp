<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" />
<fmt:bundle basename="properties.pagecontent" prefix="menu.">
	<!DOCTYPE html>
	<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/main.css"
	rel="stylesheet">
</head>
<body>
	<nav class="navbar sticky-top navbar-expand-md navbar-dark">
		<div
			class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active">


					<form name="CreatePatient" method="POST" action="controller">
						<input type="hidden" name="command" value="create_user" />
						<button class="form__btn form__btn_menu" type="submit">
							<fmt:message key="create_user" />
						</button>
					</form>
				</li>
				<li class="nav-item">


					<form name="ShowStaff" method="POST" action="controller">
						<input type="hidden" name="command" value="show_staff" />
						<button class="form__btn form__btn_menu" type="submit">
							<fmt:message key="show_staff" />
						</button>
					</form>
				</li>
			</ul>
			<c:if
				test="${ sessionScope.current_command!=sessionScope.previous_command }">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item">

						<form name="BackForm" method="POST" action="controller">
							<input type="hidden" name="command" value="back_to_previous" />
							<button class="form__btn form__btn_menu" type="submit">
								<fmt:message key="back" />
							</button>
						</form>
					</li>
				</ul>
			</c:if>
		</div>
	</nav>
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
	</html>
</fmt:bundle>

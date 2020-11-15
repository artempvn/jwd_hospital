<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ctg" uri="customtags"%>
<fmt:setLocale value="${locale}" />
<fmt:bundle basename="properties.pagecontent" prefix="header.">
	<!DOCTYPE html>
	<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/animate.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/fontawesome/css/font-awesome.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/main.css"
	rel="stylesheet">
</head>
<body>
	<div class="container-fluid header">
		<div class="row">
			<div class="col-lg-1 icon">
				<i
					class="fa  fa-plus fa-5x animate__animated animate__flip animate__infinite animate__slow"></i>
			</div>
			<div class="col-lg-2 logo">
				<h2>
					<fmt:message key="logo" />
				</h2>
			</div>
			<div class="col-lg-4 ml-auto">
				<nav>
					<ul class="menu d-flex justify-content-center">
						<li class="menu__item">

							<form name="LocaleRuForm" method="POST" action="controller">
								<input type="hidden" name="command" value="set_locale_ru" />
								<button class="form__btn" type="submit">RU</button>
							</form>
						</li>
						<li class="menu__item">

							<form name="LocaleEnForm" method="POST" action="controller">
								<input type="hidden" name="command" value="set_locale_en" />
								<button class="form__btn" type="submit">EN</button>
							</form>

						</li>
						<li class="menu__item"><c:if
								test="${ sessionScope.role!=null }">
								<form name="Logout" method="POST" action="controller">
									<input type="hidden" name="command" value="logout" />
									<button class="form__btn" type="submit">
										<fmt:message key="logout" />
									</button>
								</form>
							</c:if></li>
					</ul>
					<c:if test="${ sessionScope.role!=null }">
						<fmt:message key="login" var="login" />
						<fmt:message key="role" var="role" />
						<ctg:show-user localeLogin="${login}" localeRole="${role}" />
					</c:if>
				</nav>
			</div>
		</div>
	</div>
</body>
	</html>
</fmt:bundle>
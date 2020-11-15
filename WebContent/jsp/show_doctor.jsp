<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" />
<fmt:bundle basename="properties.pagecontent">
	<!DOCTYPE html>
	<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">

<title><fmt:message key="show_doctor.header" /></title>
</head>
<body
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
	<br />
	<main class="content">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-6 form">
					<h2>
						<fmt:message key="show_doctor.header" />
					</h2>
					<table class="table table-bordered table-hover form">
						<tbody>
							<tr>
								<td><fmt:message key="create_user.name" /></td>
								<td>${staff.name}</td>
							</tr>
							<tr>
								<td><fmt:message key="create_user.surname" /></td>
								<td>${staff.surname}</td>
							</tr>
							<tr>
								<td><fmt:message key="create_user.second_name" /></td>
								<td>${staff.secondName}</td>
							</tr>
							<tr>
								<td><fmt:message key="create_user.role_title" /></td>
								<td><fmt:message key="create_user.role.${staff.role}" /></td>
							</tr>

							<tr>
								<td><fmt:message key="create_user.speciality_title" /></td>
								<td><c:choose>
										<c:when test="${ staff.role=='nurse' }">
												-
											</c:when>
										<c:when test="${ staff.role=='doctor'}">
											<fmt:message key="create_user.speciality.${staff.speciality}" />
										</c:when>
									</c:choose></td>
							</tr>
						<tbody>
					</table>
				</div>
			</div>
		</div>
	</main>
	<c:import url="common/footer.jsp" />
</body>
	</html>
</fmt:bundle>

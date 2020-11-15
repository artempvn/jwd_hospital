<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ctg" uri="customtags"%>
<fmt:setLocale value="${locale}" />
<fmt:bundle basename="properties.pagecontent" prefix="add_purpose.">
	<!DOCTYPE html>
	<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
<title><fmt:message key="header" /></title>
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
	<main class="content">
		<div class="container-fluid">
			<div class="row justify-content-around">
				<div class="col-lg-12 center">
					<h2 class="shadowed">
						<fmt:message key="header" />
					</h2>
					<fmt:message key="patient" var="patient" />
					<ctg:show-patient localePatient="${patient }" />
				</div>
				<div class="col-lg-5 form">
					<h2>
						<fmt:message key="header.drug" />
					</h2>
					<c:import url="add_drug.jsp" />
				</div>
				<div class="col-lg-5 form ">
					<h2>
						<fmt:message key="header.procedure" />
					</h2>
					<c:import url="add_procedure.jsp" />
				</div>
				<div class="col-lg-12 center">
					<c:if test="${ success_message=='true' }">
						<h5 class="text-success shadowed">
							<fmt:message key="success" />
						</h5>
					</c:if>
					<c:if test="${ error_patient=='incorrect' }">
						<h5 class="text-danger shadowed">
							<fmt:message key="error.patient.incorrect" />
						</h5>
					</c:if>
					<c:if test="${ error_diagnosis=='incorrect' }">
						<h5 class="text-danger shadowed">
							<fmt:message key="error.diagnosis.incorrect" />
						</h5>
					</c:if>
				</div>
			</div>
		</div>
	</main>
	<c:import url="common/footer.jsp" />
</body>
	</html>
</fmt:bundle>
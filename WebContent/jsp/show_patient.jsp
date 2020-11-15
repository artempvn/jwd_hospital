<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ctg" uri="customtags"%>
<fmt:setLocale value="${locale}" />
<fmt:bundle basename="properties.pagecontent" prefix="show_patient.">
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
	<br />
	<main class="content">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-6 form">
					<h2>
						<fmt:message key="header" />
					</h2>
					<table class="table table-bordered table-hover form">
						<tbody>
							<tr>
								<td><fmt:message key="name" /></td>
								<td>${patient.name}</td>
							</tr>
							<tr>
								<td><fmt:message key="surname" /></td>
								<td>${patient.surname}</td>
							</tr>
							<tr>
								<td><fmt:message key="second_name" /></td>
								<td>${patient.secondName}</td>
							</tr>
							<tr>
								<td><fmt:message key="birthday" /></td>
								<td><ctg:date-converter date="${patient.dateBirth}" /></td>
							</tr>

							<tr>
								<td><fmt:message key="admission_date" /></td>
								<td><ctg:date-converter date="${patient.admissionDate}" /></td>
							</tr>
							<tr>
								<td><fmt:message key="admission_diagnosis" /></td>
								<td><textarea readonly class="form-control" rows="5"
										name="admission_diagnosis">${patient.admissionDiagnosis}</textarea>
								</td>
							</tr>
							<tr>
								<td><fmt:message key="treatment_result" /></td>
								<td><textarea readonly class="form-control" rows="5"
										name="admission_diagnosis">${patient.treatmentResult}</textarea>
								</td>
							</tr>
							<tr>
								<td><fmt:message key="discharge_date" /></td>
								<td><c:if test="${patient.dischargeDate!=null }">
										<ctg:date-converter date="${patient.dischargeDate}" />
									</c:if></td>
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
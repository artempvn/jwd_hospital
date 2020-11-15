<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ctg" uri="customtags"%>
<fmt:setLocale value="${locale}" />
<fmt:bundle basename="properties.pagecontent"
	prefix="establish_diagnosis.">
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
			<div class="row">
				<div class="col-lg-5">
					<h2 class="shadowed">
						<fmt:message key="header" />
					</h2>
					<fmt:message key="patient" var="patient" />
					<ctg:show-patient localePatient="${patient }" />
					<form class="form center needs-validation" novalidate
						name="DiagnosisForm" method="POST" action="controller">
						<input type="hidden" name="command" value="establish_diagnosis" />
						<input type="hidden" name="patient_id" value="${patient_id }" />
						<h4>
							<fmt:message key="name" />
							*
						</h4>
						<textarea maxlength="1000" class="form-control" rows="5"
							name="name" required>${name}</textarea>
						<br>
						<c:if test="${ error_name=='incorrect' }">
							<h5 class="text-danger">
								<fmt:message key="error.name.incorrect" />
							</h5>
						</c:if>
						<h4>
							<fmt:message key="date" />
						</h4>
						<input class="limit-date-today form__input" name="date"
							value="${date}" type="date"></input> <br />
						<c:if test="${ error_date=='incorrect' }">
							<h5 class="text-danger">
								<fmt:message key="error.date.incorrect" />
							</h5>
						</c:if>
						<br />
						<button class="button form__btn" type="submit">
							<fmt:message key="register" />
						</button>
						<br />
						<c:if test="${ success_message=='true' }">
							<h5 class="text-success">
								<fmt:message key="success" />
							</h5>
						</c:if>
						<c:if test="${ error_patient=='incorrect' }">
							<h5 class="text-danger">
								<fmt:message key="error.patient.incorrect" />
							</h5>
						</c:if>
					</form>
				</div>
			</div>
		</div>
	</main>
	<c:import url="common/footer.jsp" />
</body>
	</html>
</fmt:bundle>
<script type="text/javascript">
	<c:import url="../js/input_time.js" />
	<c:import url="../js/input_date_limit_today.js" />
	<c:import url="../js/validation.js" />
</script>
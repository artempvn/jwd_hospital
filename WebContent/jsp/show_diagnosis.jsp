<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ctg" uri="customtags"%>
<fmt:setLocale value="${locale}" />
<fmt:bundle basename="properties.pagecontent" prefix="show_diagnosis.">
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
					<fmt:message key="patient" var="patient" />
					<ctg:show-patient localePatient="${patient }" />
					<table class="table table-bordered table-hover form">
						<tbody>
							<tr>
								<td><fmt:message key="diagnosis" /></td>
								<td>
									<form class="needs-validation" novalidate
										name="UpdateDiagnosisForm" method="POST" action="controller">
										<textarea maxlength="1000" required
											class="form-control disabled" rows="10" name="name">${diagnosis.get("diagnosis").name}</textarea>
										<input type="hidden" name="command" value="update_diagnosis" />
										<button class="form__btn form__btn_menu doctor_button"
											type="submit">
											<fmt:message key="save" />
										</button>
									</form> <c:if test="${ success_message=='true' }">
										<h5 class="text-success">
											<fmt:message key="success" />
										</h5>
									</c:if> <c:if test="${ error_name=='incorrect' }">
										<h5 class="text-danger">
											<fmt:message key="error.name.incorrect" />
										</h5>
									</c:if>
								</td>
							</tr>
							<tr>
								<td><fmt:message key="establishing_time" /></td>
								<td><ctg:date-converter
										date="${diagnosis.get(\"diagnosis\").date}" /></td>
							</tr>
							<tr>
								<td><fmt:message key="doctor" /></td>
								<td>

									<form name="ShowDoctor" method="POST" action="controller">
										<input type="hidden" name="command" value="show_doctor" /> <input
											type="hidden" name="login" value="${diagnosis.get(" established_doctor").login}" />
										<button class="form__btn form__btn_menu" type="submit">
											${diagnosis.get("established_doctor").name}
											${diagnosis.get("established_doctor").surname}</button>
									</form>
								</td>
							</tr>
						<tbody>
					</table>
				</div>
			</div>
		</div>
	</main>
	<c:import url="common/footer.jsp" />
</body>
<input type="hidden" id="role" name="role" value="${sessionScope.role }" />
	</html>
</fmt:bundle>
<script type="text/javascript">
	<c:import url="../js/button_blocker.js" />
	<c:import url="../js/validation.js" />
</script>
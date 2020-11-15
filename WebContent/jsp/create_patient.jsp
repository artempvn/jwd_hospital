<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" />
<fmt:bundle basename="properties.pagecontent" prefix="create_patient.">
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
	style="background-image: url(${pageContext.request.contextPath}/img/back1.jpg); ">
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
					<form class="form center needs-validation" novalidate
						name="RegistrationForm" method="POST" action="controller">
						<input type="hidden" name="command" value="create_patient" />
						<h4>
							<fmt:message key="name" />
							*
						</h4>
						<input type="text" name="name" value="${name}"
							class="form__input form-control" required
							pattern="[А-яA-z]{1,15}" />
						<div class="invalid-feedback">
							<fmt:message key="error.name.incorrect" />
						</div>
						<c:if test="${ error_name=='incorrect' }">
							<h5 class="text-danger">
								<fmt:message key="error.name.incorrect" />
							</h5>
						</c:if>
						<h4>
							<fmt:message key="surname" />
							*
						</h4>
						<input type="text" name="surname" value="${surname}"
							class="form__input form-control" required
							pattern="[А-яA-z]{1,15}" />
						<div class="invalid-feedback">
							<fmt:message key="error.surname.incorrect" />
						</div>
						<c:if test="${ error_surname=='incorrect' }">
							<h5 class="text-danger">
								<fmt:message key="error.surname.incorrect" />
							</h5>
						</c:if>
						<br />
						<h4>
							<fmt:message key="second_name" />
						</h4>
						<input type="text" name="second_name" value="${second_name}"
							class="form__input form-control" pattern="[А-яA-z]{1,15}" />
						<div class="invalid-feedback">
							<fmt:message key="error.second_name.incorrect" />
						</div>
						<c:if test="${ error_second_name=='incorrect' }">
							<h5 class="text-danger">
								<fmt:message key="error.second_name.incorrect" />
							</h5>
						</c:if>
						<br />
						<h4>
							<fmt:message key="birthday" />
							*
						</h4>
						<input required
							class="limit-date-before-now form__input form-control" id="date"
							name="birth_date" value="${birth_date}" type="date"></input>
						<c:if test="${ error_date=='incorrect' }">
							<h5 class="text-danger">
								<fmt:message key="error.date.incorrect" />
							</h5>
						</c:if>
						<br />
						<h4>
							<fmt:message key="admission_date" />
							*
						</h4>
						<input required
							class="limit-date-before-now form__input form-control" id="date"
							name="admission_date" value="${admission_date}" type="date"></input>
						<c:if test="${ error_admission_date=='incorrect' }">
							<h5 class="text-danger">
								<fmt:message key="error.date.incorrect" />
							</h5>
						</c:if>
						<br />
						<h4>
							<fmt:message key="admission_diagnosis" />
						</h4>
						<textarea class="form-control" rows="5" name="admission_diagnosis"
							maxlength="1000">${admission_diagnosis}</textarea>
						<br>
						<c:if test="${ error_admission_diagnosis=='incorrect' }">
							<h5 class="text-danger">
								<fmt:message key="error.admission_diagnosis.incorrect" />
							</h5>
						</c:if>
						<br />
						<button class="button form__btn" type="submit">
							<fmt:message key="register" />
						</button>
						<c:if test="${ success_message=='true' }">
							<h5 class="text-success">
								<fmt:message key="success" />
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
	<c:import url="../js/input_date_limit_before_now.js" />
	<c:import url="../js/validation.js" />
</script>

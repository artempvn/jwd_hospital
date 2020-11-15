<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" />
<fmt:bundle basename="properties.pagecontent" prefix="create_user.">
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
<body id="main"
	style="background-image: url(${pageContext.request.contextPath}/img/back1.jpg);">
	<c:import url="common/header.jsp" />
	<c:choose>
		<c:when test="${ sessionScope.role=='admin' }">
			<c:import url="common/menu_admin.jsp" />
		</c:when>
		<c:when test="${ sessionScope.role=='doctor'}">
			<c:import url="common/menu_doctor.jsp" />
		</c:when>
	</c:choose>
	<main class="content">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-3 center">
					<h2 class="shadowed">
						<fmt:message key="header" />
					</h2>
					<form class="form center needs-validation" novalidate
						name="RegistrationForm" method="POST" action="controller">
						<input type="hidden" name="command" value="create_user" />
						<h4>
							<fmt:message key="login" />
							*
						</h4>
						<input type="text" name="login" value="${new_login}"
							class="form-control form__input" required pattern="[_\w]{4,10}" />
						<div class="invalid-feedback">
							<fmt:message key="error.login.incorrect" />
						</div>
						<c:choose>
							<c:when test="${ error_login=='incorrect'}">
								<h5 class="text-danger">
									<fmt:message key="error.login.incorrect" />
								</h5>
							</c:when>
							<c:when test="${ error_login=='not unique'}">
								<h5 class="text-danger">
									<fmt:message key="error.login.not_unique" />
								</h5>
							</c:when>
						</c:choose>
						<br />
						<h4>
							<fmt:message key="email" />
							*
						</h4>
						<input type="text" name="email" value="${email}"
							class="form__input form-control" required
							pattern="([-.\w]{1,20}@[^\W_]{1,15}\.[a-zA-z]{2,5})" />
						<div class="invalid-feedback">
							<fmt:message key="error.email.incorrect" />
						</div>
						<c:choose>
							<c:when test="${ error_email=='incorrect'}">
								<h5 class="text-danger">
									<fmt:message key="error.email.incorrect" />
								</h5>
							</c:when>
							<c:when test="${ error_email=='not unique'}">
								<h5 class="text-danger">
									<fmt:message key="error.email.not_unique" />
								</h5>
							</c:when>
						</c:choose>
						<br />
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
						<br />
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
							<fmt:message key="role_title" />
							*
						</h4>
						<div class="input-group lg-3">
							<select required class="custom-select" id="role" name="role">
								<option disabled selected value=""><fmt:message
										key="choose_role" /></option>
								<c:if test="${new_role!=null}">
									<option selected value="${new_role}"><fmt:message
											key="role.${new_role}" /></option>
								</c:if>
								<option value="doctor">
									<fmt:message key="role.doctor" /></option>
								<option value="nurse">
									<fmt:message key="role.nurse" /></option>
							</select>
						</div>
						<c:if test="${ error_role=='incorrect' }">
							<h5 class="text-danger">
								<fmt:message key="error.role.incorrect" />
							</h5>
						</c:if>
						<br />
						<div id="speciality_div" style="display: none;">
							<h4>
								<fmt:message key="speciality_title" />
								*
							</h4>
							<div class="input-group lg-3">
								<select class="custom-select" id="spec" name="speciality">
									<option disabled selected value=""><fmt:message
											key="choose_speciality" /></option>
									<c:if test="${speciality!=null}">
										<option selected value="${speciality}"><fmt:message
												key="speciality.${speciality}" /></option>
									</c:if>
									<option value="physician">
										<fmt:message key="speciality.physician" /></option>
									<option value="dentist">
										<fmt:message key="speciality.dentist" /></option>
									<option value="surgeon">
										<fmt:message key="speciality.surgeon" /></option>
									<option value="urologist">
										<fmt:message key="speciality.urologist" /></option>
									<option value="neurologist">
										<fmt:message key="speciality.neurologist" /></option>
									<option value="psychiatrist">
										<fmt:message key="speciality.psychiatrist" /></option>
									<option value="otolaryngologist">
										<fmt:message key="speciality.otolaryngologist" /></option>
									<option value="dermatologist">
										<fmt:message key="speciality.dermatologist" /></option>
									<option value="cardiologist">
										<fmt:message key="speciality.cardiologist" /></option>
									<option value="rheumatologist">
										<fmt:message key="speciality.rheumatologist" /></option>
								</select>
							</div>
							<c:if test="${ error_speciality=='incorrect' }">
								<h5 class="text-danger">
									<fmt:message key="error.speciality.incorrect" />
								</h5>
							</c:if>
							<br />
						</div>
						<h4>
							<fmt:message key="password" />
							*
						</h4>
						<input type="password" name="password" value=""
							class="form__input form-control" required pattern="[!-~]{4,10}" />
						<div class="invalid-feedback">
							<fmt:message key="error.password.incorrect" />
						</div>
						<c:if test="${ error_password=='incorrect' }">
							<h5 class="text-danger">
								<fmt:message key="error.password.incorrect" />
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
<script type="text/javascript">
	<c:import url="../js/specialities_hider_1.js" />
	<c:import url="../js/specialities_hider_2.js" />
	<c:import url="../js/validation.js" />
</script>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ctg" uri="customtags"%>
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
<title><fmt:message key="add_attending_doctor.header" /></title>
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
						<fmt:message key="add_attending_doctor.header" />
					</h2>
					<fmt:message key="show_diagnosis.patient" var="patient" />
					<ctg:show-patient localePatient="${patient }" />
					<div class="form center">
						<h4>
							<fmt:message key="create_user.role_title" />
							*
						</h4>
						<form class="form center needs-validation" novalidate
							name="PurposeForm" method="POST" action="controller">
							<div class="input-group lg-3">
								<select required name="role" class="custom-select" id="role">
									<option disabled selected value=""><fmt:message
											key="create_user.choose_role" /></option>
									<c:if test="${new_role!=null}">
										<option selected value="${new_role}"><fmt:message
												key="create_user.role.${new_role}" /></option>
									</c:if>
									<option value="doctor">
										<fmt:message key="create_user.role.doctor" /></option>
									<option value="nurse">
										<fmt:message key="create_user.role.nurse" /></option>
								</select>
							</div>
							<div id="speciality_div" style="display: none;">
								<h4>
									<fmt:message key="create_user.speciality_title" />
									*
								</h4>

								<div class="input-group lg-3">
									<select required id="speciality" name="speciality"
										class="custom-select">
										<option disabled selected value=""><fmt:message
												key="create_user.choose_speciality" /></option>
										<c:if test="${speciality!=null}">
											<option value="${speciality}"><fmt:message
													key="create_user.speciality.${speciality}" /></option>
										</c:if>
										<option value="physician">
											<fmt:message key="create_user.speciality.physician" /></option>
										<option value="dentist">
											<fmt:message key="create_user.speciality.dentist" /></option>
										<option value="surgeon">
											<fmt:message key="create_user.speciality.surgeon" /></option>
										<option value="urologist">
											<fmt:message key="create_user.speciality.urologist" /></option>
										<option value="neurologist">
											<fmt:message key="create_user.speciality.neurologist" /></option>
										<option value="psychiatrist">
											<fmt:message key="create_user.speciality.psychiatrist" /></option>
										<option value="otolaryngologist">
											<fmt:message key="create_user.speciality.otolaryngologist" /></option>
										<option value="dermatologist">
											<fmt:message key="create_user.speciality.dermatologist" /></option>
										<option value="cardiologist">
											<fmt:message key="create_user.speciality.cardiologist" /></option>
										<option value="rheumatologist">
											<fmt:message key="create_user.speciality.rheumatologist" /></option>
									</select>
								</div>
							</div>
							<input type="hidden" name="command" value="add_attending_doctor" />
							<input type="hidden" name="patient_id" value="${patient_id}" />
							<br />
							<h4>
								<fmt:message key="establish_purpose.attending_doctor" />
								*
							</h4>
							<div class="input-group lg-3">
								<select required id="attending_doctor" name="attending_doctor"
									class="custom-select">
									<option disabled selected value=""><fmt:message
											key="establish_purpose.choose_attending_doctor" /></option>
									<c:forEach var="doctor" items="${medical_staff}">
										<option
											class="doctor-list ${doctor.role} ${doctor.speciality}"
											value="${doctor.login}" hidden="" disabled>${doctor.name}
											${doctor.surname}</option>
									</c:forEach>
								</select>
							</div>
							<c:if test="${ error_attending_doctor=='incorrect' }">
								<h5 class="text-danger">
									<fmt:message
										key="establish_purpose.error.attending_doctor.incorrect" />
								</h5>
							</c:if>
							<br />
							<button class="button form__btn" type="submit">
								<fmt:message key="establish_purpose.register" />
							</button>
							<br />
							<c:if test="${ success_message=='true' }">
								<h5 class="text-success">
									<fmt:message key="establish_purpose.success" />
								</h5>
							</c:if>
							<c:choose>
								<c:when test="${ error_patient_purpose=='incorrect' }">
									<h5 class="text-danger">
										<fmt:message key="establish_purpose.error.patient.incorrect" />
									</h5>
								</c:when>
								<c:when test="${ error_diagnosis_purpose=='incorrect'}">
									<h5 class="text-danger">
										<fmt:message key="establish_purpose.error.diagnosis.incorrect" />
									</h5>
								</c:when>
								<c:when test="${ error_role_purpose=='incorrect'}">
									<h5 class="text-danger">
										<fmt:message key="establish_purpose.error.role.incorrect" />
									</h5>
								</c:when>
							</c:choose>
						</form>
					</div>
				</div>
			</div>
		</div>
	</main>
	<c:import url="common/footer.jsp" />
</body>
	</html>
</fmt:bundle>
<script type="text/javascript">
	<c:import url="../js/specialities_hider_1.js" />
	<c:import url="../js/specialities_hider_3.js" />
	<c:import url="../js/validation.js" />
</script>
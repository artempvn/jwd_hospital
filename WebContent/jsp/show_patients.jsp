<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" />
<fmt:bundle basename="properties.pagecontent" prefix="show_patients.">
	<!DOCTYPE html>
	<html>
<head>
<link
	href="${pageContext.request.contextPath}/css/jquery.dataTables.min.css"
	rel="stylesheet">
<title><fmt:message key="header" /></title>
</head>
<body
	style="background-image: url(${pageContext.request.contextPath}/img/back1.jpg);">
	<c:import url="common/table_var_locale.jsp" />
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
				<div class="col-lg-12 center">

					<h2 class="shadowed">
						<fmt:message key="header" />
					</h2>
					<table id="table"
						class="table display table-bordered table-hover form">
						<thead class="thead-inverse">
							<tr>
								<th>№</th>
								<th><fmt:message key="id" /></th>
								<th><fmt:message key="name" /></th>
								<th><fmt:message key="surname" /></th>
								<th><fmt:message key="status" /></th>
								<th><fmt:message key="diagnosis" /></th>
								<th><fmt:message key="purpose" /></th>
								<th><fmt:message key="attending_doctor" /></th>
								<th><fmt:message key="discharge" /></th>
							</tr>
						<thead>
						<tbody>
							<c:if test="${ patients.size()==0 }">
								<tr>
									<td colspan="9"><fmt:message key="empty_list" /></td>
								</tr>
							</c:if>
							<c:forEach items="${patients}" var="patient">
								<tr>
									<td>${patients.indexOf(patient)+1}</td>
									<td>
										<form name="ShowPatient" method="POST" action="controller">
											<input type="hidden" name="command" value="show_patient" />
											<input type="hidden" name="patient_id" value="${patient.id}" />
											<button class="form__btn form__btn_menu" type="submit">
												${patient.id}</button>
										</form>
									</td>
									<td>${patient.name}</td>
									<td>${patient.surname}</td>
									<td><fmt:message key="status.${patient.status }" /></td>
									<td><c:choose>
											<c:when test="${ patient.status=='registered' }">
												<form name="EstablishDiagnosis" method="POST"
													action="controller">
													<input type="hidden" name="command"
														value="establish_diagnosis" /> <input type="hidden"
														name="patient_id" value="${patient.id}" />
													<button class="form__btn form__btn_menu doctor_button"
														type="submit">
														<fmt:message key="button.establish_diagnosis" />
													</button>
												</form>
											</c:when>
											<c:when
												test="${ patient.status=='on_medication'||patient.status=='discharged'}">
												<form name="ShowDiagnosis" method="POST" action="controller">
													<input type="hidden" name="command" value="show_diagnosis" />
													<input type="hidden" name="patient_id"
														value="${patient.id}" />
													<button class="form__btn form__btn_menu" type="submit">
														<fmt:message key="button.show_diagnosis" />
													</button>
												</form>
											</c:when>
										</c:choose></td>
									<td><c:choose>
											<c:when
												test="${ patient.status=='on_medication'||patient.status=='discharged'}">
												<c:if test="${ patient.anyPurpose=='true'}">
													<form name="ShowPurpose" method="POST" action="controller">
														<input type="hidden" name="command"
															value="show_patient_purpose" /> <input type="hidden"
															name="patient_id" value="${patient.id}" />
														<button class="form__btn form__btn_menu" type="submit">
															<fmt:message key="button.show_purpose" />
														</button>
													</form>
												</c:if>
												<c:if test="${ patient.status=='on_medication'}">
													<form name="AddPurpose" method="POST" action="controller">
														<input type="hidden" name="command" value="add_purpose" />
														<input type="hidden" name="patient_id"
															value="${patient.id}" />
														<button class="form__btn form__btn_menu doctor_button"
															type="submit">
															<fmt:message key="button.add_purpose" />
														</button>
													</form>
												</c:if>
											</c:when>
										</c:choose></td>
									<td><c:choose>
											<c:when
												test="${ patient.status=='on_medication'||patient.status=='discharged'}">
												<c:if test="${ patient.attendingDoctor!=null}">
													<form name="ShowDoctor" method="POST" action="controller">
														<input type="hidden" name="command" value="show_doctor" />
														<input type="hidden" name="login"
															value="${patient.attendingDoctor.login}" />
														<button class="form__btn form__btn_menu" type="submit">
															${ patient.attendingDoctor.name} ${ patient.attendingDoctor.surname}
														</button>
													</form>
												</c:if>
												<c:if test="${ patient.status=='on_medication'}">
													<form name="AddAttendingDoctor" method="POST"
														action="controller">
														<input type="hidden" name="command"
															value="add_attending_doctor" /> <input type="hidden"
															name="patient_id" value="${patient.id}" />
														<button class="form__btn form__btn_menu doctor_button"
															type="submit">
															<fmt:message key="button.add_doctor" />
														</button>
													</form>
												</c:if>
											</c:when>
										</c:choose></td>
									<td><c:choose>
											<c:when test="${ patient.status=='on_medication'}">
												<form name="Discharge" method="POST" action="controller">
													<input type="hidden" name="command"
														value="discharge_patient" /> <input type="hidden"
														name="patient_id" value="${patient.id}" />
													<button class="form__btn form__btn_menu doctor_button"
														type="submit">
														<fmt:message key="button.discharge" />
													</button>
												</form>
											</c:when>
										</c:choose></td>
								</tr>
							</c:forEach>
						<tbody>
					</table>
				</div>
				<div class="col-lg-12 center">
					<c:choose>
						<c:when test="${ discharged_message=='true'}">
							<h5 class="text-success shadowed">
								<fmt:message key="discharge.true" />
							</h5>
						</c:when>
						<c:when test="${ discharged_message=='false'}">
							<h5 class="text-danger shadowed">
								<fmt:message key="discharge.false" />
							</h5>
						</c:when>
					</c:choose>
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
	<c:import url="../js/jquery-3.5.1.js" />
	<c:import url="../js/jquery.dataTables.min.js" />
	<c:import url="../js/pagination.js" />
</script>

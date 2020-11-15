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
<link
	href="${pageContext.request.contextPath}/css/jquery.dataTables.min.css"
	rel="stylesheet">
<title><fmt:message key="show_patient_purpose.header" /></title>
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
	<input type="hidden" name="patient_id" value="${patient_id}" />
	<br />
	<main class="content">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12 center">
					<h2 class="shadowed">
						<fmt:message key="show_patient_purpose.header" />
						<fmt:message key="show_patient_purpose.patient" var="patient" />
						<ctg:show-patient localePatient="${patient }" />
					</h2>
					<h2 class="shadowed">
						<fmt:message key="show_patient_purpose.header.drug" />
					</h2>
					<table id="table"
						class="table display table-bordered table-hover form">
						<thead class="thead-inverse">
							<tr>
								<th>№</th>
								<th><fmt:message key="add_drug.name" /></th>
								<th><fmt:message key="add_drug.amount" /></th>
								<th><fmt:message key="add_drug.appointment_time" /></th>
								<th><fmt:message key="add_drug.delete" /></th>
							</tr>
						<thead>
						<tbody>
							<c:if test="${ purpose.get(\"drugs\").size()==0 }">
								<tr>
									<td colspan="9"><fmt:message
											key="show_patients.empty_list" /></td>
								</tr>
							</c:if>
							<c:forEach items="${purpose.get(\"drugs\")}" var="drug">
								<tr>
									<td>${purpose.get("drugs").indexOf(drug)+1}</td>
									<td><textarea readonly class="form-control" rows="4">${drug.name}</textarea>
									</td>
									<td><textarea readonly class="form-control" rows="4">${drug.amount}</textarea>
									</td>
									<td><textarea readonly class="form-control" rows="4">${drug.appointmentTime}</textarea>
									</td>
									<td><form name="DeleteDrug" method="POST"
											action="controller">
											<input type="hidden" name="command" value="delete_drug" /> <input
												type="hidden" name="drug_id" value="${drug.drugId}" />
											<button class="form__btn form__btn_menu doctor_button"
												type="submit">
												<fmt:message key="button.delete" />
											</button>
										</form></td>
								</tr>
							</c:forEach>
						<tbody>
					</table>
					<h2 class="shadowed">
						<fmt:message key="show_patient_purpose.header.procedure" />
					</h2>
					<table id="table1"
						class="table display table-bordered table-hover form">
						<thead class="thead-inverse">
							<tr>
								<th>№</th>
								<th><fmt:message key="add_procedure.name" /></th>
								<th><fmt:message key="add_procedure.type_title" /></th>
								<th><fmt:message key="add_procedure.date_start" /></th>
								<th><fmt:message key="add_procedure.date_end" /></th>
								<th><fmt:message key="add_procedure.delete" /></th>
							</tr>
						<thead>
						<tbody>
							<c:if test="${ purpose.get(\"procedures\").size()==0 }">
								<tr>
									<td colspan="9"><fmt:message
											key="show_patients.empty_list" /></td>
								</tr>
							</c:if>
							<c:forEach items="${purpose.get(\"procedures\")}" var="procedure">
								<tr>
									<td>${purpose.get("procedures").indexOf(procedure)+1}</td>
									<td><textarea readonly class="form-control" rows="4">${procedure.name}</textarea>
									</td>
									<td><fmt:message
											key="add_procedure.type.${procedure.type}" /></td>
									<td><ctg:date-converter date="${procedure.dateStart}" /></td>
									<td><ctg:date-converter date="${procedure.dateEnd}" /></td>
									<td><form name="DeleteProcedure" method="POST"
											action="controller">
											<input type="hidden" name="command" value="delete_procedure" />
											<input type="hidden" name="procedure_id"
												value="${procedure.procedureId}" />
											<button class="form__btn form__btn_menu doctor_button"
												type="submit">
												<fmt:message key="button.delete" />
											</button>
										</form></td>
								</tr>
							</c:forEach>
						<tbody>
					</table>
					<form name="AddPurpose" method="POST" action="controller">
						<input type="hidden" name="command" value="add_purpose" /> <input
							type="hidden" name="patient_id" value="${patient_id}" />
						<button class="button form__btn doctor_button" type="submit">
							<fmt:message key="show_patients.button.add_purpose" />
						</button>
					</form>
					<c:if test="${ success_message=='true' }">
						<h5 class="text-success shadowed">
							<fmt:message key="show_patient_purpose.success" />
						</h5>
					</c:if>
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
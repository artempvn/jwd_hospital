<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" />
<fmt:bundle basename="properties.pagecontent" prefix="add_procedure.">
	<!DOCTYPE html>
	<html>
<head>
<title></title>
</head>
<body>
	<form class="form center needs-validation" novalidate
		name="ProcedureForm" method="POST" action="controller">
		<input type="hidden" name="command" value="add_procedure" /> <input
			type="hidden" name="patient_id" value="${patient_id }" /> <br />
		<h4>
			<fmt:message key="type_title" />
			*
		</h4>
		<div class="input-group lg-3">
			<select required name="type" class="custom-select"
				id="inputGroupSelect02">
				<option disabled selected value=""><fmt:message
						key="choose_type" /></option>
				<c:if test="${type!=null}">
					<option selected value="${type}"><fmt:message
							key="type.${type}" /></option>
				</c:if>
				<option value="procedure">
					<fmt:message key="type.procedure" /></option>
				<option value="operation">
					<fmt:message key="type.operation" /></option>
			</select>
		</div>
		<br />
		<c:if test="${ error_type=='incorrect' }">
			<h5 class="text-danger">
				<fmt:message key="error.type.incorrect" />
			</h5>
		</c:if>
		<br />
		<h4>
			<fmt:message key="name" />
			*
		</h4>
		<textarea maxlength="1000" class="form-control" rows="3"
			name="procedure_name" required>${procedure_name}</textarea>
		<br> <br />
		<c:if test="${ error_procedure_name=='incorrect' }">
			<h5 class="text-danger">
				<fmt:message key="error.name.incorrect" />
			</h5>
		</c:if>
		<h4>
			<fmt:message key="date_start" />
			*
		</h4>
		<input id="main-event"
			class="limit-after-now form__input form-control" name="date_start"
			value="${date_start}" type="datetime-local" required></input> <br />
		<c:if test="${ error_date_start=='incorrect' }">
			<h5 class="text-danger">
				<fmt:message key="error.date_start.incorrect" />
			</h5>
		</c:if>
		<br />
		<h4>
			<fmt:message key="date_end" />
			*
		</h4>
		<input class="limit-after-event form__input form-control"
			name="date_end" value="${date_end}" type="datetime-local" required></input>
		<br />
		<c:if test="${ error_date_end=='incorrect' }">
			<h5 class="text-danger">
				<fmt:message key="error.date_end.incorrect" />
			</h5>
		</c:if>
		<br />
		<button class="button form__btn" type="submit">
			<fmt:message key="register" />
		</button>
		<br />
		<c:if test="${ doctor_removed_message=='true' }">
			<h5 class="text-warning">
				<fmt:message key="doctor_removed" />
			</h5>
		</c:if>
	</form>
</body>
	</html>
</fmt:bundle>
<script type="text/javascript">
	<c:import url="../js/input_time.js" />
	<c:import url="../js/input_time_limit_after_now.js" />
	<c:import url="../js/input_time_limit_after_event.js" />
	<c:import url="../js/validation.js" />
</script>
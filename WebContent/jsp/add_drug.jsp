<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" />
<fmt:bundle basename="properties.pagecontent" prefix="add_drug.">
	<!DOCTYPE html>
	<html>
<head>
<title></title>
</head>
<body>
	<form class="form center needs-validation" novalidate name="DrugForm"
		method="POST" action="controller">
		<input type="hidden" name="command" value="add_drug" /> <input
			type="hidden" name="patient_id" value="${patient_id}" /> <br />
		<h4>
			<fmt:message key="name" />
			*
		</h4>
		<textarea maxlength="1000" class="form-control" rows="3"
			name="drug_name" required>${drug_name}</textarea>
		<br>
		<c:if test="${ error_drug_name=='incorrect' }">
			<h5 class="text-danger">
				<fmt:message key="error.name.incorrect" />
			</h5>
		</c:if>
		<br />
		<h4>
			<fmt:message key="amount" />
			*
		</h4>

		<textarea maxlength="1000" class="form-control" rows="3" name="amount"
			required>${amount}</textarea>
		<br>
		<c:if test="${ error_amount=='incorrect' }">
			<h5 class="text-danger">
				<fmt:message key="error.amount.incorrect" />
			</h5>
		</c:if>
		<br />
		<h4>
			<fmt:message key="appointment_time" />
			*
		</h4>

		<textarea maxlength="1000" class="form-control" rows="3"
			name="appointment_time" required>${appointment_time}</textarea>
		<br>

		<c:if test="${ error_appointment_time=='incorrect' }">
			<h5 class="text-danger">
				<fmt:message key="error.appointment_time.incorrect" />
			</h5>
		</c:if>

		<br />
		<button class="button form__btn" type="submit">
			<fmt:message key="register" />
		</button>
		<br />
	</form>
</body>
	</html>
</fmt:bundle>
<script type="text/javascript">
	<c:import url="../js/input_time.js" />
	<c:import url="../js/input_time_limit_after_now.js" />
	<c:import url="../js/validation.js" />
</script>

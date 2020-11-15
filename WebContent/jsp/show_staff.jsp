<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" />
<fmt:bundle basename="properties.pagecontent">
	<!DOCTYPE html>
	<html>
<head>
<title><fmt:message key="show_staff.header" /></title>
<link
	href="${pageContext.request.contextPath}/css/jquery.dataTables.min.css"
	rel="stylesheet">
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
	</c:choose>
	<main class="content">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12 center">

					<h2 class="shadowed">
						<fmt:message key="show_staff.header" />
					</h2>

					<table id="table"
						class="table display table-bordered table-hover form">
						<thead class="thead-inverse">
							<tr>
								<th>№</th>
								<th><fmt:message key="create_user.name" /></th>
								<th><fmt:message key="create_user.surname" /></th>
								<th><fmt:message key="create_user.email" /></th>
								<th><fmt:message key="create_user.role_title" /></th>
								<th><fmt:message key="create_user.speciality_title" /></th>
								<th><fmt:message key="show_staff.status" /></th>
							</tr>
						<thead>
						<tbody>
							<c:if test="${ staff.size()==0 }">
								<tr>
									<td colspan="9"><fmt:message
											key="show_patients.empty_list" /></td>
								</tr>
							</c:if>
							<c:forEach items="${staff}" var="doctor">
								<tr>
									<td>${staff.indexOf(doctor)+1}</td>
									<td>
										<form name="ShowDoctor" method="POST" action="controller">
											<input type="hidden" name="command" value="show_doctor" /> <input
												type="hidden" name="login" value="${doctor.login}" />
											<button class="form__btn form__btn_menu" type="submit">
												${doctor.name}</button>
										</form>
									</td>
									<td><form name="ShowDoctor" method="POST"
											action="controller">
											<input type="hidden" name="command" value="show_doctor" /> <input
												type="hidden" name="login" value="${doctor.login}" />
											<button class="form__btn form__btn_menu" type="submit">
												${doctor.surname}</button>
										</form></td>
									<td>${doctor.email}</td>
									<td><fmt:message key="create_user.role.${doctor.role}" /></td>
									<td><c:choose>
											<c:when test="${ doctor.role=='nurse' }">
												-
											</c:when>
											<c:when test="${ doctor.role=='doctor'}">
												<fmt:message
													key="create_user.speciality.${doctor.speciality}" />
											</c:when>
										</c:choose></td>
									<td><fmt:message key="show_staff.status.${doctor.status}" />
										<c:choose>
											<c:when test="${ doctor.status=='activated' }">
												<form name="BanUser" method="POST" action="controller">
													<input type="hidden" name="command" value="ban_user" /> <input
														type="hidden" name="login" value="${doctor.login}" />

													<button class="form__btn form__btn_menu" type="submit">
														<fmt:message key="show_staff.button.ban" />
													</button>
												</form>
											</c:when>
											<c:when
												test="${ doctor.status=='registered'||doctor.status=='banned'}">
												<form name="UnbanUser" method="POST" action="controller">
													<input type="hidden" name="command" value="unban_user" />
													<input type="hidden" name="login" value="${doctor.login}" />
													<button class="form__btn form__btn_menu" type="submit">
														<fmt:message key="show_staff.button.unban" />
													</button>
												</form>
											</c:when>
										</c:choose>
								</tr>
							</c:forEach>
						<tbody>
					</table>
					<div class="col-lg-12 center">
						<c:if test="${ status_message =='connection_error'}">
							<h5 class="text-danger shadowed">
								<fmt:message key="login.status.connection_error" />
							</h5>
						</c:if>
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
	<c:import url="../js/jquery-3.5.1.js" />
	<c:import url="../js/jquery.dataTables.min.js" />
	<c:import url="../js/pagination.js" />
</script>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" />
<fmt:bundle basename="properties.pagecontent" prefix="table.">
	<!DOCTYPE html>
	<html>
<head>
</head>
<body>
	<fmt:message key="EmptyTable" var="EmptyTable" />
	<input id="EmptyTable" type="hidden" value="${ EmptyTable}" />

	<fmt:message key="Info" var="Info" />
	<input id="Info" type="hidden" value="${ Info}" />

	<fmt:message key="InfoEmpty" var="InfoEmpty" />
	<input id="InfoEmpty" type="hidden" value="${ InfoEmpty}" />

	<fmt:message key="InfoFiltered" var="InfoFiltered" />
	<input id="InfoFiltered" type="hidden" value="${ InfoFiltered}" />

	<fmt:message key="LengthMenu" var="LengthMenu" />
	<input id="LengthMenu" type="hidden" value="${ LengthMenu}" />

	<fmt:message key="LoadingRecords" var="LoadingRecords" />
	<input id="LoadingRecords" type="hidden" value="${ LoadingRecords}" />

	<fmt:message key="Processing" var="Processing" />
	<input id="Processing" type="hidden" value="${ Processing}" />

	<fmt:message key="Search" var="Search" />
	<input id="Search" type="hidden" value="${ Search}" />

	<fmt:message key="ZeroRecords" var="ZeroRecords" />
	<input id="ZeroRecords" type="hidden" value="${ ZeroRecords}" />

	<fmt:message key="First" var="First" />
	<input id="First" type="hidden" value="${ First}" />

	<fmt:message key="Last" var="Last" />
	<input id="Last" type="hidden" value="${ Last}" />

	<fmt:message key="Next" var="Next" />
	<input id="Next" type="hidden" value="${ Next}" />

	<fmt:message key="Previous" var="Previous" />
	<input id="Previous" type="hidden" value="${ Previous}" />

	<fmt:message key="SortAscending" var="SortAscending" />
	<input id="SortAscending" type="hidden" value="${ SortAscending}" />

	<fmt:message key="SortDescending" var="SortDescending" />
	<input id="SortDescending" type="hidden" value="${ SortDescending}" />

</body>
	</html>
</fmt:bundle>

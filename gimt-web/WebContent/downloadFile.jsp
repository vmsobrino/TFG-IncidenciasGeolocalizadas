<%@ page import="edu.gimt.view.GeoIncidenceView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String[] yearSel = request.getParameterValues("year");
	GeoIncidenceView geoIncView = (GeoIncidenceView)request.getAttribute("GeoIncidenceView");
	if (null != yearSel) {
		geoIncView.setYearsSelected(yearSel);
	}

%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport"
		content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="icon" href="./img/favicon.ico">
	
	<title>Incidencias Geo-Localizadas</title>
	
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
	<!-- Validator -->
	<link rel="stylesheet" href="//oss.maxcdn.com/jquery.bootstrapvalidator/0.5.2/css/bootstrapValidator.min.css">
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	<script src="//oss.maxcdn.com/jquery.bootstrapvalidator/0.5.2/js/bootstrapValidator.min.js"></script>
	<meta name="google-signin-scope" content="profile email">
	<meta name="google-signin-client_id" content="1051037700045-ibeuv0apliff5qem557abkunqbilh230.apps.googleusercontent.com">
	<!-- Custom styles for this template -->
	<link href="./css/signin.css" rel="stylesheet">
	<style>
		.has-feedback .form-control {
			padding-right: 0px !important;
		}
	</style>
</head>
<body>
	<div class="col-xs-3 col-sm-3 col-lg-1" style="display: flex">
		<div style="display: grid; margin-left: 35px">
			<a href="showDiagnostic.action">Diagn&oacute;stico</a>
			<a href="loginAction.action">Mapa</a>
			<a href="logoutAction.action">Logout</a>
		</div>
	</div>

	<div class="container">
		<h2 style="margin-top: 0px; margin-bottom: 10px;"
			class="form-signin-heading text-center">GIMT - Generaci&oacute;n de ficheros de datos (.csv)</h2>
		<h4 style="margin-top: 0px; margin-bottom: 10px;"
			class="form-signin-heading text-center">Seleccione los a&ntilde;os:</h4>
		<br>
		<div class="col-lg-6 col-lg-offset-3">
			<form id="download" action="downloadFile" method="post"
				class="form-horizontal"
				data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
				data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
				data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">

				<!-- div class="form-group">
					<label class="col-lg-6 control-label">A&ntilde;o/s:</label>
				</div-->
				
				<div class="form-group">
					<select class="col-lg-offset-6 col-lg-3" required="required" multiple id="year" name="year" id="1" size="5" data-bv-notempty-message="Este valor es necesario">
						<% out.print(geoIncView.getDistinctYears()); %>
					</select>
				</div>

				<div class="form-group">
					<div class="col-lg-offset-6 col-lg-3">
						<button type="submit" class="btn btn-default"><b>Generar</b></button>
					</div>
				</div>
				
			</form>

		</div>
		<script>
			$(document).ready(function() {
				$('#diagnostic').bootstrapValidator();
			});
		</script>
	</div>
	<!-- /container -->
</body>
</html>
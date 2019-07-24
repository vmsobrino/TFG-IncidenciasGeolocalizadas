<%@ page import="edu.gimt.view.DiagnosticView"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	DiagnosticView dv = (DiagnosticView)request.getAttribute("diagnostico");
	String vuelcos = request.getParameter("vuelcos");
	String accidentesInvierno = request.getParameter("accidentesInvierno");
	String accidentesOtono = request.getParameter("accidentesOtono");
	String nivelVerdeBlanco = request.getParameter("nivelVerdeBlanco");
	String nivelAmarillo = request.getParameter("nivelAmarillo");
	String accidentes1218 = request.getParameter("accidentes1218");
	String accidentes1800 = request.getParameter("accidentes1800");
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
			<a href="showDownloadFile.action" style="margin-top: 0px">Ficheros</a>
			<a href="loginAction.action">Mapa</a>
			<a href="logoutAction.action">Logout</a>
		</div>
	</div>

	<div class="container">
		<h2 style="margin-top: 0px; margin-bottom: 10px;"
			class="form-signin-heading text-center">GIMT - Diagn&oacute;stico sobre Tipo de Zona</h2>
		<h4 style="margin-top: 0px; margin-bottom: 10px;"
			class="form-signin-heading text-center">Introduzca los siguientes par&aacute;metros para un a&ntilde;o y partido judicial:</h4>
		<br>
		<div class="col-lg-6 col-lg-offset-3">
			<form id="diagnostic" action="diagnosticAction" method="post"
				class="form-horizontal"
				data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
				data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
				data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">

				<div class="form-group">
					<label class="col-lg-6 control-label">Vuelcos</label>
					<div class="col-lg-6">
						<input required="required" type="text" class="form-control" name="vuelcos" id="1" <% if (null != vuelcos) { out.print("value=\"" + vuelcos + "\""); } %>
							data-bv-integer="true"
							data-bv-integer-message="El valor no es un n&uacute;mero entero"
							data-bv-notempty-message="Este valor es necesario" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-6 control-label">Accidentes en invierno</label>
					<div class="col-lg-6">
						<input required="required" type="text" class="form-control" name="accidentesInvierno" <% if (null != accidentesInvierno) { out.print("value=\"" + accidentesInvierno + "\""); } %>
							id="2" data-bv-integer="true"
							data-bv-integer-message="El valor no es un n&uacute;mero entero"
							data-bv-notempty-message="Este valor es necesario" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-6 control-label">Accidentes en oto&ntilde;o</label>
					<div class="col-lg-6">
						<input required="required" type="text" class="form-control" name="accidentesOtono" <% if (null != accidentesOtono) { out.print("value=\"" + accidentesOtono + "\""); } %>
							id="3" data-bv-integer="true"
							data-bv-integer-message="El valor no es un n&uacute;mero entero"
							data-bv-notempty-message="Este valor es necesario" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-6 control-label">Accidentes nivel Verde/Blanco</label>
					<div class="col-lg-6">
						<input required="required" type="text" class="form-control" name="nivelVerdeBlanco" <% if (null != nivelVerdeBlanco) { out.print("value=\"" + nivelVerdeBlanco + "\""); } %>
							id="4" data-bv-integer="true"
							data-bv-integer-message="El valor no es un n&uacute;mero entero"
							data-bv-notempty-message="Este valor es necesario" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-lg-6 control-label">Accidentes nivel amarillo</label>
					<div class="col-lg-6">
						<input required="required" type="text" class="form-control" name="nivelAmarillo" <% if (null != nivelAmarillo) { out.print("value=\"" + nivelAmarillo + "\""); } %>
							id="5" data-bv-integer="true"
							data-bv-integer-message="El valor no es un n&uacute;mero entero"
							data-bv-notempty-message="Este valor es necesario" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-lg-6 control-label">Accidentes entre 12 y 18 h.</label>
					<div class="col-lg-6">
						<input required="required" type="text" class="form-control" name="accidentes1218" <% if (null != accidentes1218) { out.print("value=\"" + accidentes1218 + "\""); } %>
							id="6" data-bv-integer="true"
							data-bv-integer-message="El valor no es un n&uacute;mero entero"
							data-bv-notempty-message="Este valor es necesario" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-lg-6 control-label">Accidentes entre 18 y 00 h.</label>
					<div class="col-lg-6">
						<input required="required" type="text" class="form-control" name="accidentes1800" <% if (null != accidentes1800) { out.print("value=\"" + accidentes1800 + "\""); } %>
							id="7" data-bv-integer="true"
							data-bv-integer-message="El valor no es un n&uacute;mero entero"
							data-bv-notempty-message="Este valor es necesario" />
					</div>
				</div>

				<div class="form-group">
					<div class="col-lg-offset-6 col-lg-3">
						<button type="submit" class="btn btn-default"><b>Diagn&oacute;stico</b></button>
					</div>
				</div>
				
				<input type="hidden" name="cluster" value="<% if (null != dv) { out.print(dv.getCluster()); } %>">

			</form>

	        <% if (null != dv && dv.getCluster().equals("")) { %>
				<div class="col-lg-offset-1 col-lg-12">
					<div class="alert alert-success" role="alert"><b>Los datos no permiten realizar un diagn&oacute;stico del Tipo de Zona</b></div>
				</div>
			<% } %>
	        <% if (null != dv) { %>
				<div class="col-lg-offset-1 col-lg-12">
					<div class="alert alert-success" role="alert">El Tipo de Zona para estos valores se considera de: <b><% out.print(dv.getCluster()); %></b></div>
				</div>
			<% } %>
			
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
<%@ page import="edu.gimt.view.GeoIncidenceView"%>
<%@ page import="edu.gimt.view.IncidenceTypeView"%>
<%@ page import="edu.gimt.view.ProvinceView"%>
<%@ page import="edu.gimt.model.IncidenceBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>


<%
	// allow access only if session exists
	/*
	String user = null;
	if (session.getAttribute("user") == null) {
		response.sendRedirect("./login.jsp");
	}
	else {
		user = (String)session.getAttribute("user");
	}
	String userName = null;
	String sessionID = null;
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("user")) {
				userName = cookie.getValue();
			}
			if (cookie.getName().equals("JSESSIONID")) {
				sessionID = cookie.getValue();
			}
		}
	}
	else {
		sessionID = session.getId();
	}
	*/

	// Possible selection
	String[] provinceSel = request.getParameterValues("province");
	String[] incidenceTypeSel = request.getParameterValues("incidenceType");
	String[] yearSel = request.getParameterValues("year");
	String[] monthSel = request.getParameterValues("month");
	String[] dayOfWeekSel = request.getParameterValues("dayOfWeek");
	String dateSel = request.getParameter("datePicker");
	String[] scheduleSel = request.getParameterValues("schedule");
	String visualizationSel = request.getParameter("visualization");

	// Search criteria
	ProvinceView provinceView = (ProvinceView)request.getAttribute("ProvinceView");
	if (null != provinceSel) {
		provinceView.setSelected(provinceSel);
	}
	IncidenceTypeView typeView = (IncidenceTypeView)request.getAttribute("IncidenceTypeView");
	if (null != incidenceTypeSel) {
		typeView.setSelected(incidenceTypeSel);
	}
	GeoIncidenceView geoIncView = (GeoIncidenceView)request.getAttribute("GeoIncidenceView");
	if (null != yearSel) {
		geoIncView.setYearsSelected(yearSel);
	}
	if (null != monthSel) {
		geoIncView.setMonthsSelected(monthSel);
	}
	if (null != dayOfWeekSel) {
		geoIncView.setDaysSelected(dayOfWeekSel);
	}
	if (null != scheduleSel) {
		geoIncView.setScheduleSelected(scheduleSel);
	}
	
	ArrayList<IncidenceBean> matches = (ArrayList<IncidenceBean>)request.getAttribute("matches");
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es-ES">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
		<title>Visualizaci&oacute;n de Incidencias - Euskadi</title>
		
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<!-- jQuery library -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<!-- Latest compiled JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<!-- Maps API -->		
		<script src="https://maps.googleapis.com/maps/api/js?key=API_KEY&libraries=visualization"></script>
		<!-- DateTimePicker -->
		<script src="./js/moment-with-locales.min.js"></script>
		<link href="./css/bootstrap-datetimepicker.min.css" rel="stylesheet">
		<script src="./js/bootstrap-datetimepicker.min.js"></script>
		<script src="https://apis.google.com/js/platform.js?onload=init" async defer></script>
		<meta name="google-signin-scope" content="profile email">
		<meta name="google-signin-client_id" content="API_KEY">

		<style>
			select {
				width: 100% !important;
				font-size: 13px;
			}
			.row {
				margin-right: 0px !important;
				margin-left: 0px !important;
			}
			.container-fluid {
				padding-right: 0px !important;
				padding-left: 0px !important;
			}
			html, body {
				height: 100%;
				margin: 0;
				padding: 0;
			}
			#wrapper {
				height: 100%;
				width: 100%;
			}
			#googleMap {
				height: 90%;
				width: 100%;
			}
			#top-panel {
				height: 10%;
				width: 100%;
				margin: 2px;
				border-width: 2px;
				text-align: left;
				padding-top: 0;
				top: auto;
				font-size: 13px;
			}
			
			.col-lg-1, .col-lg-10, .col-lg-11, .col-lg-12, .col-lg-2, .col-lg-3, .col-lg-4, .col-lg-5, .col-lg-6, .col-lg-7, .col-lg-8, .col-lg-9, .col-md-1, .col-md-10, .col-md-11, .col-md-12, .col-md-2, .col-md-3, .col-md-4, .col-md-5, .col-md-6, .col-md-7, .col-md-8, .col-md-9, .col-sm-1, .col-sm-10, .col-sm-11, .col-sm-12, .col-sm-2, .col-sm-3, .col-sm-4, .col-sm-5, .col-sm-6, .col-sm-7, .col-sm-8, .col-sm-9, .col-xs-1, .col-xs-10, .col-xs-11, .col-xs-12, .col-xs-2, .col-xs-3, .col-xs-4, .col-xs-5, .col-xs-6, .col-xs-7, .col-xs-8, .col-xs-9 {
			    padding-right: 5px;
			    padding-left: 5px;
			}
		</style>
		
		<script type="text/javascript">
			$(function() {
				$('#year').click(function(){
					enableDisableDatePicker();
				});		
				$('#month').click(function(){
					enableDisableDatePicker();
				});	
				$('#dayOfWeek').click(function(){
					enableDisableDatePicker();
				});		
				$('#datePicker').on('dp.change', function(e) {
					var formatedValue;
					if (e.date == false) {
						formatedValue = '';
					}else{
						formatedValue = e.date.format(e.date._f)
						}
					enableDisableSelects(formatedValue); 
				})
			});

			function enableDisableDatePicker() {
				if ($('#year').val().length > 0 || $('#month').val().length > 0 || $('#dayOfWeek').val().length > 0) {
					$('#datePicker').datetimepicker('disable');
				}
				else {
					$('#datePicker').datetimepicker('enable');
				}
			}

			function enableDisableSelects(date) {
				if (date != '') {
					$('#year').attr('disabled', true);
					$('#month').attr('disabled', true);
					$('#dayOfWeek').attr('disabled', true);
				}
				else {
					$('#year').attr('disabled', false);
					$('#month').attr('disabled', false);
					$('#dayOfWeek').attr('disabled', false);
				}
				
			}
			
			
			// Relacion de incidencias geolocalizadas
		    var incidences = [
<%
		if (null != matches && matches.size() > 0) {
			IncidenceBean bean = null;
			for (int i = 0; i < matches.size(); i++) {
				bean = matches.get(i);
				if (null == visualizationSel || (null != visualizationSel && visualizationSel.equals("markers"))) { // markers
					out.println("['" + bean.getDivInfo() + "', " + 
							   bean.getLatitudeString() + ", " + 
							   bean.getLongitudeString() + ", '" +
							   bean.getMarkerIcon() + "']" + 
							   ((i+1)<matches.size()?",":""));
				}
				else { // heatmap
					out.println("new google.maps.LatLng(" + 
							    bean.getLatitudeString() + ", " + 
							    bean.getLongitudeString() + ", )" +
							    ((i+1)<matches.size()?",":""));
				}
			}
		}
%>				     			    
			];

			function initialize() { // Carga del Mapa
     			var myCenter = new google.maps.LatLng(42.977348,-2.5846565); // Centro aprox. Euskadi
     			var mapProp = {
     				center:myCenter,
     				zoom:10, // 1:Mundo, 5:Tierra firme y continente, 10:Ciudad, 15:Calles,	20: Edificios
     				mapTypeId:google.maps.MapTypeId.ROADMAP // MapTypeId.ROADMAP, SATELLITE, HYBRID y TERRAIN
     			};
     			var map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
	     			<% if (null == visualizationSel || (null != visualizationSel && visualizationSel.equals("markers"))) {
	     			      out.println("setMarkers(map, incidences);");
	     			   }
	     			   else { // heatmap
	     			      out.println("setHeatMap(map, incidences);");
	     			   }
	     			%>
			}

			function setHeatMap(map, locations) {
				var heatmap = new google.maps.visualization.HeatmapLayer({
					data: locations
				});
		        var gradient = [
					'rgba(0, 255, 255, 0)',
					'rgba(0, 255, 255, 1)',
					'rgba(0, 191, 255, 1)',
					'rgba(0, 127, 255, 1)',
					'rgba(0, 63, 255, 1)',
					'rgba(0, 0, 255, 1)',
					'rgba(0, 0, 223, 1)',
					'rgba(0, 0, 191, 1)',
					'rgba(0, 0, 159, 1)',
					'rgba(0, 0, 127, 1)',
					'rgba(63, 0, 91, 1)',
					'rgba(127, 0, 63, 1)',
					'rgba(191, 0, 31, 1)',
					'rgba(255, 0, 0, 1)'
				];
				heatmap.set('gradient', gradient);
				//heatmap.set('radius', 20);
				heatmap.set('opacity', 0.6);
				heatmap.setMap(map);
			}

			function setMarkers(map, locations) {
				var bounds = new google.maps.LatLngBounds();
				var infoWindows = new Array();
			    for (var i = 0; i < locations.length; i++) {
			    	var loc = locations[i];
			        var coords = new google.maps.LatLng(loc[1], loc[2]);
			        var contentString = loc[0];
			        var infowindow = new google.maps.InfoWindow({content: contentString});
			        infoWindows.push(infowindow);
			        var marker = new google.maps.Marker({
			        	position: coords,
			            map: map,
			            icon: './img/' + loc[3],
			            //title: loc[0],
			            //zIndex: i
					});
			        google.maps.event.addListener(marker, 'click', function (infowindow, marker) {
						return function () {
							for (var i = 0; i < infoWindows.length; i++) {
								infoWindows[i].close();
							}
			            	infowindow.open(map, marker);
						};
					}(infowindow, marker));
					google.maps.event.addListener(map, 'click', function(infowindow, marker) {
						return function () {
							for (var i = 0; i < infoWindows.length; i++) {
								infoWindows[i].close();
							}
						};
					}(infowindow, marker));
			        bounds.extend(coords);
			        map.fitBounds(bounds);
				}
			}
		</script>

	</head>
	<body onload="initialize()">
	
		<div id="wrapper" class="container-fluid">
	
			<div class="row">
			
				<form action="<s:url action='searchAction'/>" method="post" id="searchCriteria">
			
					<div class="col-xs-6 col-sm-6 col-lg-1">
						<div class="form-group">
			  				<label for="province" style="font-size: 13px;">Provincia:</label><br>
							<select multiple id="province" name="province">
								<% out.print(provinceView.getHtmlOptions()); %>
							</select>
						</div>
					</div>
					
					<div class="col-xs-6 col-sm-6 col-lg-2">
						<div class="form-group">
			  				<label for="incidenceType" style="font-size: 13px;">Tipo de Incidencia:</label><br>
							<select multiple id="incidenceType" name="incidenceType">
								<% out.print(typeView.getHtmlOptions()); %>
							</select>
						</div>
					</div>
					
					<div class="col-xs-4 col-sm-4 col-lg-1">
						<div class="form-group">
			  				<label for="year" style="font-size: 13px;">Año:</label><br>
							<select multiple id="year" name="year">
								<% out.print(geoIncView.getDistinctYears()); %>
							</select>
						</div>
					</div>
					
					<div class="col-xs-4 col-sm-4 col-lg-1">
						<div class="form-group">
			  				<label for="month" style="font-size: 13px;">Mes:</label><br>
							<select multiple id="month" name="month">
								<% out.print(geoIncView.getMonths()); %>
							</select>
						</div>
					</div>
					
					<div class="col-xs-4 col-sm-4 col-lg-1">
						<div class="form-group">
			  				<label for="dayOfWeek" style="font-size: 13px;">D&iacute;a:</label><br>
							<select multiple id="dayOfWeek" name="dayOfWeek">
								<% out.print(geoIncView.getDays()); %>
							</select>
						</div>
					</div>
					
					<div class="col-xs-6 col-sm-6 col-lg-2" style="width:150px">
		  				<label for="datePicker" style="font-size: 13px;">Fecha:</label><br>
	  		            <div class="form-group">
			                <div class="input-group date" id="datePicker" style="width: 140px;">
			                    <input type="text" class="form-control" name="datePicker" <% if (null != dateSel) { out.print("value=\"" + dateSel + "\""); } else { if(null != yearSel || null != monthSel || null != dayOfWeekSel) {out.print("disabled=\"disabled\"");} } %>/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>
       					</div>
					</div>
					<script type="text/javascript">
						$(function () {
							$('#datePicker').datetimepicker({
				                format: 'DD/MM/YYYY',
				                useCurrent: false
				            });
						});
					</script>
					
					<div class="col-xs-6 col-sm-6 col-lg-2">
						<div class="form-group">
			  				<label for="schedule" style="font-size: 13px;">Horario:</label><br>
							<select multiple id="schedule" name="schedule">
								<% out.print(geoIncView.getSchedule()); %>
							</select>
						</div>
					</div>
					
					<div class="col-xs-3 col-sm-3 col-lg-1">
						<br>
						<div class="form-check">
					  		<input class="form-check-input" type="radio" name="visualization" id="markers" value="markers" <% if (null == visualizationSel || (null != visualizationSel && visualizationSel.equals("markers"))) { out.print("checked"); } %>>
					  		<label class="form-check-label" for="markers" style="font-weight: normal; font-size: small;">Marcador</label>
						</div>
						<div class="form-check">
					  		<input class="form-check-input" type="radio" name="visualization" id="heatMap" value="heatMap"  <% if (null != visualizationSel && visualizationSel.equals("heatMap")) { out.print("checked"); } %>>
					  		<label class="form-check-label" for="heatMap" style="font-weight: normal; font-size: small;">HeatMap</label>
						</div>
					</div>
					<script>
					function clearFormElements(element) {
					    $(element).find(':input').each(function() {
					        switch(this.type) {
					            case 'password':
					            case 'select-multiple':
					            case 'select-one':
					            case 'text':
					            case 'textarea':
					                $(this).val('');
					                break;
					            case 'checkbox':
					            case 'radio':
					                this.checked = false;
					        }
					    });
					    $('#datePicker').datetimepicker('enable');
					    $("#year").prop('disabled', false);
					    $("#month").prop('disabled', false);
					    $("#dayOfWeek").prop('disabled', false);
					    $("#visualization").prop('checked', true);
					    $("input[name='visualization'][value='markers']").prop('checked', true);
					}
					</script>
					<div class="col-xs-3 col-sm-3 col-lg-1" style="display:flex">
						<div>
							<br>
							<input type="submit" id="submit" class="btn btn-default btn-sm" value="Buscar" style="background-color: green;color:#fff"><br><br>
							<input type="button" id="resetButton" class="btn btn-default btn-sm" value="Limpiar" onclick="clearFormElements(this.form)"><br>
						</div>
						<div style="display:grid;margin-left:35px">
							<a href="showDownloadFile.action" style="margin-top:20px">Ficheros</a>
							<a href="showDiagnostic.action">Diagn&oacute;stico</a>
							<a href="logoutAction.action">Logout</a>
						</div>
					</div>
					<script>
					  function signOut() {
					    var auth2 = gapi.auth2.getAuthInstance();
					    auth2.signOut().then(function () {
					      window.location('login.jsp');
					    });
					  }
					</script>				
				</form>
	
			</div>
			
			<div id="googleMap" class="row" style="height:520px">
			</div>
			
		</div> <!-- wrapper -->
	</body>
 </html>
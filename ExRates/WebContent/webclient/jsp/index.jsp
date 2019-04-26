<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<link rel="stylesheet" type="text/css" href="/ExRates/webclient/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="/ExRates/webclient/css/datatables.min.css"/>
		<link rel="stylesheet" type="text/css" href="/ExRates/webclient/css/fontawesome.all.min.css"/>
		<title>Exchange Rates</title>
	</head>
	<body>
	<div class="content">
        <div class="container">
    	    <div class="row text-center">
    			<div class="col mt-3 mb-3">
    				<i class="fas fa-bullhorn fa-3x"></i>
    				<h1 class="section-header">&nbsp;Exchange Rates (Base : <s:property value="%{exRateDataObj.base}"/>)</h1>
    				<h6>Last Update Time : <s:property value="%{lastUpdateTime}"/></h6>
    			</div>
    		</div>
    		<div class="row text-center">
    			<div class="col mt-3 mb-3">
    				<form action="home.action" id="homeForm"></form>
					<button class="btn btn-primary" type="submit" form="homeForm" value="Submit">Refresh Data</button> 
    			</div>
    		</div>
        
    	    <div class="row text-justified" data-aos="fade-up">
    			<div class="col mb-3">
    				<div class="table-responsive">
    					<table id="rateTable" class="table" width="100%">
    						<thead>
    							<tr>
    								<th scope="col"><s:text name="label.Currency"></s:text></th>
    								<th scope="col"><s:text name="label.Rate"></s:text></th>
    							</tr>
    						</thead>
    						<tbody>
    							<s:iterator var="currData" value="exRateDataObj.rates">
    							<s:set var="curr_name" value="#currData.key"/>
    							<s:set var="curr_value" value="#currData.value"/>
    							<tr>
    								<td><s:property value="#curr_name"/></td>
              						<td><s:property value="#curr_value"/></td>
    							</tr>
    							</s:iterator>
    						</tbody>
    					</table>
    				</div>
    			</div>
    		</div>
    
    	</div>
	</div>
		
		
		<script type="text/javascript" src="/ExRates/webclient/js/jquery-3.3.1.min.js"></script>
		<script type="text/javascript" src="/ExRates/webclient/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="/ExRates/webclient/js/datatables.min.js"></script>
		<script type="text/javascript" src="/ExRates/webclient/js/fontawesome.all.min.js"></script>
		<script type="text/javascript" src="/ExRates/webclient/js/index.js"></script>
	</body>
</html>
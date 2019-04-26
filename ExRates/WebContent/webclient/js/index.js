$(document).ready(function() {
	initRateTable();
});

function initRateTable()
{
	$('#rateTable').DataTable( {
	"responsive" : true,
	"processing" : true,
	"scrollX" : true
	});
}

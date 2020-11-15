var EmptyTable=document.getElementById('EmptyTable').value
var Info=document.getElementById('Info').value
var InfoEmpty=document.getElementById('InfoEmpty').value
var InfoFiltered=document.getElementById('InfoFiltered').value
var LengthMenu=document.getElementById('LengthMenu').value
var LoadingRecords=document.getElementById('LoadingRecords').value
var Processing=document.getElementById('Processing').value
var Search=document.getElementById('Search').value
var ZeroRecords=document.getElementById('ZeroRecords').value
var First=document.getElementById('First').value
var Last=document.getElementById('Last').value
var Next=document.getElementById('Next').value
var Previous=document.getElementById('Previous').value
var SortAscending=document.getElementById('SortAscending').value
var SortDescending=document.getElementById('SortDescending').value

$(document).ready(function() {
    $('#table').DataTable( {
    	 language: {
    "sEmptyTable":     EmptyTable,
    "sInfo":           Info,
    "sInfoEmpty":      InfoEmpty,
    "sInfoFiltered":   InfoFiltered,
    "sInfoPostFix":    "",
    "sInfoThousands":  ",",
    "sLengthMenu":     LengthMenu,
    "sLoadingRecords": LoadingRecords,
    "sProcessing":     Processing,
    "sSearch":         Search,
    "sZeroRecords":    ZeroRecords,
    "oPaginate": {
        "sFirst":    First,
        "sLast":     Last,
        "sNext":     Next,
        "sPrevious": Previous
    },
    "oAria": {
        "sSortAscending":  SortAscending,
        "sSortDescending": SortDescending
    }
},

    		pagingType: "full_numbers",
    		 lengthMenu: [  5, 10, 25, 50, 100 ]
    });
} );

$(document).ready(function() {
    $('#table1').DataTable( {
    	 language: {
    "sEmptyTable":     EmptyTable,
    "sInfo":           Info,
    "sInfoEmpty":      InfoEmpty,
    "sInfoFiltered":   InfoFiltered,
    "sInfoPostFix":    "",
    "sInfoThousands":  ",",
    "sLengthMenu":     LengthMenu,
    "sLoadingRecords": LoadingRecords,
    "sProcessing":     Processing,
    "sSearch":         Search,
    "sZeroRecords":    ZeroRecords,
    "oPaginate": {
        "sFirst":    First,
        "sLast":     Last,
        "sNext":     Next,
        "sPrevious": Previous
    },
    "oAria": {
        "sSortAscending":  SortAscending,
        "sSortDescending": SortDescending
    }
},

    		pagingType: "full_numbers",
    		 lengthMenu: [  5, 10, 25, 50, 100 ]
    });
} );



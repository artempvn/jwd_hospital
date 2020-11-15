var startDateElement=document.getElementById("main-event");
var elements=document.getElementsByClassName("limit-after-event");
var elements1=startDateElement.getAttributeNames();
startDateElement.addEventListener('change', function(){	 
for(var i=0;i<elements.length;i++)
 {
 elements[i].setAttribute("min",startDateElement.value );
 }
});


 
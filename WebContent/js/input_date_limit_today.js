var elements=document.getElementsByClassName("limit-date-today");
for(var i=0;i<elements.length;i++)
 {
 elements[i].setAttribute("value", todayDate);
 elements[i].setAttribute("readonly","readonly");
 
 }
 
 
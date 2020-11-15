var elements=document.getElementsByClassName("doctor_button"); 
var elementsDis=document.getElementsByClassName("disabled");
var role=document.getElementById("role").value;
for(var i=0;i<elements.length;i++){
	if (role=="nurse"){
		 elements[i].setAttribute("disabled","");
	elements[i].style="background: #708090";
	}
 }
 for(var i=0;i<elementsDis.length;i++){
	if (role=="nurse"){
		 elementsDis[i].setAttribute("readonly","");
	}
 }
var d = document,
role = d.getElementById('role'),
spec1 = d.getElementById('speciality'),
doctors_select = d.getElementById('attending_doctor'),
doctors = d.getElementsByClassName("doctor-list");
role.addEventListener('change', function(){
for (var i=0;i<doctors.length;i++){
	if(role.value=='nurse'&& doctors[i].classList.contains("nurse")){
		doctors[i].removeAttribute("hidden");
		doctors[i].removeAttribute("disabled");
	} else {
		doctors[i].setAttribute("hidden","");
		doctors[i].setAttribute("disabled","");
	}
}
});
			
role.addEventListener( 'change', function(){
doctors_select.selectedIndex = 0;
});

spec1.addEventListener( 'change', function(){
doctors_select.selectedIndex = 0;
});

spec1.addEventListener('change', function(){
for (var i=0;i<doctors.length;i++){
	if(doctors[i].classList.contains(spec1.value)){
		doctors[i].removeAttribute("hidden");
		doctors[i].removeAttribute("disabled");
	} else {
		doctors[i].setAttribute("hidden","");
		doctors[i].setAttribute("disabled","");
	}
}
});	

role.addEventListener('change', function(){
	if (role.value=="doctor"){
		
	
for (var i=0;i<doctors.length;i++){
	if(doctors[i].classList.contains(spec1.value)){
		doctors[i].removeAttribute("hidden");
		doctors[i].removeAttribute("disabled");
	} else {
		doctors[i].setAttribute("hidden","");
		doctors[i].setAttribute("disabled","");
	}
}
}
});	
var d = document,
role = d.getElementById('role'),
spec = d.getElementById('speciality_div');
spec1=d.getElementById('spec');
role.addEventListener('change', function(){
spec.style.display = role.value=="doctor" ? 'block' : 'none';
if (role.value=="doctor"){
spec1.required=true;
}else {
spec1.required=false;
}
});
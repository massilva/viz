$(document).ready(function(){
	 inputRequired();
});

//Add '*' before inputs required
var inputRequired = function(){
	$("input[required='required']").before("<span class='required'>*</span>");	
};
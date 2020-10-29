$('#myModal').on('hidden.bs.modal', function() {
	location.reload();
})

//document.getElementById('checkbox1').addEventListener('change', (e) => {
//  this.checkboxValue = e.target.checked ? '1' : '0';
//  console.log(this.checkboxValue)
//})
//
//document.getElementById('checkbox1').addEventListener('change', (e) => {
//  this.checkboxValue = e.target.checked ? '1' : '0';
//  console.log(this.checkboxValue)
//})
//
//document.getElementById('checkbox2').addEventListener('change', (e) => {
//  this.checkboxValue = e.target.checked ? '1' : '0';
//  console.log(this.checkboxValue)
//})
//
//document.getElementById('checkbox3').addEventListener('change', (e) => {
//  this.checkboxValue = e.target.checked ? '1' : '0';
//  console.log(this.checkboxValue)
//})
//
//document.getElementById('checkbox4').addEventListener('change', (e) => {
//  this.checkboxValue = e.target.checked ? '1' : '0';
//  console.log(this.checkboxValue)
//})
//
//document.getElementById('checkbox5').addEventListener('change', (e) => {
//  this.checkboxValue = e.target.checked ? '1' : '0';
//  console.log(this.checkboxValue)
//})


$(document).ready(function() {
			$("#search").focus(function() {
				$(".search-box").addClass("border-searching");
				$(".search-icon").addClass("si-rotate");
			});
			$("#search").blur(function() {
				$(".search-box").removeClass("border-searching");
				$(".search-icon").removeClass("si-rotate");
			});
			$("#search").keyup(function() {
				if ($(this).val().length > 0) {
					$(".go-icon").addClass("go-in");
				} else {
					$(".go-icon").removeClass("go-in");
				}
			});
			$(".go-icon").click(function() {
				$(".search-form").submit();
			});
		});


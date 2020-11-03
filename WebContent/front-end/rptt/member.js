var form = $('#myForm');

form.bootstrapValidator({

	fields : {

		rptt_content : {
			validators : {
				notEmpty : {
					message : '原因不能為空'
				},

			}
		}
	}

});

$('#myForm').submit(function(event){
	event.preventDefault();
	var $form= $(this);
	
	
	$a.jax({
        type:'post',
        url:$form.attr('action'),
		data:$form.serialize(),
		success: function(data){
			alert("send");
		},
		
		error:function(error){
			alert(fail);
		}
	});
	
});
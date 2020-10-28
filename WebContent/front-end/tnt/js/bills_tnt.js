        /*------------------------------------------------------------------
        [ Origin-Register ]*/
        (function($) {
            "use strict";

            /*
             * ================================================================== 
             *  [ Focus Contact2 ]
             */
            $('.register100').each(function() {
                $(this).on('blur', function() {
                    if ($(this).val().trim() != "") {
                        $(this).addClass('has-val');
                    } else {
                        $(this).removeClass('has-val');
                    }
                })
            })

            /*
             * ================================================================== 
             * [ Validate for each input By myself]
             */
            // 信箱
            $('#tnt_email.register100').on('blur', function() {
                if (/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/.test($(this).val().trim()) === false) {
                    // if (validate($(this)) == false) {
                    showValidate($(this));
                }
            })
            /*
             * ================================================================== 
             * [ Shrink Bar ]
             */
            
            


        })(jQuery);

        /*
         * ================================================================== 
         * [ Validate for next button]
         */

        var showValidate = function(input) {
            var thisAlert = $(input).parent();

            $(thisAlert).addClass('alert-validate');
        }

        var hideValidate = function(input) {
            var thisAlert = $(input).parent();

            $(thisAlert).removeClass('alert-validate');
        }


        $(document).ready(function() {
            
            //借放這裡 幫身分資料focus and blur
            var inputs = $('form:eq(0) .validate-input .register100');
            var selects = $('form:eq(0) select.wrap-register100');
            for (var i = 0; i < inputs.length; i++) {
                    $(inputs[i]).focus();
                    $(inputs[i]).blur();
            }
            for (var i = 0; i < selects.length; i++) {
                $(selects[i]).focus();
                $(selects[i]).blur();
            }
            
            /*
             * ================================================================== 
             * [ Shrink]
             */
            $('.text-primary:eq(1)').click(function(){  
//        		$('#basicinfolist').show();
            	$('.shrink').animate({left:'-72px'},'fast',function(){
            		$('.basicInfo__list-backIcon').css('text-align','right');
            	});
            	$('.shrink').fadeOut('fast');
            	$('#basicinfolist').animate({left:'0px'},'normal');
			});
			$('.text-primary:eq(0)').click(function(){
				$('#basicinfolist').animate({left:'-320px'},'fast',function(){
            		$('.basicInfo__list-backIcon').css('text-align','center');
            	});
            	$('.shrink').fadeIn('fast');
            	$('.shrink').animate({left:'0px'},'fast');
//            	$('#basicinfolist').hide();
			});
			
        });
 
     
        /*
         * ================================================================== 
         *    [ validateAll(the form) ]
         */

        function validateAll(theform) {
            console.log(theform);
            //selects
            var selects = theform.find('select.wrap-register100');
            selects.each(function() {
                if ($(this).val().trim() == '') {
                    $(this).addClass('alert-validate-selects');
                }
            });

            //inputs
            var inputs = theform.find('.validate-input .register100');
            var check = true;

            for (var i = 0; i < inputs.length; i++) {
                if ($(inputs).val().trim() == '') {
                    showValidate(inputs[i]);
                    check = false;
                }
            }

            inputs.each(function() {
                $(this).focus(function() {
                    hideValidate(this);
                });
            });

            return check;
        }

        /*
         * ================================================================== 
         *    [ Angle-down-up ]
         */

        $('.angleUpDown').each(function(index, element) {
            $(this).click(function() {
                var infoformwrap = $(this).closest('div');
                //              infoformwrap.children('form').toggle();  //form toggle
                ////                 $(this).closest('div').children('form').toggle();  
                //                 var angleDown = '.angleDown:eq(' + index + ')';  //angleDownUP toggle
                //                 var angleUp = '.angleUp:eq(' + index + ')';
                //                 $(angleDown).toggle();
                //                 $(angleUp).toggle();
                //                 
                //                 if(infoformwrap.css('padding-bottom')==='48px'){ //padding-bottom toggle
                //                  pbtmp = '10px';
                //                 }else{
                //                    pbtmp = '48px';
                //                 }
                //                 infoformwrap.css('padding-bottom',pbtmp);
                //                 infoformwrap.children('div.container-login100-form-btn').toggle(); //btn toggle
                togglethefrom(infoformwrap, index);
            });
        });

        function togglethefrom(infoformwrap, index) {
            infoformwrap.children('form').toggle(); //form toggle 
            var angleDown = '.angleDown:eq(' + index + ')'; //angleDownUP toggle
            var angleUp = '.angleUp:eq(' + index + ')';
            $(angleDown).toggle();
            $(angleUp).toggle();

            if (infoformwrap.css('padding-bottom') === '48px') { //padding-bottom toggle
                pbtmp = '10px';
            } else {
                pbtmp = '48px';
            }
            infoformwrap.css('padding-bottom', pbtmp);
            infoformwrap.children('div.container-login100-form-btn').toggle(); //btn toggle
        }


        /*
         * ================================================================== 
         *    [ Info List Icon click ]
         */
        $('a.awrapBigHeadPic').click(function() {
            if ($('.info-form-wrap:eq(0)').css('padding-bottom') === '48px') {
                togglethefrom($('.info-form-wrap:eq(0)'), 0);
            }
            if ($('.info-form-wrap:eq(1)').css('padding-bottom') !== '48px') {
                togglethefrom($('.info-form-wrap:eq(1)'), 1);
            }
            if ($('.info-form-wrap:eq(2)').css('padding-bottom') === '48px') {
                togglethefrom($('.info-form-wrap:eq(2)'), 2);
            }

        });


        /*
         * ================================================================== 
         *    [ 按鈕Ajax infoProfile ]
         */

      
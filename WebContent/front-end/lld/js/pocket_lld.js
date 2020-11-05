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
            //下拉 - 銀行
            $('#lld_bank').on('blur', function() {
                if ($('#lld_bank').val().trim() == '') {
                    $('#lld_bank').addClass('alert-validate');
                    $('#lld_bank').addClass('alert-validate-select');
                } else {
                    $('#lld_bank').addClass('select-has-val');
                }
            })
            $('#lld_bank').on('focus', function() {
                $('#lld_bank').removeClass('alert-validate');
                $('#lld_bank').removeClass('alert-validate-select');
                $('#lld_bank').removeClass('alert-validate-selects');
            })

            // 銀行分行
            $('#lld_bankbranch.register100').on('blur', function() {
                if (/^[(\u4e00-\u9fa5)(a-zA-Z)]{2,}$/.test($(this).val().trim()) === false) {
                    showValidate($(this));
                }
            })
            // 銀行帳號
            $('#lld_bankacc.register100').on('blur', function() {
                if (/^[\d]{8,}$/.test($(this).val().trim()) === false) {
                    showValidate($(this));
                }
            })

            // 信用卡卡號
            $('#lld_card.register100').on('blur', function() {
                if (/^[\d]{8,16}$/.test($(this).val().trim()) === false) {
                    showValidate($(this));
                } else if (validateCreditCard($(this).val().trim()) !== true) {
                    showValidate($(this));
                }
            })
            // 信用卡安全碼
            $('#lld_cardsvc.register100').on('blur', function() {
                if (/^[\d]{3}$/.test($(this).val().trim()) === false) {
                    showValidate($(this));
                }
            })
            // 信用卡到期日
            var today = new Date();
            var thisYear = today.getFullYear();
            var thisMonth = today.getMonth() + 1;

            $('#lld_carddue.register100').on('blur', function() {
                if ($('#lld_carddue').val().trim() == '') {
                    showValidate($(this));
                }
                if ($('#lld_carddue').val().substring(0, 4) < thisYear.toString()) {
                    showValidate($(this));
                }
                var mm = ($('#lld_carddue').val().substring(5, 6) === '0') ? $('#lld_carddue').val().substring(6, 7) : $('#lld_carddue').val().substring(5, 7);
                if (($('#lld_carddue').val().substring(0, 4) === thisYear.toString()) && (mm < thisMonth)) {
                    showValidate($(this));
                }
            })

            function validateCreditCard(s) {
                // remove non-numerics
                var v = '0123456789',
                    w = '',
                    i, j, k, m, c, a, x;

                for (i = 0; i < s.length; i++) {
                    x = s.charAt(i);

                    if (v.indexOf(x, 0) !== -1) {
                        w += x;
                    }
                }
                // validate number
                j = w.length / 2;
                k = Math.floor(j);
                m = Math.ceil(j) - k;
                c = 0;

                for (i = 0; i < k; i++) {
                    a = w.charAt(i * 2 + m) * 2;
                    c += a > 9 ? Math.floor(a / 10 + a % 10) : a;
                }

                for (i = 0; i < k + m; i++) {
                    c += w.charAt(i * 2 + 1 - m) * 1;
                }

                return c % 10 === 0;
            }
            
            //借放-信用卡效期預設 最小值:this month
            var strThisMonth = thisYear + '-' + thisMonth;
            $('#lld_carddue').attr('min',strThisMonth);

        })(jQuery);
        
        /*
         * ================================================================== 
         *    [ Quick Input ]
         */
        var quickInputData = ['中國信託', '中壢分行','369540377895','4182308700046443','497','2025-03'];

        $('#quickpick').click(function() {
        	$('#lld_bank').focus();
        	$('#lld_bank').val(quickInputData[0]);
        	$('#lld_bank').blur();
        	$('#lld_bankbranch').focus();
        	$('#lld_bankbranch').val(quickInputData[1]);
        	$('#lld_bankbranch').blur();
        	$('#lld_bankacc').focus();
        	$('#lld_bankacc').val(quickInputData[2]);
        	$('#lld_bankacc').blur();
        	$('#lld_card').focus();
        	$('#lld_card').val(quickInputData[3]);
        	$('#lld_card').blur();
        	$('#lld_cardsvc').focus();
        	$('#lld_cardsvc').val(quickInputData[4]);
        	$('#lld_cardsvc').blur();
        	$('#lld_carddue').focus();
        	$('#lld_carddue').val(quickInputData[5]);
        	$('#lld_carddue').blur();
        });

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

        /*
         * ================================================================== 
         * [ Shrink]
         */
      
        $(document).ready(function() {
        	
        	$('.text-primary:eq(1)').click(function(){  
        		$('#basicinfolist').show();
            	$('.shrink').animate({left:'-72px'},'fast',function(){
            		$('.basicInfo__list-backIcon').css('text-align','right');
            	});
            	$('.shrink').fadeOut('fast');
            	$('#basicinfolist').animate({left:'0px'},'normal');
			});
			$('.text-primary:eq(0)').click(function(){
				$('#basicinfolist').animate({left:'-320px'},'fast',function(){
            		$('.basicInfo__list-backIcon').css('text-align','center');
            		$('#basicinfolist').hide();
            	});
            	$('.shrink').fadeIn('fast');
            	$('.shrink').animate({left:'0px'},'fast');
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
        $('a.upload').click(function() {
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
        
        // 1.銀行提領按鈕
        $('#btnbalanceWithdraw').click(function(e) {
            e.preventDefault();
            console.log('btn - balance Withdraw');
            inputWithdraw = $('#pocket_withdraw');
            if (!hasBankinfoJS){
            	Swal.fire({
            		icon: 'warning',
            		title: '請更新收款帳號',
            	    showDenyButton: true,
            	    animation: false
            	});
            } else if (/^\d+$/.test(inputWithdraw.val().trim()) === false || inputWithdraw.val()==="0") {
            	inputWithdraw.parent().addClass('alert-validate');
            } else {  // 前端驗證成功(提領金額為正整數) 
            	inputWithdraw.parent().removeClass('alert-validate');
            	var formData = new FormData($('#withdrawform')[0]);
            	formData.append('action', 'balanceWithdraw');
//                ajax_balanceWithdraw(formData);
            	
            	$.ajax({ // 存入資料庫階段
            		beforeSend: function() {
            			$('#btnbalanceWithdraw').attr('disabled',true);
            		},
                    url: "/EA103G2/lld/LldServlet2",
                    type: "POST",
                    data: formData,  
                    processData: false,   // 告訴jQuery不要去處理髮送的資料
                    contentType: false,   // 告訴jQuery不要去設定Content-Type請求頭
                    success: function(data) { // 以上成功才執行
                        console.log("res棒");
                        if (data === 'true') {
                        	console.log(inputWithdraw.val());
                        	Swal.fire({
                        	    title: 'Now loading',
                        	    allowEscapeKey: false,
                        	    allowOutsideClick: false,
                        	    timer: 600,                    	   
                        	    onOpen: () => {
                        	      swal.showLoading();
                        	    }
                        	  }).then(() => {
                        		  Swal.fire({
                              		icon: 'success',
                              		title: '成功提領新台幣&nbsp'+inputWithdraw.val()+'&nbsp元',
                              		animation: false,
                              		showConfirmButton: true,
                              	}).then((result) => {
                              		if (result.isConfirmed) {
                              			location.reload(true);
                              		} 
                              	});      
                        	  });              	
                       }
                       if (data === 'false') {
                    	   Swal.fire({
                       	    title: 'Now loading',
                       	    allowEscapeKey: false,
                       	    allowOutsideClick: false,
                       	    timer: 500,
                       	    onOpen: () => {
                       	      swal.showLoading();
                       	    }
                       	  }).then(() => {
                       		  Swal.fire({
    	                   			icon: 'error',
    	                    		title: '餘額不足',
    	                    		text: "提領金額不可大於餘額",
    	                    		showConfirmButton: true,
    	                    		animation: false,
                             	});      
                       	  });
                      }
                    },
                    error: function() {
                        console.log("真的不棒");
                        Swal.fire({
                    		icon: 'warning',
                    		title: '發生錯誤',
                    		text: "請稍後重新點選送出",
                    	    showDenyButton: true,
                    	});
                    },
                    complete: function() {
                    	$('#btnbalanceWithdraw').attr('disabled',false);
            		},
                });
            }
        });
        
     // 2.信用卡儲值按鈕  ---form---
        $('#btnbalanceDeposit').click(function(e) {
         e.preventDefault();
         console.log('btn - balance Deposit');
         inputDeposit = $('#pocket_deposit');
         if (!hasCardinfoJS){
         	Swal.fire({
         		icon: 'warning',
         		title: '請更新付款卡號',
         	    showDenyButton: true,
         	    animation: false
         	});
         } else if (/^\d+$/.test(inputDeposit.val().trim()) === false || inputDeposit.val()==="0") {
         	inputDeposit.parent().addClass('alert-validate');
         } else {  // 前端驗證成功(儲值金額為正整數) 
         	inputDeposit.parent().removeClass('alert-validate');
         	var depositValue = parseInt(inputDeposit.val().trim()).toLocaleString('en-US');
         	Swal.fire({
        		title: '儲值金額為&nbspNTD&nbsp' + depositValue,
        		showConfirmButton: true,
        		confirmButtonText: '前往儲值',
        		showDenyButton: true,
        		animation:false
        	}).then((result) => {
        		if (result.isConfirmed) {
        			$('#depositform').submit();
          		} 
          	});  
         }
        });
        
//     // 2.信用卡儲值按鈕  ---ajax---
//        $('#btnbalanceDeposit').click(function(e) {
//            e.preventDefault();
//            console.log('btn - balance Deposit');
//            inputDeposit = $('#pocket_deposit');
//            if (!hasCardinfoJS){
//            	Swal.fire({
//            		icon: 'warning',
//            		title: '請更新付款卡號',
//            	    showDenyButton: true,
//            	    animation: false
//            	});
//            } else if (/^\d+$/.test(inputDeposit.val().trim()) === false || inputDeposit.val()==="0") {
//            	inputDeposit.parent().addClass('alert-validate');
//            } else {  // 前端驗證成功(儲值金額為正整數) 
//            	inputDeposit.parent().removeClass('alert-validate');
//            	var formData = new FormData($('#depositform')[0]);
//            	formData.append('action', 'balanceDeposit');
//            	
//            	$.ajax({ // 存入資料庫階段
//            		beforeSend: function() {
//            			$('#btnbalanceDeposit').attr('disabled',true);
//            		},
//                    url: "/EA103G2/lld/LldServlet2",
//                    type: "POST",
//                    data: formData,  
//                    processData: false,   // 告訴jQuery不要去處理髮送的資料
//                    contentType: false,   // 告訴jQuery不要去設定Content-Type請求頭
//                    success: function(data) { // 以上成功才執行
//                        console.log("res棒");
//                        if (data === 'true') {
//                        	console.log(inputDeposit.val());
//                        	Swal.fire({
//                        	    title: 'Now loading',
//                        	    allowEscapeKey: false,
//                        	    allowOutsideClick: false,
//                        	    timer: 600,                    	   
//                        	    onOpen: () => {
//                        	      swal.showLoading();
//                        	    }
//                        	  }).then(() => {
//                        		  Swal.fire({
//                              		icon: 'success',
//                              		title: '成功儲值新台幣&nbsp'+inputDeposit.val()+'&nbsp元',
//                              		animation: false,
//                              		showConfirmButton: true,
//                              	}).then((result) => {
//                              		if (result.isConfirmed) {
//                              			location.reload(true);
//                              		} 
//                              	});      
//                        	  });              	
//                       }
//                       if (data === 'false') {
//                    	   Swal.fire({
//                       	    title: 'Now loading',
//                       	    allowEscapeKey: false,
//                       	    allowOutsideClick: false,
//                       	    timer: 500,
//                       	    onOpen: () => {
//                       	      swal.showLoading();
//                       	    }
//                       	  }).then(() => {
//                       		  Swal.fire({
//    	                   			icon: 'error',
//    	                    		title: '儲值錯誤',
//    	                    		text: "儲值錯誤!",
//    	                    		showConfirmButton: true,
//    	                    		animation: false,
//                             	});      
//                       	  });
//                      }
//                    },
//                    error: function() {
//                        console.log("真的不棒");
//                        Swal.fire({
//                    		icon: 'warning',
//                    		title: '發生錯誤',
//                    		text: "請稍後重新點選送出",
//                    	    showDenyButton: true,
//                    	});
//                    },
//                    complete: function() {
//                    	$('#btnbalanceDeposit').attr('disabled',false);
//            		},
//                });
//            }
//        });

        
        // 3.收付款設定 按鈕
        $('#btnBankCard').click(function(e) {
            e.preventDefault();
            console.log('btn - pocket update BankCard');
            var theform = $(this).parent().prev('form');
            validateAll(theform);
            if ($('.alert-validate').length === 0) {
                var formData = new FormData(theform.get(0));
                formData.append('action', 'infoUpdateBankCard');
                ajax_pocketUpdateBankCard(formData,theform);
            }
        });

        function ajax_pocketUpdateBankCard(formData,theform) {
            $.ajax({ // 存入資料庫階段
                url: "/EA103G2/lld/LldServlet2",
                type: "POST",
                data: formData,
                // 告訴jQuery不要去處理髮送的資料
                processData: false,
                // 告訴jQuery不要去設定Content-Type請求頭
                contentType: false,

                success: function(data) { // 以上成功才執行
                    console.log("res棒");
                    if (data === 'true') {
                    	Swal.fire({
                    		icon: 'success',
                    		title: '修改成功!',
                    		showConfirmButton: true,
                    	}).then((result) => {
                    		if (result.isConfirmed) {
                      			location.reload(true);
                      		} 
                      	});  
                   }
                },
                error: function() {
                    console.log("真的不棒");
                    Swal.fire({
                		icon: 'warning',
                		title: '發生錯誤',
                		text: "請稍後重新點選送出",
                	    showDenyButton: true,
                	});
                }
            });
        }
        
        
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
            $('#tnt_bank').on('blur', function() {
                if ($('#tnt_bank').val().trim() == '') {
                    $('#tnt_bank').addClass('alert-validate');
                    $('#tnt_bank').addClass('alert-validate-select');
                } else {
                    $('#tnt_bank').addClass('select-has-val');
                }
            })
            $('#tnt_bank').on('focus', function() {
                $('#tnt_bank').removeClass('alert-validate');
                $('#tnt_bank').removeClass('alert-validate-select');
                $('#tnt_bank').removeClass('alert-validate-selects');
            })

            // 銀行分行
            $('#tnt_bankbranch.register100').on('blur', function() {
                if (/^[(\u4e00-\u9fa5)(a-zA-Z)]{2,}$/.test($(this).val().trim()) === false) {
                    showValidate($(this));
                }
            })
            // 銀行帳號
            $('#tnt_bankacc.register100').on('blur', function() {
                if (/^[\d]{8,}$/.test($(this).val().trim()) === false) {
                    showValidate($(this));
                }
            })

            // 信用卡卡號
            $('#tnt_card.register100').on('blur', function() {
                if (/^[\d]{8,16}$/.test($(this).val().trim()) === false) {
                    showValidate($(this));
                } else if (validateCreditCard($(this).val().trim()) !== true) {
                    showValidate($(this));
                }
            })
            // 信用卡安全碼
            $('#tnt_cardsvc.register100').on('blur', function() {
                if (/^[\d]{3}$/.test($(this).val().trim()) === false) {
                    showValidate($(this));
                }
            })
            // 信用卡到期日
            var today = new Date();
            var thisYear = today.getFullYear();
            var thisMonth = today.getMonth() + 1;

            $('#tnt_carddue.register100').on('blur', function() {
                if ($('#tnt_carddue').val().trim() == '') {
                    showValidate($(this));
                }
                if ($('#tnt_carddue').val().substring(0, 4) < thisYear.toString()) {
                    showValidate($(this));
                }
                var mm = ($('#tnt_carddue').val().substring(5, 6) === '0') ? $('#tnt_carddue').val().substring(6, 7) : $('#tnt_carddue').val().substring(5, 7);
                if (($('#tnt_carddue').val().substring(0, 4) === thisYear.toString()) && (mm < thisMonth)) {
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

        /*------------------------------------------------------------------
        [ End-Origin-Register ]*/

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

        $('#btninfoProfile').click(function(e) {
            e.preventDefault();
            console.log('btn - info update profile');
            var theform = $(this).parent().prev('form');
            validateAll(theform);
            if ($('.alert-validate').length === 0) {
                var formData = new FormData(theform.get(0));
                formData.append('action', 'infoUpdateProfile');
                ajax_infoUpdateProfile(formData);
            }
        });

        function ajax_infoUpdateProfile(formData) {
            $.ajax({ // 存入資料庫階段
                url: "/EA103G2/tnt/TntServlet2",
                type: "POST",
                data: formData,
                // 告訴jQuery不要去處理髮送的資料
                processData: false,
                // 告訴jQuery不要去設定Content-Type請求頭
                contentType: false,

                success: function() { // 以上成功才執行
                    console.log("res棒");
                },
                error: function() {
                    console.log("真的不棒")
                }
            })
        }
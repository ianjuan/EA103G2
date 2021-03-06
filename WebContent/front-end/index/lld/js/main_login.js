        (function($) {
            "use strict";

            /*
             * ================================================================== [
             * Focus Contact2 ]
             */
            $('.register100').each(function() {
                $(this).on('blur', function() {
                    if ($(this).val().trim() != "") {
                        $(this).addClass('has-val');
                    } else {
                        $(this).removeClass('has-val');
                    }
                })
            });

            /*
             * ================================================================== 
             * [ Validate for each input By myself]
             */
            // 信箱
            $('#lld_email.register100').on('blur', function() {
                if (/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/.test($(this).val().trim()) === false) {
                    showValidate($(this));
                }
            });

            // 密碼
            $('#lld_pwd.register100').on('blur', function() {
                if (/^[\w]{8,16}$/.test($(this).val()) === false) {
                    showValidate($(this));
                }
            });

            /*
             * ================================================================== [
             * Validate ]
             */
            var input = $('.validate-input .register100');

            $('.validate-form').on('submit', function() {
                var check = true;

                for (var i = 0; i < input.length; i++) {
                    if (validate(input[i]) == false) {
                        showValidate(input[i]);
                        check = false;
                    }
                }
                return check;

            });

            $('.validate-form .register100').each(function() {
                $(this).focus(function() {
                    hideValidate(this);
                });
            });

            function validate(input) {
                if ($(input).attr('type') == 'email' ||
                    $(input).attr('name') == 'lld_email') {
                    if ($(input)
                        .val()
                        .trim()
                        .match(
                            /^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
                        return false;
                    }
                } else {
                    if ($(input).val().trim() == '') {
                        return false;
                    }
                }
            }

            function showValidate(input) {
                var thisAlert = $(input).parent();

                $(thisAlert).addClass('alert-validate');
            }

            function hideValidate(input) {
                var thisAlert = $(input).parent();

                $(thisAlert).removeClass('alert-validate');
            }

            $('#nullnav').attr('style','background-color:#D7C8B6 !important;');

        })(jQuery);
        
        /*
         * ================================================================== 
         *    [ Quick Input ]
         */
        var quickInputData = ['Vg4BvBMH@gmail.com', 'aa123456'];

        $('#quickpick').click(function() {
            var inputs = $('.validate-input .register100');
            for (var i = 0; i < inputs.length; i++) {
                if (i !== 8) {
                    $(inputs[i]).focus();
                    $(inputs[i]).val(quickInputData[i]);
                    $(inputs[i]).blur();
                }
            }
        });

        /*
         * ================================================================== 
         *    [ 按鈕Ajax - Login ]
         */
        // $('#btnLogin.login100-form-btn').click(function(e) {
        //     e.preventDefault();
        //     console.log('btn - login');
        //     validateAllProfile();
        //     if ($('.alert-validate').length === 0) {
        //         // var formData = new FormData($('#loginform')[0]);
        //         // console.log($('#loginform')[0]);
        //         // console.log(formData);
        //         // formData.append('action', 'login');
        //         // console.log(formData);
        //         // ajax_login(formData);
        //     }
        // });

        // function ajax_login(formData) {
        //     $.ajax({ // 存入資料庫階段
        //         url: "/EA103G2/lld/LldServlet2",
        //         type: "POST",
        //         data: formData,
        //         // 告訴jQuery不要去處理髮送的資料
        //         processData: false,
        //         // 告訴jQuery不要去設定Content-Type請求頭
        //         contentType: false,

        //         success: function(data) { // 以上成功才執行
        //             console.log("res棒")
        //             console.log("" + data);
        //             if (data === 'EmailnotRegisterYet') {
        //                 $('.wrap-validate-login').attr('data-validate', '信箱尚未註冊')
        //                 $('.wrap-validate-login').addClass('validate-input alert-validate-login');
        //             } else if (data === 'false') {
        //                 $('.wrap-validate-login').attr('data-validate', '帳號密碼錯誤')
        //                 $('.wrap-validate-login').addClass('validate-input alert-validate-login');
        //             } else {
        //                 ajax_redirect();
        //             }
        //         },
        //         error: function() {
        //             console.log("真的不棒")
        //         }
        //     })
        // }

        // function ajax_redirect() {
        //     $.ajax({ // 存入資料庫階段
        //         url: "/front-end/lld/Identify.html",
        //         type: "GET",
        //         success: function() { // 以上成功才執行
        //             // console.log("data=" + data);

        //             // $('html').html(data);
        //             // console.log("res棒");
        //         },
        //         error: function() {
        //             console.log("真的不棒")
        //         }
        //     })
        // }

        /*
         * ================================================================== 
         *    [ 按鈕Ajax - forgetPwd ]
         */
//        $('#btnforgetPwd.login100-form-btn').click(function(e) {
//            e.preventDefault();
//            console.log('btn - btnforgetPwd');
//            $('.wrap-validate-login').removeClass('validate-input alert-validate-login');
//            validateAllProfile();
//            if ($('.alert-validate').length === 0) {
//                var formData = new FormData($('#forgetPwdform')[0]);
//                formData.append('action', 'forgetPwd');
//                //                console.log(formData);
//                ajax_forgetPwd(formData);
//            }
//        });
//
//        function ajax_forgetPwd(formData) {
//            $.ajax({ // 存入資料庫階段
//                url: "/EA103G2/lld/LldServlet2",
//                type: "POST",
//                data: formData,
//                // 告訴jQuery不要去處理發送的資料
//                processData: false,
//                // 告訴jQuery不要去設定Content-Type請求頭
//                contentType: false,
//
//                success: function(data) { // 以上成功才執行
//                    //                    console.log("" + data);
//                    console.log("res棒");
//                    if (data === 'false') { //信箱尚未註冊
//                        $('.wrap-validate-login').addClass('validate-input alert-validate-login');
//                    } else {
//                        alert("我們已將認證信傳送到您的信箱，請盡快至您的信箱收信");
//                    }
//                },
//                error: function() {
//                    console.log("真的不棒")
//                }
//            });
//        }
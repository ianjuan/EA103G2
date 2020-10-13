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
                    showValidate($(this));
                }
            })

            // 密碼
            $('#tnt_pwd.register100').on('blur', function() {
                if (/^[A-Za-z0-9]{8}$/.test($(this).val()) === false) {
                    showValidate($(this));
                }
            })

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

        function validateAllProfile() {

            //inputs
            var inputs = $('.validate-input .register100');
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
         *    [ 按鈕Ajax - Login ]
         */
        $('#btnLogin.login100-form-btn').click(function(e) {
            e.preventDefault();
            console.log('btn - login');
            validateAllProfile();
            if ($('.alert-validate').length === 0) {
                var formData = new FormData($('#loginform')[0]);
                console.log($('#loginform')[0]);
                console.log(formData);
                formData.append('action', 'login');
                console.log(formData);
                ajax_login(formData);
            }
        });

        function ajax_login(formData) {
            $.ajax({ // 存入資料庫階段
                url: "/EA103G2/tnt/TntServlet3",
                type: "POST",
                data: formData,
                // 告訴jQuery不要去處理髮送的資料
                processData: false,
                // 告訴jQuery不要去設定Content-Type請求頭
                contentType: false,

                success: function(data) { // 以上成功才執行
                    console.log("" + data);
                    console.log("res棒");
                    if (data === 'true') {
                        console.log("" + data);
                        ajax_redirect();
                    } else {
                        //                      console.log($('.wrap-validate-login'));
                        $('.wrap-validate-login').addClass('validate-input alert-validate-login');
                    }
                },
                error: function() {
                    console.log("真的不棒")
                }
            })
        }

        function ajax_redirect() {
            $.ajax({ // 存入資料庫階段
                url: "http://localhost:8081/EA103G2/back-end/tnt/select_page.jsp",
                type: "GET",
                success: function(data) { // 以上成功才執行
                    console.log("data=" + data);

                    $('html').html(data);
                    console.log("res棒");
                },
                error: function() {
                    console.log("真的不棒")
                }
            })
        }
        
        /*
         * ================================================================== 
         *    [ 按鈕Ajax - forgetPwd ]
         */
        $('#btnforgetPwd.login100-form-btn').click(function(e) {
            e.preventDefault();
            console.log('btn - btnforgetPwd');
            validateAllProfile();
            if ($('.alert-validate').length === 0) {
                var formData = new FormData($('#forgetPwdform')[0]);
                console.log($('#forgetPwdform')[0]);
                console.log(formData);
                formData.append('action', 'forgetPwd');
                console.log(formData);
                ajax_forgetPwd(formData);
            }
        });

        function ajax_forgetPwd(formData) {
            $.ajax({ // 存入資料庫階段
                url: "/EA103G2/tnt/TntServlet3",
                type: "POST",
                data: formData,
                // 告訴jQuery不要去處理發送的資料
                processData: false,
                // 告訴jQuery不要去設定Content-Type請求頭
                contentType: false,

                success: function(data) { // 以上成功才執行
                    console.log("" + data);
                    console.log("res棒");
                    if (data === 'false') {
                    	 $('.wrap-validate-login').addClass('validate-input alert-validate-login');
                    }
                },
                error: function() {
                    console.log("真的不棒")
                }
            })
        }
        /*------------------------------------------------------------------
        [ Origin-Register ]*/
        (function($) {
            "use strict";
           
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
           
        });

        /*
         * ================================================================== 
         *    [ 圖片上傳(程昕) ]
         */
        var img = document.createElement("img");
        var inputF = document.getElementById("inputF");
        var fileInput = document.getElementById("fileInput");



        inputF.addEventListener("change", fileUpload);

        function fileUpload2(files) { //preview
            for (var i = 0; i < files.length; i++) {
                if (files[i].type.indexOf("image") !== -1) { // or picture.type.includes("image");

                    var fileReader = new FileReader();
                    fileReader.readAsDataURL(files[i]); // readyState 1
                    // console.log(fileReader);
                    let filename = files[i].name; //  Must be let!! !!!!
                    // console.log('set Filename=' + filename);
                    fileReader.addEventListener("load", function(e) { // readyState2
                        var picPreview = document.getElementsByClassName("picPreview");
                        if (typeof(picPreview[0]) !== "undefined") {
//                            $(".picPreview").empty();
                        }
                        var img = document.createElement("img");
                        var input = document.createElement("input");
                        var div = document.createElement("div");
                        img.setAttribute("src", this.result); // 只能用e.target 不能用 fileReader(readystate:1, Asynchronous)
                        //e.target 是在捕獲冒泡 
                        input.setAttribute("type", "checkbox");
                        div.appendChild(input);
                        div.appendChild(img);
                        document.getElementById("picWrapper").append(div);

                        img.addEventListener("click", (e) => { // click pic to checked checkbox
                            e.currentTarget.previousSibling.click();
                        });

                        div.addEventListener("dragstart", dragStartHandler); //adding dragEvent to newly created pic
                        div.setAttribute("draggable", true);
                        div.setAttribute("name", filename); //LIne 122 important
                        div.setAttribute("class", "picPreview");
                        input.setAttribute("class", "picCheckbox");
                        input.setAttribute("id", "checkbox_id_picf");
                        img.setAttribute("class", "tnt_pic");
                    });

                } else {
                    alert("請上傳圖片");
                }
            }
        }
        //----------------------------------------------
        var del = document.getElementById("del");
        del.addEventListener("click", deletFilePreview);

        function deletFilePreview() {
            var checkbox = document.querySelectorAll('div input[id="checkbox_id_picf"]');
//            var checkbox = document.querySelectorAll('div input[type="checkbox"]');
            var isChecked = false;
            for (var i = 0; i < checkbox.length; i++) {
                if (checkbox[i].checked) {
                    isChecked = true;
                    var filename = checkbox[i].parentElement.getAttribute("name"); //getFilename from checked div
                    checkbox[i].parentElement.remove();

                    console.log("out i=" + i);
                    console.log("out filename=" + filename);

//                    DeleteFileNameFile(filename);
                }
            }
            if (isChecked === false) {
            	Swal.fire({
            		icon: 'warning',
            		title: '請勾選刪除項目',
            		showConfirmButton: false,
            		timer: 1000,
            		animation: false
            	});
            }
        }

        //=================================================
        function fileUpload(e) {
            var picture = inputF.files;
            fileUpload2(picture);
             handleFile(picture)
        }

        function dropUploadHandler(e) {
            var dtFiles = e.dataTransfer.files; //return FileList

             handleFile(dtFiles);
            fileUpload2(dtFiles);
        }
        //=============================================
        function handleFile(files) {
            ([...files]).forEach(uploadFileDrop); //convert arraylist to array
        }

        function uploadFileDrop(file) {}
        ['dragover', 'dragleave', 'dragenter', 'drop'].forEach(ev => {
            window.addEventListener(ev, function(e) {
                e.preventDefault();
            })
        });

        function dropDelHandler(e) {
            var domName = e.dataTransfer.getData("text");
            document.getElementsByName(domName)[0].remove();
//            DeleteFileNameFile(domName);
        }

        function dragStartHandler(e) {

            e.dataTransfer.setData("text", e.target.parentElement.getAttribute("name"));
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
         *    [ 按鈕Ajax Vrf ]
         */
        
      //info按鈕2 - btninfoPic 
        $('#btnVrfPics').click(function(e) {
            e.preventDefault();
            console.log('btn - Vrf Pics');
//            var theform = $(this).parent().prev('form');
            if ($('.picPreview').find('img').length===3) {
            	var formData = new FormData($('#vrfPicsform')[0]);
//                var formData = new FormData(theform.get(0));
                formData.append('action', 'vrfPicsUpload');
//              console.log(formData);
                for (let key of formData.keys()) {
                     console.log(key + " : " + formData.get(key));
                }
                ajax_vrfPicsUpload(formData);
            }
        });

        function ajax_vrfPicsUpload(formData) {
            $.ajax({ // 存入資料庫階段
                url: "/EA103G2/tnt/TntServlet2",
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
                    		title: '上傳成功!',
                    		showConfirmButton: false,
                    		timer: 1200
                    	}).then((result) => {
                    		$(".picPreview").empty();
                    	});
                   }
                },
                error: function() {
                    console.log("真的不棒")
                }
            })
        }
        
       
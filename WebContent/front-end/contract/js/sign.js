//<!DOCTYPE html>
//<html lang="">
//
//<head>
//    <meta charset="utf-8">
//    <meta http-equiv="X-UA-Compatible" content="IE=edge">
//    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
//    <title>小畫家實作</title>
//    <style type="text/css">
//        #demo {
//            border: 3px solid brown;
//        }
//        .panel .region {
//            display: inline-block;
//            margin: 5px;
//        }
//    </style>
//</head>
//
//<body>
//    <div class="panel">
//        <div class="region">
//            <button id="pen">畫筆</button>
//            <button id="eraser">橡皮擦</button>
//        </div>
//        <div class="region">
//            <span>畫筆粗細 (單位 px)</span>
//            <input type="number" name="" id="size" value="5">
//        </div>
//        <div class="region">
//            <span>畫筆顏色</span>
//            <input type="color" name="" id="color" value="#ffaa11">
//        </div>
//    </div>
//    <canvas id="demo" width="1000" height="500">
//        您的瀏覽器不支援Canvas，請升級您的瀏覽器或使用Chrome。
//    </canvas>
//    <script type="text/javascript">
        // 題目： 請利用Canvas製作一個小畫家
        // 學習重點：
        // 1. Canvas繪製圖形
        // 2. mousedown, mousemove, mouseup事件的應用

        function init() {

            // 0. 全域變數
            var lastX = 0,
                lastY = 0; // 滑鼠最後點擊的位置
            var isDrawing = false; // 是否正在畫圖?

            // 1. 抓取DOM元素
            var demo = document.getElementById("demo");
            var size = document.getElementById("size");
            var eraser = document.getElementById("eraser");
            var pen = document.getElementById("pen");
            var color = document.getElementById("color");

            // 2. 取得2D context 提示：getContext('2d')
            var ctx = demo.getContext('2d');

            // 3. 初始化畫筆參數
            ctx.lineWidth = parseInt(size.value);
            //決定畫筆寬
            ctx.strokeStyle = color.value;
            //決定畫筆顏色
            // ctx.lineJoin = 'round'; //round為圓弧。
            ctx.lineCap = 'round'; //round為圓弧。

            // 4. 寫一個draw函式，傳入起始點與終點，畫一條線
            function draw(startX, startY, endX, endY) {
                // 新建一條Path 提示：beginPath()
                ctx.beginPath();
                // 起點 提示：moveTo(x,y)
                ctx.moveTo(startX, startY);
                // 終點 提示：lineTo(x,y)
                ctx.lineTo(endX, endY);
                // 繪製路徑 提示：stroke()
                ctx.stroke();
            }

            // 5. 測試一下，是否可以畫線？
            // draw(0, 0, 100, 100);

            // 6. 註冊mousedown事件
            demo.addEventListener('mousedown', function(e) {
                // 抓取點擊的位置，當做起始點。
                // console.log("[START] offsetX: " + e.offsetX, "offsetY: " + e.offsetY);
                //至頁面console處確認滑鼠點擊的點為起始點

                // 更新最新位置
                lastX = e.offsetX;
                lastY = e.offsetY;

                // 改狀態為正在畫圖
                isDrawing = true;
            });
            // 7. 註冊mousemove事件
            demo.addEventListener('mousemove', function(e) {
                // 如果沒有正在畫圖，跳出函式不執行下面的繪圖
                if (isDrawing) {
                    // 抓取點擊的位置，當做中繼點。console.log("[MOVE] offsetX: " + e.offsetX, "offsetY: " + e.offsetY);
                    //至頁面console處確定滑鼠經過的都是正確的

                    // 起點
                    var startX = lastX;
                    var startY = lastY;
                    // 終點
                    var endX = e.offsetX;
                    var endY = e.offsetY;
                    // 執行函式draw畫線
                    draw(startX, startY, endX, endY);
                    // 終點 => 起點
                    lastX = endX;
                    lastY = endY;
                }
            });
            // 8. 註冊mouseup事件
            demo.addEventListener('mouseup', function(e) {
                // 改狀態為不在畫圖
                isDrawing = false;
            });


            // 9. 變更線條粗細 註冊change事件
            size.addEventListener('change', function() {
                ctx.lineWidth = parseInt(size.value);
            });

            // 10. 變更線條粗細 註冊change事件
            color.addEventListener('change', function() {
                ctx.strokeStyle = color.value;
            });

            // 11. 畫筆 註冊click事件 更改顏色為選中的顏色
            pen.addEventListener('click', function() {
                ctx.strokeStyle = color.value;
            });

            // 12. 橡皮擦 註冊click事件 更改顏色為白色
            eraser.addEventListener('click', function() {
                ctx.strokeStyle = 'white';
            });
        }
//
//        window.onload = init;
//    </script>
//</body>
//
//</html>
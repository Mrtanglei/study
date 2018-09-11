<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Hello World!</h2>
</body>

<script type="text/javascript">
    var socket;
    if (typeof(WebSocket) == "undefined") {
        console.log("您的浏览器不支持WebSocket");
    } else {
        console.log("您的浏览器支持WebSocket");
    }
    socket = new WebSocket("ws://localhost:8099/api/wechat/websocket/1111");
    // socket = new WebSocket("ws://123.59.151.56/api/wechat/websocket/21312");

    // socket.send()

    //打开事件
    socket.onopen = function () {
        console.log("Socket 已打开");
        // socket.send("这是来自客户端的消息" + location.href + new Date());
        var data = {
            msgType: 'text'
        }
        socket.send('data');
        //获得消息事件
        socket.onmessage = function (msg) {
            console.log(msg.data + "===onmessage");
        };
        //关闭事件
        socket.onclose = function () {
            console.log("Socket已关闭");
        };
        //发生了错误事件
        socket.onerror = function () {
            alert("Socket发生了错误");
        };
    }

</script>
</html>

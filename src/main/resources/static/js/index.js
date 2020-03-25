$("#logout").click(function () {
    $.ajax({
        url: "/logout",
        type: "POST",
        success: function (result) {
            result = JSON.parse(result);
            if ("100" === result.status) {
                window.location.replace("/login")
            } else {
                console.log(result)
                var msg = result.msg;
                $.toast({
                    position: 'top-right',
                    stack: false,
                    heading: '登出失败，请稍后重试吧！',
                    text: msg,
                    showHideTransition: 'fade',
                    icon: 'error'
                })
            }
        }
    });
})

$(function () {
    openSocket();
    listAllMyFriend();
});

function listAllMyFriend() {
    $.ajax({
        url: "/friend/" + $("#accountId").val(),
        type: "POST",
        success: function (result) {
            result = JSON.parse(result);
            var msg = result.msg;
            if ("200" === result.status) {
                $.toast({
                    position: 'top-right',
                    stack: 10,
                    heading: '成功了',
                    text: msg,
                    showHideTransition: 'fade',
                    icon: 'success'
                })
            } else {
                console.log(result)

                $.toast({
                    position: 'top-right',
                    stack: 10,
                    heading: '出现问题了',
                    text: msg,
                    showHideTransition: 'fade',
                    icon: 'error'
                })
            }
        }
    });
}

var socket;
function openSocket() {
    if (typeof (WebSocket) == "undefined") {
        console.log("您的浏览器不支持WebSocket");
    } else {
        console.log("您的浏览器支持WebSocket");
        //实现化WebSocket对象，指定要连接的服务器地址与端口  建立连接
        //等同于socket = new WebSocket("ws://localhost:8888/xxxx/im/25");
        //var socketUrl="${request.contextPath}/im/"+$("#userId").val();
        var socketUrl = "ws://10.73.240.25/imserver/" + $("#accountId").val();
        //socketUrl = socketUrl.replace("https", "ws").replace("http", "ws");
        console.log(socketUrl);
        if (socket != null) {
            socket.close();
            socket = null;
        }
        socket = new WebSocket(socketUrl);
        //打开事件
        socket.onopen = function () {
            console.log("websocket已打开");
            //socket.send("这是来自客户端的消息" + location.href + new Date());
        };
        //获得消息事件
        socket.onmessage = function (msg) {
            console.log(msg.data);
            //发现消息进入    开始处理前端触发逻辑
            showMessage(msg.data)
        };
        //关闭事件
        socket.onclose = function () {
            console.log("websocket已关闭");
        };
        //发生了错误事件
        socket.onerror = function () {
            console.log("websocket发生了错误");
        }
    }
}

function showMessage(msg) {
    console.log(msg);
    msg = JSON.parse(msg);
    $.toast({
        position: 'top-right',
        stack: 10,
        heading: '你收到一条新消息',
        text: msg.contentText,
        showHideTransition: 'fade',
        icon: 'info'
    })
}

$("#sendMessage").click(function (e) {
    e.preventDefault();
    if (typeof (WebSocket) == "undefined") {
        console.log("您的浏览器不支持WebSocket");
    } else {
        console.log("您的浏览器支持WebSocket");
        console.log('{"toUserId":"' + $("#toUserId").val() + '","contentText":"' + $("#contentText").val() + '"}');
        socket.send('{"toUserId":"' + $("#toUserId").val() + '","contentText":"' + $("#contentText").val() + '"}');
    }
})

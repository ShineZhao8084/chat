var dialogueListData = {};
var friendListData = {};

$(function () {
    $("#accountList").niceScroll();
    openSocket();
    listAllMyDialogue();
    listAllMyFriend();
});

function listAllMyDialogue() {
    $.ajax({
        url: "/dialogue/" + $("#accountId").val(),
        type: "GET",
        success: function (result) {
            result = JSON.parse(result);
            var msg = result.msg;
            if ("200" === result.status) {
                dialogueListData = result.result;
                generateDialogueListView();
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

function listAllMyFriend() {
    $.ajax({
        url: "/friend/" + $("#accountId").val(),
        type: "GET",
        success: function (result) {
            result = JSON.parse(result);
            var msg = result.msg;
            if ("200" === result.status) {
                friendListData = result.result;
                generateFriendListView();
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

function generateDialogueListView() {
    var finalHtml = "";
    for (var i in dialogueListData) {
        var html = "<li class=\"chat-list-item\" style=\"cursor: default\">";
        html += "       <figure class=\"avatar user-online\"><img src=\"/images/user-2.png\" alt=\"image\"></figure>" +
            "           <div class=\"list-body\">" +
            "               <div class=\"chat-bttn\"><h3 class=\"mb-0 mt-2\">" + dialogueListData[i].baseFriend.friendName + "</h3><p>What's up, how are you?</p></div>" +
            "               <div class=\"list-action mt-2 text-right\"><div class=\"message-count bg-primary\">3</div><small class=\"text-primary\">03:41 PM</small></div>" +
            "           </div>" +
            "       </li>";
        finalHtml += html;
    }
    $("#dialogueList").html(finalHtml);
}

function generateFriendListView() {
    var finalHtml = "";
    for (var i in friendListData) {
        var html =  "<li class=\"chat-list-item\" style=\"cursor: default\">";
        html +=     "   <figure class=\"avatar user-online\"><img src=\"/images/user-2.png\" alt=\"image\"></figure>" +
            "   <div class=\"list-body\">" +
            "       <div class=\"chat-bttn\"><h3 class=\"mb-1 mt-1\">" + friendListData[i].friendName + "</h3><p>" + friendListData[i].baseAccount.email + "</p></div>" +
            "       <div class=\"list-action text-right\">" +
            "           <a href=\"#\" class=\"btn-plus dropdown-toggle\" data-toggle=\"dropdown\"><i class=\"ti-menu\"></i></a>" +
            "           <div class=\"dropdown-menu dropdown-menu-right\">" +
            "           <a href=\"#\" class=\"dropdown-item\" id=\"addfriend-bttn\">发起会话</a>" +
            "           <a href=\"#\" class=\"dropdown-item\" id=\"addfriend-bttn\">查看资料</a>" +
            "           <a href=\"#\" class=\"dropdown-item\">修改备注</a>" +
            "           <div class=\"dropdown-divider\"></div>" +
            "           <a href=\"#\" class=\"dropdown-item text-danger\">删除好友</a>" +
            "       </div>" +
            "   </div>" +
            "</li>";
        finalHtml += html;
    }
    $("#friendsList").html(finalHtml);
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

$("#searchForDialogue").click(function (e) {
    e.preventDefault();
    $.ajax({
        url: "/dialogue/" + $("#accountId").val() + "/" + $("#searchDialogueText").val(),
        type: "GET",
        success: function (result) {
            result = JSON.parse(result);
            var msg = result.msg;
            if ("200" === result.status) {
                dialogueListData = result.result;
                generateDialogueListView();
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
});

$("#searchForFriend").click(function (e) {
    e.preventDefault();
    $.ajax({
        url: "/friend/" + $("#accountId").val() + "/" + $("#searchFriendText").val(),
        type: "GET",
        success: function (result) {
            result = JSON.parse(result);
            var msg = result.msg;
            if ("200" === result.status) {
                friendListData = result.result;
                generateFriendListView();
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
});

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
});

$("#sendMessage").click(function (e) {
    e.preventDefault();
    if (typeof (WebSocket) == "undefined") {
        console.log("您的浏览器不支持WebSocket");
    } else {
        console.log("您的浏览器支持WebSocket");
        console.log('{"toUserId":"' + $("#toUserId").val() + '","contentText":"' + $("#contentText").val() + '"}');
        socket.send('{"toUserId":"' + $("#toUserId").val() + '","contentText":"' + $("#contentText").val() + '"}');
    }
});

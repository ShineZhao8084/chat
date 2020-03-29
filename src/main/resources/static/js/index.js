let dialogueListData = {};
let friendListData = {};
let messagesContent = $(".messages-content");
let chatBody = $(".chat-body");
let chatContent = $(".chat-content");
let nextPage = 0;
$(function () {
    scrollToBottom();
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
                console.log(dialogueListData)
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
                console.log(friendListData)
                generateFriendListView();
            } else {
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


$(".chat-begin").on("click", ".chat-begin-item", function () {
    let $currentDialogueId = $("#currentDialogueId");
    if ($currentDialogueId.val() !==  $(this).find(".dialogueId").val()) {
        $("#toUserId").val($(this).find(".accountId").val());
        let dialogueId = $(this).find(".dialogueId").val();
        $currentDialogueId.val(dialogueId);
        $(this).find(".message-count").css("visibility", "hidden");

        $(".to-friend-name").text($(this).find("h3").text());
        $(".to-friend-header").attr("src", $(this).find("img").attr("src"));

        $.ajax({
            url: "/message/" + dialogueId + "/1",
            type: "GET",
            success: function (result) {
                result = JSON.parse(result);
                let hasNextPage = result.hasNextPage;
                let messageList = result.list;
                nextPage = result.nextPage;
                $(".messages-content").html("");
                loadMessage(messageList, hasNextPage);
                chatContent.show();
                scrollToBottom();
            }
        });

    }


});

function loadMessage(messageList, hasNextPage) {
    $(".moreHtmlWrapper").remove();
    const myAccountId = $("#accountId").val();
    let myProfile = $(".my-profile");
    let count = messageList.length;
    messageList.forEach((item, index, array) => {
        //执行代码
        let isOutGoing = item.baseMessageFrom.toString() === myAccountId;
        let isOutGoingString = isOutGoing ? "outgoing-message" : "";
        let srcString = isOutGoing ? myProfile.attr("src") : $(".to-friend-header").attr("src");
        let name = isOutGoing ? $(".my-login-name").text() : $(".to-friend-name").text();
        let html = "<div class=\"message-item " + isOutGoingString + "\">";
        let lastMessageFrom = 0;

        if (index + 1 === count) {
            lastMessageFrom = 0;
        } else {
            lastMessageFrom = messageList[index + 1].baseMessageFrom;
        }
        if (item.baseMessageFrom !== lastMessageFrom) {
            html += "<div class=\"message-user\">";
            html += "   <figure class=\"avatar\">";
            html += "       <img alt=\"image\" src=" + srcString + ">";
            html += "   </figure>";
            html += "   <div>";
            html += "       <h5>" + name + "</h5>";
            html += "       <div class=\"time\">01:35 PM</div>";
            html += "   </div>";
            html += "</div>";
        }
        html += "<div class=\"message-wrap\">" + item.baseMessageContent + "</div>";
        html += "</div>";
        //console.log("html: " + html);
        messagesContent.prepend(html);
    });
    if (hasNextPage) {
        let moreHtml = "<div class=\"moreHtmlWrapper\" style=\"width: 100%; text-align: center;\">" +
            "<span id=\"moreHtml\" onclick='more()'>查看更多</span>" +
            "</div>";
        messagesContent.prepend(moreHtml);
    }

}

function more () {
    $.ajax({
        url: "/message/" + $("#currentDialogueId").val() + "/" + nextPage,
        type: "GET",
        success: function (result) {
            result = JSON.parse(result);
            let hasNextPage = result.hasNextPage;
            let messageList = result.list;
            nextPage = result.nextPage;
            loadMessage(messageList, hasNextPage);


        }
    });
};

function generateDialogueListView() {
    var finalHtml = "";
    for (var i in dialogueListData) {
        let html = "";
        const unReadMessageCount = dialogueListData[i].unReadMessageCount;
        let displayMethod = "";
        if (0 !== unReadMessageCount && null !== unReadMessageCount) {
            displayMethod = "inherit";
        } else {
            displayMethod = "hidden";
        }
        html += "<li class=\"chat-list-item chat-begin-item active\">";
        html += "   <figure class=\"avatar user-online\"><img src=\"/images/user-2.png\" alt=\"image\"></figure>";
        html += "   <input class=\"accountId\" value=\"" + dialogueListData[i].dialogueFriendId + "\" style=\"display: none\"/>";
        html += "   <input class=\"dialogueId\" value=\"" + dialogueListData[i].dialogueId + "\" style=\"display: none\"/>";
        html += "   <input class=\"lastMessageId\" value=\"" + dialogueListData[i].lastMessageId + "\" style=\"display: none\"/>";
        html += "   <div class=\"list-body\">";
        html += "       <div class=\"chat-bttn\"><h3 class=\"mb-0 mt-2\">" + dialogueListData[i].friendName + "</h3><p>" + dialogueListData[i].lastMessageContent + "</p></div>";
        html += "       <div class=\"list-action mt-2 text-right\">";
        html += "           <div class=\"message-count bg-primary\" style='visibility: " + displayMethod + "'>" + unReadMessageCount + "</div>";
        html += "           <small class=\"text-primary\">03:41 PM</small>";
        html += "       </div>";
        html += "   </div>";
        html += "</li>";
        finalHtml += html;
    }
    $("#dialogueList").html(finalHtml);
}

function generateFriendListView() {
    let finalHtml = "";
    let avatarHome = $("#avatarHome");
    avatarHome.html("");
    for (const i in friendListData) {
        let html = "";
        let avatarHtml = "<figure class=\"avatar user-online\"><img src=\"/images/user-2.png\" alt=\"image\"></figure>";
        html += "<li class=\"chat-list-item\" style=\"cursor: default\">";
        html += avatarHtml;
        html += "   <div class=\"list-body\">";
        html += "       <div class=\"chat-bttn\"><h3 class=\"mb-1 mt-1\">" + friendListData[i].friendName + "</h3><p>" + friendListData[i].baseAccount.email + "</p></div>";
        html += "       <div class=\"list-action text-right\">";
        html += "           <a href=\"#\" class=\"btn-plus dropdown-toggle\" data-toggle=\"dropdown\"><i class=\"ti-menu\"></i></a>";
        html += "           <div class=\"dropdown-menu dropdown-menu-right\">";
        html += "           <a href=\"#\" class=\"dropdown-item\" id=\"addfriend-bttn\">发起会话</a>";
        html += "           <a href=\"#\" class=\"dropdown-item\" id=\"addfriend-bttn\">查看资料</a>";
        html += "           <a href=\"#\" class=\"dropdown-item\">修改备注</a>";
        html += "           <div class=\"dropdown-divider\"></div>";
        html += "           <a href=\"#\" class=\"dropdown-item text-danger\">删除好友</a>";
        html += "       </div>";
        html += "   </div>";
        html += "</li>";
        finalHtml += html;
        let avatarHtmlWrapper = "<div id=\"avatar" + friendListData[i].friendAccountId + "\">" + avatarHtml + "</div>";
        avatarHome.append(avatarHtmlWrapper);
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
        var socketUrl = "ws://127.0.0.1/imserver/" + $("#accountId").val();
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
            let response = JSON.parse(msg.data);
            //发现消息进入    开始处理前端触发逻辑
            console.log(response.messageType);
            if ("801" === response.messageType) {   //801 发送消息的回执
                dialogueListData = response.object.baseDialogueExtendList;
                generateDialogueListView();
            } else if ("802" === response.messageType) {   //802 接受来自其他用户的信息
                const currentDialogueId = $("#currentDialogueId").val();
                let baseDialogue = response.object.baseDialogue;
                let baseDialogueExtendList = response.object.baseDialogueExtendList;
                let baseMessage = response.object.baseMessage;
                console.log(baseDialogue);
                if (baseDialogue.dialogueId + "" === currentDialogueId) {  //当前聊天窗口
                    for (let i in baseDialogueExtendList) {
                        if (baseDialogueExtendList[i].dialogueId.toString() === currentDialogueId) {
                            baseDialogueExtendList[i].unReadMessageCount = 0;
                        }
                    }
                    let html = "<div class=\"message-item\">";
                    let flag = messagesContent.children(".message-item:last-child").hasClass("outgoing-message");
                    if (0 === messagesContent.children().length) {
                        flag = true;
                    }
                    if (flag) {
                        html += "<div class=\"message-user\">";
                        html += "   <figure class=\"avatar\">";
                        html += "       <img alt=\"image\" src=" + $(".to-friend-header").attr("src") + ">";
                        html += "   </figure>";
                        html += "   <div>";
                        html += "       <h5>" + $(".to-friend-name").text() + "</h5>";
                        html += "       <div class=\"time\">01:35 PM</div>";
                        html += "   </div>";
                        html += "</div>";
                    }
                    html += "<div class=\"message-wrap\">" + baseMessage.baseMessageContent + "</div>";
                    html += "</div>";
                    messagesContent.append(html);
                    scrollToBottom();
                } else {

                }
                dialogueListData = baseDialogueExtendList;
                generateDialogueListView();
            }

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
    let message = $("#contentText").val();
    message = message.replace(/[\n\r\t]/g, "");
    let scrollHeight = 0;
    if (typeof (WebSocket) == "undefined") {
        console.log("您的浏览器不支持WebSocket");
    } else {
        let html = "<div class=\"message-item outgoing-message\">";
        let flag = messagesContent.children(".message-item:last-child").hasClass("outgoing-message");
        if (!flag) {
            html += "<div class=\"message-user\">" +
                "<figure class=\"avatar\">" +
                "<img alt=\"image\" src=" + $(".profile-detail-bttn").find("img").attr("src") + ">" +
                "</figure>" +
                "<div>" +
                "<h5>我</h5>" +
                "<div class=\"time\">01:35 PM</div>" +
                "</div></div>";
        }
        html += "<div class=\"message-wrap\">" + message + "</div>";
        html += "</div>";
        messagesContent.append(html);
        scrollToBottom();

        let object = {
            "fromAccountId": $("#accountId").val(),
            "toAccountId": $("#toUserId").val(),
            "contentType": "text",
            "contentText": message
        };
        let dataWrapper = {
            "messageType": "800",
            "object": object
        };
        socket.send(JSON.stringify(dataWrapper));
        $("#contentText").val("");
    }
});

function scrollToBottom() {

    chatBody.getNiceScroll().resize();
    chatBody.getNiceScroll(0).doScrollTop(messagesContent.height());
}

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Chat</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="chat-main col-6 offset-3">
            <div class="col-md-12 chat-header">
                <div class="row header-one text-white p-1">
                    <div class="col-md-6 name pl-2">
                        <i class="fa fa-comment"></i>
                        <span class="username-header">${toUser.getUsername()}</span>
                        <span class="last-seen-time" >last seen at ${toUser.getLastSeenAsString()}</span>
                    </div>
                    <div class="col-md-6 options text-right pr-0">
                        <i class="fa fa-times hover text-center pt-1" onclick="location.href='/liked'"></i>
                    </div>
                </div>
                <div class="row header-two w-100">
                </div>
            </div>
            <div class="chat-content">
                <div class="col-md-12 chats pt-3 pl-2 pr-3 pb-3">
                    <#list messages as message>
                    <ul class="p-0">
                        <#if (message.fromUser == fromId)>
                        <li class="send-msg float-right mb-2">
                            <p class="pt-1 pb-1 pl-2 pr-2 m-0 rounded">
                                ${message.getMessage()}
                            </p>
                            <span class="receive-msg-time">${message.getSendDateAsString()}</span>
                        </li>
                        <#else>
                        <li class="receive-msg float-left mb-2">
                            <div class="sender-img">
                                <img src="${toUser.getLink()}" class="float-left">
                            </div>
                            <div class="receive-msg-desc float-left ml-2">
                                <p class="bg-white m-0 pt-1 pb-1 pl-2 pr-2 rounded">
                                    ${message.getMessage()} <br>
                                </p>
                                <span class="receive-msg-time">${toUser.getUsername()}, ${message.getSendDateAsString()}</span>
                            </div>
                        </li>
                        </#if>
                    </ul>
                    </#list>
                </div>
                <div class="col-md-12 p-2 msg-box border border-primary">
                    <form method="post">
                    <div class="row">
                        <div class="col-md-2 options-left">
                            <i class="fa fa-smile-o"></i>
                        </div>
                        <div class="col-md-7 pl-0">
                            <input type="text" name="message" class="border-0" placeholder=" Send message" />
<#--                            <button class="btn btn-lg btn-primary btn-block" type="submit">Send</button>-->
                        </div>
                        <div class="col-md-3 text-right options-right">
                            <i class="fa fa-picture-o mr-2"></i>
                        </div>
                    </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
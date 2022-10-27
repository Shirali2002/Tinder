<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>People list</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-8 offset-2">
            <div class="panel panel-default user_panel">
                <div class="row panel-heading">
                    <div class="col-md-5 options-left text-left pr-1">
                        <h3 class="panel-title">User List</h3>
                    </div>
                    <div class="col-md-5 options-right text-right pr-2">
                        <i class="fa fa-sign-out"></i>
                    </div>
                </div>
                <div class="panel-body">
                    <div class="table-container">
                        <table class="table-users table" border="0">
                            <tbody>
                            <#list users as u>
                                <tr onclick="location.href='/message/${u.getId()}'" class="bg-danger">
                                    <td>
                                        <div class="avatar-img">
                                            <img class="img-circle" src="${u.getLink()}" />  
                                        </div>
                                    </td>
                                    <td class="align-middle">
                                        ${u.getUsername()}
                                    </td>
                                    <td  class="align-middle">
                                        Last seen at ${u.getLastSeenAsString()}
                                    </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
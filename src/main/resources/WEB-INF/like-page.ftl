<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Like page</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
          integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/style.css">
</head>
<body style="background-color: #f5f5f5;">

<form method="post">
    <div class="container">
        <div class="row justify-content-center" style="margin-top: 200px;">
            <div class="col-4 text-center">
                <div class="card">
                    <div class="card-body">
                        <img src=${pp_link} alt="" class="mx-auto rounded-circle img-fluid">
                        <h3 class="mb-0 text-truncated">${username}</h3>
                        <br>
                        <div class="buttons">
                            <button type="submit" class="btn btn-outline-danger btn-block mb-2" name="button"
                                    value="dislike"><span
                                        class="fa fa-times"></span> Dislike
                            </button>
                            <button type="submit" class="btn btn-outline-success btn-block mb-2" name="button"
                                    value="like"><span
                                        class="fa fa-heart"></span> Like
                            </button>
                            <button type="submit" class="btn btn-outline-success btn-block mb-2" name="button"
                                    value="skip"><span
                                        class="fa fa-heart"></span> Skip like session
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>

</body>
</html>
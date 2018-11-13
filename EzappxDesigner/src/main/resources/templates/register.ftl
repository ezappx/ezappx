<#import "/spring.ftl" as spring />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel='stylesheet' type='text/css' href='http://cdn.ezappx.com/css/bootstrap.min.css'>
    <link rel="stylesheet" type="text/css" th:href="/css/designer.css"/>

    <!--jQuery first, then Popper.js, then Bootstrap JS -->
    <script src='http://cdn.ezappx.com/js/jquery.min.js'></script>
    <script src='http://cdn.ezappx.com/js/popper.min.js'></script>
    <script src='http://cdn.ezappx.com/js/bootstrap.min.js'></script>

    <title>Ezappx注册</title>
</head>
<body>
<div class="container h-100">
    <div class="row h-100 justify-content-center align-items-center">

        <div class="card" style="width: 30rem">
            <div class="card-header">
                Ezappx注册
            </div>
            <div class="card-body">
                <form action="/register" method="post" class="center-form">
                    <div class="form-group">
                        <label for="username">用户名</label>
                        <input type="text" class="form-control" name="username" placeholder="输入用户名">
                    </div>
                    <div class="form-group">
                        <label for="email">邮箱地址</label>
                        <input type="email" class="form-control" name="email" aria-describedby="emailHelp" placeholder="输入邮箱">
                        <small id="emailHelp" class="form-text text-muted">邮箱将被保密</small>
                    </div>
                    <div class="form-group">
                        <label for="password">密码</label>
                        <input type="password" class="form-control" name="password" placeholder="输入密码">
                    </div>
                    <div class="form-group form-check">
                        <input type="checkbox" class="form-check-input" name="aggrement">
                        <label class="form-check-label" for="aggrement">同意Ezappx用户协议</label>
                    </div>
                    <div class="row m-auto">
                        <button type="submit" class="btn btn-primary">注册</button>
                        <a th:href="/login" class="btn btn-secondary ml-3">返回登陆</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
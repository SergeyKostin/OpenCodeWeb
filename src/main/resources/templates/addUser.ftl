<head>
    <title>Регистрация</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<section class="container">
    <div class="login">
        <h1>Регистрация</h1>
        <form action="addUser" method="post">
            <p><input type="text" name="login" value="" placeholder="Логин или Email"></p>
            <p><input type="password" name="pass" value="" placeholder="Пароль"></p>
            <p class="remember_me">
                <label>
                    <input type="checkbox" name="remember_me" id="remember_me">
                    Запомнить меня
                </label>
            </p>
            <p class="submit"><input type="submit" name="commit" value="Зарегистрироваться"></p>
        </form>
    </div>
</section>
</body>
</html>
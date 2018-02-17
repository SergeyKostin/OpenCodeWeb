<html>
<head>
    <title>БыкКорова</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <style>
        .login{
            width: 600px;
            margin: auto;
        }
    </style>
</head>
<body>
<section class="container">
    <div class="login">
<h1>Добро пожаловать в игру ${user.login}</h1>
<form action="control" method="post">
  <b>Выберите число:  </b>
    <select name="0" >
        <option value="0">0</option>
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">5</option>
        <option value="6">6</option>
        <option value="7">7</option>
        <option value="8">8</option>
        <option value="9">9</option>
    </select>
    <select name="1">
        <option value="0">0</option>
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">5</option>
        <option value="6">6</option>
        <option value="7">7</option>
        <option value="8">8</option>
        <option value="9">9</option>
    </select>
    <select name="2" >
        <option value="0">0</option>
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">5</option>
        <option value="6">6</option>
        <option value="7">7</option>
        <option value="8">8</option>
        <option value="9">9</option>
    </select>
    <select name="3">
        <option value="0">0</option>
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">5</option>
        <option value="6">6</option>
        <option value="7">7</option>
        <option value="8">8</option>
        <option value="9">9</option>
    </select>
    <p class="submit"><input type="submit" name="commit" value="Проверить"></p>
    <p></p>
    <p></p>
</form>
<h1>Ваш текущий результат: ${rez}</h1>
<h1>Ваш рейтинг: ${ret}</h1>
<form action="newGame" method="post">
    <p class="submit"><input type="submit" name="commit" value="Начать новую игру"></p>
</form>
<form action="/">
    <p class="submit"><input type="submit" name="commit" value="Выйти"></p>
</form>
        <p></p>
        <p></p>
<h1>Предыдущие попытки: </h1>
<#list list_attempt as attempt>
<li>${attempt}</li>
</#list>
    </div>
</section>
</body>
</html>
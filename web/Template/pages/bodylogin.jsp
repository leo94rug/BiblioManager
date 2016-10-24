    
<form action="login" method="post" data-parsley-validate class="logs">
    <#if errore??>
    <h4 style="color: red; text-align: center;">${errore}</h4>
    </#if>
    <div class="container">
        <br>
        <h1 class="centra">Login</h1>
        <input class="inp password" type="text" placeholder="Enter Username" data-parsley-type="email" name="email" required>

        <input class=" inp password" type="password" data-parsley-minlength="6" placeholder="Enter Password" name="password" required>

    </div>

    <ul class="actions vertical">
        <li class="centra"><input class="inp but_log" type="submit" value="Login" class="button big fit"></li>
    </ul>
    <div class="centra"><label ">Non hai un account <a href="registrazione">Registrati ora !!!</a></label></div>
    <br>
    <br>

</form>
    


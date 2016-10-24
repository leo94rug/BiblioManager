<section id="menu">
    <#if ( sessione)>
    <!-- Search -->
    <section>
        <h2>Ricerca avanzata</h2>
        <form  method="post" action="Search">
            <input class="fot" type="text" <#if isbn??>  value="${isbn}" </#if>  name="isbn" placeholder="ISBN" />
            <input class="fot" type="text" <#if titolo??> value="${titolo}" </#if> name="titolo" placeholder="Titolo" />
            <input class="fot" type="text" <#if autore??> value="${autore}" </#if> name="autore" placeholder="Autore" />
            <input class="fot" type="text" <#if anno_pub??> value="${anno_pub}" </#if>  name="anno_pub" placeholder="Anno pubblicazione" />
            <input type="checkbox" <#if italiano??>checked </#if> name="italiano">Italiano</input>
            <input type="checkbox" <#if inglese??> checked </#if> name="inglese">Inglese </input>
            <input type="checkbox" <#if download??>checked </#if> name="download">Download </input>

            <ul class="actions pagination centra sopra">
                <li><input type="submit" value="Ricerca" class="button big"></li>
            </ul>


        </form>

    </section>
    <!-- Links -->
    <section class="centra">
        <a href="profilo?email="><img class="fot" id="pp" src="images/pp.png"></a>
        <a href="profilo?email="><h2>Pagina Personale</h2></a>
        <a href="homepage"><img class="fot" id="pp" src="images/home.png"></a>
        <a href="homepage"><h2>Homepage</h2></a>
    </section>
    <#else>
    <!-- Links -->
    <form action="login" data-parsley-validate method="post">
        <div class="container">
            <label><b>Username</b></label>
            <input type="text" placeholder="Enter Username" name="email" data-parsley-type="email" required>
            <label><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="password" data-parsley-minlength="6" required>
        </div>
        <ul class="actions vertical">
            <li><input type="submit" value="Login" class="button big fit"></li>
        </ul>
        <label>Non hai un account <a href="registrazione">Registrati ora !!!</a></label>
    </form>
    </#if>
</section>    


<header id="header">
    <h1><a href="homepage">Handsome Library</a></h1>
    <nav class="links">
        <ul>
            
            <#if ( sessione)>
            <li><a href="homepage">Homepage</a></li>
            <li><a href="inserimento">Inserimento libro</a></li>
            <li class="cerc"><noscript><a href="Search">Cerca un libro</a></noscript></li>
            <li><noscript><a href="profilo?email="> Pagina Personale</a></noscript></li>
            <#else>
            <li><a href="homepage">Homepage</a></li>
            <li class="lo"><noscript><a href="login">Login</a></noscript></li>
            <li><a href="registrazione">Registrazione</a></li>
            </#if>
        </ul>
    </nav>
    <nav class="main">
        <ul>
            <#if ( sessione)>
            <li class="menu">
                <a class=" fa-sign-out" href="logout"><label>logout</label></a>
            </li>
            </#if>
            <li class="sear">
                <noscript>
                <form method="post" action="Search">
                    <input type="text" name="titolo" placeholder="Titolo del libro" />
                </form>
                </noscript>

            </li>


            <li class="menu men">
                
            </li>
        </ul>
    </nav>
</header>
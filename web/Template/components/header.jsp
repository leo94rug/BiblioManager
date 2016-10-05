<!-- Wrapper -->
<div id="wrapper">
<header id="header">
    <h1><a href="homepage">Handsome Library</a></h1>
    <nav class="links">
        <ul>
            <#if ( sessione)>
            <li><a href="homepage">Homepage</a></li>
            <li><a href="ch_insert">Inserimento libro</a></li>
            <li><a href="#menu">Cerca un libro</a></li>
            <#else>
            <li><a href="homepage">Homepage</a></li>
            <li><a href="#menu">Login</a></li>
            <li><a href="change_registrazione">Registrazione</a></li>

            </#if>
        </ul>
    </nav>
    <nav class="main">
        <ul>
            <li class="search">
                <a class="fa-search" href="#search">Search</a>
                <form id="search" method="get" action="#">
                    <input type="text" name="query" placeholder="Search" />
                </form>
            </li>
            <li class="menu">
                <a class="fa-bars" href="#menu">Menu</a>
            </li>
        </ul>
    </nav>
</header>
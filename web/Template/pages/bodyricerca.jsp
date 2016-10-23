<!-- Main -->
<div id="main">

    <!-- Libro -->
    <#if (books??)>
    <#list books as libro>
    <article class="post">
        <header>
            <div class="title">
                <h2><a href="dettaglio?isbn=${libro.isbn}">${libro.titolo}</a></h2>
            </div>
            <div class="meta">
                <time class="published" datetime="2015-11-01">${libro.data_ins}</time>
                <p>${libro.autore}</p>
            </div>
        </header>
        <a href="dettaglio?isbn=${libro.isbn}" class="image featured"><img class="format" src="${libro.url_img}" alt="" /></a>
        <p>${libro.descrizione}</p>
        <footer>
            <ul class="actions">
                <li><a href="dettaglio?isbn=${libro.isbn}" class="button big">Visualizza libro</a></li>
            </ul>
            <ul class="stats">
                <li><a href="profilo?email=${libro.utente_fk}">${libro.nome} ${libro.cognome}</a></li>
                <li><a href="dettaglio?isbn=${libro.isbn}" class="icon fa-comment">${libro.numero_commenti}</a></li>
            </ul>
        </footer>
    </article>
    </#list>
    </#if>
    <!-- Avanti&Dientro -->
    <ul class="actions pagination">
        <#if pagina gt 1>
        <li><a href="Search?page=${pagina}&mov=indietro&order=${order}&isbn=<#if isbn??>${isbn}</#if>&titolo=<#if titolo??>${titolo}</#if>&autore=<#if autore??>${autore}</#if>&anno_pub=<#if anno_pub??>${anno_pub}</#if>&italiano=<#if italiano??>${italiano}</#if>&inglese=<#if inglese??>${inglese}</#if>&download=<#if download??>${download}</#if>" class=" button big previous">Previous Page</a></li>
        </#if>
        <#if last??>
        <#if last >
        <li><a href="Search?page=${pagina}&mov=avanti&order=${order}&isbn=<#if isbn??>${isbn}</#if>&titolo=<#if titolo??>${titolo}</#if>&autore=<#if autore??>${autore}</#if>&anno_pub=<#if anno_pub??>${anno_pub}</#if>&italiano=<#if italiano??>${italiano}</#if>&inglese=<#if inglese??>${inglese}</#if>&download=<#if download??>${download}</#if>" class="button big next">Next Page</a></li>
        <#else>
        </#if>
        <#else>
        </#if>
    </ul>
</div>
<!-- Sidebar -->
<section id="sidebar">
    <!-- Intro -->
    <section id="intro">
        <header>
            <h2>HANDSOME LIBRARY</h2>
            <p>Leggere un libro non è uscire dal mondo, ma entrare nel mondo attraverso un altro ingresso.</p>

            <form action="homepage" method="post">
                <div class="row">
                    <select name="order" class="ope">
                        <option <#if order=0> selected </#if> value="0">Titolo</option>
                        <option <#if order=1> selected </#if> value="1">Autore</option>
                        <option <#if order=2> selected </#if> value="2">Anno</option>
                        <option <#if order=3> selected </#if> value="3">ISBN</option>
                        <option <#if order=4> selected </#if> value="4">Editore</option>
                        <option <#if order=5> selected </#if> value="5">Ultimi inserimenti</option>
                    </select>
                    <input type="submit" value="ordina" class="inp">
                    <br>
                </div>
            </form>
        </header>
    </section>
    <!-- Mini Posts -->
    <#include "../components/last_modify.jsp">
    <!-- Active user -->
    <#include "../components/active_user.jsp">
    <!-- Footer -->
    <#include "../components/footer.jsp">
</section>

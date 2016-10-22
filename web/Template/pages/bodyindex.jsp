<!-- Main -->
<div id="main">
    <form action="homepage" method="post">
        <select name="order">
            <option <#if order=0> selected </#if> value="0">Titolo</option>
            <option <#if order=1> selected </#if> value="1">Autore</option>
            <option <#if order=2> selected </#if> value="2">Anno</option>
            <option <#if order=3> selected </#if> value="3">ISBN</option>
            <option <#if order=4> selected </#if> value="4">Editore</option>
            <option <#if order=5> selected </#if> value="5">Ultimi inserimenti</option>
        </select>
        <br><br>
        <input type="submit">
    </form>
    <!-- Libro -->
    <#if (books??)>
    <#list books as libro>
    <article class="post">
        <header>
            <div class="title">
                <h2><a href="<#if (sessione)>dettaglio?isbn=${libro.isbn}<#else>#menu</#if>">${libro.titolo}</a></h2>
            </div>
            <div class="meta">
                <time class="published" datetime="2015-11-01">${libro.data_ins}</time>
                <p>${libro.autore}</p>
            </div>
        </header>
        <a href="<#if (sessione)>dettaglio?isbn=${libro.isbn}<#else>#menu</#if>" class="image featured"><img class="format" src="${libro.url_img}" alt="" /></a>
        <p>${libro.descrizione}</p>
        <footer>
            <ul class="actions">
                <li><a href="<#if (sessione)>dettaglio?isbn=${libro.isbn}<#else>#menu</#if>" class="button big">Visualizza libro</a></li>
            </ul>
            <ul class="stats">
                <li><a href="<#if (sessione)>profilo?email=${libro.utente_fk}<#else>#menu</#if>">${libro.nome} ${libro.cognome}</a></li>
                <li><div class="icon fa-comment">${libro.numero_commenti}</div></li>
            </ul>
        </footer>
    </article>
    </#list>
    </#if>
    <!-- Avanti&Dientro -->
    <ul class="actions pagination">
        <#if pagina gt 1>
        <li><a href="homepage?page=${pagina}&mov=indietro&order=${order}" class=" button big previous">Previous Page</a></li>
        </#if>
        <#if last??>
        <#if last >
        <li><a href="homepage?page=${pagina}&mov=avanti&order=${order}" class="button big next">Next Page</a></li>
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
        </header>
    </section>
    <!-- Mini Posts -->
    <#include "../components/last_modify.jsp">
    <!-- Active user -->
    <#include "../components/active_user.jsp">

    <!-- Footer -->
    <#include "../components/footer.jsp">
</section>

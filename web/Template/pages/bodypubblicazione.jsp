<!-- Menu -->
<section id="menu">
    <!-- Search -->
    <section>
        <form class="search" method="get" action="#">
            <input type="text" name="query" placeholder="Search" />
        </form>
    </section>
    <!-- Actions -->
    <section>
        <ul class="actions vertical">
            <li><a href="#" class="button big fit">Log In</a></li>
        </ul>
    </section>
</section>
<!-- Main -->
<div id="main">
    <!-- Post -->
    <article class="post">
        <header>
            <div class="title">
                <h2>${book.titolo}</h2>
                <#if (admin)>
                <p>${book.da_approvare} commenti da approvare!</p>
                </#if>
            </div>
            <div class="meta">
                <#if ( admin)>
                <i class="fa fa-pencil" aria-hidden="true"></i>
                <a href="modifica?isbn=${book.isbn}" value="Modifica" class="">Modifica Pubblicazione</a>
                </#if>
                <time class="published" datetime="2015-11-01">${book.data_ins}</time>
                <h6><span class="name">${book.autore}</span></h6>
            </div>
        </header>

        <a href="${book.url_img}" class="image featured"><img class="dim" src="${book.url_img}" alt="" /></a>
        <div class="row">
            <div class="col-md-12">
                <h4>Anno di pubblicazione: <label style="color:#b6b4b4;">${book.anno_pub?string["0"]}</label></h4>
                <h4>Dove comprarlo: <label style="color:#b6b4b4;"><a href="${book.link_buy}">${book.link_buy}</a></label></h4>
                <h4> Dove scaricarlo:  <label style="color:#b6b4b4;"><a href="${book.link_download}">${book.link_download}</a></label></h4>
            </div>
            <div class="destra">
                <h4>ISBN: <label style="color:#b6b4b4;">${book.isbn}</label></h4>
                <h4>Editore: <label style="color:#b6b4b4;">${book.editore}</label></h4>
                <h4>Lingua: <label style="color:#b6b4b4;">${book.lingua}</label></h4>
            </div>
        </div>
        <hr align="left" size="1" width="100%" color="#b6b4b4" noshade>
        <article>
            <h2> Descrizione</h2>
            <header>
                <p>${book.descrizione}</p>
            </header>
        </article>
        <hr align="left" size="1" width="100%" color="#b6b4b4" noshade>
        <div class="centra"><h2>Recensioni</h2></div><br>
        <ul class="posts">
            <#if (comments??)>
            <#list comments as commenti>
            <li>
                <article>
                    <#if (commenti.approvato)>
                    <#else>
                    <div class="Approva">
                        <a class="button" href="approva?id=${commenti.id}&isbn=${commenti.libro_fk}&utente=${commenti.user_fk}">Approva</a>
                    </div>
                    </#if>
                    <header>
                        <h3><a href="profilo?email=${commenti.user_fk}">${commenti.nome} ${commenti.cognome}</a></h3>
                        <time class="published" datetime="2015-10-15"><h4>${commenti.commento}</h4>${commenti.data_ins}</time>
                    </header>
                    <a class="image"><img src="images/recensione.png" alt="" /></a>
                </article>
            </li>
            </#list>
            <#else>
            <p>non</p>
            </#if>
        </ul>


        <ul>
            <h2>Inserisci un commento</h2>
            <textarea name="testo" form="form_commento" rows="4" cols="50"></textarea>
        </ul>
        <form action="commento" id="form_commento" method="post">
            <input type="hidden" name="isbn" value="${book.isbn}"

                   <ul class="actions pagination">
                <input type="submit" value="Inserisci" class="button big ">
            </ul>
        </form>
    </article>
</div>
<section id="sidebar">
    <!-- Mini Posts -->
    <#include "../components/ultime_modifiche.jsp">
    <#include "../components/capitoli.jsp">

    <!-- Footer -->
    <#include "../components/footer.jsp">
</section>


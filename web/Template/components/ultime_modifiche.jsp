<section>
    <ul class="posts">
        <#list storico as stori>
        <li>
            <article>
                <header>
                    <h3><a href="profilo?email=">${stori.nome} ${stori.cognome}</a></h3>
                    <p>${stori.descrizione}</p>
                    <time class="published" datetime="2015-10-20">${stori.time_stamp}</time>
                </header>
                <a href="<#if (sessione)>dettaglio?isbn=${stori.isbn}<#else>#menu</#if>" class="image"><img src="${stori.img_user}" alt="" /></a>
            </article>
        </li>
        </#list>
    </ul>
</section>
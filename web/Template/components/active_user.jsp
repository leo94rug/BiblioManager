<section>
    <ul class="posts">
        <#list utenti as user>
        <li>
            <article>
                <header>
                    <h3><a href="<#if (sessione)>profilo?email=${user.email}<#else>#menu</#if>">${user.nome} ${user.cognome}</a></h3>
                    <p>Ha pubblicato ${user.pubblicazioni} libri</p>
                    <time class="published" datetime="2015-10-20">${user.iscrizione}</time>
                </header>
                <a href="<#if (sessione)>profilo?email=${user.email}<#else>#menu</#if>" class="image"><img src="${user.img_url}" alt="" /></a>
            </article>
        </li>
        </#list>
    </ul>
</section>
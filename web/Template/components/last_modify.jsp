<section>
    <div class="mini-posts">
        <#list storico as stori>

        <!-- Mini Post -->
        <article class="mini-post">
            <header>
                <h6>${stori.nome} ${stori.cognome} ${stori.descrizione} ${stori.titolo}</h6>
                <time class="published" datetime="2015-10-20">${stori.time_stamp}</time>
                <a href="<#if (sessione)>profilo?email=${stori.email}<#else>#menu</#if>" class="author">
                    <img src="${stori.img_user}"  alt="" />
                </a>
            </header>
            <a href="<#if (sessione)>dettaglio?isbn=${stori.isbn}<#else>#menu</#if>" class="image"><img style="max-height: 150px" src="${stori.url_img}" alt="" /></a>
        </article>
        </#list>
    </div>
</section>
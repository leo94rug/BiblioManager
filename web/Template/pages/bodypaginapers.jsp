<!-- Main -->
<div id="main">
    <!-- Post -->
    <article class="post">
        <header>
            <div class="title">
                <h2><a href="#"></a>Profilo di <label class="blue" >${utente.nome} ${utente.cognome}</label></h2>
            </div>
            <#if (admin)>
            <#if utente.tipo == 1>
            <div class="meta">
                <a href="promozione?email=${utente.email}" class="button">Promuovi</a>
            </div>
            </#if>
            <#else>        
            </#if>
            <#if utents??>
            <#if utents == 1>
            <div class="meta">
                <i class="fa fa-pencil" aria-hidden="true"></i>
                <a href="profmod?email=${utente.email}" value="Modifica" class="">Modifica Profilo</a>
            </div>
            </#if>
            </#if>
        </header>
        <div class="row">
            <div class="col-md-12 sinistra centra">
                <img class="imagineprof" src="${utente.img_user}"></img>
            </div>
            <div class="destra">
                <h2>Nome: <label style="color:#b6b4b4;">${utente.nome}</label></h2>
                <h2>Cognome: <label style="color:#b6b4b4;">${utente.cognome}</label></h2>
                <h2>Indirizzo: <label style="color:#b6b4b4;">${utente.indirizzo}</label></h2>
                <h2>Professione: <label style="color:#b6b4b4;">${utente.professione}</label></h2>
                <h2>Email: <label style="color:#b6b4b4;">${utente.email}</label></h2>
            </div>
        </div>
    </article>
    <div class="centra">
        <h1>le pubblicazioni</h1>
    </div>
    <article class="post ">
        <#if libro??>
        <div class="row">

            <#list libro as lib>
            <article class="mini-post libs">
                <header>
                    <p class="text"><a href="#">${lib.titolo}</a></p>
                    <time class="published" datetime="2015-10-18">${lib.data_ins}</time>
                </header>
                <a href="#" class="image libsd"><img src="${lib.url_img}" alt="" /></a>
            </article>   
            </#list>


        </div>

        <#else>
        <h2> L'utente non ha pubblicato nessun libro</h2>
        </#if>

    </article>

</div>


<!-- Main -->
<div id="main">
    <!-- Post -->
    <article class="post">
        <header>
            <div class="title">
                <h2><a href="#"></a>Profilo di <label class="blue" >${utente.nome} ${utente.cognome}</label></h2>
            </div>
            <#if (admin)>
            <div class="meta">
               <input email="" type="button" value="Promuovi" class="button">
            </div>
            <#else>
            <div></div>
            </#if>
        </header>

        <div class="row">
            <div class="col-md-12 sinistra centra">
                <img class="imagine" src="images/user.png"></img>
            </div>
            
            <div class="destra">
                <h2>Nome: <label style="color:#b6b4b4;">${utente.nome}</label></h2>
                <h2>Cognome: <label style="color:#b6b4b4;">${utente.cognome}</label></h2>
                <h2>Indirizzo: <label style="color:#b6b4b4;">${utente.indirizzo}</label></h2>
                <h2>Professione: <label style="color:#b6b4b4;">${utente.professione}</label></h2>
                <h2>Email: <label style="color:#b6b4b4;">${utente.email}</label></h2>
            </div>

        </div>
</div>
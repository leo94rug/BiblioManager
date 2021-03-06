<!-- Main -->
<div id="main">
    <!-- Post -->
    <article class="post">
        <header>
            <div class="title">
                <h2>Modifica i tuoi dati personali</h2>
            </div>
        </header>

        <!-- Links -->
        <form action="profmod" method="post" data-parsley-validate enctype="multipart/form-data">
            <div class="row">
                <input type="hidden" value="${utente.img_user}" name="old"></input>
                <div class="col-md-12 sinistra centra">

                    <img id="myImg" src="${utente.img_user}" alt="your image" />
                    <label class="myFile">
                        <img class="upload" src="images/uploadd.png" alt="" />
                        <input type="file" name="photo"/>
                    </label>
                    <ul class="actions vertical">
                        <li><input type="submit" value="Modifica" class="button"></li>
                    </ul>
                </div>
                <div class="col-md-12 destra centra">
                    <div class="form-group fot">
                        <input type="text" class="form-control" required name="nome" id="nome" value="${utente.nome}">
                    </div>
                    <div class="form-group fot">
                        <input type="text" class="form-control" required name="cognome" id="cognome" value="${utente.cognome}">
                    </div>
                    <div class="form-group fot">
                        <input type="text" class="form-control" required name="indirizzo" id="residenza" value="${utente.indirizzo}">
                    </div>
                    <div class="form-group fot">
                        <input type="text" class="form-control" required name="professione" id="professione" value="${utente.professione}">
                    </div>
                    <div class="form-group fot">
                        <input type="email" class="form-control" required name="email" data-parsley-type="email" id="email" value="${utente.email}">
                    </div>
                </div>
            </div>
        </form>
    </article>
</div>


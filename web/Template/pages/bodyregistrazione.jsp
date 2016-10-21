<!-- Main -->
<div id="main">
    <!-- Post -->
    <article class="post">
        <header>
            <div class="title">
                <h2><a href="#">Sono un nuovo utente</a></h2>
            </div>
        </header>
        <div class="centra fot">
            <label>
                <h2>Inserisci i tuoi dati personali</h2>
            </label>
        </div>
        <!-- Links -->
        <form action="registrazione" method="post" enctype="multipart/form-data">
            <div class="row">
                <div class="col-md-12 sinistra centra">
                     <img id="myImg" src="images/imgs.png" alt="your image" />
                    <label class="myFile">
                        <img class="upload" src="images/uploadd.png" alt="" />
                        <input type="file" name="photo"/>
                    </label>
                    <ul class="actions vertical">
                        <li><input type="submit" value="registrati" class="button"></li>
                    </ul>
                </div>
                <div class="col-md-12 destra centra">
                    <div class="form-group fot">
                        <input type="text" class="form-control" name="nome" id="nome" placeholder="Nome">
                    </div>
                    <div class="form-group fot">
                        <input type="text" class="form-control" name="cognome" id="cognome" placeholder="Cognome">
                    </div>
                    <div class="form-group fot">
                        <input type="text" class="form-control" name="indirizzo" id="residenza" placeholder="Indirizzo">
                    </div>
                    <div class="form-group fot">
                        <input type="text" class="form-control" name="professione" id="professione" placeholder="Professione">
                    </div>
                    <div class="form-group fot">
                        <input type="email" class="form-control" name="email" id="email" placeholder="Email">
                    </div>
                    <div class="form-group fot">                   
                        <input type="password" class="form-control"  name="pwd" id="pwd" placeholder="Password">
                    </div>
                </div>
            </div>
        </form>
    </article>
</div>


<!-- Main -->
<div id="main">
    <!-- Post -->
    <article class="post">
        <header>
            <div class="title">
                <h2><a href="#">Nuovo Libro </a></h2>
            </div>
        </header>
        <div class="centra fot">
            <label>
                <h2>Inserisci i dati del libro</h2>
            </label>
        </div>
        <form action="inserimento" method="post" id="inserimento" enctype="multipart/form-data" >
            <div class="row">
                <div class="col-md-12 sinistra centra">
                    <img id="myImg" src="images/imgs.png" alt="your image" />
                    <label class="myFile">
                        <img class="upload" src="images/uploadd.png" alt="" />
                        <input type="file" name="photo"/>
                    </label>
                    <br>

                    <input type="button" value="Aggiungi Capitolo" onclick="addRow('dataTable')" />  

                    <input type="button" value="Cancella Capitolo" onclick="deleteRow('dataTable')" />  


                    <table id="dataTable" width="350px" border="1">  
                        <tr>   

                        </tr>  
                    </table>  
                    <ul class="actions pagination centra sopra">
                        <li><input type="submit"  class="button big " value="Inserisci"></li>
                    </ul>
                </div>
                <div class="col-md-12 destra centra">
                    <div class="form-group fot">
                        <input type="text" class="form-control" name="titolo" id="nome" placeholder="Titolo">
                    </div>
                    <div class="form-group fot">
                        <input type="text" class="form-control" name="autore" id="cognome" placeholder="Autore">
                    </div>
                    <div class="form-group fot">
                        <input type="text" class="form-control" name="editore"  placeholder="Editore">
                    </div>
                    <div class="form-group fot">
                        <input type="text" class="form-control" name="isbn"  placeholder="ISBN">
                    </div>
                    <div class="form-group fot">
                        <select name="lingua">
                            <option value="italiano">Italiano</option>
                            <option value="inglese">Inglese</option>
                        </select>
                    </div>
                    <div class="form-group fot">
                        <input type="text" class="form-control" name="anno"  placeholder="Anno Pubblicazione">
                    </div>
                    <div class="form-group fot">                   
                        <input type="text" class="form-control"  name="link_download"  placeholder="Link Download">
                    </div>
                    <div class="form-group fot">                   
                        <input type="password" class="form-control"  name="link_buy"  placeholder="Dove Comprarlo">
                    </div>
                    <h2>Inserisci una Descrizione</h2>
                    <textarea  rows="4" cols="50" form="inserimento" name="descrizione"></textarea>
                </div>
            </div>
        </form>
    </article>
</div>

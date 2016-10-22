<!-- Main -->
<div id="main">
    <!-- Post -->
    <article class="post">
        <header>
            <div class="title">
                <h2><a href="#">Modifica del Libro ${book.titolo} </a></h2>
            </div>
        </header>
        <div class="centra fot">
            <label>
                <h2>Modifica i dati del libro</h2>
            </label>
        </div>
        <form action="modifica" method="post" id="inserimento" enctype="multipart/form-data">
            <div class="row">
                <input type="hidden" value="${book.url_img}" name="old"></input>
                <div class="col-md-12 sinistra centra">
                    <img id="myImg" ottimo="${book.url_img}" src="${book.url_img}" alt="your image" />
                    <label class="myFile">
                        <img class="upload" src="images/uploadd.png" />
                        <input type="file" ottimo="${book.url_img}" name="photo"/>
                    </label>
                    <hr align="left" size="1" width="100%" color="#b6b4b4" noshade>
                    <div class="form-group fot">
                        <div style="text-align: left;"><h3>Anno di pubblicazione</h3></div>
                        <input type="text" class="form-control" id="anno" name="anno"  value="${book.anno_pub?string["0"]}">
                    </div>
                    <div class="form-group fot">
                        <div style="text-align: left;"><h3>Dove scaricarlo</h3></div>
                        <input type="text" class="form-control"  name="link_download"  value="${book.link_download}">
                    </div>
                    <div class="form-group fot">
                        <div style="text-align: left;"><h3>Dove Comprarlo</h3></div>
                        <input type="text" class="form-control"  name="link_buy"  value="${book.link_buy}">
                    </div>
                    <#list capitolo as cap>

                    <div class="form-group fot">
                        <div style="text-align: left;"><h3>Capitolo ${cap.num_cap}</h3></div>
                        <input type="text" class="form-control"  name="${cap.num_cap}"  value="${cap.testo}">
                    </div>


                    </#list>
                </div>
                <div class="col-md-12 destra centra">
                    <div class="form-group fot">
                        <div style="text-align: left;"><h3>Titolo</h3></div>
                        <input type="text" class="form-control" name="titolo" id="titolo" value="${book.titolo}">
                    </div>
                    <div class="form-group fot">
                        <div style="text-align: left;"><h3>Autore</h3></div>
                        <input type="text" class="form-control" name="autore" id="cognome" value="${book.autore}">
                    </div>
                    <div class="form-group fot">
                        <div style="text-align: left;"><h3>Editore</h3></div>
                        <input type="text" class="form-control" name="editore"  value="${book.editore}">
                    </div>
                    <div class="form-group fot">
                        <div style="text-align: left;"><h3>ISBN</h3></div>
                        <input type="text"  readonly="readonly"  class="form-control" name="isbn"  value="${book.isbn}">
                    </div>
                    <div class="form-group fot">
                        <div style="text-align: left;"><h3>Lingua</h3></div>
                        <select name="lingua">
                            <option value="italiano">${book.lingua}</option>
                            <option value="italiano">Italiano</option>
                            <option value="inglese">Inglese</option>

                        </select>
                    </div>
                    <ul>
                        <h2>Modifica la descrizione</h2>
                        <textarea  rows="4" cols="50" form="inserimento" name="descrizione">${book.descrizione}</textarea>
                    </ul>
                    <div class="meta">
                        <ul class="modifica pagination">
                            <input type="submit"  class="button big " value="Modifica"/>
                        </ul>
                    </div>
                </div>
            </div>
        </form>
    </article>
</div>
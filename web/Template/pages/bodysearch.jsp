<form action="Search" method="post" class="logs">
    <br>
    <h1 class="centra">Ricerca Avanzata</h1>
    <input class="fot inp password" type="text" <#if isbn??>  value="${isbn}" </#if>  name="isbn" placeholder="ISBN" />
    <input class="fot inp password" type="text" <#if titolo??> value="${titolo}" </#if> name="titolo" placeholder="Titolo" />
    <input class="fot inp password" type="text" <#if autore??> value="${autore}" </#if> name="autore" placeholder="Autore" />
    <input class="fot inp password" type="text" <#if anno_pub??> value="${anno_pub}" </#if>  name="anno_pub" placeholder="Anno pubblicazione" />
    <div class="centra">
    <input  type="checkbox" <#if italiano??>checked </#if> name="italiano">Italiano</input>
    <input  type="checkbox" <#if inglese??> checked </#if> name="inglese">Inglese </input>
    <input type="checkbox" <#if download??>checked </#if> name="download">Download </input>
        
    </div>
    

    <ul class="actions pagination centra sopra">
        <li><input type="submit" value="Ricerca" class="button big"></li>
    </ul>

</form>
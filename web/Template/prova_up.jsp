<%-- 
    Document   : newjsp
    Created on : 29-set-2016, 11.19.58
    Author     : leo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        File Upload to Database Demo
        <form  action="inserimento" method="post" <!--enctype="multipart/form-data"-->>
            <div id="ciao">

        	Titolo opera:
                <input type="text" name="titolo" id="titolo" value="ciao" size="50">
                Autore:
	        <input type="text" name="autore" value="ciao" size="50">
                Editore:
	        <input type="text" name="editore" value="ciao" size="50">
                Descrizione:
	        <input type="text" name="descrizione" value="ciao" size="50"/>
                ISBN:
	        <input type="text" name="isbn" value="ciao" size="50"/>
                Anno pubblicazione :
	        <input type="text" name="anno" value="100" size="50"/>
                Lingua (it/en) :
	        <input type="text" name="lingua" value="ciao" size="50"/>
                Scaricabile? (si/no):
	        <input type="text" name="download" value="1" size="50"/>
                Link download :
	        <input type="text" name="link_download" value="ciao" size="50"/>
                Acquistabile? (si/no):
	        <input type="text" name="buy" value="1" size="50"/>
                Link acquisto :
	        <input type="text" name="link_buy" value="ciao" size="50"/>
                <!--Photo: 
                <input type="file" name="photo"/> INSERT INTO libro (editore, descrizione, download, titolo, link_buy, anno, lingua, isbn, link_download, buy) VALUES ('ciao', 'ciao', 1, 'ciao', 'ciao', 100, 'ciao', 'ciao', 'ciao', 1)"-->
		<input type="submit" value="Save">
                </div>
	    </form>    </body>
</html>

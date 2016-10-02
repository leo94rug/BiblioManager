<!DOCTYPE HTML>
<!--
   Future Imperfect by HTML5 UP
   html5up.net | @n33co
   Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
   -->
<html>
   <head>
      <title>Inserimeno libro</title>
      <meta charset="utf-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1" />
      <!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
      <link rel="stylesheet" href="assets/css/main.css" />
      <link rel="stylesheet" href="assets/css/handsome.css" />
      <link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
      <!--[if lte IE 9]>
      <link rel="stylesheet" href="assets/css/ie9.css" />
      <![endif]-->
      <!--[if lte IE 8]>
      <link rel="stylesheet" href="assets/css/ie8.css" />
      <![endif]-->
   </head>
   <body>
      <!-- Wrapper -->
      <div id="pub">
      <!-- Header -->
      <header id="header">
         <h1><a href="#">Handsome Library</a></h1>
         <nav class="links">
            <ul>
               <li><a href="#">Lorem</a></li>
               <li><a href="#">Ipsum</a></li>
               <li><a href="#">Feugiat</a></li>
               <li><a href="#">Tempus</a></li>
               <li><a href="#">Adipiscing</a></li>
            </ul>
         </nav>
         <nav class="main">
            <ul>
               <li class="search">
                  <a class="fa-search" href="#search">Search</a>
                  <form id="search" method="get" action="#">
                     <input type="text" name="query" placeholder="Search" />
                  </form>
               </li>
               <li class="menu">
                  <a class="fa-bars" href="#menu">Menu</a>
               </li>
            </ul>
         </nav>
      </header>
      <!-- Menu -->
      <#include "menu_log.jsp">
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
         <!-- Search -->
         <!-- Links -->
         <form action="Insert_book" method="post" id="inserimento" enctype="multipart/form-data" >
                
                <div class="row">
                    <div class="col-md-12 sinistra centra">
                    <img id="myImg" src="images/imgs.png" alt="your image" />

                        <label class="myFile">
                            <img class="upload" src="images/uploadd.png" alt="" />
                            <input type="file" name="photo"/>
                        </label>
                    
                    
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
                        <ul>
                                     </form>

			<h2>Inserisci una Descrizione</h2>
                        <textarea  rows="4" cols="50" form="inserimento" name="descrizione"></textarea>
                        </ul>
                        
                    </div>
                   
                   
                    
                </div>

            
            
               
            
         <!-- Sidebar -->
         <!-- Intro -->
         <!-- Mini Posts -->
         <!-- Posts List -->
         <!-- About -->
      </div>
      <!-- Scripts -->
      <script src="assets/js/jquery.min.js"></script>
      <script src="assets/js/skel.min.js"></script>
      <script src="assets/js/util.js"></script>
      <script src="assets/js/bootstrap_min.js"></script>
      <!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
      <script src="assets/js/main.js"></script>
    
   </body>
</html>
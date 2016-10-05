<!-- Menu -->
<section id="menu">

    <!-- Search -->
    <section>
        <form class="search" method="get" action="#">
            <input type="text" name="query" placeholder="Search" />
        </form>
    </section>

    <!-- Links -->


    <!-- Actions -->
    <section>
        <ul class="actions vertical">
            <li><a href="#" class="button big fit">Log In</a></li>
        </ul>
    </section>

</section>

<!-- Main -->
<div id="main">

    <!-- Post -->

    <article class="post">
        <header>
            <div class="title">
                <h2><a href="#">${book.titolo}</a></h2>
                <p>e la maledizione dell'erede </p>
            </div>
            <div class="meta">
                <time class="published" datetime="2015-11-01">${book.data_ins}</time>
                <a href="#" class="author"><span class="name">${book.autore}</span><img src="images/j.jpg" alt="" /></a>
            </div>
        </header>
        <a href="#" class="image featured"><img class="dim" src="${book.url_img}" alt="" /></a>
        <p>${book.descrizione}</p>

        <ul class="posts">
            <#if (comments??)>
            <#list comments as commenti>
            <li>
                <article>
                    <header>
                        <h3><a href="#">${commenti.user_fk}</a></h3>
                        <time class="published" datetime="2015-10-15"><h4>${commenti.commento}</h4>${commenti.data_ins}</time>
                    </header>
                    <a href="#" class="image"><img src="images/recensione.png" alt="" /></a>
                </article>
            </li>
            </#list>
            <#else>
            <p> NON C'è NIENTE </p>
            </#if>


            <li>
                <article>
                    <header>
                        <h3><a href="#">Matteo Ricci</a></h3>
                        <time class="published" datetime="2015-10-15"><h4>Il libro mi piace tantissimo</h4>October 15, 2015</time>
                    </header>
                    <a href="#" class="image"><img src="images/recensione.png" alt="" /></a>
                </article>
            </li>


            <li>
                <article>
                    <header>
                        <h3><a href="#">Matteo Ricci</a></h3>
                        <time class="published" datetime="2015-10-15"><h4>Il libro mi piace tantissimo</h4>October 15, 2015</time>
                    </header>
                    <a href="#" class="image"><img src="images/recensione.png" alt="" /></a>
                </article>
            </li>
        </ul>
        <ul>
            <h2>Inserisci un commento</h2>
            <textarea name="testo" form="form_commento" rows="4" cols="50"></textarea>
        </ul>
        <form action="commento" id="form_commento" method="post">
            <input type="hidden" name="isbn" value="${book.isbn}"

                   <ul class="actions pagination">
                <li><input type="submit" value="Inserisci" class="button big "></li>
            </ul>
        </form>

    </article>







</div>
<!-- Sidebar

<!-- Intro -->

<!-- Mini Posts -->


<!-- Posts List -->


<section>

    <ul class="posts">

        <li>
            <h2>Ultime Modifiche</h2>
        </li>
        <li>
            <article>
                <header>
                    <h3><a href="#">Lorem ipsum fermentum ut nisl vitae</a></h3>
                    <time class="published" datetime="2015-10-20">October 20, 2015</time>
                </header>

            </article>
        </li>
        <li>
            <article>
                <header>
                    <h3><a href="#">Convallis maximus nisl mattis nunc id lorem</a></h3>
                    <time class="published" datetime="2015-10-15">October 15, 2015</time>
                </header>

            </article>
        </li>
        <li>
            <article>
                <header>
                    <h3><a href="#">Euismod amet placerat vivamus porttitor</a></h3>
                    <time class="published" datetime="2015-10-10">October 10, 2015</time>
                </header>

            </article>
        </li>
        <li>
            <article>
                <header>
                    <h3><a href="#">Magna enim accumsan tortor cursus ultricies</a></h3>
                    <time class="published" datetime="2015-10-08">October 8, 2015</time>
                </header>

            </article>
        </li>
        <li>
            <article>
                <header>
                    <h3><a href="#">Congue ullam corper lorem ipsum dolor</a></h3>
                    <time class="published" datetime="2015-10-06">October 7, 2015</time>
                </header>

            </article>
        </li>
    </ul>
</section>

<!-- About -->

</div>
<section>
    <ul class="posts">
        <h1>Lista Capitoli</h1>
        <#if capitolo??>
        <#list capitolo as cap>
        <li>
            <article>
                
                <header>
                    <h3> Capitolo : ${cap.testo}</h3>
                </header>
              
            </article>
        </li>
        </#list>
        <#else>
        <h2>IL libro non ha capitoli</h2>
        </#if>
    </ul>
</section>
<section>
    <ul class="posts">
        <h1>Lista Capitoli</h1>
        <#if capitolo??>
        <#list capitolo as cap>
        <li>
            <article>
                
                <header>
                    <h3> Capitolo ${cap.num_cap}: </h3><label>${cap.testo}</label>
                </header>
              
            </article>
        </li>
        </#list>
        <#else>
        <h2>IL libro non ha capitoli</h2>
        </#if>
    </ul>
</section>
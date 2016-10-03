<!DOCTYPE HTML>
<!--
	Future Imperfect by HTML5 UP
	html5up.net | @n33co
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
                                <#include "components/head.jsp">

				<!-- Header -->
				
                                <#include "components/header.jsp">
				<!-- Menu -->
				<#if ( sessione)>
				<!-- Menu -->
				<#include "components/menu_log.jsp">
				<!-- Main -->
                                <#else>
                                <#include "components/menu_nol.jsp">
                                </#if>
				<!-- Main -->
				
                                <#include "pages/bodyindex.jsp">
		<!-- Scripts -->
                                <#include "components/footer.jsp">

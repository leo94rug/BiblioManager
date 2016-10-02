<!DOCTYPE HTML>
<!--
	Future Imperfect by HTML5 UP
	html5up.net | @n33co
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
                                <#include "head.jsp">

				<!-- Header -->
				
                                <#include "header.jsp">
				<!-- Menu -->
				<#if ( sessione)>
				<!-- Menu -->
				<#include "menu_log.jsp">
				<!-- Main -->
                                <#else>
                                <#include "menu_nol.jsp">
                                </#if>
				<!-- Main -->
				
                                <#include "bodyindex.jsp">
		<!-- Scripts -->
                                <#include "footer.jsp">

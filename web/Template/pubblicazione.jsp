<!DOCTYPE HTML>
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
<#include "pages/bodypubblicazione.jsp">
<!-- Scripts -->
<#include "components/footer.jsp">
   <%@ taglib uri="jakarta.tags.core" prefix="c" %>
   <%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
   <!-- Header -->
    <div class="header">
        <div class="container_header">
            <a href="/index">
                <img alt="Logo" class="logo"
                    src="/Content/Logo.png">
            </a>
            <div class="container_nav" id="header_nav">
                <c:choose>
                    <c:when test="${ruolo=='Dottore'}">
                        <a class="link" href="/farmacia">Farmacia</a>
                        <a class="link" href="">Prenotazioni</a>
                        <a class="link" href="/pazienti">Pazienti</a>
                        <a class="button button_fill margins" href="/utente/logout">Log Out</a>
                    </c:when>
                    <c:when test="${ruolo=='Responsabile Farmacia'}">
                        <a class="link" href="/farmacia">Farmacia</a>
                         <a class="button button_fill margins" href="">Log Out</a>
                    </c:when>
                    <c:otherwise>
                         <a class="button button_outline margins" href="/utente/login">Log In</a>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="hamburger" id="hamburger_button" onclick="menu_show(this)">
                <div class="bar1"></div>
                <div class="bar2"></div>
                <div class="bar3"></div>
            </div>
        </div>
    </div>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Footer -->
<div class="footer">
    <div class="footer_logo">
        <img alt="Logo" src="/Content/Logo.png">
    </div>
    <c:choose>
        <c:when test="${ruolo=='Dottore'}">
            <div class="footer_menu">
                <a class="footer_link footer_head" href="/farmacia">Farmacia</a>
                <a class="footer_link" href="/farmacia/magazzino">Farmaci In Magazzino</a>
                <a class="footer_link" href="/farmacia/add-farmaco-page">Inserimento Nuovo Farmaco</a>
            </div>

            <div class="footer_menu">
                <a class="footer_link footer_head" href="/prenotazioni">Prenotazione</a>
                <a class="footer_link" href="/prenotazioni/add-prenotazione-page">Nuova Prenotazione</a>
            </div>

            <div class="footer_menu">
                <a class="footer_link footer_head" href="/pazienti">Pazienti</a>
                <a class="footer_link" href="/pazienti">Scheda Pazienti</a>
                <a class="footer_link" href="/pazienti/cerca-paziente-page">Modifica Trattamento</a>
                <a class="footer_link" href="/pazienti/add-paziente-page">Aggiungi Paziente</a>
            </div>
        </c:when>
        <c:when test="${ruolo=='Responsabile Farmacia'}">
            <div class="footer_menu">
                <a class="footer_link footer_head" href="/farmacia">Farmacia</a>
                <a class="footer_link" href="/farmacia/magazzino">Farmaci In Magazzino</a>
                <a class="footer_link" href="/farmacia/add-farmaco-page">Inserimento Nuovo Farmaco</a>
            </div>
        </c:when>
        <c:otherwise>
        </c:otherwise>
    </c:choose>
</div>
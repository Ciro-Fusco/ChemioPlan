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
                    <a class="footer_link" href="/farmacia/magazzino">Magazzino</a>
                    <a class="footer_link" href="/farmacia/add-farmaco-page">Aggiungi Farmaco</a>
                    <a class="footer_link" href="/farmacia/nuovo-ordine-page">Nuovo Ordine</a>
                    <a class="footer_link" href="/farmacia/ordini-page">Tutti gli ordini</a>
                </div>

                <div class="footer_menu">
                    <a class="footer_link footer_head" href="/prenotazioni">Prenotazione</a>
                    <a class="footer_link" href="/prenotazioni/all">Visualizza Prenotazioni</a>
                    <a class="footer_link" href="/prenotazioni/add-prenotazione-page">Aggiungi Prenotazione</a>
                    <a class="footer_link" href="prenotazioni/cerca-prenotazioneByData-page">Cerca Prenotazione</a>
                </div>

                <div class="footer_menu">
                    <a class="footer_link footer_head" href="/pazienti">Pazienti</a>
                    <a class="footer_link" href="/pazienti/all">Visualizza Pazienti</a>
                    <a class="footer_link" href="/pazienti/ricerca-paziente-page">Aggiungi Paziente</a>
                    <a class="footer_link" href="/pazienti/cerca-paziente-page">Modifica Trattamento</a>
                </div>
            </c:when>
            <c:when test="${ruolo=='Responsabile Farmacia'}">
                <div class="footer_menu">
                    <a class="footer_link footer_head" href="/farmacia">Farmacia</a>
                    <a class="footer_link" href="/farmacia/magazzino">Magazzino</a>
                    <a class="footer_link" href="/farmacia/add-farmaco-page">Aggiungi Farmaco</a>
                    <a class="footer_link" href="/farmacia/nuovo-ordine-page">Nuovo Ordine</a>
                    <a class="footer_link" href="/farmacia/ordini-page">Tutti gli ordini</a>
                </div>

            </c:when>
            <c:otherwise>
            </c:otherwise>
        </c:choose>
    </div>
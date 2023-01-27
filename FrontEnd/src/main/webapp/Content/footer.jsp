   <%@ taglib uri="jakarta.tags.core" prefix="c" %>
   <%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
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
                <a class="footer_link" href="">Inserimento Lotto</a>
                <a class="footer_link" href="">Inserimento Nuovo Farmaco</a>
                <a class="footer_link" href="">Visualizza Quantita`</a>
            </div>

            <div class="footer_menu">
                <a class="footer_link footer_head" href="">Prenotazione</a>
                <a class="footer_link" href="">Nuova Prenotazione</a>
                <a class="footer_link" href="">Modifica Prenotazione</a>
                <a class="footer_link" href="">Visualizza Prenotazione</a>
            </div>

            <div class="footer_menu">
                <a class="footer_link footer_head" href="/pazientihome">Pazienti</a>
                <a class="footer_link" href="/pazienti">Scheda Pazienti</a>
                <a class="footer_link" href="">Modifica Trattamento</a>
                <a class="footer_link" href="">Aggiungi Paziente</a>
            </div>
    </c:when>
    <c:when test="${ruolo=='Responsabile Farmacia'}">
            <div class="footer_menu">
                <a class="footer_link footer_head" href="/farmacia">Farmacia</a>
                <a class="footer_link" href="/farmacia/magazzino">Farmaci In Magazzino</a>
                <a class="footer_link" href="">Inserimento Lotto</a>
                <a class="footer_link" href="">Inserimento Nuovo Farmaco</a>
                <a class="footer_link" href="">Visualizza Quantita`</a>
            </div>
    </c:when>
    <c:otherwise>
    </c:otherwise>
</c:choose>


    </div>
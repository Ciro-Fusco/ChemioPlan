import json


class Medicinale:
    """Classe che definisce il tipo medicionale"""

    def __init__(self, nome, dosaggio, scazenza):
        # Inizializza il tipo medicionale e ne setta i parametri
        self.Medicinale = {
            "nome": nome,
            "dosaggio": dosaggio,
            "scadenza": scazenza,
            "rimanente": 0,
            "aperto": 0
        }

    def getMedicinale(self):
        # Restituisce il dizionario del medicionale
        return self.Medicinale

    def toString(self):
        # Restituisce il medicionale in formato stringa
        return str(self.Medicinale)

    def consuma(self, dose):
        # riduce il parametro rimenente del medicinale di dose
        self.Medicinale["rimanente"] -= dose
        if self.Medicinale["rimanente"] < 0:
            while self.Medicinale["rimanente"] < 0:
                self.Medicinale["rimanente"] = self.Medicinale["dosaggio"] - \
                    abs(self.Medicinale["rimanente"])
            self.Medicinale["aperto"] = 0

    def invecchia(self):
        # aumenta il parametro aperto del medicinale e ne restituisce le quantita sprecate se risulta scaduto
        scadute = 0
        if self.Medicinale["rimanente"] > 0:
            self.Medicinale["aperto"] += 1
        if self.Medicinale["aperto"] == self.Medicinale["scadenza"]:
            self.Medicinale["aperto"] = 0
            scadute += self.Medicinale["rimanente"]
            self.Medicinale["rimanente"] = 0

        # print(self.Medicinale["nome"], " : ", scadute)
        return scadute

    def azzera(self):
        # resetta parametri rimanente e aperto a 0
        self.Medicinale["rimanente"] = 0
        self.Medicinale["aperto"] = 0


class Lista_Medicinale:
    """Classe che memorizza una lista di medicinali"""

    def __init__(self):
        # Inizializza il tipo Lista_Medicinale
        self.Lista_Medicinale = {}

    def append(self, nome, dosaggio, scazenza):
        # Crea e Aggiunge un nuovo medicinale al dizionario
        self.Lista_Medicinale[nome] = Medicinale(nome, dosaggio, scazenza)

    def getLista(self):
        # Restituisce il dizionario dei medicinali
        return self.Lista_Medicinale

    def toString(self):
        # Restituisce il dizionario dei medicinali come una stringa
        out = ""
        for i in self.Lista_Medicinale:
            out += str(self.Lista_Medicinale[i].toString())+"\n"
        return out

    def getByName(self, name):
        # ottiene il medicinale passando un nome
        return self.Lista_Medicinale[name]

    def getSize(self):
        # Restituisce il numero di medicinali presenti nel dizionario
        num = 0
        for i in self.Lista_Medicinale:
            # print(self.Lista_Medicinale[i].Medicinale)
            num += 1

        return num

    def invecchia(self):
        # esegue il metodo invecchia per ogni medicinale e ne restituisce la somma delle quantita sprecate
        buttati = 0

        for i in self.Lista_Medicinale:
            buttati += self.Lista_Medicinale[i].invecchia()

        return buttati

    def azzera(self):
        # esegue il metodo azzera per ogni medicinale nel dizionario
        for i in self.Lista_Medicinale:
            self.Lista_Medicinale[i].azzera()

import json


class Medicinale:

    def __init__(self, nome, dosaggio, scazenza):
        self.Medicinale = {
            "nome": nome,
            "dosaggio": dosaggio,
            "scadenza": scazenza,
            "rimanente": 0,
            "aperto": 0
        }

    def getMedicinale(self):
        return self.Medicinale

    def toString(self):
        return str(self.Medicinale)

    def consuma(self, dose):
        self.Medicinale["rimanente"] -= dose
        if self.Medicinale["rimanente"] < 0:
            while self.Medicinale["rimanente"] < 0:
                self.Medicinale["rimanente"] = self.Medicinale["dosaggio"] - \
                    abs(self.Medicinale["rimanente"])
            self.Medicinale["aperto"] = 0

    def invecchia(self):
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
        self.Medicinale["rimanente"] = 0
        self.Medicinale["aperto"] = 0


class Lista_Medicinale:

    def __init__(self):
        self.Lista_Medicinale = {}

    def append(self, nome, dosaggio, scazenza):
        self.Lista_Medicinale[nome] = Medicinale(nome, dosaggio, scazenza)

    def getLista(self):
        return self.Lista_Medicinale

    def toString(self):
        out = ""
        for i in self.Lista_Medicinale:
            out += str(self.Lista_Medicinale[i].toString())+"\n"
        return out

    def getByName(self, name):
        return self.Lista_Medicinale[name]

    def getSize(self):
        num = 0
        for i in self.Lista_Medicinale:
            # print(self.Lista_Medicinale[i].Medicinale)
            num += 1

        return num

    def invecchia(self):
        buttati = 0

        for i in self.Lista_Medicinale:
            buttati += self.Lista_Medicinale[i].invecchia()

        return buttati

    def azzera(self):
        for i in self.Lista_Medicinale:
            self.Lista_Medicinale[i].azzera()

import json
from Medicinali import Medicinale
import numpy as np
import math


class Gruppo:
    """Definisce un gruppo di pazienti che hanno in comune lo sytesso medicinale con la stessa quantita"""

    def __init__(self, gruppo, dosaggio, dimensione, medicinale):
        # Inizializza il tipo Gruppo e ne setta i parametri
        self.Gruppo = {
            "gruppo": gruppo,
            "dosaggio": dosaggio,
            "dimensione": dimensione,
            "medicinale": medicinale
        }

    def somministra(self):
        # Esegue una somministrazione per un membro del gruppo
        self.Gruppo["medicinale"].consuma(self.Gruppo["dosaggio"])

    def toString(self):
        # Restituisce il gruppo in forma di stringa
        return str(self.Gruppo)


class Lista_Pazienti:
    """Classe che memorizza una lista di medicinali"""

    def __init__(self):
        # inizializza il dizionario di pazienti e aggiunge il gruppo vuoto
        self.Lista_Pazienti = {}
        self.Lista_Pazienti[0] = Gruppo(0, 0, 0, Medicinale(0, 0, 0))

    def aggiungiPaziente(self, nome, dosaggio, dimensione, medicinale):
        # Crea e Aggiunge un paziente nel dizionario
        self.Lista_Pazienti[len(self.Lista_Pazienti)] = Gruppo(
            nome, dosaggio, dimensione, medicinale)

    def toString(self):
        # Restituisce la lista di pazienti in forma di stringa
        stringa = ""

        for i in self.Lista_Pazienti:
            stringa += self.Lista_Pazienti[i].toString() + "\n"

        return stringa

    def getSize(self):
        # Restituisce il numero di pazienti nel dizionario
        num = 0
        for i in self.Lista_Pazienti:
            num += self.Lista_Pazienti[i].Gruppo["dimensione"]

        return num

    def getIndividual(self, size):
        # Restituisce un individuo per l'algoritmo genetico
        P = np.zeros(size)
        P = P.reshape(-1)

        index = 0
        for i in self.Lista_Pazienti:
            for k in range(self.Lista_Pazienti[i].Gruppo["dimensione"]):
                # print(i, " = ", k)
                P[index] = i
                index += 1

        np.random.shuffle(P)
        return P

    def SomministraByIndex(self, index):
        # Esegue una somministrazione per un membro del gruppo partendo dall'indice
        self.Lista_Pazienti[index].somministra()

    def minimo_spreco(self):
        # Restituisce la somma dei minimi sprechi possibile per tutti i gruppi
        spreco = 0
        for j in self.Lista_Pazienti:
            if self.Lista_Pazienti[j].Gruppo["medicinale"].Medicinale["dosaggio"] != 0:
                richiesta = self.Lista_Pazienti[j].Gruppo["dosaggio"] * \
                    self.Lista_Pazienti[j].Gruppo["dimensione"]
                dosi_aperte = math.ceil(richiesta /
                                        self.Lista_Pazienti[j].Gruppo["medicinale"].Medicinale["dosaggio"]
                                        )*self.Lista_Pazienti[j].Gruppo["medicinale"].Medicinale["dosaggio"]
                spreco += (dosi_aperte-richiesta)

        return spreco

    def getByFarmaco(self, farmaco):
        # Restituisce una lista di gruppi che utilizzano un farmaco
        Lista_Indici = []

        for j in self.Lista_Pazienti:
            if self.Lista_Pazienti[j].Gruppo["medicinale"].Medicinale["nome"] == farmaco:
                Lista_Indici.append(j)

        return Lista_Indici

    def getByFarmacoDosaggio(self, farmaco, dosaggio):
        # Restituisce il gruppo con il farmaco e il dosaggio specificato
        Lista_Indici = []

        for j in self.Lista_Pazienti:
            if self.Lista_Pazienti[j].Gruppo["medicinale"].Medicinale["nome"] == farmaco and self.Lista_Pazienti[j].Gruppo["dosaggio"] == dosaggio:
                Lista_Indici.append(j)

        return Lista_Indici

    def info(self, index):
        # Restituisce una stringa con le informazioni del gruppo con l'indice specificato
        G = self.Lista_Pazienti[index].Gruppo

        stringa = "Gruppo:"+str(G["gruppo"]) + "\n"
        stringa += "Dosaggio:"+str(G["dosaggio"]) + "\n"
        stringa += "Dimensione:"+str(G["dimensione"]) + "\n"
        stringa += "Medicinale:"+G["medicinale"].toString() + "\n"
        return stringa

    def allInfo(self):
        # Restituisce una stringa con le informazioni di tutti i gruppi
        Infos = ""
        for i in self.Lista_Pazienti:
            Infos += self.info(i) + "\n"

        return Infos

    def addPazienteCount(self, gruppo):
        # Aggiunge un paziente al gruppo specificato
        for i in self.Lista_Pazienti:
            if (self.Lista_Pazienti[i].Gruppo["gruppo"] == gruppo):
                self.Lista_Pazienti[i].Gruppo["dimensione"] += 1
                return True
        return False

    def getIndexByNome(self, nome):
        # Restituisce l'indice del gruppo specificato pertendo dal nome
        for i in self.Lista_Pazienti:
            if nome == self.Lista_Pazienti[i].Gruppo["gruppo"]:
                return i

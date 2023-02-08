import json
from Medicinali import Medicinale
import numpy as np
import math


class Gruppo:

    def __init__(self, gruppo, dosaggio, dimensione, medicinale):
        self.Gruppo = {
            "gruppo": gruppo,
            "dosaggio": dosaggio,
            "dimensione": dimensione,
            "medicinale": medicinale
        }

    def somministra(self):
        self.Gruppo["medicinale"].consuma(self.Gruppo["dosaggio"])

    def toString(self):
        return str(self.Gruppo)

    def __len__(self):
        return len(self.Gruppo)


class Lista_Pazienti:

    def __init__(self):
        self.Lista_Pazienti = {}
        self.Lista_Pazienti[0] = Gruppo(0, 0, 0, Medicinale(0, 0, 0))

    def aggiungiPaziente(self, nome, dosaggio, dimensione, medicinale):
        self.Lista_Pazienti[len(self.Lista_Pazienti)] = Gruppo(
            nome, dosaggio, dimensione, medicinale)

    def toString(self):
        stringa = ""

        for i in self.Lista_Pazienti:
            stringa += self.Lista_Pazienti[i].toString() + "\n"

        return stringa

    def getSize(self):
        num = 0
        for i in self.Lista_Pazienti:
            num += self.Lista_Pazienti[i].Gruppo["dimensione"]

        return num

    def getIndividual(self, size):
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
        self.Lista_Pazienti[index].somministra()

    def minimo_spreco(self):
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
        Lista_Indici = []

        for j in self.Lista_Pazienti:
            if self.Lista_Pazienti[j].Gruppo["medicinale"].Medicinale["nome"] == farmaco:
                Lista_Indici.append(j)

        return Lista_Indici

    def getByFarmacoDosaggio(self, farmaco, dosaggio):

        Lista_Indici = []

        for j in self.Lista_Pazienti:
            if self.Lista_Pazienti[j].Gruppo["medicinale"].Medicinale["nome"] == farmaco and self.Lista_Pazienti[j].Gruppo["dosaggio"] == dosaggio:
                Lista_Indici.append(j)

        return Lista_Indici

    def info(self, index):
        # print(self.Lista_Pazienti[index].toString())

        G = self.Lista_Pazienti[index].Gruppo

        stringa = "Gruppo:"+str(G["gruppo"]) + "\n"
        stringa += "Dosaggio:"+str(G["dosaggio"]) + "\n"
        stringa += "Dimensione:"+str(G["dimensione"]) + "\n"
        stringa += "Medicinale:"+G["medicinale"].toString() + "\n"
        return stringa

    def allInfo(self):
        Infos = ""
        for i in self.Lista_Pazienti:
            Infos += self.info(i) + "\n"

        return Infos

    def addPazienteCount(self, gruppo):
        for i in self.Lista_Pazienti:
            if (self.Lista_Pazienti[i].Gruppo["gruppo"] == gruppo):
                self.Lista_Pazienti[i].Gruppo["dimensione"] += 1
                return True
        return False

    def getIndexByNome(self, nome):
        for i in self.Lista_Pazienti:
            if nome == self.Lista_Pazienti[i].Gruppo["gruppo"]:
                return i

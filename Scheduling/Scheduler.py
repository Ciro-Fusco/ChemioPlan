import pygad
import numpy as np
import requests
from Medicinali import Medicinale, Lista_Medicinale
from Pazienti import Gruppo, Lista_Pazienti
import pandas as pd
import sys

cf = sys.argv[1]
url = "http://localhost:8080/"

Magazzino = Lista_Medicinale()
Pazienti = Lista_Pazienti()

Medicinali_Richiesti = []


def popola_magazzino():
    response = requests.get(url+"farmacia")
    if response.status_code == 200:
        farmacia = response.json()
    else:
        print("Request failed with status code", response.status_code)

    for f in farmacia:
        Magazzino.append(f["codice"], f["dimensioneFlacone"],
                         f["scadenzaDopoApertura"])


def popola_pazienti():
    response = requests.get(url+"pazienti")
    if response.status_code == 200:
        pazienti = response.json()
    else:
        print("Request failed with status code", response.status_code)

    for p in pazienti:
        for f in p["farmaci"]:
            if Pazienti.addPazienteCount(str(f) + "-" + str(int(p["farmaci"][f]))) == False:
                Pazienti.aggiungiPaziente(
                    str(f) + "-"+str(int(p["farmaci"][f])), p["farmaci"][f], 1, Magazzino.getByName(str(f)))
            if p["codiceFiscale"] == cf:
                Medicinali_Richiesti.append(
                    str(f) + "-" + str(int(p["farmaci"][f])))


def reshape_eval(array):
    return array.reshape((Giorni, Ore, Poltrone))


def getSuggetimenti(Best):
    suggerimenti = ""
    for medicinale in Medicinali_Richiesti:
        med_str = medicinale.split("-")[0]
        suggerimenti += "<br>Suggerimenti per farmaco "+med_str+"<br>"
        for day in range(Best.shape[0]):
            for ora in range(Best.shape[1]):
                for poltrona in range(Best.shape[2]):

                    Index = Pazienti.getIndexByNome(medicinale)

                    if Best[day, ora, poltrona] == Index:
                        suggerimenti += Giorni_Map[day] + \
                            " ore "+Ore_Map[ora]+"<br>"
    return suggerimenti.replace("<br>","",1)


def get_population(size):
    population = [Pazienti.getIndividual(
        Giorni*Ore*Poltrone) for _ in range(size)]
    return population


def fitness(individuo, index):
    Magazzino.azzera()
    Resh = reshape_eval(individuo)

    Sprecate = 0

    for j in Resh:
        R = j.reshape(-1)
        for k in R:
            Pazienti.SomministraByIndex(k)

        Sprecate += Magazzino.invecchia()

    for i in range(6):
        Sprecate += Magazzino.invecchia()

    return -Sprecate


# Aggiungi alla classe Lista_Medicinale tutti i farmaci della farmacia
popola_magazzino()

# Aggiungi alla classe Lista_Pazienti tutti i pazienti della struttura che usano un farmaco
popola_pazienti()

# Poltrone-Ore-Gironi
Giorni_Map = ["Lunedi", "Martedi", "Mercoledi", "Giovedi", "Venerdi"]
Giorni = len(Giorni_Map)

Ore_Map = ["8:00", "9:00", "10:00", "11:00",
           "12:00", "15:00", "16:00", "17:00"]
Ore = len(Ore_Map)

Poltrone_Map = ["A1", "A2", "A3", "B1", "B2", "C1", "C2"]
Poltrone = len(Poltrone_Map)

# Carica pazienti schedulazione
try:
    f = open("pazienti.txt", "r")
    Loaded_Pazienti = f.read()
    f.close()
except:
    Loaded_Pazienti = "FILE NON TROVATO"

# Verifica se i pazienti sono gia stati schedulati, se no lo si schedula
if Pazienti.allInfo() == Loaded_Pazienti:
    Scheduling = pd.read_csv("scheduling.csv", header=None)
    Scheduling = reshape_eval(Scheduling.to_numpy())
    Out = getSuggetimenti(Scheduling)
#     print("LOADED")
    print(Out)
else:
    # Ottieni popolazione GA
    Popolazione = get_population(100)

    # Setta GA
    ga = pygad.GA(num_generations=400,
                  num_parents_mating=100,
                  num_genes=Giorni*Ore*Poltrone,
                  fitness_func=fitness,
                  initial_population=Popolazione,
                  mutation_percent_genes=10,
                  mutation_type="swap",
                  parent_selection_type='sss',
                  crossover_type=None,
                  keep_elitism=2
                  )
    # Ottimizza con GA
    ga.run()
    # Ottini miglior generazione
    Best = ga.best_solution()
    Out = getSuggetimenti(reshape_eval(Best[0]))
    #
    # Salva
    #
    # Salva i pazienti schedulati
    Magazzino.azzera()
    np.savetxt("scheduling.csv", Best[0].reshape(-1), delimiter=",")
    f = open("pazienti.txt", "w")
    f.write(Pazienti.allInfo())
    f.close()
#     print("SAVED")
    print(Out)

# cls && python Scheduler.py RSSMRC75R13F839Q

import warnings
import numpy as np
from datetime import datetime
import requests
import pandas as pd
from sklearn.tree import DecisionTreeRegressor
from joblib import load
import sys

codice_farmaco = sys.argv[1]
url = "http://localhost:8080/"


def stima(medicinale):
    # Ottieni pazienti
    response = requests.get(url+"pazienti")

    if response.status_code == 200:
        pazienti = response.json()
    else:
        print("Request failed with status code", response.status_code)

    # Ottieni prenotazioni
    response = requests.get("http://localhost:8083/prenotazioni")

    if response.status_code == 200:
        prenotazioni = response.json()
    else:
        print("Request failed with status code", response.status_code)

    # Quantita a disposizione
    Disponibilita = 0
    for i in medicinale["lotti"]:
        Disponibilita += i["quantita"]*medicinale["dimensioneFlacone"]
    # print("Disposizione: ", Disponibilita)

    # Quantita necessaria
    Necessari = 0
    for i in pazienti:
        for m in i["farmaci"]:
            if m == str(codice_farmaco):
                Necessari += i["farmaci"][m]
    # print("Necessari: ", Necessari)

    # Scedenza Lotto
    data_scadenza = datetime.strptime(
        medicinale["lotti"][-1]["scadenzaLotto"][:medicinale["lotti"][-1]["scadenzaLotto"].rfind("T")], '%Y-%m-%d')
    diff = data_scadenza - datetime.today()
    scadenza = diff.days
    # print("Scadenza: ", scadenza)

    # Dimensione flacone
    dimensione_flacone = medicinale["dimensioneFlacone"]
    # print("Dimensione flacone: ", dimensione_flacone)

    # Farmaci Utilizzati
    utilizzati = 0

    for i in prenotazioni:
        for k in pazienti:
            data_prenotazione = datetime.strptime(
                i["data"][:i["data"].rfind("T")], '%Y-%m-%d')
            diff = data_prenotazione - datetime.today()
            if diff.days <= 5:
                try:
                    utilizzati += k["farmaci"][str(codice_farmaco)]
                except:
                    utilizzati += 0
    # print("Farmaci Utilizzati: ", utilizzati)

    # Predizione
    rt = load('regression_tree.joblib')
    mms_input = load('mms_input.bin')
    mms_output = load('mms_output.bin')

    df = pd.DataFrame([[Disponibilita, Necessari, scadenza,
                        dimensione_flacone, utilizzati]])

    df.columns = ["Disponibilita", "Necessari",
                  "Scadenza", "Dimensione", "Utilizzati"]

    input_ml = mms_input.transform(df)

    prediction = rt.predict(input_ml)

    prediction = mms_output.inverse_transform([prediction])

    return int(prediction[0, 0])


# Ottieni medicinale
response = requests.get(url+"farmacia/"+str(codice_farmaco))

if response.status_code == 200:
    medicinale = response.json()
    print(stima(medicinale))
else:
    print("")

warnings.filterwarnings("ignore", category=DeprecationWarning)

# cls && python Modello_Deploy.py 123

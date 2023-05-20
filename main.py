
import numpy as np

class Save_in_buffer():

    def __init__(self):
        self.rBh = np.full(6, np.nan)  # length 6 np.nan = not a number datentyp
        self.amount_rBh = 0
        self.rBd = np.full(24, np.nan)  # length 24
        self.amount_rBd = 0
        self.rBy = np.full(366, np.nan)  # length 366
        self.amount_rBy = 0
        self.rBc = np.full(10, np.nan)  # length 10
        self.amount_rBc = 0

    # testdaten = np.random.randint(1501, size=52704)
    testdaten = np.full(4, 28)

    def average(self ,rB):
        amount_average = 0
        average_temperature = 0
        if np.isnan(rB[0]): #prüfen ob datentyo == nan
            return None

        # Durchgehen der Einzelnen temp und nummern erhöhen
        for x in rB:
            if np.isnan(x):
                break

            amount_average = amount_average + 1
            average_temperature += x

        return average_temperature/amount_average



    def save_in_buffer(self ,datas): # datas die daten die ich alle 10 min erhalte
        self.rBh[self.amount_rBh %6] = datas # eintragen in den ersten ringbuffer % 6 für 6 reihe 6 12 18 24
        self.amount_rBh = self.amount_rBh + 1 #ring hochzähleb

        if self.amount_rBh %6 == 5: # sobald durch modullo 6 = 5  dann wieder in 0 speichern wenn ring voll in tag speichern
            self.rBd[self.amount_rBd %24] = self.average(self.rBh)
            self.amount_rBd = self.amount_rBd + 1

        if self.amount_rBd %24 == 23: # rücksetzten weil buffer voll und speichern in nächsten wert
            self.rBy[self.amount_rBy %366] = self.average(self.rBd)
            self.amount_rBy = self.amount_rBy +1

        if self.amount_rBy % 366 == 355:
            self.rBc[self.amount_rBc % 10] = self.average(self.rBy)
            self.amount_rBc = self.amount_rBc + 1

    def test(self):

        print(self.testdaten)

        for x in self.testdaten:
            self.save_in_buffer(x)
        print(self.average(self.rBd))
        print(self.average(self.rBh))
        print(self.average(self.rBy))


test = Save_in_buffer()
test.test()
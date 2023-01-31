package com.example.FrontEnd.FrontEnd.service;

import com.example.FrontEnd.FrontEnd.model.Paziente;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public interface IPazienteStub {
    public Paziente findByCf(String cf);
    List<Paziente> findPazienti(Paziente p);
}

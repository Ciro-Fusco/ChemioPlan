package com.example.FrontEnd.FrontEnd.service;

import com.example.FrontEnd.FrontEnd.model.SchedaPaziente;

public interface IPazienteService {
  SchedaPaziente[] getPazienti();
  SchedaPaziente getPaziente(String cf);
  String addPaziente(SchedaPaziente paziente);

  String modificaPaziente(SchedaPaziente scheda);
}

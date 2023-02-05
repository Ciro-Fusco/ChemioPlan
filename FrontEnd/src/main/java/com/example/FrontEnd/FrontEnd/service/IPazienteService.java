package com.example.FrontEnd.FrontEnd.service;

import com.example.FrontEnd.FrontEnd.model.SchedaPaziente;
import java.util.HashMap;

public interface IPazienteService {
  SchedaPaziente[] getPazienti();
  SchedaPaziente getPaziente(String cf);
  String addPaziente(SchedaPaziente paziente);
  String modificaPaziente(SchedaPaziente scheda);
  String eliminaPaziente(String cf);
  HashMap<String, Double> getFarmaci(String cf);

}

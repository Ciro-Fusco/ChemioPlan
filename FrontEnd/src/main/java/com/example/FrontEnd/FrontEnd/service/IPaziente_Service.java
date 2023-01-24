package com.example.FrontEnd.FrontEnd.service;

import com.example.FrontEnd.FrontEnd.model.SchedaPaziente;

public interface IPaziente_Service {
  SchedaPaziente[] getPazienti();

  SchedaPaziente getPazienteByCF(String cf);
}

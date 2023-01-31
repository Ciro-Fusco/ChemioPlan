package com.example.FrontEnd.FrontEnd.service;

import com.example.FrontEnd.FrontEnd.model.Malattia;

public interface IMalattiaStub {
  Malattia getMalattia(String codice);
  Malattia[] getAll();
}

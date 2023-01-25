package com.example.FrontEnd.FrontEnd.service;

import com.example.FrontEnd.FrontEnd.model.SchedaFarmaco;

public interface IFarmacia_Service {
    SchedaFarmaco getFarmaco(String id);
    SchedaFarmaco[] getAllFarmaci();

    String addFarmaco(SchedaFarmaco scheda);
}

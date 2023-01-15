package org.example.service;

import java.util.List;

public interface GenericsService<T> {

    //daca il dau ca si parametru o sa trebuiasca sa l citeasca prima data
    T add();

    void displayAll();
}

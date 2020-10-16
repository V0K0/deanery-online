package com.vozniuk.deanery.ui.model;

import org.springframework.ui.Model;

public interface ModelLoader<T> {


    void load(T entity, Model model);

}

package com.udemy.rrhh.combobox;

import com.udemy.domain.Departamento;
import javafx.util.StringConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rene on 22/06/16.
 */
public class ConvertidorComboBoxDepartamento extends StringConverter<Departamento> {

    @Override
    public String toString(Departamento departamento) {
        return departamento.getNombre();
    }

    @Override
    public Departamento fromString(String arg) {
        return null;
    }
}

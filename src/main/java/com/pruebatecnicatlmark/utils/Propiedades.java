/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pruebatecnicatlmark.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Propiedades {

    private Properties prop = new Properties();
    private InputStream input = null;

    public Propiedades() {
        this.cargarPropiedades("../../WEB-INF/configuracion.properties");
        if(prop == null || prop.isEmpty()){
            System.out.println("-----------Load Default Properties---------");
            this.cargarPropiedades("../../WEB-INF/default.properties");
        }
    }
    
    private void cargarPropiedades(String ruta){
        try {
            input = this.getClass().getClassLoader()
                    .getResourceAsStream(ruta);

            prop.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String get(String key) throws Exception {
        String value = prop.getProperty(key);
        if (value == null) {
            throw new Exception("No se encuentra la propiedad \"" + key + "\"");
        }
        return value;
    }

    public void set(String key, String value) {
        prop.setProperty(key, value);
    }
}

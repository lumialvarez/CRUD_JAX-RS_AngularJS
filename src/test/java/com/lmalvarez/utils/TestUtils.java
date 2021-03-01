/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lmalvarez.utils;

import com.lmalvarez.model.Persona;
import java.util.Date;

/**
 *
 * @author luigu
 */
public class TestUtils {
    public static Persona createTestPersona(){
        Persona persona = new Persona();
        persona.setDni(String.valueOf(System.currentTimeMillis()));
        persona.setNombres("Nprueba");
        persona.setApellidos("Aprueba");
        persona.setCorreo("correo@prueba.com");
        persona.setFechaNacimiento(new Date());
        persona.setUsuarioRed("usuPrueba");
        return persona;
    }
}

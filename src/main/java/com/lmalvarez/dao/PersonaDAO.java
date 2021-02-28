/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lmalvarez.dao;

import com.lmalvarez.model.Persona;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.Connection;
import org.sql2o.Query;

public class PersonaDAO {

    public List<Persona> consultarPersonas() throws Exception {
        List<Persona> lstPersonas = new ArrayList<>();
        try (Connection conn = DAO.getConnection()) {

            String sql = "SELECT dni, nombres, apellidos, correo, fecha_nacimiento fechaNacimiento, usuario_red usuarioRed, fecha_baja fechaBaja "
                    + "FROM persona "
                    + "ORDER BY nombres, apellidos;";
            Query query = conn.createQuery(sql);
            lstPersonas.addAll(query.executeAndFetch(Persona.class));
        }
        return lstPersonas;
    }

    public Persona consultarPersonaByDNI(String dni) throws Exception {
        Persona persona;
        try (Connection conn = DAO.getConnection()) {

            String sql = "SELECT dni, nombres, apellidos, correo, fecha_nacimiento fechaNacimiento, usuario_red usuarioRed, fecha_baja fechaBaja "
                    + "FROM persona "
                    + "WHERE dni = :dni;";
            Query query = conn.createQuery(sql);
            query.addParameter("dni", dni);
            persona = query.executeAndFetchFirst(Persona.class);
        }
        if (persona == null) {
            persona = new Persona();
        }
        return persona;
    }

    public boolean insertarPersona(Persona persona) throws Exception {
        try (Connection conn = DAO.getConnection()) {
            String sql = "INSERT INTO persona( "
                    + "            dni, nombres, apellidos, correo, fecha_nacimiento, usuario_red,  "
                    + "            fecha_baja) "
                    + "    VALUES (:dni, :nombres, :apellidos, :correo, :fechaNacimiento, :usuarioRed,  "
                    + "            :fechaBaja);";
            Query query = conn.createQuery(sql);
            return query.bind(persona).executeUpdate().getResult() > 0;
        }
    }

    public boolean actualizarPersona(Persona persona) throws Exception {
        try (Connection conn = DAO.getConnection()) {
            String sql = "UPDATE persona "
                    + "   SET nombres=:nombres, apellidos=:apellidos, correo=:correo, fecha_nacimiento=:fechaNacimiento,  "
                    + "       usuario_red=:usuarioRed, fecha_baja=:fechaBaja "
                    + " WHERE dni = :dni;";
            Query query = conn.createQuery(sql);
            return query.bind(persona).executeUpdate().getResult() > 0;
        }
    }

    public boolean eliminarPersona(String dni) throws Exception {
        try (Connection conn = DAO.getConnection()) {
            String sql = "DELETE FROM persona "
                    + " WHERE dni = :dni;";
            Query query = conn.createQuery(sql);
            query.addParameter("dni", dni);
            return query.executeUpdate().getResult() > 0;
        }
    }
}

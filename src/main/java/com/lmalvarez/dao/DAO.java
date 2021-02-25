/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lmalvarez.dao;

import com.lmalvarez.utils.Propiedades;
import java.util.UUID;
import org.sql2o.Connection;

import org.sql2o.Sql2o;
import org.sql2o.converters.UUIDConverter;
import org.sql2o.quirks.PostgresQuirks;


public class DAO {

    private static Sql2o sql2o;

    private static void ini() throws Exception {
        Propiedades prop = new Propiedades();
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        String urlConexion = "jdbc:postgresql://" + prop.get("db-host") + ":" + prop.get("db-port") + "/" + prop.get("db-name") + "?characterEncoding=UTF-8";
        sql2o = new Sql2o(
                urlConexion,
                prop.get("db-user"),
                prop.get("db-password"),
                new PostgresQuirks() {
            {
                converters.put(UUID.class, new UUIDConverter());
            }
        }
        );
    }

    public static Connection getConnection() throws Exception {
        ini();
        Connection conn = sql2o.open();
        return conn;
    }

    public static Connection getConnectionOnTransaction() throws Exception {
        ini();
        Connection conn = sql2o.beginTransaction();
        return conn;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lmalvarez.service;

import com.lmalvarez.model.Persona;
import com.lmalvarez.utils.TestUtils;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Date;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author luigu
 */
public class PersonaEndpointTest extends JerseyTest {

    private final String PATH = "personas";

    @Override
    protected Application configure() {
        return new ResourceConfig(PersonaEndPoint.class);
    }

    @Test
    public void crudPersonasSuccess() {
        //GET ALL
        Response responseObtener = target(PATH).request().get();
        Assert.assertTrue(responseObtener.getStatus() == Response.Status.OK.getStatusCode());
        //POST
        Persona persona = TestUtils.createTestPersona();
        Entity<Persona> personaEntity = Entity.entity(persona, MediaType.APPLICATION_JSON);
        Response responseCrear = target(PATH).request().post(personaEntity);
        Assert.assertTrue(responseCrear.getStatus() == Response.Status.CREATED.getStatusCode());
        //GET
        Persona responseObtenerUnitario = target(PATH).path(persona.getDni()).request().get(Persona.class);
        Assert.assertTrue(persona.getDni().equals(responseObtenerUnitario.getDni()));
        //PUT
        persona.setFechaBaja(new Date());
        personaEntity = Entity.entity(persona, MediaType.APPLICATION_JSON);
        Response responseActualizar = target(PATH).request().put(personaEntity);
        Assert.assertTrue(responseActualizar.getStatus() == Response.Status.OK.getStatusCode());
        //DELETE
        Response responseEliminar = target(PATH).path(persona.getDni()).request().delete();
        Assert.assertTrue(responseEliminar.getStatus() == Response.Status.OK.getStatusCode());
    }

}

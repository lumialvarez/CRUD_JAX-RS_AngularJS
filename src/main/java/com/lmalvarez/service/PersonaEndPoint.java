/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lmalvarez.service;

import com.lmalvarez.dao.PersonaDAO;
import com.lmalvarez.model.Persona;
import com.lmalvarez.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/personas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonaEndPoint {

    private final PersonaDAO personaDAO = new PersonaDAO();

    @GET
    public Response consultarPersonas() throws Exception {
        List<Persona> lstPersonas = new ArrayList<>();
        lstPersonas.addAll(personaDAO.consultarPersonas());
        return Utils.createResponseOk(lstPersonas);
    }
    
    @GET
    @Path("{dni}")
    public Response consultarPersona(@PathParam("dni") String dni) throws Exception {
        Persona persona = personaDAO.consultarPersonaByDNI(dni);
        if (!dni.equalsIgnoreCase(persona.getDni())) {
            return Utils.createResponseNotFound("DNI no existe");
        }
        return Utils.createResponseOk(persona);
    }

    @POST
    public Response insertarPersona(Persona persona) throws Exception {
        Persona personaTmp = personaDAO.consultarPersonaByDNI(persona.getDni());
        if (persona.getDni().equalsIgnoreCase(personaTmp.getDni())) {
            return Utils.createResponseConflict("DNI ya existe");
        }
        boolean insertado = personaDAO.insertarPersona(persona);
        if (!insertado) {
            throw new Exception("Persona no creada");
        }
        return Utils.createResponseCreated("Creado correctactamente");
    }

    @PUT
    public Response actualizarPersona(Persona persona) throws Exception {
        Persona personaTmp = personaDAO.consultarPersonaByDNI(persona.getDni());
        if (!persona.getDni().equalsIgnoreCase(personaTmp.getDni())) {
            return Utils.createResponseNotFound("DNI no existe");
        }
        boolean actualizado = personaDAO.actualizarPersona(persona);
        return actualizado ? Utils.createResponseOk() : Utils.createResponseNotModified("No fue posible la actualizacion");
    }

    @DELETE
    @Path("{dni}")
    public Response eliminarPersona(@PathParam("dni") String dni) throws Exception {
        boolean eliminado = personaDAO.eliminarPersona(dni);
        if (!eliminado) {
            throw new Exception("Persona no eliminada");
        }
        return Utils.createResponseOk();
    }

}

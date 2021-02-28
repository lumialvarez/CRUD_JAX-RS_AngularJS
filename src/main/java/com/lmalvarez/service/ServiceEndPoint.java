/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lmalvarez.service;

import com.lmalvarez.dao.PersonaDAO;
import com.lmalvarez.model.Persona;
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
public class ServiceEndPoint {
    private final PersonaDAO personaDAO = new PersonaDAO();

    @GET
    public Response consultarPersonas() throws Exception {
        List<Persona> lstPersonas = new ArrayList<>();
        lstPersonas.addAll(personaDAO.consultarPersonas());
        return Response.ok(lstPersonas).build();
    }

    @POST
    public Response insertarPersona(Persona persona) throws Exception {
        Persona personaTmp = personaDAO.consultarPersonaByDNI(persona.getDni());
        if(persona.getDni().equalsIgnoreCase(personaTmp.getDni())){
            return Response.status(409).build();
        }
        
        boolean insertado = personaDAO.insertarPersona(persona);

        return insertado ? Response.ok("Insertado", MediaType.TEXT_PLAIN).build() : Response.notModified().build();
    }

    @PUT
    public Response actualizarPersona(Persona persona) throws Exception {
        boolean actualizado = personaDAO.actualizarPersona(persona);

        return actualizado ? Response.ok("Actualizado", MediaType.TEXT_PLAIN).build() : Response.notModified().build();
    }

    @DELETE
    @Path("{dni}")
    public Response eliminarPersona(@PathParam("dni") String dni) throws Exception {
        boolean eliminado = personaDAO.eliminarPersona(dni);

        return eliminado ? Response.ok("Eliminado", MediaType.TEXT_PLAIN).build() : Response.notModified().build();
    }

}

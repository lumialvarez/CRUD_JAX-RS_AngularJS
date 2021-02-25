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
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/personas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServiceEndPoint {
    private final PersonaDAO personaDAO = new PersonaDAO();

    @GET
    public Response consultarPersonas() throws Exception {
        System.out.println("consultarPersonas");
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

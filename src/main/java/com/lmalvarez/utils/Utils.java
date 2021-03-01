/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lmalvarez.utils;

import com.lmalvarez.model.RespuestaEstandar;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author luigu
 */
public class Utils {
    
    public static Response createResponseOk(){
        return Response.ok().build();
    }
    
    public static Response createResponseOk(Object object){
        return Response.ok().entity(object).build();
    }
    
    public static Response createResponseCreated(String descripcion){
        RespuestaEstandar re = new RespuestaEstandar(Response.Status.CREATED.getStatusCode(), descripcion);
        return Response.status(Response.Status.CREATED.getStatusCode()).entity(re).build();
    }
    
    public static Response createResponseServerError(String descripcion){
        RespuestaEstandar re = new RespuestaEstandar(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), descripcion);
        return Response.serverError().entity(re).build();
    }
    
    public static Response createResponseNotModified(String descripcion){
        RespuestaEstandar re = new RespuestaEstandar(Response.Status.NOT_MODIFIED.getStatusCode(), descripcion);
        return Response.notModified().entity(re).build();
    }
    
    public static Response createResponseConflict(String descripcion){
        RespuestaEstandar re = new RespuestaEstandar(Response.Status.CONFLICT.getStatusCode(), descripcion);
        return Response.status(Response.Status.CONFLICT.getStatusCode()).entity(re).build();
    }
}

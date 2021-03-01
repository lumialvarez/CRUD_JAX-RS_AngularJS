/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lmalvarez.service;

import com.lmalvarez.utils.Utils;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 *
 * @author luigu
 */
@Provider
public class MapeoExcepcion extends Throwable  implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable e) {
        e.printStackTrace();
        return Utils.createResponseServerError(e.getMessage());
    }
}

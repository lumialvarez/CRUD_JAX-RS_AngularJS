/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lmalvarez.model;

/**
 *
 * @author luigu
 */
public class RespuestaEstandar {

    private int codigo;
    private String descripcionCodigo;

    public RespuestaEstandar() {
    }

    public RespuestaEstandar(int codigo, String descripcionCodigo) {
        this.codigo = codigo;
        this.descripcionCodigo = descripcionCodigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcionCodigo() {
        return descripcionCodigo;
    }

    public void setDescripcionCodigo(String descripcionCodigo) {
        this.descripcionCodigo = descripcionCodigo;
    }

}

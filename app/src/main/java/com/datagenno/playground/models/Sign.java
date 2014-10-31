package com.datagenno.playground.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by juliobetta on 10/31/14.
 */

@Table(name = "Signs")
public class Sign extends Model {
    @Column(name = "id_sinal")
    public String id_sinal;

    @Column(name = "id_grupo")
    public String id_grupo;

    @Column(name = "sinal")
    public String sinal;

    @Column(name = "grupo")
    public String grupo;
}

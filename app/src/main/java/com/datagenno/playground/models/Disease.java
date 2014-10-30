package com.datagenno.playground.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by juliobetta on 10/30/14.
 */

@Table(name = "Diseases")
public class Disease extends Model {
    @Column(name = "name")
    public String name;

    @Column(name = "therapy")
    public String therapy;

    @Column(name = "description")
    public String description;

    @Column(name = "id", index = true)
    public Integer id;

    @Column(name = "name_sanitized")
    public String name_sanitized;

    @Column(name = "url")
    public String url;

    @Column(name = "signs")
    public String signs;

    @Column(name = "images")
    public String images;
}

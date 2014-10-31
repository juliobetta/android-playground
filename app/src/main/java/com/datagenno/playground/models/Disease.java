package com.datagenno.playground.models;

/**
 * Created by juliobetta on 10/30/14.
 */

//@Table(name = "Diseases")
public class Disease /** extends Model **/{
    //@Column(name = "name")
    public String name;

    //@Column(name = "therapy")
    public String therapy;

    //@Column(name = "description")
    public String description;

    //@Column(name = "id", index = true)
    public Integer id;

    //@Column(name = "name_sanitized")
    public String name_sanitized;

    //@Column(name = "url")
    public String url;

    //@Column(name = "signs")
    public Object signs;

    //@Column(name = "images")
    public Object images;
}

package com.datagenno.playground.models;

import com.activeandroid.Model;

/**
 * Created by juliobetta on 11/10/14.
 */
public class Group extends Model {
    public String name;

    public Group(String name) {
        this.name = name;
    }


    public String toString() {
        return this.name;
    }
}

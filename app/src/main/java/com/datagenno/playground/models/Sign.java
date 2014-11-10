package com.datagenno.playground.models;

import com.activeandroid.Model;


/**
 * Created by juliobetta on 10/31/14.
 */
public class Sign extends Model {

    public Sign(String name) {
        this.sinal = name;
    }
    public String id_sinal;
    public String id_grupo;
    public String sinal;
    public String grupo;
    public String imagens;
    public String videos;

    public String getId_sinal() {
        return id_sinal;
    }

    public void setId_sinal(String id_sinal) {
        this.id_sinal = id_sinal;
    }

    public String getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(String id_grupo) {
        this.id_grupo = id_grupo;
    }

    public String getSinal() {
        return sinal;
    }

    public void setSinal(String sinal) {
        this.sinal = sinal;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getImagens() {
        return imagens;
    }

    public void setImagens(String imagens) {
        this.imagens = imagens;
    }

    public String getVideos() {
        return videos;
    }

    public void setVideos(String videos) {
        this.videos = videos;
    }

    public String toString() {
        return this.sinal;
    }
}

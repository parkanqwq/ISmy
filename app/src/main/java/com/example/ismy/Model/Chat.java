package com.example.ismy.Model;

public class Chat {

    private String id;
    private String name;
    private String vopros;
    private String otvet;

    public Chat(String id, String name, String vopros, String otvet) {
        this.id = id;
        this.name = name;
        this.vopros = vopros;
        this.otvet = otvet;
    }

    public Chat() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVopros() {
        return vopros;
    }

    public void setVopros(String vopros) {
        this.vopros = vopros;
    }

    public String getOtvet() {
        return otvet;
    }

    public void setOtvet(String otvet) {
        this.otvet = otvet;
    }
}

package br.com.fiap.Simpsons.model;
public class Personagem {
    private String id;
    private String name;
    private String normalized_name;
    private String gender;
    public Personagem(String id, String name, String normalized_name, String gender) {
        this.id = id;
        this.name = name;
        this.normalized_name = normalized_name;
        this.gender = gender;
    }
    // Getters e Setters
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
    public String getNormalized_name() {
        return normalized_name;
    }
    public void setNormalized_name(String normalized_name) {
        this.normalized_name = normalized_name;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
}
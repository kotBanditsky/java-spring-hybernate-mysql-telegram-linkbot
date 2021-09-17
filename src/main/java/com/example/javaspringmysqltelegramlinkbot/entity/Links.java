package com.example.javaspringmysqltelegramlinkbot.entity;

import javax.persistence.*;

@Entity
@Table (name = "baselinks")
public class Links {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name= "link")
    private String link;

    @Column(name= "rate")
    private int rate;

    public Links() {
    }

    public Links(String link, int rate) {
        this.id = id;
        this.link = link;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "links{" +
                "id=" + id +
                ", link='" + link + '\'' +
                ", rate=" + rate +
                '}';
    }

}

package com.cognizant.hqllearn.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qt_id")
    private int id;

    @Column(name = "qt_text", length = 255)
    private String text;

    @OneToMany(mappedBy = "question")
    private Set<Options> optionsList;

    public Question() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Set<Options> getOptionsList() {
        return optionsList;
    }

    public void setOptionsList(Set<Options> optionsList) {
        this.optionsList = optionsList;
    }
}

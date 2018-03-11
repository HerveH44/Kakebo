package com.huneau.kakebo.category;

import com.huneau.kakebo.outcome.Outcome;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue
    private long id;
    private String name;

    @OneToMany(mappedBy = "category_id")
    private List<Outcome> outcomes;

    @OneToMany(mappedBy = "category")
    private List<SubCategory> subCategories;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Outcome> getOutcomes() {
        return outcomes;
    }

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }
}

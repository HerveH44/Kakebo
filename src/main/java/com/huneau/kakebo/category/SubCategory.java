package com.huneau.kakebo.category;

import com.huneau.kakebo.outcome.Outcome;

import javax.persistence.*;
import java.util.List;

@Entity
public class SubCategory {

    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private long category_id;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Category category;

    @OneToMany(mappedBy = "subcategory_id")
    private List<Outcome> outcomes;

    private String name;

    public long getId() {
        return id;
    }

    public Category getCategory() {
        return category;
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

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }
}

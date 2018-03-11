package com.huneau.kakebo.outcome;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.huneau.kakebo.category.Category;
import com.huneau.kakebo.category.SubCategory;

import javax.persistence.*;
import java.util.Date;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Outcome {

    @Id
    @GeneratedValue()
    private long id;
    private String name;
    private String notes;
    private long cost;
    @Column(nullable = false)
    private long category_id;
    @Column(nullable = false)
    private long subcategory_id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "subcategory_id", referencedColumnName = "id", insertable = false, updatable = false)
    private SubCategory subcategory;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public long getSubcategory_id() {
        return subcategory_id;
    }

    public void setSubcategory_id(long subcategory_id) {
        this.subcategory_id = subcategory_id;
    }
}

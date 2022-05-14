package kz.runtime.catalog.jpa.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "options", schema = "public", catalog = "postgres")

public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "option")
    private List<Value> values;

    private String name;

    public Option() {
    }

    public Option(Long id, Category category, List<Value> values, String name) {
        this.id = id;
        this.category = category;
        this.values = values;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
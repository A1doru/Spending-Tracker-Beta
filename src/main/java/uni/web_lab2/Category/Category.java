package uni.web_lab2.Category;

import jakarta.persistence.*;

@Entity
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private double amountOfSpendings;

    public Category() {
    }

    public Category(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Category(Long id, String title, String description, double amountOfSpendings) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.amountOfSpendings = amountOfSpendings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmountOfSpendings() {
        return amountOfSpendings;
    }

    public void setAmountOfSpendings(double amountOfSpendings) {
        this.amountOfSpendings = amountOfSpendings;
    }
}

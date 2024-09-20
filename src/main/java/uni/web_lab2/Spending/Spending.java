package uni.web_lab2.Spending;

import jakarta.persistence.*;
import uni.web_lab2.Category.Category;

import java.time.LocalDate;

@Entity
@Table(name = "Spending")
public class Spending {

    @Id
    @SequenceGenerator(
            name = "spending_sequence",
            sequenceName = "spending_sequence",
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "spending_sequence"
    )
    private Long id;

    private LocalDate date;
    private String note;
    private Double amount;
    private Boolean isRepeated;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Spending() {
    }

    public Spending(String note, double amount, Category category,LocalDate date, Boolean isRepeated) {
        this.date = date;
        this.note = note;
        this.amount = amount;
        this.category = category;
        this.isRepeated = isRepeated;
    }

    public Spending(long id, LocalDate date, String note, double amount, Category category, Boolean isRepeated) {
        this.id = id;
        this.date = date;
        this.note = note;
        this.amount = amount;
        this.category = category;
        this.isRepeated = isRepeated;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Spending [id=" + id +
                ", category=" + category.getTitle() +
                ", date=" + date +
                ", note=" + note +
                ", amount=" + amount +
                "]";
    }

    public Boolean getRepeated() {
        return isRepeated;
    }

    public void setRepeated(Boolean repeated) {
        isRepeated = repeated;
    }
}


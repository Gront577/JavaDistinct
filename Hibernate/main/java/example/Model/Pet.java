package example.Model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    private ColorEnum color;

    private String poroda;

    private LocalDate BDay;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;

    @ManyToMany
    @JoinTable(
            name = "cat_friends",
            joinColumns = @JoinColumn(name = "cat_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private List<Pet> friends = new ArrayList<>();

    public enum ColorEnum {
        BLACK, WHITE, BROWN
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public ColorEnum getColor() {
        return color;
    }

    public String getPoroda() {
        return poroda;
    }

    public LocalDate getBDay() {
        return BDay;
    }

    public Owner getOwner() {
        return owner;
    }

    public List<Pet> getFriends() {
        return friends;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setColor(ColorEnum color) {
        this.color = color;
    }

    public void setPoroda(String poroda) {
        this.poroda = poroda;
    }

    public void setBDay(LocalDate BDay) {
        this.BDay = BDay;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void setFriends(List<Pet> friends) {
        this.friends = friends;
    }
}

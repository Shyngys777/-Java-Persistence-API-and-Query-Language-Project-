package ylix9g.catalog.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "characteristics")
public class Characteristics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    @OneToMany
    @JoinColumn(name = "characteristics")
    private List<CharacteristicsMeaning> characteristicsMeaningList;

    private String name;

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

    public List<CharacteristicsMeaning> getCharacteristicsMeaningList() {
        return characteristicsMeaningList;
    }

    public void setCharacteristicsMeaningList(List<CharacteristicsMeaning> characteristicsMeaningList) {
        this.characteristicsMeaningList = characteristicsMeaningList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package ashsic.SpiritFare.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cards")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = SpiritCard.class, name = "SpiritCard"),
    @JsonSubTypes.Type(value = EquipmentCard.class, name = "equipment")
})
public abstract class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private Element element;
    @Enumerated(EnumType.STRING)
    private ResourceType resourceType;
    private int resourceCost;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "card_effect_id")
    private CardEffect cardEffect;

    public enum ResourceType {
        BLOOD,
        MANA
    }

    public abstract CardType getType();
    public abstract void activate(GameState state);
    public abstract boolean canActivate(GameState state);
}


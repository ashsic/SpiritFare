package ashsic.SpiritFare.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
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


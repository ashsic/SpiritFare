package ashsic.SpiritFare.models;

import lombok.Data;

@Data
public abstract class Card {
    private Long id;
    private String name;
    private String description;
    private Element element;
    private ResourceType resourceType;
    private int resourceCost;
    private CardEffect cardEffect;

    public enum ResourceType {
        BLOOD,
        MANA
    }

    public abstract CardType getType();
    
    public abstract void activate(GameState state);
    public abstract boolean canActivate(GameState state);
}
// This file should remain unchanged - the classes should be created in separate files:


// SpiritCard.java:
public class SpiritCard extends Card {
    private int attack;
    private int health;

    @Override
    public CardType getType() {
        return CardType.SPIRIT;
    }

    @Override
    public void activate(GameState state) {
        // Spirit-specific activation logic
    }

    @Override
    public boolean canActivate(GameState state) {
        // Spirit-specific activation check
        return true;
    }
}

// EquipmentCard.java:
public class EquipmentCard extends Card {
    private int durability;

    @Override
    public CardType getType() {
        return CardType.EQUIPMENT;
    }

    @Override
    public void activate(GameState state) {
        // Equipment-specific activation logic
    }

    @Override
    public boolean canActivate(GameState state) {
        // Equipment-specific activation check
        return true;
    }
}


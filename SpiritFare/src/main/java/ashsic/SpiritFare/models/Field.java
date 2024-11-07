package ashsic.SpiritFare.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Field {
    private Long id;
    private String name;
    private String description;
    private Element dominantElement;
    private FieldEffect fieldEffect;
    private boolean isActive;
    
    // Represents the environmental effects that can be applied to cards
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FieldEffect {
        private ElementalBonus elementalBonus;
        private ResourceModifier resourceModifier;
        private String specialEffect;
        
        public void applyEffect(GameState gameState) {
            // Apply field effects to the game state
            if (elementalBonus != null) {
                // Apply elemental bonuses to matching cards
            }
            
            if (resourceModifier != null) {
                // Modify resource generation/costs
            }
        }
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ElementalBonus {
        private Element element;
        private int attackBonus;
        private int healthBonus;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResourceModifier {
        private Card.ResourceType resourceType;
        private int costModifier;      // Negative for discount, positive for increase
        private int generationBonus;   // Amount of extra resources generated per turn
    }
    
    public void activate(GameState gameState) {
        if (isActive && fieldEffect != null) {
            fieldEffect.applyEffect(gameState);
        }
    }
    
    public void deactivate() {
        this.isActive = false;
    }
    
    public boolean counters(Field otherField) {
        if (this.dominantElement == null || otherField.getDominantElement() == null) {
            return false;
        }
        
        return switch (this.dominantElement) {
            case WATER -> otherField.getDominantElement() == Element.FIRE;
            case FIRE -> otherField.getDominantElement() == Element.EARTH;
            case EARTH -> otherField.getDominantElement() == Element.AIR;
            case AIR -> otherField.getDominantElement() == Element.WATER;
            case VOID -> true;  // VOID counters all elements
        };
    }
} 
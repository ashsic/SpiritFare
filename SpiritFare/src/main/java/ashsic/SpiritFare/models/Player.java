package ashsic.SpiritFare.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    private Long id;
    private String username;
    private int maxHealth;
    private int maxMana;
    private int maxBlood;
    private List<Card> deck;
    private List<Card> hand;
    private List<Card> field;
    private List<Card> graveyard;
    
    // Constructor with essential fields
    public Player(Long id, String username) {
        this.id = id;
        this.username = username;
        this.maxHealth = 20;  // Default starting health
        this.maxMana = 10;    // Default max mana
        this.maxBlood = 5;    // Default max blood
        this.deck = new ArrayList<>();
        this.hand = new ArrayList<>();
        this.field = new ArrayList<>();
        this.graveyard = new ArrayList<>();
    }

    public boolean canPlayCard(Card card, GameState gameState) {
        return gameState.hasEnoughResources(this, card) && 
               card.canActivate(gameState);
    }

    public void playCard(Card card, GameState gameState) {
        if (!canPlayCard(card, gameState)) {
            throw new IllegalStateException("Cannot play this card");
        }

        gameState.spendResources(this, card);
        hand.remove(card);
        
        if (card.getType() == CardType.SPIRIT || card.getType() == CardType.EQUIPMENT) {
            field.add(card);
        } else {
            graveyard.add(card);
        }
        
        card.activate(gameState);
    }
} 
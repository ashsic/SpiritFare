package ashsic.SpiritFare.models;

import lombok.Data;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import ashsic.SpiritFare.models.Card.ResourceType;

import java.util.Arrays;
import java.util.HashMap;

@Data
public class GameState {
    private Long id;
    private Player[] players;
    private int currentTurn;
    private int activePlayerIndex;
    private GamePhase currentPhase;
    private Map<Player, Integer> playerMana;
    private Map<Player, Integer> playerBlood;
    private Card[][] playerHands;
    private Map<Player, List<Card>> playerDecks;
    private Map<Player, Card[]> playerFields;
    private Map<Player, List<Card>> playerDiscards;

    public enum GamePhase {
        DRAW,
        MAIN,
        COMBAT,
        END
    }

    public GameState() {
        this.players = new Player[2];
        this.playerMana = new HashMap<>();
        this.playerHands = new Card[2][6];
        this.playerDecks = new HashMap<>();
        this.playerFields = new HashMap<>();
        this.playerDiscards = new HashMap<>();
    }

    public boolean isPlayerTurn(Player player) {
        return players[activePlayerIndex].equals(player);
    }

    public void nextTurn() {
        activePlayerIndex = (activePlayerIndex + 1) % 2;
        currentTurn++;
        currentPhase = GamePhase.DRAW;
    }

    public void nextPhase() {
        currentPhase = switch (currentPhase) {
            case DRAW -> GamePhase.MAIN;
            case MAIN -> GamePhase.COMBAT;
            case COMBAT -> GamePhase.END;
            case END -> {
                nextTurn();
                yield GamePhase.DRAW;
            }
        };
    }

    public boolean hasEnoughResources(Player player, Card card) {
        return switch (card.getResourceType()) {
            case MANA -> playerMana.getOrDefault(player, 0) >= card.getResourceCost();
            case BLOOD -> playerBlood.getOrDefault(player, 0) >= card.getResourceCost();
        };
    }

    public void spendResources(Player player, Card card) {
        switch (card.getResourceType()) {
            case MANA -> playerMana.merge(player, -card.getResourceCost(), Integer::sum);
            case BLOOD -> playerBlood.merge(player, -card.getResourceCost(), Integer::sum);
        }
    }

    public void addResources(Player player, ResourceType type, int amount) {
        switch (type) {
            case MANA -> playerMana.merge(player, amount, Integer::sum);
            case BLOOD -> playerBlood.merge(player, amount, Integer::sum);
        }
    }

    public void setResources(Player player, ResourceType type, int amount) {
        switch (type) {
            case MANA -> playerMana.put(player, Math.min(amount, player.getMaxMana()));
            case BLOOD -> playerBlood.put(player, Math.min(amount, player.getMaxBlood()));
        }
    }

    public boolean isGameOver() {
        return playerMana.values().stream().anyMatch(mana -> mana <= 0);
    }
    
    public Player getWinner() {
        if (!isGameOver()) {
            return null;
        }
        return players[playerMana.get(players[0]) <= 0 ? 1 : 0];
    }

    public void drawCard(Player player) {
        List<Card> deck = playerDecks.get(player);
        if (deck.isEmpty()) {
            // Handle deck out condition
            return;
        }
        Card card = deck.remove(0);
        List<Card> hand = Arrays.asList(playerHands[getPlayerIndex(player)]);
        if (hand.stream().filter(Objects::nonNull).count() < 6) {
            // Add to first empty slot
            for (int i = 0; i < 6; i++) {
                if (playerHands[getPlayerIndex(player)][i] == null) {
                    playerHands[getPlayerIndex(player)][i] = card;
                    break;
                }
            }
        } else {
            playerDiscards.get(player).add(card);
        }
    }

    private int getPlayerIndex(Player player) {
        return player.equals(players[0]) ? 0 : 1;
    }
} 
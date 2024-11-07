package ashsic.SpiritFare.controllers;

import ashsic.SpiritFare.models.*;
import ashsic.SpiritFare.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    private CardRepository cardRepository;

    @GetMapping
    public List<Card> getAllCards() {
        return (List<Card>) cardRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable Long id) {
        Optional<Card> card = cardRepository.findById(id);
        return card.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/element/{element}")
    public List<Card> getCardsByElement(@PathVariable Element element) {
        return cardRepository.findByElement(element);
    }

    @GetMapping("/cost/{cost}")
    public List<Card> getCardsByCost(@PathVariable int cost) {
        return cardRepository.findByResourceCost(cost);
    }

    @GetMapping("/spirits/attack/{attack}")
    public List<SpiritCard> getSpiritsByMinAttack(@PathVariable int attack) {
        return cardRepository.findSpiritCardByAttackGreaterThan(attack);
    }

    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        try {
            Card savedCard = cardRepository.save(card);
            return ResponseEntity.ok(savedCard);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCard(@PathVariable Long id) {
        if (cardRepository.existsById(id)) {
            cardRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Card> updateCard(@PathVariable Long id, @RequestBody Card updatedCard) {
        if (!cardRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        try {
            updatedCard.setId(id); // Ensure we're updating the correct card
            Card savedCard = cardRepository.save(updatedCard);
            return ResponseEntity.ok(savedCard);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

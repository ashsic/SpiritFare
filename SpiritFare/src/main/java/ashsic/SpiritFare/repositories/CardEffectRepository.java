package ashsic.SpiritFare.repositories;

import ashsic.SpiritFare.models.CardEffect;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardEffectRepository extends CrudRepository<CardEffect, Long> {
} 
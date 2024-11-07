-- First insert card effects
INSERT INTO card_effects (id, effect_name, effect_description, effect_value, duration, effect_type) VALUES
(1, 'Basic Attack', 'Deals damage to target', 3, 0, 'DAMAGE'),
(2, 'Minor Heal', 'Restores health to target', 2, 0, 'HEAL'),
(3, 'Strength Buff', 'Increases attack power', 2, 2, 'BUFF'),
(4, 'Draw Cards', 'Draw additional cards', 1, 0, 'DRAW'),
(5, 'Mana Surge', 'Gain additional mana', 2, 0, 'RESOURCE_GAIN'),
(6, 'Weaken', 'Reduces target''s attack power', -2, 2, 'DEBUFF');

-- Then insert cards with references to card effects
INSERT INTO cards (dtype, name, description, element, resource_type, resource_cost, attack, defense, card_effect_id) VALUES
('SpiritCard', 'Flame Strike', 'A basic fire attack', 'FIRE', 'MANA', 2, 5, 5, 1),
('SpiritCard', 'Healing Light', 'Restore health to a friendly target', 'LIGHT', 'MANA', 2, 5, 5, 2),
('SpiritCard', 'Battle Rage', 'Increase your attack power', 'NEUTRAL', 'BLOOD', 1, 5, 5, 3),
('SpiritCard', 'Arcane Intellect', 'Draw more cards', 'ARCANE', 'MANA', 3, 5, 5, 4),
('SpiritCard', 'Mana Crystal', 'Gain additional mana', 'ARCANE', 'MANA', 0, 5, 5, 5),
('SpiritCard', 'Shadow Weakness', 'Weaken your opponent', 'DARK', 'BLOOD', 2, 5, 5, 6);
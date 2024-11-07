CREATE TABLE IF NOT EXISTS card_effects (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    effect_name VARCHAR(255) NOT NULL,
    effect_description TEXT,
    effect_value INT,
    duration INT,
    effect_type VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS cards (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    dtype VARCHAR(31) NOT NULL,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    element VARCHAR(20),
    resource_type VARCHAR(20),
    resource_cost INT,
    card_effect_id BIGINT,
    attack INT,
    defense INT,
    CONSTRAINT fk_card_effect FOREIGN KEY (card_effect_id) REFERENCES card_effects(id)
);

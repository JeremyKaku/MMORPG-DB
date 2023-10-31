DROP SCHEMA IF EXISTS CS5200Project;
CREATE SCHEMA CS5200Project;
USE CS5200Project;

CREATE TABLE player(
  player_id INT AUTO_INCREMENT,
  player_name VARCHAR(50) NOT NULL,
  email VARCHAR(255) NOT NULL,
  `password` CHAR(64) NOT NULL,
  CONSTRAINT pk_player_player_id PRIMARY KEY(player_id)
);

CREATE TABLE item(
  item_id INT AUTO_INCREMENT,
  item_name VARCHAR(50) NOT NULL,
  max_stack_size INT NOT NULL,
  vendor_price INT,
  CONSTRAINT pk_item_item_id PRIMARY KEY(item_id)
);

CREATE TABLE gear_weapon_customization(
  customized_id INT AUTO_INCREMENT,
  item_quality ENUM('high', 'normal') NOT NULL,
  `condition` DECIMAL(10,2) NOT NULL,
  dye_color VARCHAR(255),
  maker VARCHAR(255),
  CONSTRAINT pk_gear_weapon_customization_customized_id PRIMARY KEY(customized_id)
);

CREATE TABLE gear_slot(
  gear_slot_id INT,
  gear_slot_name VARCHAR(255) NOT NULL,
  CONSTRAINT pk_gear_slot_gear_slot_id PRIMARY KEY(gear_slot_id)
);

CREATE TABLE gear(
  item_id INT,
  item_level INT NOT NULL,
  gear_slot_id INT NOT NULL,
  required_level INT NOT NULL,
  defense_rating INT NOT NULL,
  magic_defense_rating INT NOT NULL,
  customized_id INT,
  CONSTRAINT pk_gear_item_id PRIMARY KEY(item_id),
  CONSTRAINT fk_gear_item_id FOREIGN KEY(item_id)
    REFERENCES item(item_id)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_gear_gear_slot_id FOREIGN KEY(gear_slot_id)
    REFERENCES gear_slot(gear_slot_id)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_gear_customized_id FOREIGN KEY(customized_id)
    REFERENCES gear_weapon_customization(customized_id)
    ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE weapon(
  item_id INT,
  item_level INT NOT NULL,
  required_level INT NOT NULL,
  damage_done INT NOT NULL,
  auto_attack DECIMAL(10,2) NOT NULL,
  attack_delay DECIMAL(10,2) NOT NULL,
  customized_id INT,
  CONSTRAINT pk_weapon_item_id PRIMARY KEY(item_id),
  CONSTRAINT fk_weapon_item_id FOREIGN KEY(item_id)
    REFERENCES item(item_id)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_weapon_customized_id FOREIGN KEY(customized_id)
    REFERENCES gear_weapon_customization(customized_id)
    ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE consumable_item(
  item_id INT,
  item_level INT NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  CONSTRAINT pk_consumable_item_item_id PRIMARY KEY(item_id),
  CONSTRAINT fk_consumable_item_item_id FOREIGN KEY(item_id)
    REFERENCES item(item_id)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE miscellaneous_item(
  item_id INT,
  `description` VARCHAR(255) NOT NULL,
  CONSTRAINT pk_miscellaneous_item_item_id PRIMARY KEY(item_id),
  CONSTRAINT fk_miscellaneous_item_item_id FOREIGN KEY(item_id)
    REFERENCES item(item_id)
    ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE `character`(
  character_id INT AUTO_INCREMENT,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  player_id INT  NOT NULL,
  weapon_id INT NOT NULL,
  CONSTRAINT pk_character_character_id PRIMARY KEY(character_id),
  CONSTRAINT fk_character_player_id FOREIGN KEY(player_id)
    REFERENCES player(player_id)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT uq_character_first_name_last_name UNIQUE(first_name, last_name),
  CONSTRAINT fk_character_weapon_id FOREIGN KEY(weapon_id)
    REFERENCES weapon(item_id)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE currency(
  currency_id INT AUTO_INCREMENT,
  currency_name VARCHAR(50) NOT NULL,
  maximum_amount INT NOT NULL,
  weekly_cap INT,
  availiability BOOL NOT NULL,
  CONSTRAINT pk_currency_currency_id PRIMARY KEY(currency_id)
);

CREATE TABLE character_currency(
  character_id INT NOT NULL,
  currency_id INT NOT NULL,
  weekly_amount INT,
  total_amount INT NOT NULL,
  CONSTRAINT pk_character_currency_character_id_currency_id PRIMARY KEY(character_id, currency_id),
  CONSTRAINT fk_character_currency_character_id FOREIGN KEY(character_id)
    REFERENCES `character`(character_id)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_character_currency_currency_id FOREIGN KEY(currency_id)
    REFERENCES currency(currency_id)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE job(
  job_id INT AUTO_INCREMENT,
  job_name VARCHAR(50) NOT NULL,
  availiability BOOL NOT NULL,
  CONSTRAINT pk_job_job_id PRIMARY KEY(job_id)
);

CREATE TABLE character_job(
  character_id INT NOT NULL,
  job_id INT NOT NULL,
  job_level INT NOT NULL,
  experience_point INT NOT NULL,
  is_current_job BOOL NOT NULL,
  CONSTRAINT pk_character_job_character_id_job_id PRIMARY KEY(character_id, job_id),
  CONSTRAINT fk_character_job_character_id FOREIGN KEY(character_id)
    REFERENCES `character`(character_id)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_character_job_job_id FOREIGN KEY(job_id)
    REFERENCES job(job_id)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE attribute(
  attribute_id INT AUTO_INCREMENT,
  attribute_name VARCHAR(50) NOT NULL,
  CONSTRAINT pk_attribute_attribute_id PRIMARY KEY(attribute_id)
);

CREATE TABLE character_attribute(
  attribute_id INT,
  character_id INT,
  attribute_value INT NOT NULL,
  CONSTRAINT pk_character_attribute_attribute_id_character_id PRIMARY KEY(attribute_id, character_id),
  CONSTRAINT fk_character_attribute_attribute_id FOREIGN KEY(attribute_id)
    REFERENCES attribute(attribute_id)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_character_attribute_character_id FOREIGN KEY(character_id)
    REFERENCES `character`(character_id)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE character_equipped(
  character_id INT,
  gear_slot_id INT,
  item_id INT NOT NULL,
  CONSTRAINT pk_character_equipped_character_id_gear_slot_id PRIMARY KEY(character_id, gear_slot_id),
  CONSTRAINT fk_character_equipped_character_id FOREIGN KEY(character_id)
    REFERENCES `character`(character_id)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_character_equipped_gear_slot_id FOREIGN KEY(gear_slot_id)
    REFERENCES gear_slot(gear_slot_id)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_character_equipped_item_id FOREIGN KEY(item_id)
    REFERENCES gear(item_id)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE gear_weapon_bonus(
  item_id INT,
  attribute_id INT,
  bonus_value INT NOT NULL,
  CONSTRAINT pk_gear_weapon_bonus_item_id_attribute_id PRIMARY KEY(item_id, attribute_id),
  CONSTRAINT fk_gear_weapon_bonus_item_id_gear FOREIGN KEY(item_id)
    REFERENCES gear(item_id)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_gear_weapon_bonus_item_id_weapon FOREIGN KEY(item_id)
    REFERENCES weapon(item_id)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_gear_weapon_bonus_attribute_id FOREIGN KEY(attribute_id)
    REFERENCES attribute(attribute_id)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE consumable_item_effect(
  item_id INT,
  attribute_id INT,
  effect_percentage DECIMAL(10,2) NOT NULL,
  max_effect_value INT NOT NULL,
  CONSTRAINT pk_consumable_item_effect_item_id_attribute_id PRIMARY KEY(item_id, attribute_id),
  CONSTRAINT fk_consumable_item_effect_item_id FOREIGN KEY(item_id)
    REFERENCES consumable_item(item_id)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_consumable_item_effect_attribute_id FOREIGN KEY(attribute_id)
    REFERENCES attribute(attribute_id)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE inventory(
  inventory_slot_id INT AUTO_INCREMENT,
  character_id INT,
  quantity INT NOT NULL,
  item_id INT NOT NULL,
  CONSTRAINT pk_inventory_inventory_slot_id_character_id PRIMARY KEY(inventory_slot_id, character_id),
  CONSTRAINT fk_inventory_inventory_character_id FOREIGN KEY(character_id)
    REFERENCES `character`(character_id)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_inventory_inventory_item_id FOREIGN KEY(item_id)
    REFERENCES item(item_id)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE gear_weapon_job(
 item_id INT,
 job_id INT,
 CONSTRAINT pk_gear_weapon_job_item_id_job_id PRIMARY KEY(item_id, job_id),
 CONSTRAINT fk_gear_weapon_job_item_id_gear FOREIGN KEY(item_id)
   REFERENCES gear(item_id)
   ON UPDATE CASCADE ON DELETE CASCADE,
 CONSTRAINT fk_gear_weapon_job_item_id_weapon FOREIGN KEY(item_id)
   REFERENCES weapon(item_id)
   ON UPDATE CASCADE ON DELETE CASCADE,
 CONSTRAINT fk_gear_weapon_job_job_id FOREIGN KEY(job_id)
   REFERENCES job(job_id)
   ON UPDATE CASCADE ON DELETE CASCADE
);







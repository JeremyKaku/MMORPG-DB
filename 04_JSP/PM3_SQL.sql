DROP SCHEMA IF EXISTS CS5200Project;
CREATE SCHEMA CS5200Project;
USE CS5200Project;

CREATE TABLE player (
    player_id INT AUTO_INCREMENT,
    player_name VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL,
    player_password VARCHAR(64) NOT NULL,
    CONSTRAINT pk_player_player_id PRIMARY KEY (player_id)
);

CREATE TABLE item (
    item_id INT AUTO_INCREMENT,
    item_name VARCHAR(50) NOT NULL,
    max_stack_size INT NOT NULL,
    vendor_price INT,
    CONSTRAINT pk_item_item_id PRIMARY KEY (item_id)
);

CREATE TABLE quality (
    item_quality VARCHAR(64),
    CONSTRAINT pk_quality_item_quality PRIMARY KEY (item_quality)
);

CREATE TABLE gear_slot (
    gear_slot_id INT AUTO_INCREMENT,
    gear_slot_name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_gear_slot_gear_slot_id PRIMARY KEY (gear_slot_id)
);

CREATE TABLE gear (
    item_id INT,
    item_level INT NOT NULL,
    gear_slot_id INT NOT NULL,
    required_level INT NOT NULL,
    defense_rating INT NOT NULL,
    magic_defense_rating INT NOT NULL,
    CONSTRAINT pk_gear_item_id PRIMARY KEY (item_id),
    CONSTRAINT fk_gear_item_id FOREIGN KEY (item_id)
        REFERENCES item (item_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_gear_gear_slot_id FOREIGN KEY (gear_slot_id)
        REFERENCES gear_slot (gear_slot_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE weapon (
    item_id INT,
    item_level INT NOT NULL,
    required_level INT NOT NULL,
    damage_done INT NOT NULL,
    auto_attack DECIMAL(10 , 2 ) NOT NULL,
    attack_delay DECIMAL(10 , 2 ) NOT NULL,
    CONSTRAINT pk_weapon_item_id PRIMARY KEY (item_id),
    CONSTRAINT fk_weapon_item_id FOREIGN KEY (item_id)
        REFERENCES item (item_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE customized_gear (
    item_id INT,
    item_quality VARCHAR(64) NOT NULL,
    customized_condition INT NOT NULL,
    dye_color VARCHAR(6),
    maker VARCHAR(255),
    CONSTRAINT pk_customized_gear_item_id PRIMARY KEY (item_id),
    CONSTRAINT fk_customized_gear_item_id FOREIGN KEY (item_id)
        REFERENCES gear (item_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_customized_gear_item_quality FOREIGN KEY (item_quality)
        REFERENCES quality (item_quality)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE customized_weapon (
    item_id INT,
    item_quality VARCHAR(64) NOT NULL,
    customized_condition INT NOT NULL,
    dye_color VARCHAR(6),
    maker VARCHAR(255),
    CONSTRAINT pk_customized_weapon_item_id PRIMARY KEY (item_id),
    CONSTRAINT fk_customized_weapon_item_id FOREIGN KEY (item_id)
        REFERENCES weapon (item_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_customized_weapon_item_quality FOREIGN KEY (item_quality)
        REFERENCES quality (item_quality)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE consumable_item (
    item_id INT,
    item_level INT NOT NULL,
    item_description TEXT NOT NULL,
    CONSTRAINT pk_consumable_item_item_id PRIMARY KEY (item_id),
    CONSTRAINT fk_consumable_item_item_id FOREIGN KEY (item_id)
        REFERENCES item (item_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE miscellaneous_item (
    item_id INT,
    item_description TEXT NOT NULL,
    CONSTRAINT pk_miscellaneous_item_item_id PRIMARY KEY (item_id),
    CONSTRAINT fk_miscellaneous_item_item_id FOREIGN KEY (item_id)
        REFERENCES item (item_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE game_character (
    character_id INT AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    player_id INT NOT NULL,
    weapon_id INT NOT NULL,
    CONSTRAINT pk_game_character_character_id PRIMARY KEY (character_id),
    CONSTRAINT fk_game_character_player_id FOREIGN KEY (player_id)
        REFERENCES player (player_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT uq_game_character_first_name_last_name UNIQUE (first_name , last_name),
    CONSTRAINT fk_game_character_weapon_id FOREIGN KEY (weapon_id)
        REFERENCES weapon (item_id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE currency (
    currency_id INT AUTO_INCREMENT,
    currency_name VARCHAR(50) NOT NULL,
    maximum_amount INT NOT NULL,
    weekly_cap INT,
    availiability BOOL NOT NULL,
    CONSTRAINT pk_currency_currency_id PRIMARY KEY (currency_id)
);

CREATE TABLE character_currency (
    character_id INT NOT NULL,
    currency_id INT NOT NULL,
    weekly_amount INT,
    total_amount INT NOT NULL,
    CONSTRAINT pk_character_currency_character_id_currency_id PRIMARY KEY (character_id , currency_id),
    CONSTRAINT fk_character_currency_character_id FOREIGN KEY (character_id)
        REFERENCES game_character (character_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_character_currency_currency_id FOREIGN KEY (currency_id)
        REFERENCES currency (currency_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE job (
    job_id INT AUTO_INCREMENT,
    job_name VARCHAR(50) NOT NULL,
    availiability BOOL NOT NULL,
    CONSTRAINT pk_job_job_id PRIMARY KEY (job_id)
);

CREATE TABLE character_job (
    character_id INT NOT NULL,
    job_id INT NOT NULL,
    job_level INT NOT NULL,
    experience_point INT NOT NULL,
    is_current_job BOOL NOT NULL,
    CONSTRAINT pk_character_job_character_id_job_id PRIMARY KEY (character_id , job_id),
    CONSTRAINT fk_character_job_character_id FOREIGN KEY (character_id)
        REFERENCES game_character (character_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_character_job_job_id FOREIGN KEY (job_id)
        REFERENCES job (job_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE attribute (
    attribute_id INT AUTO_INCREMENT,
    attribute_name VARCHAR(50) NOT NULL,
    CONSTRAINT pk_attribute_attribute_id PRIMARY KEY (attribute_id)
);

CREATE TABLE character_attribute (
    character_id INT,
    attribute_id INT,
    attribute_value INT NOT NULL,
    CONSTRAINT pk_character_attribute_attribute_id_character_id PRIMARY KEY (attribute_id , character_id),
    CONSTRAINT fk_character_attribute_attribute_id FOREIGN KEY (attribute_id)
        REFERENCES attribute (attribute_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_character_attribute_character_id FOREIGN KEY (character_id)
        REFERENCES game_character (character_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE character_equipped (
    character_id INT,
    gear_slot_id INT,
    item_id INT NOT NULL,
    CONSTRAINT pk_character_equipped_character_id_gear_slot_id PRIMARY KEY (character_id , gear_slot_id),
    CONSTRAINT fk_character_equipped_character_id FOREIGN KEY (character_id)
        REFERENCES game_character (character_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_character_equipped_gear_slot_id FOREIGN KEY (gear_slot_id)
        REFERENCES gear_slot (gear_slot_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_character_equipped_item_id FOREIGN KEY (item_id)
        REFERENCES gear (item_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE gear_bonus (
    item_id INT,
    attribute_id INT,
    bonus_value INT NOT NULL,
    CONSTRAINT pk_gear_bonus_item_id_attribute_id PRIMARY KEY (item_id , attribute_id),
    CONSTRAINT fk_gear_bonus_item_id FOREIGN KEY (item_id)
        REFERENCES gear (item_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_gear_bonus_attribute_id FOREIGN KEY (attribute_id)
        REFERENCES attribute (attribute_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE weapon_bonus (
    item_id INT,
    attribute_id INT,
    bonus_value INT NOT NULL,
    CONSTRAINT pk_weapon_bonus_item_id_attribute_id PRIMARY KEY (item_id , attribute_id),
    CONSTRAINT fk_weapon_bonus_item_id FOREIGN KEY (item_id)
        REFERENCES weapon (item_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_weapon_bonus_attribute_id FOREIGN KEY (attribute_id)
        REFERENCES attribute (attribute_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE consumable_item_effect (
    item_id INT,
    attribute_id INT,
    effect_percentage INT NOT NULL,
    max_effect_value INT NOT NULL,
    CONSTRAINT pk_consumable_item_effect_item_id_attribute_id PRIMARY KEY (item_id , attribute_id),
    CONSTRAINT fk_consumable_item_effect_item_id FOREIGN KEY (item_id)
        REFERENCES consumable_item (item_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_consumable_item_effect_attribute_id FOREIGN KEY (attribute_id)
        REFERENCES attribute (attribute_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE inventory (
    character_id INT,
    inventory_slot_id INT,
    quantity INT NOT NULL,
    item_id INT NOT NULL,
    CONSTRAINT pk_inventory_inventory_slot_id_character_id PRIMARY KEY (inventory_slot_id , character_id),
    CONSTRAINT fk_inventory_inventory_character_id FOREIGN KEY (character_id)
        REFERENCES game_character (character_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_inventory_inventory_item_id FOREIGN KEY (item_id)
        REFERENCES item (item_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE gear_job (
    item_id INT,
    job_id INT,
    CONSTRAINT pk_gear_job_item_id_job_id PRIMARY KEY (item_id , job_id),
    CONSTRAINT fk_gear_job_item_id FOREIGN KEY (item_id)
        REFERENCES gear (item_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_gear_job_job_id FOREIGN KEY (job_id)
        REFERENCES job (job_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE weapon_job (
    item_id INT,
    job_id INT,
    CONSTRAINT pk_gear_weapon_job_item_id_job_id PRIMARY KEY (item_id , job_id),
    CONSTRAINT fk_weapon_job_item_id FOREIGN KEY (item_id)
        REFERENCES weapon (item_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_weapon_job_job_id FOREIGN KEY (job_id)
        REFERENCES job (job_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

-- Insert Data
INSERT INTO player VALUES
(1,'Jing Guo','guo.jing2@northeastern.edu','password123'),
(2,'Xue Han','han.xue2@northeastern.edu','password123'),
(3,'Riddhi Gohil','gohil.r@northeastern.edu','password123'),
(4,'Yashvi Garg','garg.yas@northeastern.edu','password123'),
(5,'Gaurav Jurani','jurani.g@northeastern.edu','password123'),
(6,'Lee HOI','dc.g@northeastern.edu','password123'),
(7,'John Smith','dsi.g@northeastern.edu','password123'),
(8,'Joe Raku','jssni.g@northeastern.edu','password123'),
(9,'Hash Go','jaani.g@northeastern.edu','password123'),
(10,'Mina Nai','srani.g@northeastern.edu','password123');

INSERT INTO job VALUES
(1,'Dragoon',TRUE),
(2,'Bard',TRUE),
(3,'Dancer',TRUE),
(4,'Dark Knight',TRUE),
(5,'Scholar',TRUE),
(6,'Paladin',TRUE),
(7,'Lancer',TRUE),
(8,'Reaper',TRUE),
(9,'mage',TRUE),
(10,'Samurai',TRUE);

INSERT INTO attribute VALUES
(1,'Strength'),
(2,'Vitality'),
(3,'Dexterity'),
(4,'Critical Hit'),
(5,'Direct Hit Rate'),
(6,'Determination'),
(7,'Mind'),
(8,'Critical'),
(9,'Speed Hit'),
(10,'Skill Speed'),
(11,'Tenacity');

INSERT INTO item VALUES
(1,'Majestic Manderville Spear',1,NULL),
(2,'Amazing Manderville Harp Bow',1,NULL),
(3,'Replica Manderville Chakrams',1,1000),
(4,'Serpent Private‘s Zweihander',1,90),
(5,'Law‘s Order Codex',1,NULL),
(6,'Majestic Manderville Shield',1,NULL),
(7,'Ascension Face Guard of Maiming	',1,NULL),
(8,'Ascension Mail of Maiming',1,NULL),
(9,'Ascension Gauntlets of Maiming',1,NULL),
(10,'Ascension Hose of Maiming',1,NULL),
(11,'Ascension Sollerets of Maiming',1,NULL),
(12,'Ascension Earring of Slaying',1,NULL),
(13,'Ascension Necklace of Slaying',1,NULL),
(14,'Ascension Bracelet of Slaying',1,NULL),
(15,'Ascension Ring of Slaying',1,NULL),
(16,'Soul of the Dragoon',1,NULL),
(17,'Honeyed Dragonfruit',999,30),
(18,'Melon Juice',999,30),
(19,'Consecrated Chocolate',999,30),
(20,'Baked Eggplant',999,30),
(21,'Scallop Curry',999,30),
(22,'Terebinth',999,1),
(23,'Fire Shard',999,NULL),
(24,'Cherry Bomb',1,60),
(25,'Certificate of Collaboration',1,NULL),
(26,'Lionsmane Belt',1,223),
(27,'Tarnished Makai Bow',1,NULL),
(28,'Tropaios Bow',1,NULL),
(29,'Nue Greatbow',1,4),
(30,'Normal A',999,1),
(31,'Normal B',999,1),
(32,'Normal C',999,1),
(33,'Normal D',999,1),
(34,'Normal E',999,1),
(35,'Weapon A',1,1),
(36,'Weapon D',1,1),
(37,'Weapon E',1,1),
(38,'Normal F',999,1),
(39,'Normal G',999,1),
(40,'Normal EQ',999,1),
(41,'Normal DB',999,1),
(42,'Normal ES',999,1)
;

INSERT INTO miscellaneous_item VALUES
(22,'This foul-smelling sap can be used to remove dyes quickly and easily. Remember to always use in a well-ventilated area.'),
(23,'A tiny crystalline manifestation of aetheric fire energy.'),
(24,'Summons a miniature recreation of a voidsent bomb, created via the thaumaturgical rendering of umbrally charged aetherial energy. Guaranteed never to self-destruct, or your gil back.'),
(25,'A small, square slip of parchment stamped with the Ul''dahn royal seneschal''s seal proving your aid in the promotion of the Songbirds.'),
(26,'Before becoming obsolete due to advancements in tailoring, belts such as this were used for holding up trousers and the like.'),
(30,'Just a normal thing.'),
(31,'Just a normal thing.'),
(32,'Just a normal thing.'),
(33,'Just a normal thing.'),
(34,'Just a normal thing.');

INSERT INTO weapon VALUES
(1,645,90,128,119.46,2.80),
(2,630,90,125,126.66,3.04),
(3,1,90,9,9.36,3.12),
(4,31,30,37,35.52,2.88),
(5,510,80,103,107.12,3.12),
(27,1,1,9,9.12,3.04),
(28,1,1,9,9.12,3.04),
(29,1,1,9,9.12,3.04),
(35,1,1,9,9.12,3.04),
(36,1,1,9,9.12,3.04),
(37,1,1,9,9.12,3.04);

INSERT INTO gear_slot VALUES
(1,'Shield'),
(2,'Head'),
(3,'Body'),
(4,'Hands'),
(5,'Legs'),
(6,'Feet'),
(7,'Earrings'),
(8,'Necklace'),
(9,'Bracelets'),
(10,'Ring'),
(11,'Soul Crystal');

INSERT INTO gear VALUES
(6,645,1,90,1267,1267),
(7,660,2,90,624,491),
(8,660,3,90,837,658),
(9,660,4,90,624,491),
(10,660,5,90,837,658),
(11,660,6,90,624,491),
(12,660,7,90,1,1),
(13,660,8,90,1,1),
(14,660,9,90,1,1),
(15,660,10,90,1,1),
(16,30,11,30,0,0);

INSERT INTO weapon_job VALUES
(1,1),
(2,2),
(3,3),
(4,4),
(5,5),
(27,2),
(28,2),
(29,2),
(35,2),
(36,2),
(37,2);

INSERT INTO gear_job VALUES
(6,6),
(7,1),
(8,1),
(8,7),
(8,8),
(9,1),
(9,7),
(9,8),
(10,1),
(10,7),
(10,8),
(11,1),
(11,7),
(11,8),
(12,1),
(13,1),
(14,1),
(15,1),
(16,1);

INSERT INTO weapon_bonus VALUES
(1,1,378),
(1,2,414),
(2,2,376),
(2,3,348),
(2,4,283),
(2,5,283),
(4,1,7),
(4,2,7),
(4,6,9),
(5,2,189),
(5,7,202),
(5,8,199),
(5,9,139);

INSERT INTO gear_bonus VALUES
(6,1,108),
(6,2,118),
(7,1,248),
(7,2,277),
(7,10,184),
(7,5,129),
(8,1,394),
(8,2,440),
(8,4,292),
(8,5,204),
(9,1,248),
(9,2,277),
(9,6,129),
(9,5,184),
(10,1,394),
(10,2,440),
(10,6,292),
(10,5,204),
(11,1,248),
(11,2,277),
(11,4,184),
(11,5,129),
(12,1,196),
(12,2,218),
(12,4,145),
(12,6,102),
(13,1,196),
(13,2,218),
(13,10,145),
(13,5,102),
(14,1,196),
(14,2,218),
(14,4,145),
(14,5,102),
(15,1,196),
(15,2,218),
(15,4,102),
(15,6,145);

INSERT INTO consumable_item VALUES
(17,640,'A generous serving of dragonfruit slathered with a silky sweet layer of golden honey and a few sour drops of lemonette juice.'),
(18,610,'Paralyzingly delicious juice freshly squeezed from thundermelons.'),
(19,10,'This heart chocolate has been blessed by an ordained sister of the Temple of Menphina.'),
(20,640,'Topped with sliced vegetables and a healthy coating of melted Garlean cheese, these rich eggplants have been roasted to tender perfection.'),
(21,580,'Premium scallops are simmered with a rich blend of spices to create this savory dish.'),
(38,6,'Pao A.'),
(39,2,'Pao B.'),
(40,3,'Pao C.'),
(41,3,'Noodle.'),
(42,4,'Milk.');

INSERT INTO consumable_item_effect VALUES
(17,5,8,82),
(17,2,8,114),
(17,6,8,49),
(18,10,8,78),
(18,2,8,94),
(18,5,8,46),
(19,2,8,2),
(20,6,8,82),
(20,2,8,114),
(20,4,8,49),
(21,6,8,72),
(21,2,8,74),
(21,11,8,43);

INSERT INTO game_character VALUES
(1,	'Cloud','Strife',1,1),
(2,	'Tifa','Lockhart',1,2),
(3,	'Aerith','Gainsborough',1,3),
(4,	'Yuffie','Kisaragi',1,4),
(5,	'Barret','Wallace',5,5),
(6,	'Ani','Bob',2,2),
(7,	'Lee','Mario',3,3),
(8,	'Oea','Pee',4,4),
(9,	'Jerry','Rick',2,2),
(10,'Lu','Ace',5,5);

INSERT INTO character_job VALUES
(1,1,90,10838000,TRUE),
(1,7,90,10838000,FALSE),
(1,2,2,23,FALSE),
(1,3,1,4,FALSE),
(1,4,12,2444,FALSE),
(1,5,12,2433,FALSE),
(2,2,90,10838000,TRUE),
(3,3,90,10838000,TRUE),
(4,4,40,142000,TRUE),
(5,5,81,599200,TRUE);

INSERT INTO currency VALUES
(1,'Gil',999999999,NULL,TRUE),
(2,'Company Seals',80000,NULL,TRUE),
(3,'Ventures',999999999,NULL,TRUE),
(4,'Wolf Marks',200000,NULL,TRUE),
(5,'Capped Tomestone',2000,900,TRUE),
(6,'Allagan Tomestone of Causality',2000,NULL,FALSE),
(7,'Time',2000,NULL,FALSE),
(8,'PvP',4200,NULL,FALSE),
(9,'PVE',1200,NULL,FALSE),
(10,'Lose Heart',3000,NULL,FALSE);

INSERT INTO character_currency VALUES
(1,1,NULL,259222112),
(1,2,NULL,3000),
(1,3,NULL,500),
(1,4,NULL,25),
(1,5,400,1800),
(2,1,NULL,236000),
(3,1,NULL,346600),
(4,1,NULL,30000),
(5,1,NULL,250000);

INSERT INTO inventory VALUES
(1,1,1,6),
(1,14,520,17),
(1,70,25,18),
(1,122,11,19),
(1,144,1,25),
(2,3,59,17),
(3,4,2,20),
(4,22,1,22),
(5,14,520,17),
(5,70,25,18),
(5,122,11,19),
(5,144,1,25),
(5,109,1,26);

INSERT INTO quality VALUES
('high'),
('normal');

INSERT INTO customized_gear VALUES
(6,'high',100,'D8BFD8','Adaillie'),
(7,'normal',20,'B3B3B3',NULL),
(8,'normal',66,NULL,NULL),
(9,'normal',78,'BBFFBB','Jul Oul'),
(10,'high',88,NULL,NULL),
(11,'high',88,NULL,NULL),
(12,'high',88,NULL,NULL),
(13,'high',88,NULL,NULL),
(14,'high',88,NULL,NULL),
(15,'high',88,NULL,NULL),
(16,'high',88,NULL,NULL);

INSERT INTO customized_weapon VALUES
(1,'high',69,'FF0000','Dark Matter'),
(2,'high',69,'FF0000','Dark'),
(3,'high',69,'FF0000','Matter'),
(5,'high',69,'FF0000','Dter'),
(35,'high',69,'FF0000','rkter'),
(36,'high',69,'FF0000','DMatt'),
(4,'normal',100,NULL,NULL),
(27,'normal',100,NULL,NULL),
(28,'normal',100,NULL,NULL),
(29,'normal',100,'0000FF','Satoru Gojo');

INSERT INTO character_attribute VALUES
(1,1,144),
(1,2,211),
(1,4,223),
(1,5,232),
(1,6,256),
(1,10,431),
(2,1,200),
(3,2,200),
(4,8,34),
(5,7,256);

INSERT INTO character_equipped VALUES
(1,2,7),
(1,3,8),
(1,4,9),
(1,5,10),
(1,6,11),
(1,7,12),
(1,8,13),
(1,9,14),
(1,10,15),
(1,11,16);

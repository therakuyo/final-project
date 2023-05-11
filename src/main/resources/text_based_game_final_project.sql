-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 11, 2023 at 06:21 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.0.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `text_based_game_final_project`
--

-- --------------------------------------------------------

--
-- Table structure for table `armour`
--

CREATE TABLE `armour` (
  `id` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `health_points` int(11) DEFAULT NULL,
  `resistance` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `armour`
--

INSERT INTO `armour` (`id`, `name`, `health_points`, `resistance`) VALUES
(13, 'common helmet', 50, 10),
(14, 'lion pelt helmet', 50, 50);

-- --------------------------------------------------------

--
-- Table structure for table `characters`
--

CREATE TABLE `characters` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `level` int(11) NOT NULL,
  `class_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `characters`
--

INSERT INTO `characters` (`id`, `name`, `level`, `class_id`) VALUES
(1, 'TheRakuyo', 20, 1);

-- --------------------------------------------------------

--
-- Table structure for table `characters_items`
--

CREATE TABLE `characters_items` (
  `character_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `characters_items`
--

INSERT INTO `characters_items` (`character_id`, `item_id`) VALUES
(1, 4),
(1, 5),
(1, 10),
(1, 12),
(1, 14);

-- --------------------------------------------------------

--
-- Table structure for table `characters_zones`
--

CREATE TABLE `characters_zones` (
  `character_id` int(11) NOT NULL,
  `zone_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `characters_zones`
--

INSERT INTO `characters_zones` (`character_id`, `zone_id`) VALUES
(1, 7);

-- --------------------------------------------------------

--
-- Table structure for table `class`
--

CREATE TABLE `class` (
  `id` int(11) NOT NULL,
  `class_name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `class`
--

INSERT INTO `class` (`id`, `class_name`) VALUES
(1, 'juggernaut'),
(2, 'healer'),
(3, 'assassin'),
(4, 'ranger'),
(5, 'berserker'),
(6, 'sorcerer'),
(7, 'elementalist'),
(8, 'engineer');

-- --------------------------------------------------------

--
-- Table structure for table `enemies`
--

CREATE TABLE `enemies` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `enemies`
--

INSERT INTO `enemies` (`id`, `name`) VALUES
(1, 'Minotaur');

-- --------------------------------------------------------

--
-- Table structure for table `enemies_items`
--

CREATE TABLE `enemies_items` (
  `enemy_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `enemies_zones`
--

CREATE TABLE `enemies_zones` (
  `enemy_id` int(11) NOT NULL,
  `zone_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `id` int(11) NOT NULL,
  `type` enum('ONE_HANDED_WEAPON','TWO_HANDED_WEAPON','RING','AMULET','HELMET','BOOTS','GLOVES','SHOULDER_PLATES','CHEST_PIECE','BELT') NOT NULL,
  `rarity` enum('COMMON','MAGIC','EPIC','LEGENDARY','PRIMAL') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`id`, `type`, `rarity`) VALUES
(4, 'ONE_HANDED_WEAPON', 'COMMON'),
(5, 'ONE_HANDED_WEAPON', 'COMMON'),
(6, 'ONE_HANDED_WEAPON', 'MAGIC'),
(10, 'RING', 'MAGIC'),
(11, 'RING', 'LEGENDARY'),
(12, 'RING', 'COMMON'),
(13, 'HELMET', 'COMMON'),
(14, 'HELMET', 'MAGIC');

-- --------------------------------------------------------

--
-- Table structure for table `jewellery`
--

CREATE TABLE `jewellery` (
  `id` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `attack_damage` int(11) DEFAULT NULL,
  `magic_damage` int(11) DEFAULT NULL,
  `health_points` int(11) DEFAULT NULL,
  `resistance` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `jewellery`
--

INSERT INTO `jewellery` (`id`, `name`, `attack_damage`, `magic_damage`, `health_points`, `resistance`) VALUES
(10, 'stone ring', 0, 0, 50, 50),
(11, 'The Ring of The Ages', 250, 190, 50, 50),
(12, 'common ring', 15, 15, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `name` enum('ROLE_PLAYER','ROLE_ADMIN') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(1, 'ROLE_PLAYER'),
(2, 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `birth_date` date NOT NULL,
  `age` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `first_name`, `last_name`, `email`, `password`, `birth_date`, `age`) VALUES
(1, 'lucian', 'gandu', 'gandu.lucian@yahoo.com', '1234', '2001-01-25', 22);

-- --------------------------------------------------------

--
-- Table structure for table `users_roles`
--

CREATE TABLE `users_roles` (
  `users_id` int(11) NOT NULL,
  `roles_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users_roles`
--

INSERT INTO `users_roles` (`users_id`, `roles_id`) VALUES
(1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `weapons`
--

CREATE TABLE `weapons` (
  `id` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `attack_damage` int(11) DEFAULT NULL,
  `requirements` enum('TWO_HANDED','ONE_HANDED') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `weapons`
--

INSERT INTO `weapons` (`id`, `name`, `attack_damage`, `requirements`) VALUES
(4, 'common sword', 10, 'ONE_HANDED'),
(5, 'basic wand', 15, 'ONE_HANDED'),
(6, 'blue dagger', 35, 'ONE_HANDED');

-- --------------------------------------------------------

--
-- Table structure for table `zones`
--

CREATE TABLE `zones` (
  `id` int(11) NOT NULL,
  `zone_name` varchar(30) NOT NULL,
  `difficulty` enum('EASY','MEDIUM','HARD','EXTREME') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `zones`
--

INSERT INTO `zones` (`id`, `zone_name`, `difficulty`) VALUES
(5, 'grass field', 'EASY'),
(6, 'dark forest', 'MEDIUM'),
(7, 'swamp', 'HARD');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `armour`
--
ALTER TABLE `armour`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `characters`
--
ALTER TABLE `characters`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `class_id` (`class_id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `characters_items`
--
ALTER TABLE `characters_items`
  ADD UNIQUE KEY `character_name` (`character_id`,`item_id`),
  ADD UNIQUE KEY `character_id` (`character_id`,`item_id`),
  ADD KEY `fk_characters_items_items` (`item_id`);

--
-- Indexes for table `characters_zones`
--
ALTER TABLE `characters_zones`
  ADD PRIMARY KEY (`character_id`,`zone_id`),
  ADD UNIQUE KEY `character_name` (`character_id`,`zone_id`),
  ADD UNIQUE KEY `character_id` (`character_id`,`zone_id`),
  ADD KEY `fk_characters_zones_zones` (`zone_id`);

--
-- Indexes for table `class`
--
ALTER TABLE `class`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `enemies`
--
ALTER TABLE `enemies`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `enemies_items`
--
ALTER TABLE `enemies_items`
  ADD UNIQUE KEY `enemy_name` (`enemy_id`,`item_id`),
  ADD UNIQUE KEY `enemy_id` (`enemy_id`,`item_id`),
  ADD KEY `fk_enemies_items_items` (`item_id`);

--
-- Indexes for table `enemies_zones`
--
ALTER TABLE `enemies_zones`
  ADD PRIMARY KEY (`enemy_id`,`zone_id`),
  ADD UNIQUE KEY `enemy_name` (`enemy_id`,`zone_id`),
  ADD UNIQUE KEY `enemy_id` (`enemy_id`,`zone_id`),
  ADD KEY `fk_enemies_zones_zones` (`zone_id`);

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `jewellery`
--
ALTER TABLE `jewellery`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `users_roles`
--
ALTER TABLE `users_roles`
  ADD PRIMARY KEY (`users_id`,`roles_id`),
  ADD KEY `fk_users_roles_roles` (`roles_id`);

--
-- Indexes for table `weapons`
--
ALTER TABLE `weapons`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `zones`
--
ALTER TABLE `zones`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `characters`
--
ALTER TABLE `characters`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `class`
--
ALTER TABLE `class`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `enemies`
--
ALTER TABLE `enemies`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `items`
--
ALTER TABLE `items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `zones`
--
ALTER TABLE `zones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `armour`
--
ALTER TABLE `armour`
  ADD CONSTRAINT `fk_armour_items` FOREIGN KEY (`id`) REFERENCES `items` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `characters`
--
ALTER TABLE `characters`
  ADD CONSTRAINT `fk_class_characters` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `characters_items`
--
ALTER TABLE `characters_items`
  ADD CONSTRAINT `fk_characters_items_characters` FOREIGN KEY (`character_id`) REFERENCES `characters` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_characters_items_items` FOREIGN KEY (`item_id`) REFERENCES `items` (`id`);

--
-- Constraints for table `characters_zones`
--
ALTER TABLE `characters_zones`
  ADD CONSTRAINT `fk_characters_zones_characters` FOREIGN KEY (`character_id`) REFERENCES `characters` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_characters_zones_zones` FOREIGN KEY (`zone_id`) REFERENCES `zones` (`id`);

--
-- Constraints for table `enemies_items`
--
ALTER TABLE `enemies_items`
  ADD CONSTRAINT `fk_enemies_items_enemies` FOREIGN KEY (`enemy_id`) REFERENCES `enemies` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_enemies_items_items` FOREIGN KEY (`item_id`) REFERENCES `items` (`id`);

--
-- Constraints for table `enemies_zones`
--
ALTER TABLE `enemies_zones`
  ADD CONSTRAINT `fk_enemies_zones_enemies` FOREIGN KEY (`enemy_id`) REFERENCES `enemies` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_enemies_zones_zones` FOREIGN KEY (`zone_id`) REFERENCES `zones` (`id`);

--
-- Constraints for table `jewellery`
--
ALTER TABLE `jewellery`
  ADD CONSTRAINT `fk_jewellery_items` FOREIGN KEY (`id`) REFERENCES `items` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `users_roles`
--
ALTER TABLE `users_roles`
  ADD CONSTRAINT `fk_users_roles_roles` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`),
  ADD CONSTRAINT `fk_users_roles_users` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `weapons`
--
ALTER TABLE `weapons`
  ADD CONSTRAINT `fk_weapons_items` FOREIGN KEY (`id`) REFERENCES `items` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

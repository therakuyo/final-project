-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 02, 2023 at 04:43 PM
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
-- Table structure for table `armor`
--

create TABLE `armor` (
  `id` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `health_points` int(11) DEFAULT NULL,
  `resistance` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `characters`
--

create TABLE `characters` (
  `name` varchar(30) NOT NULL,
  `level` int(11) NOT NULL,
  `class_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `characters`
--

insert into `characters` (`name`, `level`, `class_id`) VALUES
('bam', 5, 7),
('diablo', 25, 5),
('kain', 15, 6),
('ozen', 10, 1);

-- --------------------------------------------------------

--
-- Table structure for table `characters_items`
--

create TABLE `characters_items` (
  `character_name` varchar(30) NOT NULL,
  `item_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `characters_zones`
--

create TABLE `characters_zones` (
  `character_name` varchar(30) NOT NULL,
  `zone_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `class`
--

create TABLE `class` (
  `id` int(11) NOT NULL,
  `class_name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `class`
--

insert into `class` (`id`, `class_name`) VALUES
(1, 'juggernaut'),
(2, 'healer'),
(3, 'assassin'),
(4, 'ranger'),
(5, 'berserker'),
(6, 'sorcerer'),
(7, 'elementalist'),
(8, 'engineer'),
(9, 'assassin');

-- --------------------------------------------------------

--
-- Table structure for table `enemies`
--

create TABLE `enemies` (
  `name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `enemies`
--

insert into `enemies` (`name`) VALUES
('bear'),
('dog'),
('dragon'),
('giant spider'),
('rabbit'),
('slime'),
('wolf'),
('zombie');

-- --------------------------------------------------------

--
-- Table structure for table `enemies_items`
--

create TABLE `enemies_items` (
  `enemy_name` varchar(30) NOT NULL,
  `item_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `enemies_zones`
--

create TABLE `enemies_zones` (
  `enemy_name` varchar(30) NOT NULL,
  `zone_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

create TABLE `items` (
  `id` int(11) NOT NULL,
  `type` varchar(30) NOT NULL,
  `rarity` enum('common','magic','epic','legenary','primal') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `jewellery`
--

create TABLE `jewellery` (
  `id` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `attack_damage` int(11) DEFAULT NULL,
  `magic_damage` int(11) DEFAULT NULL,
  `health_points` int(11) DEFAULT NULL,
  `resistance` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `weapons`
--

create TABLE `weapons` (
  `id` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `attack_damage` int(11) DEFAULT NULL,
  `requirements` enum('two_handed','one_handed') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `zones`
--

create TABLE `zones` (
  `id` int(11) NOT NULL,
  `zone_name` varchar(30) NOT NULL,
  `difficulty` enum('easy','medium','hard','extreme') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `zones`
--

insert into `zones` (`id`, `zone_name`, `difficulty`) VALUES
(5, 'grass field', 'easy'),
(6, 'dark forest', 'medium'),
(7, 'swamp', 'hard'),
(8, 'cave', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `armor`
--
alter table `armor`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `characters`
--
alter table `characters`
  ADD PRIMARY KEY (`name`),
  ADD UNIQUE KEY `class_id` (`class_id`);

--
-- Indexes for table `characters_items`
--
alter table `characters_items`
  ADD UNIQUE KEY `character_name` (`character_name`,`item_id`),
  ADD KEY `fk_characters_items_items` (`item_id`);

--
-- Indexes for table `characters_zones`
--
alter table `characters_zones`
  ADD PRIMARY KEY (`character_name`,`zone_id`),
  ADD UNIQUE KEY `character_name` (`character_name`,`zone_id`),
  ADD KEY `fk_characters_zones_zones` (`zone_id`);

--
-- Indexes for table `class`
--
alter table `class`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `enemies`
--
alter table `enemies`
  ADD PRIMARY KEY (`name`);

--
-- Indexes for table `enemies_items`
--
alter table `enemies_items`
  ADD UNIQUE KEY `enemy_name` (`enemy_name`,`item_id`),
  ADD KEY `fk_enemies_items_items` (`item_id`);

--
-- Indexes for table `enemies_zones`
--
alter table `enemies_zones`
  ADD PRIMARY KEY (`enemy_name`,`zone_id`),
  ADD UNIQUE KEY `enemy_name` (`enemy_name`,`zone_id`),
  ADD KEY `fk_enemies_zones_zones` (`zone_id`);

--
-- Indexes for table `items`
--
alter table `items`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `jewellery`
--
alter table `jewellery`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `weapons`
--
alter table `weapons`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `zones`
--
alter table `zones`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `class`
--
alter table `class`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `items`
--
alter table `items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `zones`
--
alter table `zones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `armor`
--
alter table `armor`
  ADD CONSTRAINT `fk_armor_items` FOREIGN KEY (`id`) REFERENCES `items` (`id`) ON delete CASCADE;

--
-- Constraints for table `characters`
--
alter table `characters`
  ADD CONSTRAINT `fk_class` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`) ON delete CASCADE ON update CASCADE;

--
-- Constraints for table `characters_items`
--
alter table `characters_items`
  ADD CONSTRAINT `fk_characters_items_characters` FOREIGN KEY (`character_name`) REFERENCES `characters` (`name`) ON delete CASCADE ON update CASCADE,
  ADD CONSTRAINT `fk_characters_items_items` FOREIGN KEY (`item_id`) REFERENCES `items` (`id`);

--
-- Constraints for table `characters_zones`
--
alter table `characters_zones`
  ADD CONSTRAINT `fk_characters_zones_characters` FOREIGN KEY (`character_name`) REFERENCES `characters` (`name`) ON delete CASCADE ON update CASCADE,
  ADD CONSTRAINT `fk_characters_zones_zones` FOREIGN KEY (`zone_id`) REFERENCES `zones` (`id`) ON delete CASCADE ON update CASCADE;

--
-- Constraints for table `enemies_items`
--
alter table `enemies_items`
  ADD CONSTRAINT `fk_enemies_items_enemies` FOREIGN KEY (`enemy_name`) REFERENCES `enemies` (`name`) ON delete CASCADE ON update CASCADE,
  ADD CONSTRAINT `fk_enemies_items_items` FOREIGN KEY (`item_id`) REFERENCES `items` (`id`);

--
-- Constraints for table `enemies_zones`
--
alter table `enemies_zones`
  ADD CONSTRAINT `fk_enemies_zones_enemies` FOREIGN KEY (`enemy_name`) REFERENCES `enemies` (`name`),
  ADD CONSTRAINT `fk_enemies_zones_zones` FOREIGN KEY (`zone_id`) REFERENCES `zones` (`id`);

--
-- Constraints for table `jewellery`
--
alter table `jewellery`
  ADD CONSTRAINT `fk_jewellery_items` FOREIGN KEY (`id`) REFERENCES `items` (`id`) ON delete CASCADE;

--
-- Constraints for table `weapons`
--
alter table `weapons`
  ADD CONSTRAINT `fk_weapons_items` FOREIGN KEY (`id`) REFERENCES `items` (`id`) ON delete CASCADE;
commit;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jan 15, 2024 at 01:50 AM
-- Server version: 8.2.0
-- PHP Version: 7.4.3-4ubuntu2.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `market_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `id` int NOT NULL,
  `username` text NOT NULL,
  `password` text NOT NULL,
  `type` enum('Admin','Marketing Employee','Inventory Employee','Seller') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`id`, `username`, `password`, `type`) VALUES
(4, 'inventory', '$2a$10$5QJNocJgapkWwQHrxYoGKuUvUaWZ/DLsBChHXL/ziqB0qBDuY.Ru2', 'Inventory Employee'),
(8, 'omarmm', '$2a$10$UKjlrjhQ68GNZfAdtb/KXeOM3RREI5TwogFw7lJ/dvmzOwePw.Miq', 'Inventory Employee'),
(20, 'bor3i', '$2a$10$xfCWCUfZeGj3E4Mhydy1NOBNhiSRKY41/Zjb1/LvSWG.kCfKMQgde', 'Marketing Employee'),
(22, 'marketing', '$2a$10$2ga1oiimKPhsslNW2rNNEePsricmVvsYc5RsUspPzyRSd9RUQ4/yW', 'Marketing Employee'),
(23, 'sales', '$2a$10$A54bkqoSgjy4aC2Z9FU87O34cW8rQuwt8YBmpTdptYHn0ejfe/Y62', 'Seller'),
(25, 'mona', '$2a$10$yRFuVVe7WLKt.l9ptZws4.MKZkGEewPh4tbDiDeF7LlOSt.WKELIO', 'Seller'),
(26, 'omar', '$2a$10$48T/E7PVcZpVQEBpf3brZuTw2LCqPDgJqtfSLDC0a7WIvrLGI.Rb6', 'Admin'),
(27, 'omar', '$2a$10$n1aYV3IzU/aHhIdSq6vBkufptoAPnrTdtHLBYPfm4DQpiQWYZORl2', 'Admin');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int NOT NULL,
  `product_name` text NOT NULL,
  `quantity` int NOT NULL,
  `bill` double NOT NULL,
  `product_category` text,
  `date` text,
  `order_id` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `product_name`, `quantity`, `bill`, `product_category`, `date`, `order_id`) VALUES
(15, 'coca', 44, 35.19999999999999, 'Beverages', '2023-06-02T20:03:59.572125', '4'),
(16, 'meet', 99, 79200, 'Meat & Seafood', '2023-06-02T20:04:07.678863', '4'),
(24, 'fury', 250, 2550, 'Beverages', '2023-06-02T23:20:23.649036', '5'),
(28, 'fury', 198, 2019.6, 'Beverages', '2023-07-04T02:06:13.779653', '6');

-- --------------------------------------------------------

--
-- Table structure for table `stock`
--

CREATE TABLE `stock` (
  `id` int NOT NULL,
  `name` text NOT NULL,
  `category` text NOT NULL,
  `production_date` text NOT NULL,
  `expiry_date` text NOT NULL,
  `price` double NOT NULL,
  `quantity` int NOT NULL,
  `damages` int NOT NULL,
  `shortage` int NOT NULL,
  `offer` double UNSIGNED NOT NULL DEFAULT '0',
  `offerEndDate` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `stock`
--

INSERT INTO `stock` (`id`, `name`, `category`, `production_date`, `expiry_date`, `price`, `quantity`, `damages`, `shortage`, `offer`, `offerEndDate`) VALUES
(9, 'fury', 'Beverages', '2023-02-27', '2024-03-22', 12, 89802, 0, 0, 15, '2023-06-05'),
(11, 'coca', 'Beverages', '2023-09-09', '2025-01-01', 8, 900000, 8, 9, 90, '2026-01-01'),
(13, 'meet', 'Meat & Seafood', '2023-09-09', '2025-01-01', 800, 90000, 8, 9, 10, '2025-01-01'),
(16, 'salamon', 'Meat & Seafood', '2023-09-08', '2024-03-09', 900, 90002, 80, 9, 10, '2025-01-01');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD UNIQUE KEY `order_id` (`id`);

--
-- Indexes for table `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `stock`
--
ALTER TABLE `stock`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
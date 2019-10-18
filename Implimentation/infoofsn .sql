-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 15, 2019 at 11:38 AM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `avgratingdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `infoofsn`
--

CREATE TABLE `infoofsn` (
  `id` int(11) NOT NULL,
  `ipaddress` varchar(32) NOT NULL,
  `vmip` varchar(32) NOT NULL,
  `countryip` varchar(32) NOT NULL,
  `portno` int(11) NOT NULL,
  `connected` int(11) NOT NULL,
  `avgrating` int(11) NOT NULL,
  `country` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `infoofsn`
--

INSERT INTO `infoofsn` (`id`, `ipaddress`, `vmip`, `countryip`, `portno`, `connected`, `avgrating`, `country`) VALUES
(0, '192.168.50.6', '192.168.50.23', '23.250.11.57', 1234, 1, 3, 'Canada (CA)'),
(1, '192.168.50.6', '192.168.50.24', '52.109.113.220', 3456, 3, 4, 'Australia (AU)'),
(2, '192.168.50.6', '192.168.50.26', '1.32.202.128', 7668, 1, 2, 'Singapore (SG)'),
(3, '192.168.50.6', '192.168.50.27', '62.50.60.7', 4321, 1, 1, 'United Kingdom (GB)');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `infoofsn`
--
ALTER TABLE `infoofsn`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `infoofsn`
--
ALTER TABLE `infoofsn`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

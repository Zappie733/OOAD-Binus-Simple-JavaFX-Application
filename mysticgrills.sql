-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 20, 2023 at 06:30 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mysticgrills`
--

-- --------------------------------------------------------

--
-- Table structure for table `menuitems`
--

CREATE TABLE `menuitems` (
  `menuItemId` int(11) NOT NULL,
  `menuItemName` varchar(255) DEFAULT NULL,
  `menuItemDescription` varchar(255) DEFAULT NULL,
  `menuItemPrice` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `menuitems`
--

INSERT INTO `menuitems` (`menuItemId`, `menuItemName`, `menuItemDescription`, `menuItemPrice`) VALUES
(1, 'Grilled Chicken', 'Juicy chicken marinated in herbs and grilled to perfection.', 7.99),
(2, 'Grilled Salmon', 'Fresh salmon fillet grilled with a special seasoning.', 10.99),
(3, 'Grilled Vegetables', 'Assorted vegetables grilled to enhance their flavors.', 5.49),
(4, 'BBQ Ribs', 'Tender pork ribs glazed in barbecue sauce.', 12.99),
(5, 'Grilled Steak', 'Premium beef steak grilled to your preference.', 15.99);

-- --------------------------------------------------------

--
-- Table structure for table `orderitems`
--

CREATE TABLE `orderitems` (
  `orderId` int(11) NOT NULL,
  `menuItemId` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orderitems`
--

INSERT INTO `orderitems` (`orderId`, `menuItemId`, `quantity`) VALUES
(1, 1, 2),
(1, 3, 1),
(2, 2, 1),
(2, 4, 3),
(3, 1, 2),
(3, 5, 2),
(4, 3, 3),
(4, 4, 1),
(5, 2, 1),
(5, 5, 1);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `orderId` int(11) NOT NULL,
  `orderUserId` int(11) DEFAULT NULL,
  `orderStatus` varchar(255) DEFAULT NULL,
  `orderDate` date DEFAULT NULL,
  `orderTotal` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`orderId`, `orderUserId`, `orderStatus`, `orderDate`, `orderTotal`) VALUES
(1, 5, 'Pending', '2023-12-18', 21.47),
(2, 5, 'Paid', '2023-12-19', 49.96),
(3, 5, 'Prepared', '2023-12-20', 47.96),
(4, 5, 'Served', '2023-12-21', 29.46),
(5, 5, 'Cancel', '2023-12-17', 26.98);

-- --------------------------------------------------------

--
-- Table structure for table `receipts`
--

CREATE TABLE `receipts` (
  `receiptId` int(11) NOT NULL,
  `receiptOrderId` int(11) DEFAULT NULL,
  `receiptPaymentAmount` double DEFAULT NULL,
  `receiptPaymentDate` date DEFAULT NULL,
  `receiptPaymentType` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `receipts`
--

INSERT INTO `receipts` (`receiptId`, `receiptOrderId`, `receiptPaymentAmount`, `receiptPaymentDate`, `receiptPaymentType`) VALUES
(1, 2, 49.96, '2023-12-19', 'Debit');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userId` int(11) NOT NULL,
  `userRole` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `userEmail` varchar(255) DEFAULT NULL,
  `userPassword` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userId`, `userRole`, `userName`, `userEmail`, `userPassword`) VALUES
(1, 'Admin', 'Admin1', 'Admin1@gmail.com', 'Admin123'),
(2, 'Chef', 'Chef1', 'Chef1@gmail.com', 'Chef123'),
(3, 'Waiter', 'Waiter1', 'Waiter1@gmail.com', 'Waiter123'),
(4, 'Cashier', 'Cashier1', 'Cashier1@gmail.com', 'Cashier123'),
(5, 'Customer', 'Customer1', 'Customer1@gmail.com', 'Customer123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `menuitems`
--
ALTER TABLE `menuitems`
  ADD PRIMARY KEY (`menuItemId`);

--
-- Indexes for table `orderitems`
--
ALTER TABLE `orderitems`
  ADD PRIMARY KEY (`orderId`,`menuItemId`),
  ADD KEY `menuItemId` (`menuItemId`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`orderId`),
  ADD KEY `orderUserId` (`orderUserId`);

--
-- Indexes for table `receipts`
--
ALTER TABLE `receipts`
  ADD PRIMARY KEY (`receiptId`),
  ADD UNIQUE KEY `receiptOrderId` (`receiptOrderId`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `menuitems`
--
ALTER TABLE `menuitems`
  MODIFY `menuItemId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `orderId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `receipts`
--
ALTER TABLE `receipts`
  MODIFY `receiptId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `orderitems`
--
ALTER TABLE `orderitems`
  ADD CONSTRAINT `orderitems_ibfk_1` FOREIGN KEY (`orderId`) REFERENCES `orders` (`orderId`) ON DELETE CASCADE,
  ADD CONSTRAINT `orderitems_ibfk_2` FOREIGN KEY (`menuItemId`) REFERENCES `menuitems` (`menuItemId`) ON DELETE CASCADE;

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`orderUserId`) REFERENCES `users` (`userId`) ON DELETE CASCADE;

--
-- Constraints for table `receipts`
--
ALTER TABLE `receipts`
  ADD CONSTRAINT `receipts_ibfk_1` FOREIGN KEY (`receiptOrderId`) REFERENCES `orders` (`orderId`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

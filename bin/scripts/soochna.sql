-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 02, 2017 at 11:58 AM
-- Server version: 5.7.18-0ubuntu0.16.04.1
-- PHP Version: 7.0.15-0ubuntu0.16.04.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `soochna`
--

-- --------------------------------------------------------

--
-- Table structure for table `notices`
--

CREATE TABLE `notice` (
  `ministry_name` varchar(100) NOT NULL,
  `notice_title` varchar(100) NOT NULL,
  `notice_content` text NOT NULL,
  `created_time_stamp` timestamp NULL DEFAULT NULL,
  `modified_time_stamp` timestamp NULL DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notices`
--

INSERT INTO `notices` (`ministry_name`, `notice_title`, `notice_content`, `created_time_stamp`, `modified_time_stamp`, `is_deleted`, `id`) VALUES
('myministry', 'Benefits', '<html>Hello again</html>', '2017-11-29 18:38:38', '2017-11-29 18:38:38', 0, 5),
('hrd', 'Mitro1', '<html>Hello mitro</html>', '2017-11-29 18:40:17', '2017-11-29 18:40:17', 0, 6),
('hrd', 'Mitro2', '<html>Hello mitro2</html>', '2017-11-29 18:46:38', '2017-11-29 18:46:38', 0, 7);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `ministry_name` varchar(100) DEFAULT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `uid` varchar(12) CHARACTER SET utf8 NOT NULL DEFAULT '100000000000',
  `created_time_stamp` timestamp NULL DEFAULT NULL,
  `modified_time_stamp` timestamp NULL DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL,
  `role` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`ministry_name`, `first_name`, `last_name`, `email`, `password`, `uid`, `created_time_stamp`, `modified_time_stamp`, `is_deleted`, `role`) VALUES
('HRD', 'shu2', 'Jain', 'shu2@soochna.com', 'b9b00833a9f0cb35d48cf0958acebc9c60b43750', '111122223321', '2017-11-29 15:40:02', '2017-11-29 15:40:02', 0, 'ROLE_ADMIN'),
('HRD', 'shu', 'Jain', 'shu@soochna.com', 'c7941a7d8afa77569071a3ebe2855eb7b5f91196', '111122223330', '2017-11-29 15:33:08', '2017-11-29 15:33:08', 0, 'ROLE_ADMIN'),
('HRD', 'Ankit', 'Jain', 'ankit@soochna.com', '7d7f3b0de32b6cc2e9608ebf7986c26b5954d288', '111122223335', '2017-11-26 16:07:04', '2017-11-26 16:07:04', 0, 'ROLE_ADMIN'),
('HRD', 'shu3', 'Jain', 'shu3@soochna.com', 'b9b00833a9f0cb35d48cf0958acebc9c60b43750', '111122223341', '2017-11-30 15:21:00', '2017-11-30 15:21:00', 0, 'ADMIN'),
('HRD', 'shu4', 'Jain', 'shu4@soochna.com', '95a2083c33f5f5009d4b97c6eca6eb4568479c3e', '111122223347', '2017-11-30 16:13:06', '2017-11-30 16:13:06', 0, 'ADMIN'),
('HRD', 'shu5', 'Jain', 'shu5@soochna.com', '7b73a340b9057cccc5dfd2ce1ac0abc8fb7c9cdd', '111122223348', '2017-11-30 16:25:54', '2017-11-30 16:25:54', 0, 'ADMIN');

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE `user_role` (
  `uid` varchar(12) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `notices`
--
ALTER TABLE `notices`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`uid`),
  ADD KEY `uid` (`uid`),
  ADD KEY `uid_2` (`uid`);

--
-- Indexes for table `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`uid`,`role_id`),
  ADD KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `notices`
--
ALTER TABLE `notices`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`),
  ADD CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

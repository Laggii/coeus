/*
Navicat MariaDB Data Transfer

Source Server         : MariaDB
Source Server Version : 100118
Source Host           : localhost:3306
Source Database       : coeus

Target Server Type    : MariaDB
Target Server Version : 100118
File Encoding         : 65001

Date: 2016-10-27 21:24:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for courses
-- ----------------------------
DROP TABLE IF EXISTS `courses`;
CREATE TABLE `courses` (
  `course_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(35) NOT NULL,
  `owner_id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `date_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`course_id`),
  KEY `owner_id` (`owner_id`),
  CONSTRAINT `owner_id` FOREIGN KEY (`owner_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of courses
-- ----------------------------
INSERT INTO `courses` VALUES ('13', 'Math', '25', 'Math Course will start this November', '2016-10-26 18:38:43');
INSERT INTO `courses` VALUES ('14', 'Algebra', '25', 'School Algebra course, join!', '2016-10-26 18:39:54');
INSERT INTO `courses` VALUES ('15', 'Theoretical mechanics', '25', 'Only for students', '2016-10-26 18:40:44');
INSERT INTO `courses` VALUES ('16', 'Programming', '25', 'Programming course', '2016-10-26 18:41:08');
INSERT INTO `courses` VALUES ('30', 'English language', '26', 'English language Course', '2016-10-26 18:58:40');
INSERT INTO `courses` VALUES ('31', 'Russian language', '26', 'Russian language course', '2016-10-26 18:59:01');

-- ----------------------------
-- Table structure for messages
-- ----------------------------
DROP TABLE IF EXISTS `messages`;
CREATE TABLE `messages` (
  `message_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_from` bigint(20) NOT NULL,
  `id_to` bigint(20) NOT NULL,
  `body` varchar(255) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`message_id`,`id_from`,`id_to`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of messages
-- ----------------------------

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL,
  `name` text NOT NULL,
  `description` mediumtext,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('1', 'Student', 'Basic Student');
INSERT INTO `roles` VALUES ('2', 'Teacher', 'Basic Teacher');
INSERT INTO `roles` VALUES ('3', 'Admin', 'Administrator');

-- ----------------------------
-- Table structure for usercourses
-- ----------------------------
DROP TABLE IF EXISTS `usercourses`;
CREATE TABLE `usercourses` (
  `course_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`course_id`,`user_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `course_id` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usercourses
-- ----------------------------
INSERT INTO `usercourses` VALUES ('13', '25');
INSERT INTO `usercourses` VALUES ('14', '25');
INSERT INTO `usercourses` VALUES ('14', '30');
INSERT INTO `usercourses` VALUES ('14', '31');
INSERT INTO `usercourses` VALUES ('15', '25');
INSERT INTO `usercourses` VALUES ('16', '24');
INSERT INTO `usercourses` VALUES ('16', '25');
INSERT INTO `usercourses` VALUES ('16', '28');
INSERT INTO `usercourses` VALUES ('30', '26');
INSERT INTO `usercourses` VALUES ('30', '28');
INSERT INTO `usercourses` VALUES ('30', '30');
INSERT INTO `usercourses` VALUES ('31', '24');
INSERT INTO `usercourses` VALUES ('31', '26');

-- ----------------------------
-- Table structure for userfriends
-- ----------------------------
DROP TABLE IF EXISTS `userfriends`;
CREATE TABLE `userfriends` (
  `user_id` bigint(20) NOT NULL,
  `friend_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`friend_id`),
  KEY `user_friend` (`friend_id`),
  CONSTRAINT `friends` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `user_friend` FOREIGN KEY (`friend_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userfriends
-- ----------------------------
INSERT INTO `userfriends` VALUES ('24', '25');
INSERT INTO `userfriends` VALUES ('24', '27');
INSERT INTO `userfriends` VALUES ('24', '28');
INSERT INTO `userfriends` VALUES ('25', '26');
INSERT INTO `userfriends` VALUES ('26', '24');
INSERT INTO `userfriends` VALUES ('26', '25');
INSERT INTO `userfriends` VALUES ('26', '27');
INSERT INTO `userfriends` VALUES ('28', '26');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(254) NOT NULL,
  `hash` varchar(255) NOT NULL,
  `firstname` varchar(35) NOT NULL,
  `lastname` varchar(35) NOT NULL,
  `gender` char(1) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `phone` varchar(18) DEFAULT NULL,
  `role_id` int(11) NOT NULL,
  `regdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`),
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users (not sure if tokens will be valid on your instance)
-- ----------------------------
INSERT INTO `users` VALUES ('24', 'ivan@example.com', '$2a$10$u26mwXrgVvtX9Msd9.pk3eVfGtXeskDpjOYtimcnafMHjVAoK8REi', 'Иван', 'Алексеев', 'm', '1994-08-05', '+8 (812) 323-32-33', '1', '2016-10-26 18:01:51');
INSERT INTO `users` VALUES ('25', 'teacher@example.com', '$2a$10$6X.VOew9.9I1JVJKHJgxVOt5tBZEpffOoHMa1nIhDXhnn0Mu0/1dS', 'Анатолий', 'Максимов', 'm', '1981-02-02', '+7 (960) 324-32-42', '2', '2016-10-26 18:05:04');
INSERT INTO `users` VALUES ('26', 'teacher2@example.com', '$2a$10$fKajN2SNWMTQ5uhA2hPyxe0QuCeW0Nd3f6I/d4/9f3YO4tVn8yfO.', 'John', 'Doe', 'm', '2015-10-05', '+5 (654) 645-65-46', '2', '2016-10-26 18:56:05');
INSERT INTO `users` VALUES ('27', 'admin@example.com', '$2a$10$OH6Zik5oXWwkBWVLHSAaM.yqqZGeJlgi9PnI2DARuuUoIXS.IwHQi', 'Max', 'Kovalev', '\0', null, null, '3', '2016-10-26 18:56:46');
INSERT INTO `users` VALUES ('28', 'student@example.com', '$2a$10$rRpfGtMZ9hJgj74cNO83z.C2cbgbtYfIq3AD0oxhZL6Sn5BR76XmC', 'Marina', 'Marinina', 'f', '2001-01-16', '+5 (654) 654-65-46', '1', '2016-10-26 19:01:12');
INSERT INTO `users` VALUES ('29', 'student2@example.com', '$2a$10$cHHIrak2o/BtzKJXCJFmseGNkuzI9p2OdDpTvGsJP5bRnnbz5T9xO', 'Анна', 'Каренина', '\0', null, null, '1', '2016-10-26 19:08:59');
INSERT INTO `users` VALUES ('30', 'student3@example.com', '$2a$10$gepRIrmAOabOp7cBjLGg8efgdI6ASguzvfXEHjSFj3cpiU47MXehe', 'Mark', 'Zuckerberg', 'm', '2009-10-19', '+5 (756) 556-56-65', '1', '2016-10-26 22:15:00');
INSERT INTO `users` VALUES ('31', 'student4@example.com', '$2a$10$xS51znc5JAPdqissWXdJuexS/4mXKLC/gzgXV/MzEoYdB8Ni0NYN6', 'Иван', 'Алексеев', 'm', '2012-10-08', '+6 (667) 677-77-76', '1', '2016-10-27 14:09:36');

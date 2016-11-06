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
-- Records of courses
-- ----------------------------
INSERT INTO `courses` VALUES ('1', 'Math', '2', 'Math Course will start this November', '2016-10-26 18:38:43');
INSERT INTO `courses` VALUES ('2', 'Algebra', '2', 'School Algebra course, join!', '2016-10-26 18:39:54');
INSERT INTO `courses` VALUES ('3', 'Theoretical mechanics', '2', 'Only for students', '2016-10-26 18:40:44');
INSERT INTO `courses` VALUES ('4', 'Programming', '2', 'Programming course', '2016-10-26 18:41:08');
INSERT INTO `courses` VALUES ('5', 'English language', '3', 'English language Course', '2016-10-26 18:58:40');
INSERT INTO `courses` VALUES ('6', 'Russian language', '3', 'Russian language course', '2016-10-26 18:59:01');

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('1', 'Student', 'Basic Student');
INSERT INTO `roles` VALUES ('2', 'Teacher', 'Basic Teacher');
INSERT INTO `roles` VALUES ('3', 'Admin', 'Administrator');

-- ----------------------------
-- Records of usercourses
-- ----------------------------
INSERT INTO `usercourses` VALUES ('1', '2');
INSERT INTO `usercourses` VALUES ('2', '2');
INSERT INTO `usercourses` VALUES ('2', '7');
INSERT INTO `usercourses` VALUES ('2', '8');
INSERT INTO `usercourses` VALUES ('3', '2');
INSERT INTO `usercourses` VALUES ('4', '1');
INSERT INTO `usercourses` VALUES ('4', '2');
INSERT INTO `usercourses` VALUES ('5', '5');
INSERT INTO `usercourses` VALUES ('6', '3');
INSERT INTO `usercourses` VALUES ('6', '5');
INSERT INTO `usercourses` VALUES ('6', '7');
INSERT INTO `usercourses` VALUES ('1', '1');
INSERT INTO `usercourses` VALUES ('1', '3');

-- ----------------------------
-- Records of userfriends
-- ----------------------------
INSERT INTO `userfriends` VALUES ('1', '2');
INSERT INTO `userfriends` VALUES ('1', '4');
INSERT INTO `userfriends` VALUES ('1', '5');
INSERT INTO `userfriends` VALUES ('2', '3');
INSERT INTO `userfriends` VALUES ('3', '1');
INSERT INTO `userfriends` VALUES ('3', '2');
INSERT INTO `userfriends` VALUES ('3', '4');
INSERT INTO `userfriends` VALUES ('5', '3');

------------------------
-- Records of users (not sure if tokens will be valid on your instance)
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'ivan@example.com', '$2a$10$u3mwXrgVvtX9Msd9.pk3eVfGtXeskDpjOYtimcnafMHjVAoK8REi', 'Иван', 'Алексеев', 'm', '1994-08-05', '+8 (812) 323-32-33', '1', '2016-10-26 18:01:51');
INSERT INTO `users` VALUES ('2', 'teacher@example.com', '$2a$10$6X.VOew9.9I1JVJKHJgxVOt5tBZEpffOoHMa1nIhDXhnn0Mu0/1dS', 'Анатолий', 'Максимов', 'm', '1981-02-02', '+7 (960) 324-32-42', '2', '2016-10-26 18:05:04');
INSERT INTO `users` VALUES ('3', 'teacher2@example.com', '$2a$10$fKajN2SNWMTQ5uhA2hPyxe0QuCeW0Nd3f6I/d4/9f3YO4tVn8yfO.', 'John', 'Doe', 'm', '2015-10-05', '+5 (654) 645-65-46', '2', '2016-10-26 18:56:05');
INSERT INTO `users` VALUES ('4', 'admin@example.com', '$2a$10$OH6Zik5oXWwkBWVLHSAaM.yqqZGeJlgi9PnI2DARuuUoIXS.IwHQi', 'Max', 'Kovalev', '\0', null, null, '3', '2016-10-26 18:56:46');
INSERT INTO `users` VALUES ('5', 'student@example.com', '$2a$10$rRpfGtMZ9hJgj74cNO83z.C2cbgbtYfIq3AD0oxhZL6Sn5BR76XmC', 'Marina', 'Marinina', 'f', '2001-01-16', '+5 (654) 654-65-46', '1', '2016-10-26 19:01:12');
INSERT INTO `users` VALUES ('6', 'student2@example.com', '$2a$10$cHHIrak2o/BtzKJXCJFmseGNkuzI9p2OdDpTvGsJP5bRnnbz5T9xO', 'Анна', 'Каренина', '\0', null, null, '1', '2016-10-26 19:08:59');
INSERT INTO `users` VALUES ('7', 'student3@example.com', '$2a$10$gepRIrmAOabOp7cBjLGg8efgdI6ASguzvfXEHjSFj3cpiU47MXehe', 'Mark', 'Zuckerberg', 'm', '2009-10-19', '+5 (756) 556-56-65', '1', '2016-10-26 22:15:00');
INSERT INTO `users` VALUES ('8', 'student4@example.com', '$2a$10$xS51znc5JAPdqissWXdJuexS/4mXKLC/gzgXV/MzEoYdB8Ni0NYN6', 'Иван', 'Алексеев', 'm', '2012-10-08', '+6 (667) 677-77-76', '1', '2016-10-27 14:09:36');

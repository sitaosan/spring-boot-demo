CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `user_address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
INSERT INTO `t_user` (`user_name`, `user_address`) VALUES ( '张三', '北京市昌平区');
INSERT INTO `t_user` (`user_name`, `user_address`) VALUES ( '李小四', '北京市朝阳区');
INSERT INTO `t_user` (`user_name`, `user_address`) VALUES ( '王儿五', '河北省保定市');
INSERT INTO `t_user` (`user_name`, `user_address`) VALUES ( '司马图', '河北省邯郸市');
INSERT INTO `t_user` (`user_name`, `user_address`) VALUES ( '周艳', '河北省邯郸市');
INSERT INTO `t_user` (`user_name`, `user_address`) VALUES ( '文会山', '黑龙江省哈尔滨市');
INSERT INTO `t_user` (`user_name`, `user_address`) VALUES ( '吕贵', '黑龙江省齐齐哈尔市');

#CREATE DATABASE IF NOT EXISTS spring_demo_server DEFAULT CHARSET utf8mb4;


--
-- 表tbl_user_profile
--
DROP TABLE IF EXISTS `tbl_user_profile`;
CREATE TABLE `tbl_user_profile` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `uuid` varchar(64) NOT NULL DEFAULT '' comment 'uuid',
  `name` varchar(256) NOT NULL DEFAULT '' comment 'name',
  `age` int unsigned NOT NULL DEFAULT 1 comment 'age',
  `gender` int unsigned NOT NULL DEFAULT 0 comment '0-unkown, 1-man, 2-female',
  `income` varchar(128) NOT NULL DEFAULT '' comment 'income',
  `phone` varchar(128) NOT NULL DEFAULT '' comment 'phone',
  `occupation` varchar(128) NOT NULL DEFAULT '' comment 'occupation',
  `update_at` bigint unsigned NOT NULL DEFAULT 0,
  `create_at` bigint unsigned NOT NULL DEFAULT 0,
  UNIQUE KEY (`uuid`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

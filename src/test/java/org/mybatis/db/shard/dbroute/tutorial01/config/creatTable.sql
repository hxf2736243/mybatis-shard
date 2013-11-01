
## shard_01
CREATE TABLE `user_0001` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_shard_id` (`shard_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `user_0002` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_shard_id` (`shard_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

## shard_02

CREATE TABLE `user_0003` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_shard_id` (`shard_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `user_0004` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_shard_id` (`shard_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
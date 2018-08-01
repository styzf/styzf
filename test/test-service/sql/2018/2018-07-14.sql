CREATE TABLE `test_main` (
  `test_id` int(10) NOT NULL COMMENT '主键id',
  `test_rel_id` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '关联id',
  `test_name` VARCHAR(16) NOT NULL DEFAULT '' COMMENT '测试表名称',
  `test_age` int(10) NOT NULL DEFAULT 0 COMMENT '测试表年龄',
  PRIMARY KEY (`test_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='测试表';

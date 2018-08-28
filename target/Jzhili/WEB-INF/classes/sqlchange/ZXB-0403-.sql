alter table birthday add column needPush int(2) default 1 comment '是否需要推送服务: 0 不需要 1 需要';

alter table user add column qqcode varchar(30) default '' comment 'QQ号';
alter table user add column wechatcode varchar(30) default '' comment '微信号';

CREATE TABLE `pushsetting` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userName` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `pushType` int(2) COLLATE utf8_bin DEFAULT 0 COMMENT '推送方式: 0 不需要 1 短信服务 2 邮箱 3 qq 4 微信',  
  `forwardDays` int(3) COLLATE utf8_bin DEFAULT 0 COMMENT '退钱多少天推送通知',  
  `pushDate` bigint(20) COLLATE utf8_bin DEFAULT 0 COMMENT '上次推送时间',  

  PRIMARY KEY (`id`),
  UNIQUE KEY `user_UNIQUE` (`userName`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='推送设置';
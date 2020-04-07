create database if not exists simplefarm;

use simplefarm;

#账户信息相关

create table account(
	`id` int auto_increment key comment '账号ID',
	`username` varchar(20) not null unique key comment '用户名',
	`password` varchar(50) not null comment '密码'
);

create table user(
	`id` int auto_increment key comment '用户ID',
	`account_id` int not null comment '账号ID',
	`nickname` varchar(50) not null comment '昵称',
	`level` int not null comment '等级',
	`exp` int not null comment '经验',
	`coin` int not null comment '金币数',
	foreign key(account_id) references account(id)
);


#静态配置相关

create table user_levelup(
	`level` int not null comment '等级',
	`exp` int not null comment '升级所需经验'
);

create table food(
	`id` int key comment '食物ID',
	`price` int not null comment '价格'
);

create table goods(
	`id` int key comment '货物ID',
	`price` int not null comment '价格'
);

create table baby(
	`id` int key comment '幼崽ID',
	`price` int not null comment '价格',
	`population` int not null comment '人口',
	`reap_interval` int not null comment '收获时间间隔，单位秒',
	`food_id` int not null comment '食物ID',
	`max_feed_count` int not null comment '最大喂养次数',
	`goods_id` int not null comment '货物ID',
	`min_goods` int not null comment '成熟最小货物数',
	`max_goods` int not null comment '成熟最大货物数',
	foreign key(food_id) references food(id),
	foreign key(goods_id) references goods(id)
);

create table seed(
	`id` int key comment '种子ID',
	`price` int not null comment '价格',
	`reap_interval` int not null comment '收获时间间隔，单位秒',
	`food_id` int not null comment '食物ID',
	`max_water_count` int not null comment '最大浇水次数',
	`goods_id` int not null comment '货物ID',
	`min_goods` int not null comment '成熟最小货物数',
	`max_goods` int not null comment '成熟最大货物数',
	foreign key(food_id) references food(id),
	foreign key(goods_id) references goods(id)
);

create table build_levelinfo(
	`id` int not null comment '建筑ID',
	`level` int not null comment '建筑等级',
	`price` int not null comment '价格'
);

create table build_maxlevel(
	`user_level` int not null comment '用户等级',
	`id` int not null comment '建筑ID',
	`max_level` int not null comment '最大等级'
);

create table livestock_levelinfo(
	`level` int not null comment '等级',
	`max_population` int not null comment '最大人口数'
);

create table well_levelinfo(
	`level` int not null comment '等级',
	`reap_count` int not null comment '一次收获水滴数',
	`reap_interval` int not null comment '收获时间间隔，单位秒',
	`max_count` int not null comment '最大水数量'
);

create table hunter_levelinfo(
	`level` int not null comment '等级',
	`min_receive_count` int not null comment '一次收获最少货物数量',
	`max_receive_count` int not null comment '一次收获最大货物数量',
	`receive_interval` int not null comment '收获时间间隔，单位秒'
);


#账号数据相关

create table warehouse(
	`user_id` int not null comment '用户ID',
	`object_type` int not null comment '对象类型，0-幼崽，1-种子，2-食物，3-货物',
	`object_id` int not null comment '对象ID',
	`count` int not null comment '数量',
	foreign key(user_id) references user(id)
);

create table user_build(
	`user_id` int not null comment '用户ID',
	`build_id` int not null comment '建筑ID',
	`level` int not null comment '建筑等级',
	foreign key(user_id) references user(id)
);

create table user_livestock(
	`user_id` int not null comment '用户ID',
	`build_id` int not null comment '建筑ID',
	`baby_id` int not null comment '幼崽ID，-1为未养殖',
	`count` int not null comment '幼崽数量',
	`feed_count` int not null comment '当前喂养次数',
	`breed_datetime` datetime not null comment '养殖时间',
	`goods_count` int not null comment '货物数量',
	foreign key(user_id) references user(id)
);

create table user_ground(
	`user_id` int not null comment '用户ID',
	`index` int not null comment '地块序号',
	`seed_id` int not null comment '种子ID',
	`water_count` int not null comment '浇水次数',
	`sow_datetime` datetime not null comment '播种时间',
	`goods_count` int not null comment '货物数量',
	foreign key(user_id) references user(id),
	foreign key(seed_id) references seed(id)
);

create table user_well(
	`user_id` int not null comment '用户ID',
	`count` int not null comment '水滴数',
	`reap_datetime` datetime not null comment '上次收获时间',
	foreign key(user_id) references user(id)
);

create table user_hunter(
	`user_id` int not null comment '用户ID',
	`send_datetime` datetime comment '放出时间',
	foreign key(user_id) references user(id)
);
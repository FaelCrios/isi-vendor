create table `estoque`(
                            `id` BIGINT(20) not null AUTO_INCREMENT,
                            `quantidade` BIGINT(100),
                            `produto_id` BIGINT(20),
                            primary key (`id`),
                            foreign key (`produto_id`) references produto(`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;;
CREATE TABLE IF NOT EXISTS `pagamento`(
    `id` BIGINT(20) not null AUTO_INCREMENT,
    `momento` varchar(120),
    `pedido_id` BIGINT(20),
    primary key (`id`),
    foreign key (pedido_id) references pedido(`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;;
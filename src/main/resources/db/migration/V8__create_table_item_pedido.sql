CREATE TABLE IF NOT EXISTS `item_pedido` (
                                     `id_produto` BIGINT(20) NOT NULL,
                                     `id_pedido` BIGINT(20) NOT NULL,
                                     `quantidade` BIGINT(20) NOT NULL,
                                     `preco` double,
                                     foreign key (`id_produto`)  references produto(`id`),
                                     foreign key (`id_pedido`) references pedido(`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `pedido` (
                          `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                          `instante` varchar(60),
                          `id_usuario` BIGINT(20),
                          PRIMARY KEY (`id`),
                          foreign key (`id_usuario`) references usuario(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;




    create table `produto_categoria` (
                    `id_produto` BIGINT(20) NOT NULL,
                    `id_categoria` BIGINT(20) NOT NULL,
                    foreign key (`id_produto`)  references produto(`id`),
                    foreign key (id_categoria) references categoria(`id`)
    )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


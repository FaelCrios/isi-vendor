CREATE TABLE `usuario` (
                           `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                           `nome` varchar(45),
                           `sobrenome` varchar(100),
                           `email` varchar(60),
                           `telefone` varchar(20),
                           `senha` varchar(100),
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

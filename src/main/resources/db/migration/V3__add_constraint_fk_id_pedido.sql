alter table usuario
add `id_pedido` BIGINT(20),
add constraint fk_id_pedido foreign key (id_pedido)  references pedido(`id`);
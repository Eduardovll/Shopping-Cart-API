INSERT INTO restaurante (id, cep, complemento, nome) VALUES
    (1L, '16800000', 'Rua Tres, 114', 'HamburguerValle'),
    (2L, '16800000', 'Rua Dois, 115', 'ChoppValle');

INSERT INTO cliente (id, cep, complemento, nome) VALUES
    (1L, '16800000', 'Rua Tres, 114', 'Eduardo Valle');

INSERT INTO produto (id, disponivel, nome, valor_unitario, restaurante_id) VALUES
    (1L, true, 'Chopp-500ml', 5.99, 2L),
    (2L, true, 'Chopp-300ml', 4.99, 2L),
    (3L, true, 'Duplo-Burguer', 21.99, 1L);

INSERT INTO cart (id, forma_pagamento, fechada, valor_total, cliente_id) VALUES
    (1L, 0, false, 0.0, 1L);
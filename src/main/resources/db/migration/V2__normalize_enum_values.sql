DO $$
BEGIN
    IF EXISTS (
        SELECT 1 FROM information_schema.columns
        WHERE table_name = 'pedido'
          AND column_name = 'forma_pagamento'
          AND data_type IN ('smallint', 'integer', 'bigint')
    ) THEN
        ALTER TABLE pedido DROP CONSTRAINT IF EXISTS pedido_forma_pagamento_check;
        ALTER TABLE pedido ALTER COLUMN forma_pagamento TYPE VARCHAR(255)
            USING (CASE forma_pagamento
                WHEN 0 THEN 'DINHEIRO'
                WHEN 1 THEN 'CARTAO'
                WHEN 2 THEN 'BOLETO'
                WHEN 3 THEN 'PIX'
            END);
    END IF;

    IF EXISTS (
        SELECT 1 FROM information_schema.columns
        WHERE table_name = 'pedido'
          AND column_name = 'status_pedido'
          AND data_type IN ('smallint', 'integer', 'bigint')
    ) THEN
        ALTER TABLE pedido DROP CONSTRAINT IF EXISTS pedido_status_pedido_check;
        ALTER TABLE pedido ALTER COLUMN status_pedido TYPE VARCHAR(255)
            USING (CASE status_pedido
                WHEN 0 THEN 'EM_ANDAMENTO'
                WHEN 1 THEN 'LIBERADO'
                WHEN 2 THEN 'CANCELADO'
                WHEN 3 THEN 'FINALIZADO'
            END);
    END IF;
END $$;

-- Acrescenta o campo status, necessário para o padrão State aplicado
-- ao fluxo de venda do carro (AVAILABLE -> RESERVED -> SOLD).
ALTER TABLE cars
    ADD COLUMN status VARCHAR(20) NOT NULL DEFAULT 'AVAILABLE';

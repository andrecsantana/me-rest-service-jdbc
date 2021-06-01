DROP TABLE IF EXISTS TB_PEDIDO;
DROP TABLE IF EXISTS TB_ITEM_PEDIDO;

CREATE TABLE TB_PEDIDO (
  pedidoId CHAR(10) PRIMARY KEY,
  itensAprovados INT NOT NULL,
  valorAprovado  INT NOT NULL
);

CREATE TABLE TB_ITEM_PEDIDO (
  pedidoId       CHAR(10),
  itemId         INT     ,
  descricao      VARCHAR(250) NOT NULL,
  precoUnitario  INT,
  qtd            INT
);

ALTER TABLE TB_ITEM_PEDIDO
  ADD CONSTRAINT itemPedidoKey UNIQUE(pedidoId, itemId);

INSERT INTO TB_PEDIDO (pedidoId, itensAprovados, valorAprovado) VALUES
  ('1',0, 0);   
  
INSERT INTO TB_ITEM_PEDIDO (pedidoId, ItemId, descricao, precoUnitario, qtd) VALUES
 ('1',1,'Item 1',10,1),
 ('1',2,'Item 2',5 ,3);
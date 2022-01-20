INSERT INTO productos (id, unidad, precio, etiqueta, descripcion) VALUES
(1, 2, 40000, 'Tableta', 'Tableta para piso'),
(2, 2, 30000, 'Tableta', 'Tableta para pared'),
(3, 1, 10000, 'Tela Jeans', 'Tela para jeans'),
(4, 5, 3600, 'Leche', 'Leche de vaca'),
(5, 4, 45000, 'Acero HSS', 'Acero HSS para CNC');


INSERT INTO servicios (id, unidad, precio, precio_fijo, etiqueta, descripcion) VALUES
(1, 2, 20000, false, 'Instalacion tableta de piso', 'Instalacion tableta de piso'),
(2, 2, 20000, false, 'Instalacion tableta de pared', 'Instalacion tableta depared'),
(3, 11, 100000, true, 'Toma de medidas', 'Toma de medidas de apartamento para planos'),
(4, 5, 200, false, 'Pasteurizacion de lacteos', 'Pasteurizacion de lacteos por litro'),
(5, 7, 50000, false, 'Servicio Torno, Fresadora y CNC', 'Torneado, resado y CNC de piezas en metal');
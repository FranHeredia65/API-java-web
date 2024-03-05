INSERT INTO proveedores (nombre,direccion,telefono,email) VALUES ('ReyFernando','Calle donde este','958463267','reyfernandoPasteleria@gmail.com')
INSERT INTO proveedores (nombre,direccion,telefono,email) VALUES ('Seira','Calle algun sitio','95423454','SeiraPasteleria@gmail.com')
INSERT INTO proveedores (nombre,direccion,telefono,email) VALUES ('DeliFrance','Por el kinepolis','95973863','DeliFrance@gmail.com')

INSERT INTO clientes (nombre,email) VALUES ('Fran Heredia','fran@gmail.com')
INSERT INTO clientes (nombre,email) VALUES ('Jose Pablo','jose@gmail.com')
INSERT INTO clientes (nombre,email) VALUES ('Carlos Muñoz','carlos@gmail.com')
INSERT INTO clientes (nombre,email) VALUES ('Santiago Santiago','santi@gmail.com')

INSERT INTO Pasteleria (title, company, image, price,proveedor_key) VALUES ('Pastel de fresa', 'La Isla', 'https://www.cocinatis.com/archivos/202207/CTIS0888-receta-tarta-de-nata-con-fresas.jpg', 79.99,NULL);
INSERT INTO Pasteleria (title, company, image, price,proveedor_key) VALUES ('Pastel de chocolate', 'Vilchez', 'https://content-cocina.lecturas.com/medio/2023/03/23/el-mejor-pastel-de-chocolate_24bd9cda_1200x1200.jpg', 25.99,1);
INSERT INTO Pasteleria (title, company, image, price,proveedor_key) VALUES ('Pastel de limón', 'Vilchez', 'https://okdiario.com/img/2018/12/14/tarta-de-limon.jpg', 45.99,1);
INSERT INTO Pasteleria (title, company, image, price,proveedor_key) VALUES ('Pastel de donut', 'Vilchez', 'https://elsabordepatty.com/wp-content/uploads/2022/04/tarta-donuts-patty.png', 6.99,1);
INSERT INTO Pasteleria (title, company, image, price,proveedor_key) VALUES ('Cheesecake', 'Crissant', 'https://static.vecteezy.com/system/resources/previews/025/065/317/non_2x/cheesecake-with-ai-generated-free-png.png', 69.99,1);
INSERT INTO Pasteleria (title, company, image, price,proveedor_key) VALUES ('Arroz con leche', 'La Isla', 'https://png.pngtree.com/png-clipart/20231001/original/pngtree-rice-and-milk-pudding-png-image_13024893.png', 8.99,2);
INSERT INTO Pasteleria (title, company, image, price,proveedor_key) VALUES ('Pastel de mango', 'Crissant', 'https://i.ytimg.com/vi/mSO4m9H79e4/maxresdefault.jpg', 21.99,2);

INSERT INTO cliente_Pasteleria (Pasteleria_id,cliente_id) VALUES (2,1)
INSERT INTO cliente_Pasteleria (Pasteleria_id,cliente_id) VALUES (2,2)
INSERT INTO cliente_Pasteleria (Pasteleria_id,cliente_id) VALUES (2,3)
INSERT INTO cliente_Pasteleria (Pasteleria_id,cliente_id) VALUES (3,1)
INSERT INTO cliente_Pasteleria (Pasteleria_id,cliente_id) VALUES (4,1)

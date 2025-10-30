
--  Categorías
INSERT INTO CATEGORY (ID, NAME)
VALUES (nextval('category_sequence'), 'Fruta y Verdura');

INSERT INTO CATEGORY (ID, NAME)
VALUES (nextval('category_sequence'), 'Pescado');

INSERT INTO CATEGORY (ID, NAME)
VALUES (nextval('category_sequence'), 'Secos');

-- Países
INSERT INTO COUNTRY (ID, CODE, NAME)
VALUES (nextval('country_sequence'), '08', 'España');

INSERT INTO COUNTRY (ID, CODE, NAME)
VALUES (nextval('country_sequence'), '09', 'Portugal');

-- Tiendas
INSERT INTO STORE (ID, NAME, CODE, COUNTRY_ID)
VALUES (nextval('store_sequence'), 'La esprilla', '003117', 1);

INSERT INTO STORE (ID, NAME, CODE, COUNTRY_ID)
VALUES (nextval('store_sequence'), 'Solares', '004778', 1);

INSERT INTO STORE (ID, NAME, CODE, COUNTRY_ID)
VALUES (nextval('store_sequence'), 'Oporto', '007008', 2);

-- Productos
INSERT INTO PRODUCT (ID, NAME, CODE, STATE, CATEGORY_ID)
VALUES (nextval('product_sequence'), 'Manzana', '12232390', 'ACTIVE', 1);

-- Surtido
INSERT INTO ASSORTMENT (PRODUCT_ID, STORE_ID)
VALUES (1, 1);


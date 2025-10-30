-- Product
insert into product (name, price, id)
values ('Product 1', 6.25, NEXT VALUE FOR product_sequence);

-- Tag
insert into tag (name, product_id, id)
values ('Tag 1', CURRVAL('product_sequence'), NEXT VALUE FOR tag_sequence);
insert into tag (name, product_id, id)
values ('Tag 2', CURRVAL('product_sequence'), NEXT VALUE FOR tag_sequence);
insert into tag (name, product_id, id)
values ('Tag 3', CURRVAL('product_sequence'), NEXT VALUE FOR tag_sequence);
insert into tag (name, product_id, id)
values ('Tag 4', CURRVAL('product_sequence'), NEXT VALUE FOR tag_sequence);
insert into tag (name, product_id, id)
values ('Tag 5', CURRVAL('product_sequence'), NEXT VALUE FOR tag_sequence);
insert into tag (name, product_id, id)
values ('Tag 6', CURRVAL('product_sequence'), NEXT VALUE FOR tag_sequence);

-- Comment
insert into comment (name, product_id, id)
values ('Comment 1', CURRVAL('product_sequence'), NEXT VALUE FOR comment_sequence);

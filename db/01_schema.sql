CREATE TABLE public.orders (
	order_id bigserial NOT NULL,
	order_price numeric(38, 2) NULL,
	order_vat numeric(38, 2) NULL,
	CONSTRAINT orders_pkey PRIMARY KEY (order_id)
);

CREATE TABLE public.products (
	product_id bigserial NOT NULL,
	price numeric(38, 2) NULL,
	vat numeric(38, 2) NULL,
	CONSTRAINT products_pkey PRIMARY KEY (product_id)
);

CREATE TABLE public.order_products (
	id bigserial NOT NULL,
	product_id int8 NOT NULL,
	quantity int4 NULL,
	price numeric(38, 2) NULL,
	vat numeric(38, 2) NULL,
	order_id int8 NOT NULL,
	CONSTRAINT order_products_pkey PRIMARY KEY (id),
	CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES public.orders(order_id),
	CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES public.products(product_id)
);
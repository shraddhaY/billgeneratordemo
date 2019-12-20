CREATE TABLE categories
(
  id character(32) NOT NULL,
  category character varying(10),
  tax_in_percent double,
  PRIMARY KEY (id)
);

CREATE TABLE product_master
(
  id character(32) NOT NULL,
  product_name character varying(70),
  price double,
  category_id character(32),
  PRIMARY KEY (id),
  FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE bill_master
(
  id character(32) NOT NULL,
  invoice_number character varying(70),
  first_name character varying(70),
  last_name character varying(70),
  email character varying(100),
  mobile_number character varying(20),
  total_price double,
  total_tax double,
  grand_total double,
  PRIMARY KEY (id)
);

CREATE TABLE bill_details
(
  id character(32) NOT NULL,
  bill_master_id character(32),
  product_master_id character(32),
  quantity bigint,
  per_product_price double,
  price double,
  taxable_amount double,
  total_amount double,
  PRIMARY KEY (id),
  FOREIGN KEY (bill_master_id) REFERENCES bill_master(id),
  FOREIGN KEY (product_master_id) REFERENCES product_master(id)
);

alter table bill_master add column bill_date bigint;

INSERT INTO `categories` (`id`, `category`, `tax_in_percent`) VALUES ('d6449889405f49879c6f58c9000aee0f', 'A', '10');
INSERT INTO `categories` (`id`, `category`, `tax_in_percent`) VALUES ('72e960f6ba2645c8b31ac66dc7ffcff8', 'B', '20');
INSERT INTO `categories` (`id`, `category`, `tax_in_percent`) VALUES ('606198521c65416e9d93e1684bab8496', 'C', '0');
===========================================================================================================================

INSERT INTO `product_master` (`id`, `product_name`, `price`, `category_id`) VALUES ('2f1a5d6598e64905bec0fb288d07b471', 'Product1', '500', '72e960f6ba2645c8b31ac66dc7ffcff8');
INSERT INTO `product_master` (`id`, `product_name`, `price`, `category_id`) VALUES ('59a7fd4261a14de6ab25e33d6e829ef6', 'Product2', '400', '606198521c65416e9d93e1684bab8496');
INSERT INTO `product_master` (`id`, `product_name`, `price`, `category_id`) VALUES ('436b3cb802b84d0492949f0ef090f5ad', 'Product3', '800', 'd6449889405f49879c6f58c9000aee0f');

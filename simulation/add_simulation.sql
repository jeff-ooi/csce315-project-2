"create orders table"
(create_orders.sql)
CREATE TABLE orders (id int NOT NULL PRIMARY KEY, price real, date_time timestamp);

"populate orders"
(populate_orders.sql)
\copy orders from 'C:\CSCE 315\project 2\csce315-project-2\simulation\year_data_simulation.csv' CSV HEADER

"create order_add_ons junction table"
(create_order_add_ons_unction.sql)
CREATE TABLE order_add_ons (order_menu_junction_id int NOT NULL, add_on_id int NOT NULL);

"populate order_add_ons junction table"
(populate_order_add_ons_junction.sql)
\copy order_add_ons from 'C:\CSCE 315\project 2\csce315-project-2\simulation\order_add_ons_junction.csv' CSV HEADER

"create order_menu junction table"
(create_order_menu_junction.sql)
CREATE TABLE order_menu (id int NOT NULL PRIMARY KEY, order_id int NOT NULL, menu_id int NOT NULL);

"populate order_menu junction table"
(populate_order_menu_junction.sql)
\copy order_menu from 'C:\CSCE 315\project 2\csce315-project-2\simulation\order_menu_items_junction.csv' CSV HEADER

"Grant permissions"
(grant_permissions.sql)
GRANT ALL PRIVILEGES ON orders TO csce315_971_spencertlogan;
GRANT ALL PRIVILEGES ON orders TO csce315_908_suhulavu;
GRANT ALL PRIVILEGES ON orders TO csce315_971_arnav_sood;
GRANT ALL PRIVILEGES ON order_menu TO csce315_971_spencertlogan;
GRANT ALL PRIVILEGES ON order_menu TO csce315_908_suhulavu;
GRANT ALL PRIVILEGES ON order_menu TO csce315_971_arnav_sood;
GRANT ALL PRIVILEGES ON order_add_ons TO csce315_971_spencertlogan;
GRANT ALL PRIVILEGES ON order_add_ons TO csce315_908_suhulavu;
GRANT ALL PRIVILEGES ON order_add_ons TO csce315_971_arnav_sood;


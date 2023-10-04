"select count of orders grouped by week"
(count_by_week.sql)
SELECT COUNT(*) FROM orders GROUP BY DATE_PART('week', date_time);

"select count of orders, sum of order total grouped by hour"
(count_sum_by_hour.sql)
SELECT COUNT(*), SUM(price) FROM orders GROUP BY DATE_PART('year', date_time), DATE_PART('month', date_time), DATE_PART('week', date_time), DATE_PART('day', date_time), DATE_PART('hour', date_time);

"select top 10 sums of order total grouped by day in descending order"
(top_10_sums.sql)
SELECT SUM(price) FROM orders GROUP BY DATE_PART('year', date_time), DATE_PART('month', date_time), DATE_PART('week', date_time), DATE_PART('day', date_time) ORDER BY SUM(price) DESC LIMIT 10;

"select row count from inventory"
(row_count.sql)
SELECT COUNT(*) FROM inventory;

"select item price from menu for specific item"
(item_price.sql)
SELECT price FROM menu WHERE name = 'The Alley Trio';

"select item name from menu under $5"
(item_under_5.sql)
SELECT name FROM menu WHERE price < 5;

"insert new order into orders"
(insert_order.sql)
INSERT INTO orders (id, price, date_time) VALUES (555555, 6.99, '2019-01-01 00:00:00');

"update menu item price in menu"
(update_menu_item.sql)
UPDATE menu SET price = 6.99 WHERE name = 'The Alley Trio';

-- Arnav --

"list all cashier names"
(list_cashier_names.sql)
SELECT name FROM employee WHERE position = 'cashier';

"list all manager names"
(list_manager_names.sql)
SELECT name FROM employee WHERE position = 'manager';

"insert new manager"
(insert_manager.sql)
INSERT INTO employee (id, username, password, name, start_date, salary, position) VALUES (6, 'tempM', 'tempMPass', 'temp Manager', '2011-10-31', 30, 'manager');

"insert new cashier"
(insert_cashier.sql)
INSERT INTO employee (id, username, password, name, start_date, salary, position) VALUES (6, 'tempC', 'tempCPass', 'temp Cashier', '2019-10-31', 1, 'cashier');

"list all lulu drinks"
(list_lulu.sql)
SELECT name FROM menu WHERE name LIKE '%LuLu%';

"list all milk tea"
(list_milk_tea.sql)
SELECT name FROM menu WHERE name LIKE '%Milk Tea%';

"list all green tea"
(list_green_tea.sql)
SELECT name FROM menu WHERE name LIKE '%Green Tea%';

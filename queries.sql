"select count of orders grouped by week"
(count_by_week.sql)
SELECT COUNT(*) FROM orders GROUP BY DATE_PART('week', date_time);

"select count of orders, sum of order total grouped by hour"
(count_sum_by_hour.sql)
SELECT COUNT(*), SUM(price) FROM orders GROUP BY DATE_PART('hour', date_time);

"select top 10 sums of order total grouped by day in descending order"
(top_10_sums.sql)
SELECT SUM(price) FROM orders GROUP BY DATE_PART('day', date_time) ORDER BY SUM(price) DESC LIMIT 10;

"select row count from inventory"
(row_count.sql)
SELECT COUNT(*) FROM inventory;

"select item price from menu for specific item"
(item_price.sql)
SELECT price FROM menu WHERE name = 'The Alley Trio';

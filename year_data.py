import datetime
import random
import csv

DATE_ONE = datetime.timedelta(days=1)
MENU_ITEMS = [0,1,2,3,4,5,6,7,8,9,10]
ADD_ONS = [11,12,13,14,15]

with open("./order_menu_items_junction.csv", "w", newline='') as orderMenuJunctionFile:
    orderMenuJunctionWriter = csv.writer(orderMenuJunctionFile)
    orderMenuJunctionWriter.writerow(["id","order_id","menu_item_id"])

with open("./order_add_ons_junction.csv", "w", newline='') as orderAddOnsJunctionFile:
    orderAddOnsJunctionWriter = csv.writer(orderAddOnsJunctionFile)
    orderAddOnsJunctionWriter.writerow(["id","order_menu_junction_id","add_on_id"])

current_date = datetime.datetime(year=2022,month=1,day=1)
order_id = 0
order_menu_junction_id = 0
order_add_on_junction_id = 0
with open("./year_data_simulation.csv", "w", newline='') as ordersFile:
    ordersWriter = csv.writer(ordersFile)
    ordersWriter.writerow(["id", "price", "date/time"])
    # hour = random.randint(8,8)
    # minute = random.randint(0,59)
    # print(hour)
    # print(minute)
    # current_date = current_date.replace(hour=hour,minute=minute)
    # print(current_date.time())
    # print(current_date)
    # writer.writerow([current_date])
    while (current_date.year != 2023):
        current_date = current_date.replace(hour=random.randrange(8,8+1),minute=random.randrange(0,60))
        minProfit = random.uniform(2739.73-250,2739.73+250)
        if (current_date.date() == datetime.datetime(year=2022,month=1,day=17).date() or current_date == datetime.datetime(year=2022,month=9,day=21).date()):
            minProfit = 5000
        sumPrice = 0
        while sumPrice < minProfit:
            add_ons_amount = 0
            amount_ordered = random.randrange(1,5)
            with open("./order_menu_items_junction.csv", "a", newline='') as orderMenuJunctionFile:
                orderMenuJunctionWriter = csv.writer(orderMenuJunctionFile)
                for i in range(amount_ordered):
                    add_ons_amount = random.randrange(0,3)
                    menu_item = random.choice(MENU_ITEMS)
                    with open("./order_add_ons_junction.csv", "a", newline='') as orderAddOnsJunctionFile:
                        orderAddOnsJunctionWriter = csv.writer(orderAddOnsJunctionFile)
                        for i in range(add_ons_amount):
                            add_on = random.choice(ADD_ONS)
                            orderAddOnsJunctionWriter.writerow([order_add_on_junction_id,order_menu_junction_id,add_on])
                            order_add_on_junction_id += 1
                    orderMenuJunctionWriter.writerow([order_menu_junction_id,order_id,menu_item])
                    order_menu_junction_id += 1
            # price = round(random.uniform(6.50-0.25, 6.50+0.25),2)*amount_ordered+round(random.uniform(0.75-0.25,0.75+0.25),2)*add_ons_amount
            price = 6.50*amount_ordered+0.75*add_ons_amount
            sumPrice += price
            ordersWriter.writerow([order_id,price,current_date])
            order_id += 1
        current_date += DATE_ONE
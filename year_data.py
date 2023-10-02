import datetime
import random
import csv

DATE_ONE = datetime.timedelta(days=1)   # to add 1 day
MENU_ITEMS = [0,1,2,3,4,5,6,7,8,9,10]   # menu item ids
ADD_ONS = [11,12,13,14,15]              # add-on ids

# clears the file and writes the header
with open("./simulation/order_menu_items_junction.csv", "w", newline='') as orderMenuJunctionFile:
    orderMenuJunctionWriter = csv.writer(orderMenuJunctionFile)
    orderMenuJunctionWriter.writerow(["id","order_id","menu_item_id"])  # header

# clears the file and writes the header
with open("./simulation/order_add_ons_junction.csv", "w", newline='') as orderAddOnsJunctionFile:
    orderAddOnsJunctionWriter = csv.writer(orderAddOnsJunctionFile)
    orderAddOnsJunctionWriter.writerow(["id","order_menu_junction_id","add_on_id"]) # header

current_date = datetime.datetime(year=2022,month=1,day=1)
order_id = 0
order_menu_junction_id = 0
order_add_on_junction_id = 0

with open("./simulation/year_data_simulation.csv", "w", newline='') as ordersFile:
    ordersWriter = csv.writer(ordersFile)
    ordersWriter.writerow(["id", "price", "date/time"]) # header
    while (current_date.year != 2023):
        # generates a random hour and minute
        current_date = current_date.replace(hour=random.randrange(8,8+1),minute=random.randrange(0,60))
        # since we need at least $1 million in sales, 1 mil/365 is 2739.73. $250 is variance in sales
        minProfit = random.uniform(2739.73-250,2739.73+250)
        # to create to two peak days, literally the day before the start of the two semesters in 2022
        if (current_date.date() == datetime.datetime(year=2022,month=1,day=17).date() or current_date.date() == datetime.datetime(year=2022,month=9,day=21).date()):
            minProfit = 5000
        sumPrice = 0
        # will keep generating sales data for the day until the mininum sales for the day is met
        while sumPrice < minProfit:
            add_ons_amount = 0
            # random number of menu items ordered
            amount_ordered = random.randrange(1,5)
            with open("./simulation/order_menu_items_junction.csv", "a", newline='') as orderMenuJunctionFile:
                orderMenuJunctionWriter = csv.writer(orderMenuJunctionFile)
                for i in range(amount_ordered):
                    # selects a random menu item by id
                    menu_item = random.choice(MENU_ITEMS)
                    # selects a random number of add-ons for the menu item
                    add_ons_amount = random.randrange(0,3)
                    with open("./simulation/order_add_ons_junction.csv", "a", newline='') as orderAddOnsJunctionFile:
                        orderAddOnsJunctionWriter = csv.writer(orderAddOnsJunctionFile)
                        for i in range(add_ons_amount):
                            # selects a random add-on by id
                            add_on = random.choice(ADD_ONS)
                            # writes to the "order and add-ons" junction table
                            # though it's really the junction table between the "order and menu item junction table" and "add-ons"
                            orderAddOnsJunctionWriter.writerow([order_add_on_junction_id,order_menu_junction_id,add_on])
                            order_add_on_junction_id += 1
                    # writes to the "order and menu item" junction table
                    orderMenuJunctionWriter.writerow([order_menu_junction_id,order_id,menu_item])
                    order_menu_junction_id += 1
            # this price assignment adds more randomness, but i don't think it is necessary
            # price = round(random.uniform(6.50-0.25, 6.50+0.25),2)*amount_ordered+round(random.uniform(0.75-0.25,0.75+0.25),2)*add_ons_amount
            # determines price. 6.50 and 0.75 were randomly picked by me
            price = 6.50*amount_ordered+0.75*add_ons_amount
            sumPrice += price
            # writes to the orders csv
            ordersWriter.writerow([order_id,price,current_date])
            order_id += 1
        current_date += DATE_ONE
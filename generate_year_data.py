import datetime
import random
import csv

DATE_ONE = datetime.timedelta(days=1)   # to add 1 day
# MENU_ITEMS = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20]   # menu item ids
# ADD_ONS = [1,2,3,4,5,6,7,8,9,10,11,12]      # add-on ids
MENU_ITEMS = {  # menu_item_id: add_on_ids
    1:[1,2,3,4,5,6,7,11],
    2:[1,2,3,4,5,6,7,8],
    3:[1,3,5,7,6],
    4:[1,2,3,4,5,6,7],
    5:[1,2,3,4,5,6,7],
    6:[1,3,5,6,9],
    7:[1,2,3,4,5,6,7],
    8:[1,2,3,4,5,6,7],
    9:[1,2,3,4,5,6,7],
    10:[1,2,3,4,5,6,7],
    11:[1,2,3,4,5,6,7,10],
    12:[1,3,4,5,6],
    13:[1,3,4,5,6],
    14:[1,3,4,5,6],
    15:[1,3,4,5,6],
    16:[5],
    17:[2],
    18:[11,12],
    19:[1,3,6,7],
    20:[1,3,6]
}
MENU_ITEMS_PRICE = [6.99,6.59,5.99,5.25,4.99,6.59,5.99,4.99,4.99,4.99,6.59,6.59,6.99,6.99,6.99,6.45,6.45,6.95,5.99,5.99]    # prices of the menu items. idx is menu_item_id-1
ADD_ONS_PRICE = [0.6,0.6,0.6,0.8,0.6,0.8,0.8,0.6,0.6,0.6,0.6,0.6]   # prices of the add-ons. idx is add_on_id-1

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
    # get a full year of data
    while (current_date.year != 2023):
    # while (current_date.day != 2):
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
            price = 0
            add_ons_amount = 0
            # random number of menu items ordered
            amount_ordered = random.randrange(1,5)
            with open("./simulation/order_menu_items_junction.csv", "a", newline='') as orderMenuJunctionFile:
                orderMenuJunctionWriter = csv.writer(orderMenuJunctionFile)
                
                for i in range(amount_ordered):
                    # selects a random menu item by id
                    menu_item = random.randrange(1,len(MENU_ITEMS)+1)
                    # adds the price of that menu item to the total price of the order 
                    price += MENU_ITEMS_PRICE[menu_item-1]
                    # selects a random number of add-ons for the menu item
                    add_ons_amount = random.randrange(0,3)
                    
                    with open("./simulation/order_add_ons_junction.csv", "a", newline='') as orderAddOnsJunctionFile:
                        orderAddOnsJunctionWriter = csv.writer(orderAddOnsJunctionFile)
                        for i in range(add_ons_amount):
                            # selects a random add-on by id
                            add_on = random.randrange(1,len(ADD_ONS)+1)
                            # first add-on is free?
                            # if i != 0:
                            #     price += ADD_ONS_PRICE[add_on-1]
                            # adds the price of that add-on to the total price of the order
                            price += ADD_ONS_PRICE[add_on-1]
                            # writes to the "order and add-ons" junction table
                            # though it's really the junction table between the "order and menu item junction table" and "add-ons"
                            orderAddOnsJunctionWriter.writerow([order_add_on_junction_id,order_menu_junction_id,add_on])
                            order_add_on_junction_id += 1
                    
                    # writes to the "order and menu item" junction table
                    orderMenuJunctionWriter.writerow([order_menu_junction_id,order_id,menu_item])
                    order_menu_junction_id += 1

            price = round(price,2)
            sumPrice += price
            # writes to the orders csv
            ordersWriter.writerow([order_id,price,current_date])
            order_id += 1
        current_date += DATE_ONE
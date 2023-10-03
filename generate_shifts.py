import random
import csv

with open("./simulation/shifts.csv", "w", newline='') as shiftsFile:
    shiftsWriter = csv.writer(shiftsFile)
    shiftsWriter.writerow(["employee_id","start_time","end_time","day_of_week","month"])    # header
    
    id = 1
    # manager is employee_id 1, works 11-21 every day (rip)
    for i in range(12):
        for j in range(7):
            shiftsWriter.writerow([id,11,9+12,j+1,i+1])
    
    id += 1
    while id < 6:
        month = 1
        
        while month < 13:
            day_of_week = 1
            
            while day_of_week < 8:
                start_time = random.randrange(11,9+12+1-5)  # random start_time
                end_time = random.randrange(start_time+1,9+12+1)    # random end_time > start_time
                # end_time = 9+12     # each employee works until the shop closes
                shiftsWriter.writerow([id,start_time,end_time,day_of_week,month])
                day_of_week += 1
            
            month += 1
        
        id += 1

import random
import csv

shifts = [0,1,2,3,4,5,6,7,8]
employee_ids = [1,2,3,4,5]

# clears the file and writes the header
with open("./simulation/employee_shift.csv", "w", newline='') as employeeShiftJunctionFile:
    employeeShiftJunctionWriter = csv.writer(employeeShiftJunctionFile)
    employeeShiftJunctionWriter.writerow(["id","shift_id","employee_id","month","day_of_week"])    # header
    
    id = 0
    # manager is employee_id 1, works 11-21 every day (rip)
    for month in range(12):
        for day in range(7):
            for shift in range(len(shifts)):
                employeeShiftJunctionWriter.writerow([id,shift,1,month+1,day+1])
                
    id += 1
    month = 1
    # all 12 months
    while month < 13:
        day_of_week = 1
        
        while day_of_week < 8:
            for i in range(len(employee_ids)):
                employee = i+1
                # chosses a random shift that starts before or on 13
                shift = random.randrange(0,3)
                for i in range(3):
                    employeeShiftJunctionWriter.writerow([id,shift,employee,month,day_of_week])
                    if i < 3-2:
                        shift = random.randrange(shift+1,shift+3+1)
                    else:
                        shift = random.randrange(shift,8+1)
                    id += 1
                
            day_of_week += 1
        
        month += 1
exit(0)
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

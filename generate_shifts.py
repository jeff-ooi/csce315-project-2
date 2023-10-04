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
        # all 7 days
        while day_of_week < 8:
            for i in range(len(employee_ids)):
                employee = i+1
                # chooses a random shift that starts before or on 13
                shift = random.randrange(0,3)
                # each employee works 3 shifts
                for i in range(3):
                    # third iteration is to write the second iteration's shift
                    employeeShiftJunctionWriter.writerow([id,shift,employee,month,day_of_week])
                    # chooses a random shift that is later than shifts already worked
                    if i < 3-2:
                        # chooses a random shift that is later than the shift already worked, but not all the way until the final shift
                        shift = random.randrange(shift+2,shift+3+1)
                    else:
                        # last second goes until the final shift
                        # if the shift is 
                        if (shift+2 >= 8):
                            shift = 8
                        else:
                            shift = random.randrange(shift+2,8+1)
                    id += 1
                
            day_of_week += 1
        
        month += 1

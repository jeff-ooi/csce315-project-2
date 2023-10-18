# csce315-project-2
Unofficial POS system for The Alley with fake data.  Team 08M

Replace jeffooi with your netid without periods or dashes  
Replace 971 with your section number  
psql -h csce-315-db.engr.tamu.edu -U csce315_971_jeffooi -d csce315331_08m_db  

Compile and run GUI  
javac <path/to/java/directory/*.java>  
Not Mac:  
java -cp ".;<path/to/file/postgresql-42.2.8.jar>" <path/to/java/file.java>  
Mac:  
java -cp ".:<path/to/file/postgresql-42.2.8.jar>" <path/to/java/file.java> 
  
Compile and run ManagerGUI  
cd into ManagerGUI/src/main/java  
javac com/mycompany/managergui/*.java  
Not Mac:  
java -cp ".;com/mycompany/managergui/postgresql-42.2.8.jar" com/mycompany/managergui/ManagerGUI  
Mac:  
java -cp ".:com/mycompany/managergui/postgresql-42.2.8.jar" com/mycompany/managergui/ManagerGUI  
ManagerGUI Docs:  
https://people.tamu.edu/~jeffooi/Documentation/com/mycompany/managergui/package-summary.html  

Compile and run CashierGUI
cd into cashierPOS/src/  
javac cashierpos/*.java  
Not Mac:  
java -cp ".;cashierpos/postgresql-42.2.8.jar" cashierpos/OrderMenu  
Mac:  
java -cp ".:cashierpos/postgresql-42.2.8.jar" cashierpos/OrderMenu  
CashierGUI Docs:  
https://people.tamu.edu/~jeffooi/cashierDocumentation/cashierpos/package-summary.html  


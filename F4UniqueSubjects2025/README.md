# StAlbanDataProjects

## Form 4 Subject Choice CSV 
This project is designed to handle the importation of student subject choices from a CSV file into a MySQL database. 

### File Structure
- **.gitignore**: Specifies files and directories to be ignored by Git, including CSV files.
- **.vscode/settings.json**: Contains settings for the Visual Studio Code environment, including database connection configurations.
- **Form4LocalDB.session.sql**: Contains SQL queries or commands related to the Form 4 Local Database.
- **pom.xml**: Maven project configuration file that defines project dependencies, build settings, and plugins.
- **src/data/F4_subject_choices.csv**: The CSV file containing student names and their subject choices.
- **src/main/java/com/form4/app.java**: Source code for the App class, which reads from the CSV file and inserts data into the database.
- **src/test/java**: Directory intended for test files related to the project.

### Usage
1. Ensure that the MySQL database is set up and running.
2. Update the database connection details in `app.java` if necessary.
3. Place the `F4_subject_choices.csv` file in the `src/data` directory.
4. Run the application to import student data into the database.

### Subject Code Information
| Subject                     | Subject Code |
|-----------------------------|--------------|
| Mathematics                 | MAT          |
| English                     | ENG          |
| Physical Science            | PHY          |
| Life Science                | LIF          |
| Information Technology       | INF          |
| Computer Applied Technology  | CAT          |
| Music                       | MUS          |
| Drama                       | DRA          |
| Geography                   | GEO          |
| History                     | HIS          |
| Engineering Graphic Design   | EGD          |
| French                      | FRE          |
| Accounting                  | ACC          |
| Business Studies            | BUS          |
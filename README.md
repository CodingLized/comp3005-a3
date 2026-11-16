# comp3005-a3
Database Management Systems Assignment 3 Q1

## Installing
- Download the .tar.gz file from Releases and extract using `tar -xvzf`
## Configuration
- Ensure that you have both JDK 21 and the Maven CLI tool (mvn) installed on your system.
- Navigate to java/src/resources/
- Copy `hibernate.properties.example` to `hibernate.properties` and replace the example data with your database credentials.

## Building / Running
- Navigate to the `java` directory
- Run `mvn clean compile exec:exec`
- You will be greeted with a menu listing options corresponding to the CRUD operations
### Adding a New Student
- After the menu appears, enter 2 and you will be prompted for the new student's information.
- Ensure that your input is space-delimited and then press enter to add the new student's information.
- You can run option 1 to verify that the student has been successfully added.
### Updating a Student's Email
- Select option 3 from the menu
- Enter the ID number for the student
- Enter the new email for the student and press enter. Run option 1 again to verify that the email has been updated.
### Deleting a Student's Record
- Select option 4 from the menu
- Enter the ID number for the student. The record should now be deleted.

## Demo Video
- Link: https://vimeo.com/1247412290


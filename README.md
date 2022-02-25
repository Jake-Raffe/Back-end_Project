BNTA Server Side Project 

<h1>Our Project: HealthFirst</h1>

Authors - Aoife, Suraya, Michael, Jake, Hajr 

<h3>What is HealthFirst?</h3>

Our project is a model of a booking system for NHS appointments. With the system, an NHS administrator can book, cancel, or reschedule doctors appointments for patients. The appointments are stored in a SQL database and different queries can be run that allow the administrator to see details of appointments and patient records.

They can also add new patients to the system.

The technologies used for this project were Java, Spring Boot, JDBC Template and a PostgreSQL database.

<b>Why make this system?</b>

We chose this project because we had several group members work for the NHS so we were very familiar with the operations such a system needs to be able to perform. 
There are also lots of options for filtering and returning results for this sort of system, which allows us to extend it easily. 

Setup and Instructions for using the Project

<i>Setup</i>

1. Project Clone Link: git@github.com:Jake-Raffe/Back-end_Project.git
2. Make a Database (Recommend with Postico & Postgres) using the PSQL 'database' file in the project repo - This will give you the correct table and column names, and some starter data. 
3. If you don't already have it, download Postman so that you can interact with the API,. The HTTP queries you will make will be on localhost:8080/(command here)

<b>Instructions for Setting your Application Properties</b>
Ensure that your application properties file is properly configured. We have set this for you, and it should look like this

</b>
spring.datasource.url=jdbc:postgresql://localhost/serverproject <br>
spring.datasource.username=.  <br>
spring.datasource.password=.  <br>
spring.datasource.driver-class-name=org.postgresql.Driver. <br>
server.error.include-message=always. <br>
server.error.include-binding-errors=always <br>
</b>

<i>How To Guide </i>

Once you have the project cloned in your local machine, and your database set up, you should test the build. On successful run of the Main, your IntelliJ (or any other IDE you choose to use) should look something like this.

<img width="1440" alt="Screenshot 2022-02-25 at 09 30 34" src="https://user-images.githubusercontent.com/97964074/155691073-cd5962c6-474b-4333-9f1a-59abd4862420.png">


<h2>Commands</h2>

<h4>Patient Commands</h4>

@GET Requests

Ensure your Postman is set to run a HTTP GET request

-> To get the list of all patients: localhost:8080/patients
-> To get a specific patient if you know their ID, your format should be as follows (replace {id} with the id number): localhost:8080/patients/{id}

@POST

Ensure your Postman is set to run a HTTP POST request
-> To add a patient: localhost:8080/patients/

Now you must enter the patient data. You should do this in JSON Object format within the body. It should look like this

[. <br>
    {. <br>
        "patientName": "Julie",  <br>
        "patientPhoneNumber": "09238407986",  <br>
        "patientEmailAddress": "julie@example.com",  <br>
        "bloodType": "B". <br>
    },  <br>
 ]    <br>
 
@PUT 

Ensure your Postman is set to run a HTTP PUT request
-> To update a patient

Imagine you have already added the above patient from the example, however, you want to change their blood type to A. 
It looks very similar to adding a patient, but you need to know the id number. 
You just run localhost:8080/patients/{id}, and put the new body as so; 

[
    {
        "patientName": "Julie",  <br>
        "patientPhoneNumber": "09238407986",  <br>
        "patientEmailAddress": "julie@example.com",  <br>
        "bloodType": "A" <br>
    },  <br>
 ]   <br>

Don't worry if you get it wrong and try to update a patient id that doesn't exists, the progam will give you an error in  Postman to tell you that there is no existing patient at that ID.

@DELETE 

Ensure your Postman is set to run a HTTP DELETE request
--> To delete a patient

Imagine you have already added the above patient from the example, however, you want to now delete their record.
Again, you can run localhost:8080/patients/{id}


--> To delete all patients

If you run run localhost:8080/patients/ with the @DELETE request selected, it will delete all records.
BE CAREFUL DOING THIS! It is not reversible. Also be aware that if you now add more patients, the previous IDs will not ever be used again. 

<h4>Doctor Commands </h4>

Our requests allow you to do the following

-> Add doctor   @POST + localhost:8080/doctors/. <br>
-> Get doctor by ID    @GET + localhost:8080/doctors/{id} <br>
-> Get all doctors    @GET + localhost:8080/doctors/ <br>
-> Update a doctor   @PUT + localhost:8080/doctors/{id} <br>
-> Delete doctor    @DELETE + localhost:8080/doctors/{id} <br>

These Commands work as the patient commands above, except with the word 'doctors' substitiuted for 'patients'.

However there is also an additional method, where you can fill the database with our preset doctors.  

<h5>Fill Preset Doctors </h5>
@POST
Ensure your Postman is set to run a HTTP POST request
-> To fill the doctors: localhost:8080/doctors/fill

<b>You do not need to put anything in the body of the JSON object for this command. </b>

<h4>Appointment Commands</h4>

These work as above, except with the word 'appointments' substitiuted for patients. 

-> Add appointment   @POST + localhost:8080/appointments/ <br>
-> Get appt by ID    @GET + localhost:8080/appointments/{id} <br>
-> Get all appointments   @GET + localhost:8080/appointments/ <br>
-> Update a doctor   @PUT + localhost:8080/appointments/{id} <br>
-> Delete appointment    @DELETE + localhost:8080/appointments/{id} <br>

However there is also an additional method, where you can fill the database with our preset doctors.  

<h5>Get Appointment by Patient Bloodtype </h5>
@GET
Ensure your Postman is set to run a HTTP GET request
-> To get patient by patient boodtype: localhost:8080/appointments/bloodtype/{bloodtype} <br>
Bloodtypes are A, B, AB, and O, and they are strings in the JSON object, so you should use double quotes to enclose them as so "A".

<b>You do not need to put anything in the body of the JSON object for this command. </b>


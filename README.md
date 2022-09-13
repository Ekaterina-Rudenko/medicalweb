 ## Application for a medical centre where a patient can make an appointment with a doctor

There are four roles with access rights: Guest, Patient, Doctor, Administrator

Stack: Java EE, Servlets, JDBC, JSP, MySql, Apache Tomcat, Bootstrap, Html

**Guest** can:
- log in
- register
- see all services and prices
- filter services by specializations
- see all doctors
- filter doctors by specializations
- change language

**Patient** can:
- log out
- change language
- see his/her profile info
- change password
- make an appointmnet (choose specialization, doctor, date, time, type of payment if needed)
- cancel an appoitment
- see the list of his/her appointments
- see reccomendations from doctors
- see and top up the balance
- see all services and prices
- filter services by specializations
- see all doctors
- filter doctors by specializations

**Doctor** can:
- log out
- change password
- change language
- see planned visits for a chosen date
- see patient info
- make recommendation (linked to certain visit)
- see/filter recommendations by visit id

**Admin** can:
- log out
- change language
- see his/her profile info
- change password
- add/update service
- see all services
- filter services by specialization
- add specialization
- add doctor
- see/update doctor profile
- see all doctors
- filter doctors
- add administrator
- see/update admin profile
- block/unblock patient/doctor/admin
- see all visits
- filter visits by date, doctor
- change visit status

The EER diagram for data base is attached below:

![medical_web](https://user-images.githubusercontent.com/81095100/153303794-a2198f86-d557-48bd-bcee-a5ca21af71a4.png)

Some pages:
![image](https://user-images.githubusercontent.com/81095100/189779100-a25f0951-9bf5-4754-89b4-ed621e8a2e1d.png)
![image](https://user-images.githubusercontent.com/81095100/189779114-65fd5f0e-d24f-4307-95fa-6518889d727e.png)
![image](https://user-images.githubusercontent.com/81095100/189779139-aa6f01e0-ebdd-4b7a-9680-1c273de70212.png)
![image](https://user-images.githubusercontent.com/81095100/189779175-3b311c50-05f7-46ed-98d2-f514ad6648a3.png)
![image](https://user-images.githubusercontent.com/81095100/189779183-46260206-0266-43da-821f-890eb83a2fdd.png)
![image](https://user-images.githubusercontent.com/81095100/189779225-037d136d-498c-4077-a488-95b3f0d08b9e.png)

![image](https://user-images.githubusercontent.com/81095100/189779241-930c9a3e-ab36-4d3c-b1e8-7cef54909f3d.png)

![image](https://user-images.githubusercontent.com/81095100/189779260-fcba5770-b5e8-40be-81da-b452743c079c.png)


 


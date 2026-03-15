Healthcare Appointment and Patient Management System
A microservices-based healthcare management platform designed to streamline interactions between patients, doctors, and administrative staff. The system enables efficient appointment scheduling, patient management, and billing operations while maintaining scalability and modularity through a microservices architecture.
The platform also integrates a machine learning–based prediction module that predicts whether a patient is likely to attend or miss an appointment, helping healthcare providers proactively manage scheduling and reduce no-shows.
________________________________________
Architecture
The application follows a microservices architecture, where each service is responsible for a specific domain and communicates with other services using REST APIs and asynchronous messaging.
Core Microservices
Patient Service
•	Manages patient registration and profile information.
•	Stores patient details such as name, age, and contact information.
•	Provides APIs to retrieve and update patient data.
Doctor Service
•	Manages doctor profiles and their specialization.
•	Maintains available time slots for appointments.
Appointment Booking Service
•	Handles appointment scheduling between patients and doctors.
•	Validates doctor availability before confirming bookings.
Billing Service
•	Generates billing records based on completed appointments.
________________________________________
Machine Learning Integration
To enhance scheduling efficiency, the system integrates a Machine Learning model that predicts patient attendance.
Prediction Goal
Predict whether a patient is likely to attend or miss an appointment.
Model Used
Logistic Regression (Supervised Machine Learning)
Features Used
•	distanceFromClinicKm
•	previousNumberOfAppointments
•	pastNoShowCount
Output
Binary prediction:
1 → Patient likely to attend
0 → Patient likely to miss appointment
The trained model is built using Python and Scikit-learn, saved as a serialized model file, and invoked from the Java microservice to generate predictions.
This feature enables:
•	identification of high-risk no-show patients
•	proactive scheduling decisions
•	improved healthcare resource utilization
________________________________________
Technologies Used
Backend
•	Java
•	Spring Boot
•	Spring Data JPA
•	Spring ORM
•	Spring AOP
Microservices & Communication
•	RESTful APIs
•	Apache Kafka
Machine Learning
•	Python
•	Scikit-learn
•	Logistic Regression
Deployment
•	Docker


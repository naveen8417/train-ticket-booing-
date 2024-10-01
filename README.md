Ticket Booking System API
Project Description:
This is a Spring Boot application that simulates a ticket booking system for a train journey. Users can purchase tickets, view their booking details, modify seat allocations, and cancel bookings. The application manages seat assignments for two sections on the train (Section A and Section B).

Technologies Used:
Language: Java
Framework: Spring Boot
Database: In-memory data storage (No persistence layer)
Build Tool: Maven
JSON Processor: Jackson
Setup Instructions:
Clone this repository:
git clone <repo-url>

Navigate to the project directory:
cd ticket-booking-system

Run the application using Maven:
mvn spring-boot:run

The API will be available at http://localhost:8080/api/booking.

API Endpoints:
Here are the available API endpoints for testing:

Purchase a Ticket
Endpoint: POST /api/booking/purchase
Description: Purchases a train ticket and assigns a seat to the user.
Request Body (JSON):

json
Copy code
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com"
}
Response:

json
Copy code
{
  "from": "London",
  "to": "France",
  "user": {
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com"
  },
  "price": 20,
  "seat": "A1",
  "section": "A"
}
Get Ticket Details by Email
Endpoint: GET /api/booking/ticket/{email}
Description: Retrieves ticket details for a user by their email.
Example: http://localhost:8080/api/booking/ticket/john.doe@example.com
Response:

json
Copy code
{
  "from": "London",
  "to": "France",
  "user": {
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com"
  },
  "price": 20,
  "seat": "A1",
  "section": "A"
}
Get Users by Section
Endpoint: GET /api/booking/users/{section}
Description: Lists all users and their allocated seats in a specific section (A or B).
Example: http://localhost:8080/api/booking/users/A
Response:

json
Copy code
[
  {
    "from": "London",
    "to": "France",
    "user": {
      "firstName": "John",
      "lastName": "Doe",
      "email": "john.doe@example.com"
    },
    "price": 20,
    "seat": "A1",
    "section": "A"
  }
]
Remove a User by Email
Endpoint: DELETE /api/booking/remove/{email}
Description: Removes a user and their booking by email.
Example: http://localhost:8080/api/booking/remove/john.doe@example.com
Response: HTTP 204 No Content (if the user is removed).

Modify Seat Section
Endpoint: PUT /api/booking/modify/{email}
Description: Modifies the seat section for a user.
Parameters:

email: User’s email.
newSection: New section (A or B).
Example: http://localhost:8080/api/booking/modify/john.doe@example.com?newSection=B
Response: HTTP 200 OK (if the seat is updated).

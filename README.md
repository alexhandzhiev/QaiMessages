# QaiMessages
Technical task: 

The goal of the application is to build a dummy messaging server (chat server) which communicates over HTTP. 

The server expose one endpoint: POST /messages/{type} 

For the current example the server should support only 2 types of messages: 

• send_text (e.g. http://localhost/messages/send_text) 

• send_emotion (e.g. http://localhost/messages/send_emotion) 

This endpoint expect one mandatory parameter with name payload, which should not be passed in the URL. 

In case of send_text the payload length should be between 1 and 160 In case of send_emotion the payload should be between 2 and 10 and should not contain characters between 0 and 9 

In case the preconditions are not met the server should respond with status code 412 and empty body. 

In case the preconditions are met the server should respond with status code 201 and empty body. 

It is very important to implement the decision logic using OOP. 

Every request to this endpoint should also be stored in a database table with the following schema (id, type, payload, created_at). For dealing with the database use an ORM framework. 

We also expect to have unit tests for the application. 

When we run the test suite the database should not be populated with test data (the ORM should be mocked/stubbed). 

# Database

The database is embedded H2.

Accessed in a humanly manner at: http://localhost:8080/console

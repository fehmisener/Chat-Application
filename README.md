# Chat Application
 This program is an online chat application that can run on mobile and desktop. It is designed using Socket Programming. 
 The program consists of two parts, server and client. By establishing a connection with the client server, it is used to transfer the messages of the user to the server and at the same time to receive these messages from the server. Although unnecessary, Swing is used in the server part and JavaFx is used in the client part. Sign in is required to log in. Messages are stored on the client's device.
# How to Use
In order to connect to the client server and communicate with other clients, the server program must be run at first on YOUR SERVER ( like Google Cloud, Azure, Amazon AWS).
Before the server program runs, you need to arrange the port that the client will connect. You can find it on the serverMain class in the ChatApp_Server folder. (at line 54)
Then you need to set the connection address on the database class in the ChatApp_Client folder according to your database. For login.
Then you need to change the ip and port number that you will connect with the socket in the SecondController class in the same folder. (at line 100)
After setting up all of these, you can run the server program on the server and then open the client on the device you want and chat with the people around you.

# About Socket Programming
Socket programming is a way of connecting two nodes on a network to communicate with each other. One socket(node) listens on a particular port at an IP, while other socket reaches out to the other to form a connection. Server forms the listener socket while client reaches out to the server.

# Where is Socket Used?
A Unix Socket is used in a client-server application framework. A server is a process that performs some functions on request from a client. Most of the application-level protocols like FTP, SMTP, and POP3 make use of sockets to establish connection between client and server and then for exchanging data.

# ToDo List Manager Server
This is the server that has the options for manage a list of tasks.
It uses SOAP technology and the clients can access to this options.

Inside src/main folder there are two folders, one for each technology 
used in this project.

Folder java contains two folders. Bigws that has ToDoServices.java with
the options used for manage the list, add, remove, list, tasks. The folder
fomats contains auxiliary classes.

Folder webapp has an index.html that show three links to important information
of the project.

When a client wants to use this services, the server must be running with the 
gradle jettyEclipseRun option.
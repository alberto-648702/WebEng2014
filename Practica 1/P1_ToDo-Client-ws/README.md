# ToDo List Manager Client
This project contains a client of a ToDo list manager web application. 
The src folder has a main folder with another three, one for each technology.

Folder java contains the main client functions in Opciones.java. 
It is a servlet that connects with the main .jsp (index.jsp) and it is used
to connect with the SOAP server and use its functions. (Add or remove)

Folder webapp has the main page, index.jsp, with all the GUI and it
connects with the servlet for use the options of remove and add a task. For list
the tasks it uses a little fragment of java code using the server option of list.
Also, this folder contains another with the web.xml file.

Folder wsdl contains the neccesary specifications to connect with the SOAP server, 
all the locations and the definitions of the messages.

We can use this webapp with the gradle jettyEclipseRun option, but first the server
must be running.
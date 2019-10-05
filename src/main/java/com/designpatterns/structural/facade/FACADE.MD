# Facade design pattern

The Facade design pattern is used where we require a unified interface to a set of interfaces which represent 
different subsystems or subprocesses. This unification of subsystems or subprocesses often makes the application 
easier to use.

The Facade does not encapsulate the subsystem classes or interfaces; it just provides a simplified interface to their 
functionality. A client can access these classes directly. 
It still exposes the full functionality of the system for the clients who may need it.

## Example

Let us assume we have a server application which is used to manage various jobs. See `ScheduleServer.java` and 
`ScheduleServerImpl.java`. It 
has 
few methods to start the server and other initialization tasks and few methods to stop the server along with the 
clean-up tasks.

If we were to use this system as-is, the client application has to call each and every method individually. This is a
 tedious task and increases complexity for the client system. Wouldn't it be good if we make the client care about 
 only starting and stopping the server and not the underlying initilization and clean-up tasks?
 
In such scenarios comes the Facade design pattern. Here, we create a wrapper of the interface. See 
`ScehdulerServerFacade.java` class. Here, we have divided the functionality into two API calls - `startServer()` and 
`stopServer()` methods. These methods now take care of the underlying tasks and the client applications do not have 
to worry about these.

## Benefits

- A Facade is not just only able to simplify an interface, but it also decouples a client from a subsystem. 
- It adheres to the Principle of Least Knowledge, which avoids tight coupling between the client and the subsystem. 
- Facade provides flexibility: suppose in the above problem, the company wants to add some more steps to start or stop 
the Schedule Server, that have their own different interfaces. If you coded your client code to the facade rather than the subsystem, your client code doesn’t need to be change, 
just the facade required to be changed, that’s would be delivered with a new version to the client.
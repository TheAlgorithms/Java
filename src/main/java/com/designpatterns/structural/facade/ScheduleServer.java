package com.designpatterns.structural.facade;

/**
 * This interface defines the complex logic to start and stop a server.
 * We need to create a wrapper/facade for this server so that the client applications have a simpler way to start and
 * stop a server.
 * But this does not mean that this cannot be used directly.
 */
public interface ScheduleServer {

	// Starting the server
	String startBooting();

	String readSystemConfigFile();

	String init();

	String initializeContext();

	String initializeListeners();

	String createSystemObjects();

	// Stopping the server
	String releaseProcesses();

	String destroy();

	String destroySystemObjects();

	String destroyListeners();

	String destroyContext();

	String shutdown();
}

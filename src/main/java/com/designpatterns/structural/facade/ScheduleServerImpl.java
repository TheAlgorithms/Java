package com.designpatterns.structural.facade;

/**
 * Implementation of ScheduleServer interface.
 * This class defines the "Complex" logic of starting and stopping a server.
 */
public class ScheduleServerImpl implements ScheduleServer {

	/**************************************
	 * Kicking off Server's start process
	 **************************************/
	@Override
	public String startBooting() {
		return "Start booting";
	}

	@Override
	public String readSystemConfigFile() {
		return "Reading system config file";
	}

	@Override
	public String init() {
		return "Initializing stuff";
	}

	@Override
	public String initializeContext() {
		return "Initializing context";
	}

	@Override
	public String initializeListeners() {
		return "Initializing listeners";
	}

	@Override
	public String createSystemObjects() {
		return "Creating system objects";
	}

	/**************************************
	 * Kicking off Server's stop process
	 **************************************/
	@Override
	public String releaseProcesses() {
		return "Releasing processes";
	}

	@Override
	public String destroy() {
		return "Start destroying";
	}

	@Override
	public String destroySystemObjects() {
		return "Destroying system objects";
	}

	@Override
	public String destroyListeners() {
		return "Destroying listeners";
	}

	@Override
	public String destroyContext() {
		return "Destroying context";
	}

	@Override
	public String shutdown() {
		return "Shutting down";
	}
}

package com.designpatterns.structural.facade;

import java.util.LinkedList;
import java.util.List;

/**
 * This class is the Facade for the SchedulerServer interface.
 * By facade, we mean a relatively simpler way to access interfaces by client applications.
 * More details can be found in the FACADE.MD file
 */
public class ScheduleServerFacade {

	private final ScheduleServer scheduleServer;
	private final List<String> startEventsList;
	private final List<String> stopEventsList;

	public ScheduleServerFacade(ScheduleServer scheduleServer) {
		this.scheduleServer = scheduleServer;
		startEventsList = new LinkedList<>();
		stopEventsList = new LinkedList<>();
	}

	/**
	 * This method wraps around the complex logic of starting a server.
	 * If some client application wants to start a server, then they just have to call this method.
	 *
	 * @return List of start processes
	 */
	public List<String> startServer() {
		// Adding start events to the list
		startEventsList.add(scheduleServer.startBooting());
		startEventsList.add(scheduleServer.readSystemConfigFile());
		startEventsList.add(scheduleServer.init());
		startEventsList.add(scheduleServer.initializeContext());
		startEventsList.add(scheduleServer.initializeListeners());
		startEventsList.add(scheduleServer.createSystemObjects());

		return startEventsList;
	}

	/**
	 * This method wraps around the complex logic of stopping a server.
	 * If some client application wants to stop a server, then they just have to call this method.
	 *
	 * @return List of stop processes
	 */
	public List<String> stopServer() {
		// Adding stop events to the list
		stopEventsList.add(scheduleServer.releaseProcesses());
		stopEventsList.add(scheduleServer.destroy());
		stopEventsList.add(scheduleServer.destroySystemObjects());
		stopEventsList.add(scheduleServer.destroyListeners());
		stopEventsList.add(scheduleServer.destroyContext());
		stopEventsList.add(scheduleServer.shutdown());

		return stopEventsList;
	}
}

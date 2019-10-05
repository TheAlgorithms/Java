package com.designpatterns.structural.facade;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class ScheduleServerFacadeTest {

	private ScheduleServer scheduleServer = new ScheduleServerImpl();
	private ScheduleServerFacade facade = new ScheduleServerFacade(scheduleServer);

	@Test
	void testStartServer() {
		List<String> expectedStartServerList = Arrays.asList("Start booting", "Reading system config file", "Initializing stuff", "Initializing context", "Initializing listeners", "Creating system objects");
		Assertions.assertEquals(expectedStartServerList, facade.startServer());
	}

	@Test
	void testStopServer() {
		List<String> expectedStopServerList = Arrays.asList("Releasing processes", "Start destroying", "Destroying system objects", "Destroying listeners", "Destroying context", "Shutting down");
		Assertions.assertEquals(expectedStopServerList, facade.stopServer());
	}
}

package com.designpatterns.behavioral.observer;

/**
 * The Observer pattern is a design pattern which provides an object, called subject. This subject maintains
 * a list of dependents, called observers. This subject notifies the observers whenever there is any change
 * in it's state.
 * It is primarily used in event handling systems. In such systems, the subject is usually called a "stream of events"
 * or "stream source of events", while the observers are called "sink of events".
 * Here the observers do not have any control over the dataflow, and only accept the incoming data. It is widely
 * used in systems where there is data flow in one direction.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Observer_pattern">Observer pattern</a>
 */
public interface Observer {

	/**
	 * Updates the observer object when the subject's state has changed
	 *
	 * @param temperature is updated temperature being passed
	 * @param wind is the updated wind being passed
	 *
	 */
	void update(int temperature, int wind);
}

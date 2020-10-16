package com.designpatterns.behavioral.observer;

public interface Subject {
	/**
	 * Registers the observer in the subject's dependent list
	 * @param observer contains the observer object which needs to be registered
	 */
	void register(Observer observer);

	/**
	 * Unregisters the observer from the subject's dependent list
	 *
	 * @param observer contains the observer object which needs to be unregistered
	 */

	void unRegister(Observer observer);

	/**
	 * Notifies the observer objects about the changes in the state of the Subject
	 */
	void notifyObservers();
}

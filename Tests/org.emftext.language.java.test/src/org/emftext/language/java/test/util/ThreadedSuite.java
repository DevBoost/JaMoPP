/*******************************************************************************
 * Copyright (c) 2006-2012
 * Software Technology Group, Dresden University of Technology
 * DevBoost GmbH, Berlin, Amtsgericht Charlottenburg, HRB 140026
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   Software Technology Group - TU Dresden, Germany;
 *   DevBoost GmbH - Berlin, Germany
 *      - initial API and implementation
 ******************************************************************************/
/**
 *
 */
package org.emftext.language.java.test.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestResult;
import junit.framework.TestSuite;

/**
 * A custom TestSuite that can be used to run tests in parallel. It is
 * configurable by the number of threads to be used and the timeout after
 * which threads are interrupted.
 */
public final class ThreadedSuite extends TestSuite {

	private List<Thread> threads = new ArrayList<Thread>();
	private final int timeout;
	private final int maxActiveThreads;

	public ThreadedSuite(String name, int timeout, int maxActiveThreads) {
		super(name);
		this.timeout = timeout;
		this.maxActiveThreads = maxActiveThreads;
	}

	public void run(final TestResult result) {
		Enumeration<Test> tests = tests();

		while (tests.hasMoreElements()) {
			assert threads.size() <= maxActiveThreads;
			if (threads.size() >= maxActiveThreads) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				continue;
			}
			final Test each = tests.nextElement();
			if (result.shouldStop()) {
	  			break;
			}
			Runnable runnable = new Runnable() {
				public void run() {
					try {
						runTest(each, result);
					} catch (Exception sbue) {
						// do nothing, just end the test
					}
					threads.remove(Thread.currentThread());
				}
			};
			final Thread workerThread = new Thread(runnable, "Worker Thread");
			threads.add(workerThread);

			Thread timeoutThread = new Thread(new Runnable() {

				public void run() {
					try {
						workerThread.join(timeout);
						boolean wasStillAlive = workerThread.isAlive();
						workerThread.interrupt();
						while (workerThread.isAlive()) {
							Thread.sleep(100);
						}
						if (wasStillAlive) {
							result.addError(each, new InterruptedException("Test was interrupted by timeout."));
						}
					}
					catch (Exception e1) {
					}
					threads.remove(workerThread);
				}
			}, "Timeout Thread");

			workerThread.start();
			timeoutThread.start();
		}
		// wait for last thread to end
		while (threads.size() > 0) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.astrapi69.swing.robot;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.HeadlessException;
import java.awt.Point;

import org.junit.jupiter.api.Test;

/**
 * The unit test class for the class {@link MouseExtensions}
 */
public class MouseExtensionsTest
{

	/**
	 * Test method for {@link MouseExtensions#getMousePosition()}
	 */
	@Test
	public void testGetMousePosition()
	{
		try
		{
			Point currentMousePosition;

			currentMousePosition = MouseExtensions.getMousePosition();
			assertNotNull(currentMousePosition);
		}
		catch (HeadlessException e)
		{
			java.util.logging.Logger logger = java.util.logging.Logger
				.getLogger(this.getClass().getName());
			logger.info("Ignoring Headless exception.");
		}
	}
}

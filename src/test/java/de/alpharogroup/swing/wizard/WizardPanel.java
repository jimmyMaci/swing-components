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
package de.alpharogroup.swing.wizard;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import de.alpharogroup.design.pattern.state.wizard.WizardStateMachine;

public class WizardPanel extends JFrame
{

	private static final long serialVersionUID = 1L;

	public static void main(final String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				new WizardPanel();
			}
		});
	}

	private final WizardStateMachine stateMachine;
	private final WizardContentPanel wizardContentPanel;

	private final NavigationPanel navigationPanel;

	public WizardPanel()
	{
		setTitle("Simple Wizard");

		stateMachine = WizardStateMachine.builder().currentState(CustomState.FIRST).build();
		wizardContentPanel = newWizardContentPanel();
		navigationPanel = newNavigationPanel();
		updateButtonState();
		setSize(600, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(wizardContentPanel, BorderLayout.CENTER);
		add(navigationPanel, BorderLayout.SOUTH);
	}


	protected NavigationPanel newNavigationPanel()
	{
		final NavigationPanel navigationPanel = new NavigationPanel()
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onNext()
			{
				WizardPanel.this.onNext();
			}

			@Override
			protected void onPrevious()
			{
				WizardPanel.this.onPrevious();
			}

			@Override
			protected void onCancel()
			{
				WizardPanel.this.onCancel();
			}

			@Override
			protected void onFinish()
			{
				WizardPanel.this.onFinish();
			}
		};
		return navigationPanel;
	}

	protected WizardContentPanel newWizardContentPanel()
	{
		final WizardContentPanel cardsPanel = new WizardContentPanel();
		return cardsPanel;
	}

	protected void onCancel()
	{
		stateMachine.cancel();
		// from here application specific behavior...
		setVisible(false);
		System.exit(0);
	}

	protected void onFinish()
	{
		stateMachine.finish();
		// from here application specific behavior...
		setVisible(false);
		System.exit(0);
	}

	protected void onNext()
	{
		stateMachine.next();
		updateButtonState();
		final String name = stateMachine.getCurrentState().getName();
		final CardLayout cardLayout = wizardContentPanel.getCardLayout();
		cardLayout.show(wizardContentPanel, name);
	}

	protected void onPrevious()
	{
		stateMachine.previous();
		updateButtonState();
		final String name = stateMachine.getCurrentState().getName();
		final CardLayout cardLayout = wizardContentPanel.getCardLayout();
		cardLayout.show(wizardContentPanel, name);
	}

	protected void updateButtonState()
	{
		navigationPanel.getBtnPrevious()
			.setEnabled(stateMachine.getCurrentState().hasPrevious());
		navigationPanel.getBtnNext().setEnabled(stateMachine.getCurrentState().hasNext());
	}

}
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
package io.github.astrapi69.swing.component;

import javax.swing.JPasswordField;
import javax.swing.event.DocumentEvent;
import javax.swing.text.Document;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.api.IModel;
import io.github.astrapi69.swing.listener.document.DocumentListenerAdapter;

@Getter
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JMPasswordField extends JPasswordField
{

	/** The model. */
	IModel<char[]> propertyModel = BaseModel.of();

	{
		getDocument().addDocumentListener(new DocumentListenerAdapter()
		{
			@Override
			public void onDocumentChanged(final DocumentEvent documentEvent)
			{
				char[] password = getPassword();
				if (JMPasswordField.this.propertyModel != null)
				{
					JMPasswordField.this.propertyModel.setObject(password);
				}
				else
				{
					setPropertyModel(BaseModel.of(password));
				}
			}
		});
	}

	/**
	 * Constructs a new <code>TextField</code>.
	 *
	 * @param propertyModel
	 *            the text model to be displayed
	 */
	public JMPasswordField(final @NonNull IModel<char[]> propertyModel)
	{
		this.propertyModel = propertyModel;
	}

	/**
	 * Constructs a new <code>TextField</code>. A default model is created, the initial string is
	 * <code>null</code>, and the number of columns is set to 0.
	 */
	public JMPasswordField()
	{
	}

	/**
	 * Constructs a new <code>TextField</code> initialized with the specified text. A default model
	 * is created and the number of columns is 0.
	 *
	 * @param text
	 *            the text to be displayed, or <code>null</code>
	 */
	public JMPasswordField(String text)
	{
		super(text);
	}

	/**
	 * Constructs a new empty <code>TextField</code> with the specified number of columns. A default
	 * model is created and the initial string is set to <code>null</code>.
	 *
	 * @param columns
	 *            the number of columns to use to calculate the preferred width; if columns is set
	 *            to zero, the preferred width will be whatever naturally results from the component
	 *            implementation
	 */
	public JMPasswordField(int columns)
	{
		super(columns);
	}

	/**
	 * Constructs a new <code>TextField</code> initialized with the specified text and columns. A
	 * default model is created.
	 *
	 * @param text
	 *            the text to be displayed, or <code>null</code>
	 * @param columns
	 *            the number of columns to use to calculate the preferred width; if columns is set
	 *            to zero, the preferred width will be whatever naturally results from
	 */
	public JMPasswordField(String text, int columns)
	{
		super(text, columns);
	}

	/**
	 * Constructs a new <code>JTextField</code> that uses the given text storage model and the given
	 * number of columns. This is the constructor through which the other constructors feed. If the
	 * document is <code>null</code>, a default model is created.
	 *
	 * @param doc
	 *            the text storage to use; if this is <code>null</code>, a default will be provided
	 *            by calling the <code>createDefaultModel</code> method
	 * @param text
	 *            the initial string to display, or <code>null</code>
	 * @param columns
	 *            the number of columns to use to calculate the preferred width &gt;= 0; if
	 *            <code>columns</code> is set to zero, the preferred width will be whatever
	 *            naturally results from the component implementation
	 * @throws IllegalArgumentException
	 *             if <code>columns</code> &lt; 0
	 */
	public JMPasswordField(Document doc, String text, int columns)
	{
		super(doc, text, columns);
	}

	public JMPasswordField setPropertyModel(final @NonNull IModel<char[]> propertyModel)
	{
		this.propertyModel = propertyModel;
		char[] object = this.propertyModel.getObject();
		if (object != null && 0 < object.length)
		{
			setText(String.valueOf(object));
		}
		return this;
	}
}

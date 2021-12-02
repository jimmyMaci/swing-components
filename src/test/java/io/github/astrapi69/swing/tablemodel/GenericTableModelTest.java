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
package io.github.astrapi69.swing.tablemodel;

import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Optional;

import javax.swing.*;

import io.github.astrapi69.swing.mouse.MouseDoubleClickListener;
import io.github.astrapi69.test.instances.TestPermissionFactory;
import io.github.astrapi69.swing.x.GenericJXTable;
import io.github.astrapi69.window.adapter.CloseWindow;
import io.github.astrapi69.test.objects.Permission;

/**
 * The class GenericTableModelTest.
 */
public class GenericTableModelTest
{

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args)
	{
		newTableModelWithTableAndShow();
	}

	/**
	 * Creates the table model with table and show.
	 */
	private static void newTableModelWithTableAndShow()
	{
		// 1. Create a list with data.
		final List<Permission> permissions = TestPermissionFactory.getPermissions();
		// 2. Create a generic table model for the class Permission.
		final TestPermissionsTableModel permissionsTableModel = new TestPermissionsTableModel();
		// 3. Add the data to the model.
		permissionsTableModel.addList(permissions);
		// 4. Create the generic table and associate with the generic table model.
		final GenericJXTable<Permission> permissionTable = new GenericJXTable<>(
			permissionsTableModel);
		// ListSelectionModel selectionModel = permissionTable.getSelectionModel();
		// selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// selectionModel.addListSelectionListener(e -> {
		// Optional<Permission> singleSelectedRowData = permissionTable.getSingleSelectedRowData();
		// if(singleSelectedRowData.isPresent()) {
		// System.out.println(singleSelectedRowData.get());
		// }
		// });
		permissionTable.addMouseListener(new MouseDoubleClickListener()
		{
			public void onSingleClick(MouseEvent e)
			{
				System.out.println("single click");
			}

			public void onDoubleClick(MouseEvent e)
			{
				System.out.println("double click");
				Optional<Permission> singleSelectedRowData = permissionTable
					.getSingleSelectedRowData();
				if (singleSelectedRowData.isPresent())
				{
					System.out.println(singleSelectedRowData.get());
				}
			}
		});
		// 5. Add the table to a JScrollPane.
		final JScrollPane scrPnTblPermissions = new JScrollPane();
		scrPnTblPermissions.setViewportView(permissionTable);
		// 6. Create a Frame for displaying the table.
		final JFrame frame = new JFrame();
		frame.addWindowListener(new CloseWindow());
		// 7. Add the JScrollPane to the Frame.
		frame.add(scrPnTblPermissions);
		frame.setSize(600, 400);
		// 8. Show the Frame.
		frame.setVisible(true);
		if (!frame.isActive())
		{
			frame.toFront();
		}
	}

}

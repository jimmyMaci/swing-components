/**
 * The MIT License
 * <p>
 * Copyright (C) 2015 Asterios Raptis
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.astrapi69.swing.base;

import de.alpharogroup.lang.ClassExtensions;
import io.github.astrapi69.layout.CloseWindow;
import io.github.astrapi69.swing.components.factories.JComponentFactory;
import io.github.astrapi69.swing.img.ImageExtensions;
import io.github.astrapi69.swing.img.ImageIconFactory;
import io.github.astrapi69.swing.panels.splitpane.ApplicationTestModel;
import io.github.astrapi69.swing.panels.splitpane.JXMultiSplitPanePanel;
import io.github.astrapi69.swing.panels.splitpane.SplitFactory;
import io.github.astrapi69.throwable.RuntimeExceptionDecorator;
import org.imgscalr.Scalr;
import org.jdesktop.swingx.MultiSplitLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ApplicationPanelFrameExample
	extends ApplicationPanelFrame<ApplicationTestModel<String>>
{


	private static final long serialVersionUID = 1L;

	/**
	 * Test init layout.
	 * @param args the arguments
	 */
	public static void main(final String[] args)
	{
		final Frame frame = new ApplicationPanelFrameExample("ApplicationSplitPaneFrameExample");
		frame.addWindowListener(new CloseWindow());
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Instantiates a new {@link ApplicationSplitPaneFrame}
	 *
	 * @param title
	 *            the title
	 */
	public ApplicationPanelFrameExample(String title)
	{
		super(title);
	}

	protected JComponent newBottomComponent()
	{
		JLabel label = new JLabel("Bottom Component");
		Border border = BorderFactory.createLineBorder(Color.lightGray, 1);
		label.setBorder(border);
		return label;
	}


	@Override protected String newIconPath()
	{
		return "img/xmas/bell.png";
	}

	protected JComponent newLeftComponent()
	{
		JLabel label = new JLabel("Left Component");
		Border border = BorderFactory.createLineBorder(Color.lightGray, 1);
		label.setBorder(border);
		return label;
	}

	@SuppressWarnings("serial") @Override protected JXMultiSplitPanePanel<ApplicationTestModel<String>> newMainComponent()
	{
		final JXMultiSplitPanePanel<ApplicationTestModel<String>> multiSplitPanePanel = new JXMultiSplitPanePanel<ApplicationTestModel<String>>()
		{
			@Override protected MultiSplitLayout.Node newRootNode(String layoutDefinition)
			{
				return ApplicationPanelFrameExample.this.newRootNode();
			}
		};
		multiSplitPanePanel.getMultiSplitPane().add(newTopComponent(), "content");
		multiSplitPanePanel.getMultiSplitPane().add(newBottomComponent(), "bottom");
		multiSplitPanePanel.getMultiSplitPane().add(newLeftComponent(), "left");
		return multiSplitPanePanel;
	}

	protected MultiSplitLayout.Split newRootNode()
	{

		MultiSplitLayout.Split col1 = SplitFactory.newSplit(false, 0.75);
		MultiSplitLayout.Leaf content = SplitFactory.newLeaf("content", 0.75);
		MultiSplitLayout.Leaf bottom = SplitFactory.newLeaf("bottom", 0.25);
		col1.setChildren(content, new MultiSplitLayout.Divider(), bottom);
		MultiSplitLayout.Split root = new MultiSplitLayout.Split();
		root.setRowLayout(true);
		MultiSplitLayout.Leaf left = SplitFactory.newLeaf("left", 0.25);
		SplitFactory.setChildren(root, left, new MultiSplitLayout.Divider(), col1);
		return root;
	}

	protected JComponent newTopComponent()
	{
		JLabel label = new JLabel("Top Component");
		Border border = BorderFactory.createLineBorder(Color.lightGray, 1);
		label.setBorder(border);
		return label;
	}

	@Override protected JToolBar newJToolBar()
	{
		JToolBar toolBar = super.newJToolBar();
		toolBar.setSize(this.getWidth(), 25);
		final BufferedImage img1 = RuntimeExceptionDecorator
			.decorate(() -> ImageIO.read(ClassExtensions.getResourceAsStream("img/xmas/bell.png")));

		byte[] resized = ImageExtensions.resize(img1, "Bell", 25,
			25);
		BufferedImage result = RuntimeExceptionDecorator
			.decorate(() -> ImageExtensions.read(resized));
		ImageIcon imageIcon = ImageIconFactory.newImageIcon(result);
		JButton bellButton = JComponentFactory
			.newJButton(imageIcon, "bell", "Bell", "Bell");
		Icon directoryIcon = UIManager.getIcon("FileView.directoryIcon");
		Icon fileIcon = UIManager.getIcon("FileView.fileIcon");
		Icon computerIcon = UIManager.getIcon("FileView.computerIcon");
		Icon hardDriveIcon = UIManager.getIcon("FileView.hardDriveIcon");
		Icon floppyDriveIcon = UIManager.getIcon("FileView.floppyDriveIcon");

		Icon newFolderIcon = UIManager.getIcon("FileChooser.newFolderIcon");
		Icon upFolderIcon = UIManager.getIcon("FileChooser.upFolderIcon");
		Icon homeFolderIcon = UIManager.getIcon("FileChooser.homeFolderIcon");
		Icon detailsViewIcon = UIManager.getIcon("FileChooser.detailsViewIcon");
		Icon listViewIcon = UIManager.getIcon("FileChooser.listViewIcon");

//		toolBar.add(JComponentFactory
//			.newJButton(directoryIcon, "Dir"));
//		toolBar.add(JComponentFactory
//			.newJButton(fileIcon, "File"));
		toolBar.add(new JButton(computerIcon));
		toolBar.add(JComponentFactory
			.newJButton(hardDriveIcon));
		toolBar.add(JComponentFactory
			.newJButton(floppyDriveIcon));
		toolBar.add(JComponentFactory
			.newJButton(newFolderIcon));
		return toolBar;
	}
}
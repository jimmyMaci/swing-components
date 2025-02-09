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
package io.github.astrapi69.swing.panel.network;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EtchedBorder;

import lombok.Getter;
import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.api.IModel;
import io.github.astrapi69.swing.base.BasePanel;

/**
 * The class NetworkSettingsPanel.
 */
@Getter
public class NetworkSettingsPanel extends BasePanel<NetworkSettingsModelBean>
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1185900387188091319L;

	/** The ButtonGroup proxy settings. */
	private ButtonGroup btngrpProxySettings;

	/** The CheckBox socks proxy. */
	private JCheckBox chckbxSocksProxy;

	/** The Label host or ipaddress. */
	private JLabel lblHostOrIpaddress;

	/** The Label port. */
	private JLabel lblPort;

	/** The LayeredPane for the host or ipaddress. */
	private JLayeredPane lpHostOrIpaddress;

	/** The LayeredPane for no proxy. */
	private JLayeredPane lpNoProxy;

	/** The LayeredPane for use system settings. */
	private JLayeredPane lpUseSystemSettings;

	/** The RadioButton manual proxy configuration. */
	private JRadioButton rdbtnManualProxyConfiguration;

	/** The RadioButton no proxy. */
	private JRadioButton rdbtnNoProxy;

	/** The RadioButton use system settings. */
	private JRadioButton rdbtnUseSystemSettings;

	/** The TextField host or ipaddress. */
	private JTextField txtHostOrIpaddress;

	/** The TextField port. */
	private JTextField txtPort;

	/**
	 * Create a new instance from the NetworkSettingsPanel.
	 */
	public NetworkSettingsPanel()
	{
		this(BaseModel.of(NetworkSettingsModelBean.builder().build()));
	}


	public NetworkSettingsPanel(final IModel<NetworkSettingsModelBean> model)
	{
		super(model);
	}

	/**
	 * Initialize actions.
	 */
	public void initializeActions()
	{

	}

	/**
	 * Initialize components.
	 */
	@Override
	protected void onInitializeComponents()
	{
		lpNoProxy = new JLayeredPane();
		lpNoProxy.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));

		lpHostOrIpaddress = new JLayeredPane();
		lpHostOrIpaddress.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));

		rdbtnManualProxyConfiguration = new JRadioButton("Manual proxy configuration");
		rdbtnManualProxyConfiguration.setBounds(6, 7, 313, 23);
		lpHostOrIpaddress.add(rdbtnManualProxyConfiguration);

		lblHostOrIpaddress = new JLabel("Host or IP-address:");
		lblHostOrIpaddress.setBounds(26, 37, 109, 14);
		lpHostOrIpaddress.add(lblHostOrIpaddress);

		txtHostOrIpaddress = new JTextField();
		txtHostOrIpaddress.setBounds(145, 37, 213, 20);
		lpHostOrIpaddress.add(txtHostOrIpaddress);
		txtHostOrIpaddress.setColumns(10);

		lblPort = new JLabel("Port:");
		lblPort.setBounds(368, 40, 46, 14);
		lpHostOrIpaddress.add(lblPort);

		txtPort = new JTextField();
		txtPort.setBounds(408, 37, 67, 20);
		lpHostOrIpaddress.add(txtPort);
		txtPort.setColumns(10);

		rdbtnNoProxy = new JRadioButton("Direct connection to internet (no proxy)");
		rdbtnNoProxy.setBounds(6, 7, 305, 23);

		lpNoProxy.add(rdbtnNoProxy);

		lpUseSystemSettings = new JLayeredPane();
		lpUseSystemSettings.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));

		rdbtnUseSystemSettings = new JRadioButton("Use proxy settings from system");
		rdbtnUseSystemSettings.setBounds(6, 7, 305, 23);
		lpUseSystemSettings.add(rdbtnUseSystemSettings);

		// Group the radio buttons.
		btngrpProxySettings = new ButtonGroup();
		btngrpProxySettings.add(rdbtnNoProxy);
		btngrpProxySettings.add(rdbtnManualProxyConfiguration);

		chckbxSocksProxy = new JCheckBox("SOCKS-Proxy?");
		chckbxSocksProxy.setToolTipText("Is it a SOCKS-Proxy?");
		chckbxSocksProxy.setBounds(26, 63, 97, 23);
		lpHostOrIpaddress.add(chckbxSocksProxy);
		btngrpProxySettings.add(rdbtnUseSystemSettings);
	}

	/**
	 * Initialize layout.
	 */
	@Override
	protected void onInitializeLayout()
	{
		final GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
			.addGroup(groupLayout.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
					.addComponent(lpHostOrIpaddress, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
						501, Short.MAX_VALUE)
					.addComponent(lpNoProxy, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 501,
						Short.MAX_VALUE)
					.addComponent(lpUseSystemSettings, GroupLayout.PREFERRED_SIZE, 501,
						GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
			.addGroup(groupLayout.createSequentialGroup().addContainerGap()
				.addComponent(lpNoProxy, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
				.addGap(18)
				.addComponent(lpHostOrIpaddress, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
				.addGap(18).addComponent(lpUseSystemSettings, GroupLayout.PREFERRED_SIZE, 42,
					GroupLayout.PREFERRED_SIZE)
				.addGap(29)));
		setLayout(groupLayout);
	}
}

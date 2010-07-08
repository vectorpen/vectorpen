/*
 * Copyright (c) 2007-2010, Clemens Akens and Oleg Slobodskoi.
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package com.vectorpen.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.vectorpen.core.PDFModule;

@SuppressWarnings("serial")
public final class UIAboutDialog extends JDialog implements MouseListener
{
	private static final URL IMAGE_PATH_BACKGROUND_CENTER;
	private static final URL IMAGE_PATH_BACKGROUND_LEFT;
	private static final URL IMAGE_PATH_BACKGROUND_RIGHT;
	private static final URL IMAGE_PATH_LOGO;

	static
	{
		IMAGE_PATH_BACKGROUND_CENTER = UIAboutDialog.class.getResource("/com/vectorpen/img/About-Dialog-Background-Center.png");
		IMAGE_PATH_BACKGROUND_LEFT = UIAboutDialog.class.getResource("/com/vectorpen/img/About-Dialog-Background-Left.png");
		IMAGE_PATH_BACKGROUND_RIGHT = UIAboutDialog.class.getResource("/com/vectorpen/img/About-Dialog-Background-Right.png");
		IMAGE_PATH_LOGO = UIAboutDialog.class.getResource("/com/vectorpen/img/About-Dialog-Logo.png");
	}

	private static final int WIDTH = 600;
	private static final int HEIGHT = 400;
	private static final Color COLOR_LABEL_SMALL = Color.BLACK;
	private static final Color COLOR_LABEL_VERSION = new Color(253, 182, 74);
	private static final Color COLOR_LABEL_SYSTEM_INFO = Color.WHITE;

	private String textAbout;
	private String textLabelContact;
	private String textLabelCopyright;
	private String textSystemInfo;
	private ImageIcon imageBackgroundCenter;
	private ImageIcon imageBackgroundLeft;
	private ImageIcon imageBackgroundRight;
	private ImageIcon imageLogo;
	private JPanel backgroundPanel;

	public UIAboutDialog()
	{
		getLocalizedStrings();

		imageBackgroundCenter = new ImageIcon(IMAGE_PATH_BACKGROUND_CENTER);
		imageBackgroundLeft = new ImageIcon(IMAGE_PATH_BACKGROUND_LEFT);
		imageBackgroundRight = new ImageIcon(IMAGE_PATH_BACKGROUND_RIGHT);
		imageLogo = new ImageIcon(IMAGE_PATH_LOGO);

		setTitle(textAbout);
		setUndecorated(true);
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setModal(true);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLayout(null);
		addMouseListener(this);

		backgroundPanel = new JPanel()
		{
			public void paintComponent(Graphics graphics)
			{
				super.paintComponent(graphics);

				int leftWidth = imageBackgroundLeft.getIconWidth();
				int rightWidth = imageBackgroundRight.getIconWidth();

				graphics.drawImage(imageBackgroundCenter.getImage(), leftWidth, 0, getWidth() - (rightWidth * 2), getHeight(), this);
				graphics.drawImage(imageBackgroundLeft.getImage(), 0, 0, leftWidth, getHeight(), this);
				graphics.drawImage(imageBackgroundRight.getImage(), getWidth() - rightWidth, 0, rightWidth, getHeight(), this);
				graphics.drawImage(imageLogo.getImage(), 25, 16, imageLogo.getIconWidth(), imageLogo.getIconHeight(), this);
			}
		};

		backgroundPanel.setBounds(0, 0, WIDTH, HEIGHT);
		backgroundPanel.setLayout(null);
		add(backgroundPanel);

		createLabels();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 155, WIDTH - 50, 170);

		backgroundPanel.add(scrollPane);

		scrollPane.setCorner(JScrollPane.LOWER_RIGHT_CORNER, new JScrollBar());

		JTextArea textArea = new JTextArea(readGPL());

		scrollPane.setViewportView(textArea);

		textArea.setBackground(Color.WHITE);
		textArea.setLineWrap(true);

		setLocationRelativeTo(null);
		setVisible(true);
	}

	private String readGPL()
	{
		StringBuilder contents = new StringBuilder();

		InputStream is = getClass().getResourceAsStream("/gpl.txt");
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line = null;

		try {
			while (( line = br.readLine()) != null)
			{
				contents.append(line);
				contents.append(System.getProperty("line.separator"));
			}

			br.close();
			isr.close();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return contents.toString();
	}

	private void getLocalizedStrings()
	{
		ResourceBundle bundle = ResourceBundle.getBundle("Localizable");

		textAbout = bundle.getString("Actions.Text.About");
		textLabelContact = "<html><font style=\"color: #9e0000\">vectorpen@googlemail.com</font><font style=\"color: #000000\"> | </font><font style=\"color: #9e0000\">http://vectorpen.com</font></html>";
		textLabelCopyright = "Copyright (c) 2007-2010 Clemens Akens and Oleg Slobodskoi";

		String totalMemory = Integer.toString((int)(Runtime.getRuntime().totalMemory() / 1024));
		String maxMemory = Integer.toString((int)(Runtime.getRuntime().maxMemory() / 1024));

		textSystemInfo = System.getProperty("os.arch") + "_" + System.getProperty("os.name") + "_" + totalMemory + "kB" + "_" + maxMemory + "kB";
	}

	private void createLabels()
	{
		int x = 0;
		int y = 0;
		int width = 0;
		int height = 0;

		JLabel version = new JLabel();
		version.setText(PDFModule.PRODUCER + " 1.0");
		version.setForeground(COLOR_LABEL_VERSION);
		version.setFont(new Font(null, Font.BOLD, 20));
		version.setHorizontalAlignment(SwingConstants.LEFT);
		version.setVerticalAlignment(SwingConstants.TOP);

		width = (int)version.getPreferredSize().getWidth();
		height = (int)version.getPreferredSize().getHeight();
		x = WIDTH - width - 25;
		y = 75;

		version.setBounds(x, y, width, height);
		backgroundPanel.add(version);

		JLabel systemInfo = new JLabel();
		systemInfo.setText(textSystemInfo);
		systemInfo.setForeground(COLOR_LABEL_SYSTEM_INFO);
		systemInfo.setFont(new Font(null, Font.PLAIN, 8));
		systemInfo.setHorizontalAlignment(SwingConstants.LEFT);
		systemInfo.setVerticalAlignment(SwingConstants.TOP);

		width = (int)systemInfo.getPreferredSize().getWidth();
		height = (int)systemInfo.getPreferredSize().getHeight();
		x = getWidth() - width - 1;
		y = 1;

		systemInfo.setBounds(x, y, width, height);
		backgroundPanel.add(systemInfo);

		JLabel copyright = new JLabel();
		copyright.setText(textLabelCopyright);
		copyright.setForeground(COLOR_LABEL_SMALL);
		copyright.setFont(new Font(null, Font.PLAIN, 14));
		copyright.setHorizontalAlignment(SwingConstants.LEFT);
		copyright.setVerticalAlignment(SwingConstants.TOP);

		width = (int)copyright.getPreferredSize().getWidth();
		height = (int)copyright.getPreferredSize().getHeight();
		x = (getWidth() - width) / 2;
		y = getHeight() - height - 9;

		copyright.setBounds(x, y, width, height);
		backgroundPanel.add(copyright);

		JLabel contact = new JLabel();
		contact.setText(textLabelContact);
		contact.setForeground(COLOR_LABEL_SMALL);
		contact.setFont(new Font(null, Font.PLAIN, 14));
		contact.setHorizontalAlignment(SwingConstants.LEFT);
		contact.setVerticalAlignment(SwingConstants.TOP);

		width = (int)contact.getPreferredSize().getWidth();
		height = (int)contact.getPreferredSize().getHeight();
		x = (getWidth() - width) / 2;
		y = getHeight() - height - 18 - copyright.getHeight();

		contact.setBounds(x, y, width, height);
		backgroundPanel.add(contact);
	}

	public void mousePressed(MouseEvent event)
	{
	}

	public void mouseReleased(MouseEvent event)
	{
	}

	public void mouseEntered(MouseEvent event)
	{
	}

	public void mouseExited(MouseEvent event)
	{
	}

	public void mouseClicked(MouseEvent event)
	{
		dispose();
	}
}
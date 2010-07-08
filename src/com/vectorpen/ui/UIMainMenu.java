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

import java.util.ResourceBundle;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.vectorpen.core.VectorFile;

@SuppressWarnings("serial")
public final class UIMainMenu extends JMenuBar
{
	private static UIMainMenu instance;

	private String textFileMac;
	private String textFileWin;
	private String textViewMac;
	private String textViewWin;
	private String textHelp;
	private JMenuItem addMenuItem;
	private JMenuItem removeMenuItem;
	private JMenuItem convertMenuItem;
	private JMenuItem exitMenuItem;
	private JMenuItem rotateLeftMenuItem;
	private JMenuItem rotateRightMenuItem;
	private JMenuItem zoomInMenuItem;
	private JMenuItem zoomOutMenuItem;
	private JMenuItem actualSizeMenuItem;
	private JMenuItem helpMenuItem;
	private JMenuItem aboutMenuItem;

	public static synchronized UIMainMenu getInstance()
	{
		if (instance == null)
			instance = new UIMainMenu();

		return instance;
	}

	private UIMainMenu()
	{
		getLocalizedStrings();

		add(createFileMenu());
		add(createViewMenu());
		add(createHelpMenu());
	}

	private void getLocalizedStrings()
	{
		ResourceBundle bundle = ResourceBundle.getBundle("Localizable");

		textFileMac = bundle.getString("MainMenu.Text.FileMac");
		textFileWin = bundle.getString("MainMenu.Text.FileWin");
		textViewMac = bundle.getString("MainMenu.Text.ViewMac");
		textViewWin = bundle.getString("MainMenu.Text.ViewWin");
		textHelp = bundle.getString("MainMenu.Text.Help");
	}

	private JMenu createFileMenu()
	{
		if (Environment.isMac())
		{
			JMenu fileMenu = new JMenu(textFileMac);

			addMenuItem = new JMenuItem(Actions.getInstance().getAdd());
			removeMenuItem = new JMenuItem(Actions.getInstance().getRemove());
			convertMenuItem = new JMenuItem(Actions.getInstance().getConvert());

			fileMenu.add(addMenuItem);
			fileMenu.add(removeMenuItem);
			fileMenu.addSeparator();
			fileMenu.add(convertMenuItem);

			addMenuItem.setEnabled(true);
			removeMenuItem.setEnabled(false);
			convertMenuItem.setEnabled(false);

			return fileMenu;
		}
		else
		{
			JMenu fileMenu = new JMenu(textFileWin);

			addMenuItem = new JMenuItem(Actions.getInstance().getAdd());
			removeMenuItem = new JMenuItem(Actions.getInstance().getRemove());
			convertMenuItem = new JMenuItem(Actions.getInstance().getConvert());
			exitMenuItem = new JMenuItem(Actions.getInstance().getExit());

			fileMenu.add(addMenuItem);
			fileMenu.add(removeMenuItem);
			fileMenu.addSeparator();
			fileMenu.add(convertMenuItem);
			fileMenu.addSeparator();
			fileMenu.add(exitMenuItem);

			addMenuItem.setEnabled(true);
			removeMenuItem.setEnabled(false);
			convertMenuItem.setEnabled(false);
			exitMenuItem.setEnabled(true);

			return fileMenu;
		}
	}

	private JMenu createViewMenu()
	{
		JMenu viewMenu;

		if (Environment.isMac())
		{
			viewMenu = new JMenu(textViewMac);
		}
		else
		{
			viewMenu = new JMenu(textViewWin);
		}

		rotateLeftMenuItem = new JMenuItem(Actions.getInstance().getRotateLeft());
		rotateRightMenuItem = new JMenuItem(Actions.getInstance().getRotateRight());
		zoomInMenuItem = new JMenuItem(Actions.getInstance().getZoomIn());
		zoomOutMenuItem = new JMenuItem(Actions.getInstance().getZoomOut());
		actualSizeMenuItem = new JMenuItem(Actions.getInstance().getActualSize());

		viewMenu.add(rotateLeftMenuItem);
		viewMenu.add(rotateRightMenuItem);
		viewMenu.addSeparator();
		viewMenu.add(zoomInMenuItem);
		viewMenu.add(zoomOutMenuItem);
		viewMenu.add(actualSizeMenuItem);

		rotateLeftMenuItem.setEnabled(false);
		rotateRightMenuItem.setEnabled(false);
		zoomInMenuItem.setEnabled(false);
		zoomOutMenuItem.setEnabled(false);
		actualSizeMenuItem.setEnabled(false);

		return viewMenu;
	}

	/*
	private JMenu createHelpMenu()
	{
		if (Environment.isMac())
	    {
			JMenu helpMenu = new JMenu(textHelp);

			helpMenuItem = new JMenuItem(Actions.getInstance().getHelp());

			helpMenu.add(helpMenuItem);

			helpMenuItem.setEnabled(false);

			return helpMenu;
	    }
		else
		{
			JMenu helpMenu = new JMenu(textHelp);

			helpMenuItem = new JMenuItem(Actions.getInstance().getHelp());
			aboutMenuItem = new JMenuItem(Actions.getInstance().getAbout());

			helpMenu.add(helpMenuItem);
			helpMenu.addSeparator();
			helpMenu.add(aboutMenuItem);

			helpMenuItem.setEnabled(false);
			aboutMenuItem.setEnabled(true);

			return helpMenu;
		}
	}
	 */

	private JMenu createHelpMenu()
	{
		JMenu helpMenu = new JMenu(textHelp);

		helpMenuItem = new JMenuItem(Actions.getInstance().getHelp());
		aboutMenuItem = new JMenuItem(Actions.getInstance().getAbout());

		helpMenu.add(helpMenuItem);
		helpMenu.addSeparator();
		helpMenu.add(aboutMenuItem);

		helpMenuItem.setEnabled(false);
		aboutMenuItem.setEnabled(true);

		return helpMenu;
	}

	public void updateMenuItems()
	{
		int selectedRow = UIMainTable.getInstance().getSelectedRow();

		if (selectedRow != -1)
		{
			removeMenuItem.setEnabled(true);
			rotateLeftMenuItem.setEnabled(true);
			rotateRightMenuItem.setEnabled(true);

			int zoom = VectorFiles.getInstance().getZoom(selectedRow);

			if (zoom < VectorFile.ZOOM_MAX)
			{
				zoomInMenuItem.setEnabled(true);
			}
			else
			{
				zoomInMenuItem.setEnabled(false);
			}

			if (zoom > VectorFile.ZOOM_MIN)
			{
				zoomOutMenuItem.setEnabled(true);
			}
			else
			{
				zoomOutMenuItem.setEnabled(false);
			}

			if (zoom != VectorFile.ZOOM_ACTUAL_SIZE)
			{
				actualSizeMenuItem.setEnabled(true);
			}
			else
			{
				actualSizeMenuItem.setEnabled(false);
			}

			convertMenuItem.setEnabled(true);
		}
		else
		{
			removeMenuItem.setEnabled(false);
			rotateLeftMenuItem.setEnabled(false);		
			rotateRightMenuItem.setEnabled(false);
			zoomInMenuItem.setEnabled(false);
			zoomOutMenuItem.setEnabled(false);
			actualSizeMenuItem.setEnabled(false);
			convertMenuItem.setEnabled(false);
		}
	}
}
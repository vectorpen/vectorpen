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

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;

import com.vectorpen.core.FileInput;
import com.vectorpen.core.VectorFile;

@SuppressWarnings("serial")
public final class Actions
{
	private static Actions instance;

	private String textAdd;
	private String textRemove;
	private String textRotateLeft;
	private String textRotateRight;
	private String textZoomIn;
	private String textZoomActualSize;
	private String textZoomOut;
	private String textConvert;
	private String textExit;
	private String textAbout;
	private String textHelp;
	private Action add;
	private Action remove;
	private Action rotateLeft;
	private Action rotateRight;
	private Action zoomIn;
	private Action actualSize;
	private Action zoomOut;
	private Action convert;
	private Action exit;
	private Action about;
	private Action help;

	public static synchronized Actions getInstance()
	{
		if (instance == null)
			instance = new Actions();

		return instance;
	}

	private Actions()
	{
		getLocalizedStrings();

		int shortcutKeyMask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

		KeyStroke addKey = KeyStroke.getKeyStroke(KeyEvent.VK_O, shortcutKeyMask);
		KeyStroke removeKey = KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, shortcutKeyMask);
		KeyStroke rotateLeftKey = KeyStroke.getKeyStroke(KeyEvent.VK_L, shortcutKeyMask);
		KeyStroke rotateRightKey = KeyStroke.getKeyStroke(KeyEvent.VK_R, shortcutKeyMask);
		KeyStroke zoomInKey = KeyStroke.getKeyStroke(KeyEvent.VK_1, shortcutKeyMask);
		KeyStroke actualSizeKey = KeyStroke.getKeyStroke(KeyEvent.VK_0, shortcutKeyMask);
		KeyStroke zoomOutKey = KeyStroke.getKeyStroke(KeyEvent.VK_2, shortcutKeyMask);
		KeyStroke convertKey = KeyStroke.getKeyStroke(KeyEvent.VK_S, shortcutKeyMask);

		add = new Add(textAdd, addKey);
		remove = new Remove(textRemove, removeKey);
		rotateLeft = new RotateLeft(textRotateLeft, rotateLeftKey);
		rotateRight = new RotateRight(textRotateRight, rotateRightKey);
		zoomIn = new ZoomIn(textZoomIn, zoomInKey);
		actualSize = new ActualSize(textZoomActualSize, actualSizeKey);
		zoomOut = new ZoomOut(textZoomOut, zoomOutKey);
		convert = new Convert(textConvert, convertKey);
		exit = new Exit(textExit);
		about = new About(textAbout);
		help = new Help(textHelp);
	}

	private void getLocalizedStrings()
	{
		ResourceBundle bundle = ResourceBundle.getBundle("Localizable");

		textAdd = bundle.getString("Actions.Text.Add");
		textRemove = bundle.getString("Actions.Text.Remove");
		textRotateLeft = bundle.getString("Actions.Text.RotateLeft");
		textRotateRight = bundle.getString("Actions.Text.RotateRight");
		textZoomIn = bundle.getString("Actions.Text.ZoomIn");
		textZoomActualSize = bundle.getString("Actions.Text.ZoomActualSize");
		textZoomOut = bundle.getString("Actions.Text.ZoomOut");
		textConvert = bundle.getString("Actions.Text.Convert");
		textExit = bundle.getString("Actions.Text.Exit");
		textAbout = bundle.getString("Actions.Text.About");
		textHelp = bundle.getString("Actions.Text.Help");
	}

	public Action getAdd()
	{
		return add;
	}

	public Action getRemove()
	{
		return remove;
	}

	public Action getRotateLeft()
	{
		return rotateLeft;
	}

	public Action getRotateRight()
	{
		return rotateRight;
	}

	public Action getZoomIn()
	{
		return zoomIn;
	}

	public Action getActualSize()
	{
		return actualSize;
	}

	public Action getZoomOut()
	{
		return zoomOut;
	}

	public Action getConvert()
	{
		return convert;
	}

	public Action getExit()
	{
		return exit;
	}

	public Action getAbout()
	{
		return about;
	}

	public Action getHelp()
	{
		return help;
	}

	private class Add extends AbstractAction
	{
		public Add(String text, KeyStroke shortcut)
		{
			super(text);

			putValue(ACCELERATOR_KEY, shortcut);
		}

		public void actionPerformed(ActionEvent event)
		{
			System.gc();

			int returnValue = UIFileChooser.getInstance().showOpenDialog(null);

			if (returnValue == JFileChooser.APPROVE_OPTION)
			{
				File files[] = UIFileChooser.getInstance().getSelectedFiles();

				int count = files.length;

				ArrayList<VectorFile> vectorFiles = new ArrayList<VectorFile>();

				for (int index = 0; index < count; index++)
				{
					try
					{
						vectorFiles.add(FileInput.readFile(files[index]));
					}
					catch (Exception exception)
					{
						UIExceptionDialog.showDialog(exception);
					}
				}

				if (vectorFiles.size() > 0)
					VectorFiles.getInstance().add(vectorFiles);
			}

			UIMainTable.getInstance().repaint();
			UIMainTable.getInstance().revalidate();
		}
	}

	private class Remove extends AbstractAction
	{
		public Remove(String text, KeyStroke shortcut)
		{
			super(text);

			putValue(ACCELERATOR_KEY, shortcut);
		}

		public void actionPerformed(ActionEvent event)
		{
			VectorFiles.getInstance().remove();

			System.gc();

			UIMainTable.getInstance().repaint();
			UIMainTable.getInstance().revalidate();
		}
	}

	private class RotateLeft extends AbstractAction
	{
		public RotateLeft(String text, KeyStroke shortcut)
		{
			super(text);

			putValue(ACCELERATOR_KEY, shortcut);
		}

		public void actionPerformed(ActionEvent event)
		{	
			VectorFiles.getInstance().rotateByAngle(-90);

			System.gc();

			UIMainTable.getInstance().repaint();
			UIMainTable.getInstance().revalidate();
		}
	}

	private class RotateRight extends AbstractAction
	{
		public RotateRight(String text, KeyStroke shortcut)
		{
			super(text);

			putValue(ACCELERATOR_KEY, shortcut);
		}

		public void actionPerformed(ActionEvent event)
		{
			VectorFiles.getInstance().rotateByAngle(90);

			System.gc();

			UIMainTable.getInstance().repaint();
			UIMainTable.getInstance().revalidate();
		}
	}

	private class ZoomIn extends AbstractAction
	{
		public ZoomIn(String text, KeyStroke shortcut)
		{
			super(text);

			putValue(ACCELERATOR_KEY, shortcut);
		}

		public void actionPerformed(ActionEvent event)
		{
			System.gc();

			VectorFiles.getInstance().zoomIn();
		}
	}

	private class ActualSize extends AbstractAction
	{
		public ActualSize(String text, KeyStroke shortcut)
		{
			super(text);

			putValue(ACCELERATOR_KEY, shortcut);
		}

		public void actionPerformed(ActionEvent event)
		{
			System.gc();

			VectorFiles.getInstance().zoomActualSize();
		}
	}

	private class ZoomOut extends AbstractAction
	{
		public ZoomOut(String text, KeyStroke shortcut)
		{
			super(text);

			putValue(ACCELERATOR_KEY, shortcut);
		}

		public void actionPerformed(ActionEvent event)
		{
			System.gc();

			VectorFiles.getInstance().zoomOut();
		}
	}

	private class Convert extends AbstractAction
	{
		public Convert(String text, KeyStroke shortcut)
		{
			super(text);

			putValue(ACCELERATOR_KEY, shortcut);
		}

		public void actionPerformed(ActionEvent event)
		{
			System.gc();

			UIConvertDialog.getInstance().open();
		}
	}

	private class Exit extends AbstractAction
	{
		public Exit(String text)
		{
			super(text);
		}

		public void actionPerformed(ActionEvent event)
		{
			System.exit(0);
		}
	}

	private class About extends AbstractAction
	{
		public About(String text)
		{
			super(text);
		}

		public void actionPerformed(ActionEvent event)
		{
			new UIAboutDialog();
		}
	}

	private class Help extends AbstractAction
	{
		public Help(String text)
		{
			super(text);
		}

		public void actionPerformed(ActionEvent event)
		{
		}
	}
}
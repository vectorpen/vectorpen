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

import java.io.File;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

@SuppressWarnings("serial")
public final class UIFileChooser extends JFileChooser
{
	private static UIFileChooser instance;

	private String titleOpenDialog;
	private String textFileFilter;

	public static synchronized UIFileChooser getInstance()
	{
		if (instance == null)
			instance = new UIFileChooser();

		return instance;
	}

	private UIFileChooser()
	{
		getLocalizedStrings();

		setDialogTitle(titleOpenDialog);
		setCurrentDirectory(null);
		setMultiSelectionEnabled(true);
		setAcceptAllFileFilterUsed(false);

		String fileExtensions[] = {"dhw", "top", "dnt"};

		MyFileFilter fileFilter = new MyFileFilter(fileExtensions, textFileFilter);

		addChoosableFileFilter(fileFilter);
	}

	public void getLocalizedStrings()
	{
		ResourceBundle bundle = ResourceBundle.getBundle("Localizable");

		titleOpenDialog = bundle.getString("FileChooser.Title.OpenDialog");
		textFileFilter = bundle.getString("FileChooser.Text.FileFilter");
	}

	private final class MyFileFilter extends FileFilter
	{
		private String[] extensions;
		private String description;

		public MyFileFilter(String[] extensions, String description)
		{
			this.extensions = extensions;
			this.description = description;
		}

		public boolean accept(File file)
		{
			if (file.isDirectory()) return true;

			String fileName = file.getName().toLowerCase();

			int count = extensions.length;

			for (int index = 0; index < count; index++)
			{
				if (fileName.endsWith("." + extensions[index]))
					return true;
			}

			return false;
		}

		public String getDescription()
		{
			return description;
		}
	}
}
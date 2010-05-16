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
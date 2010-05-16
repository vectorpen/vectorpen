package com.vectorpen.ui;

import java.io.File;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

public final class FilePath
{
	private static final String EM1;
	private static final String EMD_TITLE;

	static
	{
		ResourceBundle bundle = ResourceBundle.getBundle("Localizable");

		EM1 = bundle.getString("FilePath.EM1");
		EMD_TITLE = bundle.getString("FilePath.EMD_TITLE");
	}

	public static boolean check(String filePath, boolean errorMessage)
	{
		if (filePath == null)
			return false;

		File file = new File(filePath);

		boolean result = true;
		result = file.isDirectory();
		result = file.canWrite();

		if (errorMessage && !result)
			JOptionPane.showMessageDialog(UIMainWindow.getInstance(), EM1, EMD_TITLE, JOptionPane.ERROR_MESSAGE);

		return result;
	}

	public static String createAbsolutePath(String directory, String name, String extension)
	{
		String absolutePath = null;
		File file = null;

		int i = 0;

		boolean valid = false;

		while (!valid)
		{
			file = new File(directory);

			if (i == 0)
			{
				absolutePath = String.format("%s/%s.%s", file.getAbsolutePath(), name, extension);
			}
			else
			{
				absolutePath = String.format("%s/%s%d.%s", file.getAbsolutePath(), name, i, extension);
			}

			file = new File(absolutePath);

			valid = !file.exists();

			i++;
		}

		return absolutePath;
	}
}
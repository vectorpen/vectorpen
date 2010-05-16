package com.vectorpen.ui;

import java.util.ResourceBundle;

import javax.swing.JOptionPane;

public final class FileName
{
	private static final String EM1;
	private static final String EM2;
	private static final String EM3;
	private static final String EM4;
	private static final String EMD_TITLE;

	static
	{
		ResourceBundle bundle = ResourceBundle.getBundle("Localizable");

		EM1 = bundle.getString("FileName.EM1");
		EM2 = bundle.getString("FileName.EM2");
		EM3 = bundle.getString("FileName.EM3");
		EM4 = bundle.getString("FileName.EM4");
		EMD_TITLE = bundle.getString("FileName.EMD_TITLE");
	}

	private FileName() {}

	public static boolean check(String fileName, boolean errorMessage)
	{
		if (fileName.length() == 0)
			return false;

		if (Environment.isMac())
		{
			return mac(fileName, errorMessage);
		}
		else if (Environment.isWindows())
		{
			return windows(fileName, errorMessage);
		}
		else
		{
			return linux(fileName, errorMessage);
		}
	}

	private static boolean mac(String fileName, boolean errorMessage)
	{
		boolean result = true;

		if (fileName.contains("/") ||
				fileName.contains(":"))
		{
			if (errorMessage)
				JOptionPane.showMessageDialog(UIMainWindow.getInstance(), EM1, EMD_TITLE, JOptionPane.ERROR_MESSAGE);

			result = false;
		}
		else if (fileName.startsWith("."))
		{
			if (errorMessage)
				JOptionPane.showMessageDialog(UIMainWindow.getInstance(), EM2, EMD_TITLE, JOptionPane.ERROR_MESSAGE);

			result = false;
		}

		return result;
	}

	private static boolean windows(String fileName, boolean errorMessage)
	{
		boolean result = true;

		if (fileName.contains("/") ||
				fileName.contains("/") ||
				fileName.contains("\"") ||
				fileName.contains("*") ||
				fileName.contains(":") ||
				fileName.contains("<") ||
				fileName.contains(">") ||
				fileName.contains("?") ||
				fileName.contains("\\") ||
				fileName.contains("|"))
		{
			result = false;

			if (errorMessage)
				JOptionPane.showMessageDialog(UIMainWindow.getInstance(), EM3, EMD_TITLE, JOptionPane.ERROR_MESSAGE);
		}

		return result;
	}

	private static boolean linux(String fileName, boolean errorMessage)
	{
		boolean result = true;

		if (fileName.contains("/"))
		{
			result = false;

			if (errorMessage)
				JOptionPane.showMessageDialog(UIMainWindow.getInstance(), EM4, EMD_TITLE, JOptionPane.ERROR_MESSAGE);
		}

		return result;
	}
}
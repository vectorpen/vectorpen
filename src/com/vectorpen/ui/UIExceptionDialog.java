package com.vectorpen.ui;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.zip.DataFormatException;

import javax.swing.JOptionPane;

public final class UIExceptionDialog
{
	public static void showDialog(Exception exception)
	{
		ResourceBundle bundle = ResourceBundle.getBundle("Localizable");

		String titleExceptionDialog = bundle.getString("ExceptionEngine.Title.ExceptionDialog");
		String exceptionDataFormat = bundle.getString("ExceptionEngine.Exception.DataFormat");
		String exceptionIO = bundle.getString("ExceptionEngine.Exception.IO");
		String exceptionUnknown = bundle.getString("ExceptionEngine.Exception.Unknown");
		String exceptionNormal = bundle.getString("ExceptionEngine.Exception");

		String totalMemory = Integer.toString((int)(Runtime.getRuntime().totalMemory() / 1024));
		String maxMemory = Integer.toString((int)(Runtime.getRuntime().maxMemory() / 1024));

		String systemInfo = System.getProperty("os.arch") + "_" + System.getProperty("os.name") + "_" + totalMemory + "kB" + "_" + maxMemory + "kB";
		String message;

		if (exception.getClass() == IOException.class && exception.getMessage() != null)
		{
			message = String.format(exceptionIO, exception.getMessage(), systemInfo);
		}
		else if (exception.getClass() == DataFormatException.class && exception.getMessage() != null)
		{
			message = String.format(exceptionDataFormat, exception.getMessage(), systemInfo);
		}
		else
		{
			if (exception.getMessage() != null)
			{
				message = String.format(exceptionNormal, exception.getMessage(), systemInfo);
			}
			else
			{
				message = String.format(exceptionUnknown, systemInfo);
			}
		}

		JOptionPane.showMessageDialog(UIMainWindow.getInstance(), message, titleExceptionDialog, JOptionPane.ERROR_MESSAGE);
	}
}
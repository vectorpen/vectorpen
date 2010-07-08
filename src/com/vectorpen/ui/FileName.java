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
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
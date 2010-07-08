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
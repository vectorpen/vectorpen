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

package com.vectorpen.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;

public final class FileInput
{
	public static VectorFile readFile(File file)
	throws IOException, DataFormatException
	{
		try
		{
			FileInputStream fileInputStream = new FileInputStream(file);
			byte data[] = new byte[(int)file.length()];

			fileInputStream.read(data);
			fileInputStream.close();

			String fileExtension = getExtension(file.getName());
			String title = getTitle(file.getName());

			if (fileExtension.compareToIgnoreCase(".DHW") == 0 && DHWModule.checkDHWFileHeader(data))
			{
				return DHWModule.readDHWFile(data, title);
			}
			else if (fileExtension.compareToIgnoreCase(".TOP") == 0 && TOPModule.checkTOPFileHeader(data))
			{
				return TOPModule.readTOPFile(data, title);
			}
			else if (fileExtension.compareToIgnoreCase(".DNT") == 0 && DNTModule.checkDNTFileHeader(data))
			{
				return DNTModule.readDNTFile(data, title);
			}
			else
			{
				throw new DataFormatException(ExceptionCodes.FILE_EXTENSION);
			}
		}
		catch (Exception exception)
		{
			if (exception.getClass() == DataFormatException.class)
			{
				throw new DataFormatException(exception.getMessage());
			}
			else
			{
				throw new IOException(ExceptionCodes.FILE_READ);
			}
		}
	}

	private static String getExtension(String fileName)
	throws Exception
	{
		int dotPosition = fileName.lastIndexOf(".");

		return fileName.substring(dotPosition);
	}

	private static String getTitle(String fileName)
	throws Exception
	{
		int dotPosition = fileName.lastIndexOf(".");

		return fileName.substring(0, dotPosition);
	}
}
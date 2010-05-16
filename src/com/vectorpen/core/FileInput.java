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
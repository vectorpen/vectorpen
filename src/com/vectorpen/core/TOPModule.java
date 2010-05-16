package com.vectorpen.core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

public final class TOPModule
{
	protected static VectorFile readTOPFile(byte data[], String title)
	throws DataFormatException
	{
		try
		{
			ArrayList<Path> paths = new ArrayList<Path>();

			int width = 8500;
			int height = 11000;

			int x;
			int y;

			int length = data.length;
			int index = 26;

			while (index < length)
			{
				switch (data[index] & 0xFF)
				{
				case 0:
					if (index <= length - 12)
					{
						x = (data[index + 9] & 0xFF) + ((data[index + 10] & 0xFF) << 8);
						y = height - ((data[index + 7] & 0xFF) + ((data[index + 8] & 0xFF) << 8));

						paths.add(new Path(new Point(x, y), Color.BLACK));
					}

					index = index + 12;
					break;
				default:
					x = (data[index + 3] & 0xFF) + ((data[index + 4] & 0xFF) << 8);
					y = height - ((data[index + 1] & 0xFF) + ((data[index + 2] & 0xFF) << 8));

					paths.get(paths.size() - 1).addPoint(new Point(x, y));

					index = index + 6;
				}
			}

			return new VectorFile(paths, new Size(width, height), title, VectorFile.TOP_FILE);
		}
		catch (Exception exception)
		{
			throw new DataFormatException(ExceptionCodes.TOP_DATA);
		}
	}

	protected static boolean checkTOPFileHeader(byte data[])
	throws DataFormatException
	{
		try
		{
			if (data.length < 32) return false;

			String idString = "WALTOP";
			if (!idString.equals(new String(data, 0, 6))) return false;

			if (data[6] != 1) return false;

			return true;
		}
		catch (Exception exception)
		{
			throw new DataFormatException(ExceptionCodes.TOP_HEADER);
		}
	}
}
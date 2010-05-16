package com.vectorpen.core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

public final class DNTModule
{
	protected static VectorFile readDNTFile(byte data[], String title)
	throws DataFormatException
	{
		try
		{
			ArrayList<Path> paths = new ArrayList<Path>();

			int width = (data[24] & 0xFF) + ((data[25] & 0xFF) << 8);
			int height = (data[28] & 0xFF) + ((data[29] & 0xFF) << 8);

			int x;
			int y;

			int length = data.length;
			int index = 56;

			while (index < length)
			{
				switch (data[index] & 0xFF)
				{
				case 0xFF:
				case 0xE0:
					if (index <= length - 16)
					{
						x = (data[index + 9] & 0xFF) + ((data[index + 10] & 0xFF) << 7) + 
						((((data[index + 14] & 0xFF) & ((1 << 2) - 1)) >> 0) << 14);
						y = (data[index + 11] & 0xFF) + ((data[index + 12] & 0xFF) << 7) + 
						((((data[index + 14] & 0xFF) & ((1 << 4) - 1)) >> 2) << 14);

						switch (data[index + 8] & 0xFF)
						{
						case 0xA3:
							paths.add(new Path(new Point(x, y), Color.BLUE));
							break;
						case 0xA5:
							paths.add(new Path(new Point(x, y), Color.RED));
							break;
						default:
							paths.add(new Path(new Point(x, y), Color.BLACK));
						}
					}

					index = index + 16;
					break;
				default:
					x = (data[index + 1] & 0xFF) + ((data[index + 2] & 0xFF) << 7) + 
					((((data[index + 6] & 0xFF) & ((1 << 2) - 1)) >> 0) << 14);
					y = (data[index + 3] & 0xFF) + ((data[index + 4] & 0xFF) << 7) + 
					((((data[index + 6] & 0xFF) & ((1 << 4) - 1)) >> 2) << 14);

					paths.get(paths.size() - 1).addPoint(new Point(x, y));

					index = index + 8;
				}
			}

			return new VectorFile(paths, new Size(width, height), title, VectorFile.DNT_FILE);
		}
		catch (Exception exception)
		{
			throw new DataFormatException(ExceptionCodes.DNT_DATA);
		}
	}

	protected static boolean checkDNTFileHeader(byte data[])
	throws DataFormatException
	{
		try
		{
			if (data.length < 64) return false;

			String idString = "UEDY";
			if (!idString.equals(new String(data, 0, 4))) return false;

			idString = "HARD";
			if (!idString.equals(new String(data, 8, 4))) return false;

			if (data[12] != 1) return false;
			if (data[13] + data[14] + data[15] != 0) return false;

			return true;
		}
		catch (Exception exception)
		{
			throw new DataFormatException(ExceptionCodes.DNT_HEADER);
		}
	}
}
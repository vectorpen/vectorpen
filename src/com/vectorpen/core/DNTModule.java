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
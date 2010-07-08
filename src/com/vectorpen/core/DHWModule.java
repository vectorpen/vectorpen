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

public final class DHWModule
{
	protected static VectorFile readDHWFile(byte data[], String title)
	throws DataFormatException
	{
		try
		{
			ArrayList<Path> paths = new ArrayList<Path>();

			int width = (data[33] & 0xFF) + ((data[34] & 0xFF) << 8);
			int height = (data[35] & 0xFF) + ((data[36] & 0xFF) << 8);

			int x;
			int y;

			int length = data.length;
			int index = 40;

			while (index < length)
			{
				switch (data[index] & 0xFF)
				{
				case 0x80:
				case 0x82:
				case 0x84:
				case 0x86:
					x = (data[index + 1] & 0xFF) + ((data[index + 2] & 0xFF) << 7);
					y = height - ((data[index + 3] & 0xFF) + ((data[index + 4] & 0xFF) << 7));

					paths.get(paths.size() - 1).addPoint(new Point(x, y));

					index = index + 5;
					break;
				case 0x81:
					x = (data[index + 1] & 0xFF) + ((data[index + 2] & 0xFF) << 7);
					y = height - ((data[index + 3] & 0xFF) + ((data[index + 4] & 0xFF) << 7));

					paths.add(new Path(new Point(x, y), Color.BLACK));

					index = index + 5;
					break;
				case 0x83:
					x = (data[index + 1] & 0xFF) + ((data[index + 2] & 0xFF) << 7);
					y = height - ((data[index + 3] & 0xFF) + ((data[index + 4] & 0xFF) << 7));

					paths.add(new Path(new Point(x, y), Color.RED));

					index = index + 5;
					break;
				case 0x85:
					x = (data[index + 1] & 0xFF) + ((data[index + 2] & 0xFF) << 7);
					y = height - ((data[index + 3] & 0xFF) + ((data[index + 4] & 0xFF) << 7));

					paths.add(new Path(new Point(x, y), Color.BLUE));

					index = index + 5;
					break;
				case 0x87:
					x = (data[index + 1] & 0xFF) + ((data[index + 2] & 0xFF) << 7);
					y = height - ((data[index + 3] & 0xFF) + ((data[index + 4] & 0xFF) << 7));

					paths.add(new Path(new Point(x, y), Color.GREEN));

					index = index + 5;
					break;
				case 0x88:
				case 0x90:
					index = index + 2;
					break;
				default:
					x = (data[index] & 0xFF) + ((data[index + 1] & 0xFF) << 7);
					y = height - ((data[index + 2] & 0xFF) + ((data[index + 3] & 0xFF) << 7));

					paths.get(paths.size() - 1).addPoint(new Point(x, y));

					index = index + 4;
				}
			}

			return new VectorFile(paths, new Size(width, height), title, VectorFile.DHW_FILE);
		}
		catch (Exception exception)
		{
			throw new DataFormatException(ExceptionCodes.DHW_DATA);
		}
	}

	protected static boolean checkDHWFileHeader(byte data[])
	throws DataFormatException
	{
		try
		{
			if (data.length < 40) return false;

			String idString = "ACECAD_DIGIMEMO_HANDWRITING_____";
			if (!idString.equals(new String(data, 0, 32))) return false;

			if (data[32] != 1) return false;

			return true;
		}
		catch (Exception exception)
		{
			throw new DataFormatException(ExceptionCodes.DHW_HEADER);
		}
	}
}
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

import java.util.ArrayList;
import java.util.Calendar;

public final class PDFModule
{
	public static final String PRODUCER = "VectorPen";

	public static String getPDFData(ArrayList<VectorFile> vectorFiles, DocInfoDict docInfoDict)
	throws Exception
	{
		try
		{
			StringBuffer pdfData = new StringBuffer();

			int pageCount = vectorFiles.size();

			if (pageCount != 0)
			{
				pdfData.append("%PDF-1.3");
				pdfData.append("\r\r");
				pdfData.append(createDocumentCatalog());
				pdfData.append("\r\r");
				pdfData.append(createPageTree(pageCount));
				pdfData.append("\r\r");
				pdfData.append(createDocumentInformationDictionary(vectorFiles, docInfoDict));
				pdfData.append("\r\r");
				pdfData.append(createGraphicsStateParameterDictionary(pageCount));
				pdfData.append("\r\r");
				pdfData.append(createPages(vectorFiles));
				pdfData.append("\r\r");
				pdfData.append(createContentStreams(vectorFiles));
				pdfData.append("\r\r");
				pdfData.append(createFileTrailer(pageCount));
			}

			return pdfData.toString();
		}
		catch (Exception exception)
		{
			throw new Exception(ExceptionCodes.PDF_DATA);
		}
	}

	private static String createDocumentCatalog()
	throws Exception
	{
		StringBuffer objectData = new StringBuffer();

		objectData.append("1 0 obj\r");
		objectData.append("\t<<\t/Type /Catalog\r");
		objectData.append("\t\t/Pages 2 0 R\r");
		objectData.append("\t>>\r");
		objectData.append("endobj");

		return objectData.toString();
	}

	private static String createPageTree(int pageCount)
	throws Exception
	{
		StringBuffer objectData = new StringBuffer();

		objectData.append("2 0 obj\r");
		objectData.append("\t<<\t/Type /Pages\r");
		objectData.append("\t\t/Kids [");

		for (int index = 0; index < pageCount; index++)
		{
			objectData.append(String.format("%d 0 R", index + 5));

			if (pageCount > 1 && index < pageCount - 1) objectData.append(" ");
		}

		objectData.append("]\r");
		objectData.append(String.format("\t\t/Count %d\r", pageCount));
		objectData.append("\t>>\r");
		objectData.append("endobj");

		return objectData.toString();
	}

	private static String createDocumentInformationDictionary(ArrayList<VectorFile> vectorFiles, DocInfoDict docInfoDict)
	throws Exception
	{
		StringBuffer objectData = new StringBuffer();

		objectData.append("3 0 obj\r");
		objectData.append("\t<<");

		if (docInfoDict.getTitle().compareToIgnoreCase("") != 0)
			objectData.append(String.format("\t/Title (%s)\r\t", docInfoDict.getTitle()));

		if (docInfoDict.getAuthor().compareToIgnoreCase("") != 0)
			objectData.append(String.format("\t/Author (%s)\r\t", docInfoDict.getAuthor()));

		if (docInfoDict.getSubject().compareToIgnoreCase("") != 0)
			objectData.append(String.format("\t/Subject (%s)\r\t", docInfoDict.getSubject()));

		if (docInfoDict.getKeywords().size() > 0)
		{
			int count = docInfoDict.getKeywords().size();

			objectData.append("\t/Keywords (");

			for (int index = 0; index < count; index++)
			{
				objectData.append(docInfoDict.getKeywords().get(index));

				if (count > 1 && index < count - 1) objectData.append(", ");
			}

			objectData.append(")\r\t");
		}

		String creator = getCreator(vectorFiles);

		objectData.append(String.format("\t/Creator (%s)\r\t", creator));
		objectData.append(String.format("\t/Producer (%s)\r\t", PRODUCER));

		String currentDate = getCurrentDate();

		objectData.append(String.format("\t/CreationDate (%s)\r\t", currentDate));
		objectData.append(String.format("\t/ModDate (%s)\r", currentDate));
		objectData.append("\t>>\r");
		objectData.append("endobj");

		return objectData.toString();
	}

	private static String getCreator(ArrayList<VectorFile> vectorFiles)
	throws Exception
	{
		StringBuffer creator = new StringBuffer();

		boolean dhw = false;
		boolean top = false;
		boolean dnt = false;

		ArrayList<String> types = new ArrayList<String>();

		int pageCount = vectorFiles.size();

		for (int index = 0; index < pageCount; index++)
		{
			int type = vectorFiles.get(index).getType();

			if (type == VectorFile.DHW_FILE && !dhw)
			{
				dhw = types.add("DHW");
			}
			else if (type == VectorFile.TOP_FILE && !top)
			{
				top = types.add("TOP");
			}
			else if (type == VectorFile.DNT_FILE && !dnt)
			{
				dnt = types.add("DNT");
			}
		}

		int count = types.size();

		for (int index = 0; index < count; index++)
		{
			creator.append(types.get(index));

			if (count == 3 && index == 0)
			{
				creator.append(", ");
			}
			else if (count > 1 && index < count - 1)
			{
				creator.append(" and ");
			}
			else
			{
				creator.append(" ");
			}
		}

		if (count > 1)
		{
			creator.append("Files");
		}
		else if (count == 1)
		{
			creator.append("File");
		}

		return creator.toString();
	}

	private static String getCurrentDate()
	throws Exception
	{
		Calendar currentDate = Calendar.getInstance();

		int zoneOffset = currentDate.get(Calendar.ZONE_OFFSET);
		int year = currentDate.get(Calendar.YEAR);
		int month = currentDate.get(Calendar.MONTH) + 1;
		int day = currentDate.get(Calendar.DAY_OF_MONTH);
		int hour = currentDate.get(Calendar.HOUR_OF_DAY);
		int minute = currentDate.get(Calendar.MINUTE);
		int second = currentDate.get(Calendar.SECOND);

		int HH = zoneOffset / 60000 / 60;
		int mm = zoneOffset / 60000 - zoneOffset / 60000 / 60 * 60;
		String Z = "+";

		if (HH < 0)
		{
			HH = Math.abs(HH);
			mm = Math.abs(mm);
			Z = "-";
		}

		return String.format("D:%d%02d%02d%02d%02d%02d%s%02d'%02d'", year, month, day, hour, minute, second, Z, HH, mm);
	}

	private static String createPages(ArrayList<VectorFile> vectorFiles)
	throws Exception
	{
		StringBuffer objectData = new StringBuffer();

		int pageCount = vectorFiles.size();

		for (int index = 0; index < pageCount; index++)
		{
			Size size = vectorFiles.get(index).getPaperSize(72);

			String width = Float.toString(size.getWidth());
			String height = Float.toString(size.getHeight());

			objectData.append(String.format("%d 0 obj\r", index + 5));
			objectData.append("\t<<\t/Type /Page\r");
			objectData.append("\t\t/Parent 2 0 R\r");
			objectData.append("\t\t/Resources << /ExtGState << /GS0 4 0 R >> >>\r");
			objectData.append(String.format("\t\t/MediaBox [0.0 0.0 %s %s]\r", width, height));
			objectData.append(String.format("\t\t/Contents %d 0 R\r", index + pageCount + 5));
			objectData.append("\t>>\r");
			objectData.append("endobj");

			if (pageCount > 1 && index < pageCount - 1)
				objectData.append("\r\r");
		}

		return objectData.toString();
	}

	private static String createGraphicsStateParameterDictionary(int pageCount)
	throws Exception
	{
		StringBuffer objectData = new StringBuffer();

		objectData.append("4 0 obj\r");
		objectData.append("\t<<\t/Type /ExtGState\r");
		objectData.append("\t\t/SA true\r");
		objectData.append("\t\t/OP false\r");
		objectData.append("\t\t/op false\r");
		objectData.append("\t\t/OPM 1\r");
		objectData.append("\t>>\r");
		objectData.append("endobj");

		return objectData.toString();
	}

	private static String createContentStreams(ArrayList<VectorFile> vectorFiles)
	throws Exception
	{
		StringBuffer objectData = new StringBuffer();

		int pageCount = vectorFiles.size();

		for (int index = 0; index < pageCount; index++)
		{
			String streamData = vectorFiles.get(index).getPDFRepresentation();

			objectData.append(String.format("%d 0 obj\r", index + pageCount + 5));
			objectData.append(String.format("\t<<\t/Length %d\r", streamData.length()));
			objectData.append("\t>>\r");
			objectData.append("stream\r");
			objectData.append(streamData);
			objectData.append("endstream\r");
			objectData.append("endobj");

			if (pageCount > 1 && index < pageCount - 1)
			{
				objectData.append("\r\r");
			}
		}

		return objectData.toString();
	}

	private static String createFileTrailer(int pageCount)
	throws Exception
	{
		StringBuffer objectData = new StringBuffer();

		objectData.append("trailer\r");
		objectData.append(String.format("\t<<\t/Size %d\r", (pageCount * 2) + 5));
		objectData.append("\t\t/Root 1 0 R\r");
		objectData.append("\t\t/Info 3 0 R\r");
		objectData.append("\t>>\r");
		objectData.append("%%EOF");

		return objectData.toString();
	}
}
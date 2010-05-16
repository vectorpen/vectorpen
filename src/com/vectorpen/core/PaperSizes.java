package com.vectorpen.core;

import java.util.ArrayList;
import java.util.HashMap;

public final class PaperSizes
{
	public static final String ISO_A4 = "ISO A4";
	public static final String ISO_A5 = "ISO A5";
	public static final String ISO_A6 = "ISO A6";
	public static final String ISO_B5 = "ISO B5";
	public static final String ISO_B6 = "ISO B6";
	public static final String US_LETTER = "US Letter";

	public static final String DEFAULT_PAPERSIZENAME = ISO_A4;
	public static final String LINE_SEPARATOR = "LINE_SEPARATOR";

	private static PaperSizes instance;

	private HashMap<String, Size> paperSizes;

	protected static synchronized PaperSizes getInstance()
	{
		if (instance == null)
			instance = new PaperSizes();

		return instance;
	}

	private PaperSizes()
	{
		this.paperSizes = new HashMap<String, Size>();
		this.paperSizes.put(ISO_A4, new Size(210.0f / 25.4f, 297.0f / 25.4f));
		this.paperSizes.put(ISO_A5, new Size(148.0f / 25.4f, 210.0f / 25.4f));
		this.paperSizes.put(ISO_A6, new Size(105.0f / 25.4f, 148.0f / 25.4f));
		this.paperSizes.put(ISO_B5, new Size(176.0f / 25.4f, 250.0f / 25.4f));
		this.paperSizes.put(ISO_B6, new Size(125.0f / 25.4f, 176.0f / 25.4f));
		this.paperSizes.put(US_LETTER, new Size(8.5f, 11.0f));
	}

	protected Size getPaperSize(String paperSizeName, int ppi)
	{
		return this.paperSizes.get(paperSizeName).cloneByPPI(ppi);
	}

	public static ArrayList<String> getPaperSizeNames()
	{
		ArrayList<String> paperSizeNames = new ArrayList<String>();

		paperSizeNames.add(ISO_A4);
		paperSizeNames.add(ISO_A5);
		paperSizeNames.add(ISO_A6);
		paperSizeNames.add(LINE_SEPARATOR);
		paperSizeNames.add(ISO_B5);
		paperSizeNames.add(ISO_B6);
		paperSizeNames.add(LINE_SEPARATOR);
		paperSizeNames.add(US_LETTER);

		return paperSizeNames;
	}

	protected static String getPaperSizeName(Size size, int type)
	{
		if (type == VectorFile.DHW_FILE)
		{
			if (size.getHeight() == 8464 || size.getHeight() == 8307)
			{
				return ISO_A5;
			}
			else if (size.getHeight() == 11693)
			{
				return ISO_A4;
			}
			else
			{
				return US_LETTER;
			}
		}
		else if (type == VectorFile.TOP_FILE)
		{
			return US_LETTER;
		}
		else if (type == VectorFile.DNT_FILE)
		{
			if (size.getHeight() == 16800 || size.getHeight() == 21600)
			{
				return ISO_A4;
			}
			else
			{
				return ISO_A5;
			}
		}
		else
		{
			return DEFAULT_PAPERSIZENAME;
		}
	}

	protected static boolean isValidPaperSizeName(String paperSizeName)
	{
		if (paperSizeName.compareTo(ISO_A4) == 0) return true;
		if (paperSizeName.compareTo(ISO_A5) == 0) return true;  	
		if (paperSizeName.compareTo(ISO_A6) == 0) return true;

		if (paperSizeName.compareTo(ISO_B5) == 0) return true;
		if (paperSizeName.compareTo(ISO_B6) == 0) return true;

		if (paperSizeName.compareTo(US_LETTER) == 0) return true;

		return false;
	}
}
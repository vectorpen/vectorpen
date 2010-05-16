package com.vectorpen.ui;

import javax.swing.JLabel;

public final class Environment
{
	private static final boolean OS_TIGER;
	private static final boolean OS_LEOPARD;
	private static final boolean OS_MAC;
	private static final boolean OS_XP;
	private static final boolean OS_VISTA;
	private static final boolean OS_WINDOWS;
	private static final boolean OS_LINUX;
	private static final String LABEL_FONT_NAME;
	private static final int LABEL_FONT_STYLE;
	private static final int LABEL_FONT_SIZE;
	private static final int VECTOR_PEN_VERSION;

	static
	{
		String name = System.getProperty("os.name");
		String version = System.getProperty("os.version");

		boolean tiger = false;
		boolean leopard = false;
		boolean mac = false;
		boolean xp = false;
		boolean vista = false;
		boolean windows = false;
		boolean linux = false;

		if (name.substring(0, 3).equalsIgnoreCase("Mac"))
		{
			if (version.substring(0, 4).equalsIgnoreCase("10.4"))
			{
				tiger = true;
			}
			else if (version.substring(0, 4).equalsIgnoreCase("10.5"))
			{
				leopard = true;
			}

			mac = true;
		}
		else if (name.substring(0, 3).equalsIgnoreCase("Win"))
		{
			if (version.substring(0, 1).equalsIgnoreCase("5"))
			{
				xp = true;
			}
			else if (version.substring(0, 1).equalsIgnoreCase("6"))
			{
				vista = true;
			}

			windows = true;
		}
		else
		{
			linux = true;
		}

		OS_TIGER = tiger;
		OS_LEOPARD = leopard;
		OS_MAC = mac;
		OS_XP = xp;
		OS_VISTA = vista;
		OS_WINDOWS = windows;
		OS_LINUX = linux;

		JLabel label = new JLabel();

		LABEL_FONT_NAME = label.getFont().getName();
		LABEL_FONT_STYLE = label.getFont().getStyle();
		LABEL_FONT_SIZE = label.getFont().getSize();

		VECTOR_PEN_VERSION = 10;
	}

	private Environment()
	{

	}

	public static boolean isTiger()
	{
		return OS_TIGER;
	}

	public static boolean isLeopard()
	{
		return OS_LEOPARD;
	}

	public static boolean isMac()
	{
		return OS_MAC;
	}

	public static boolean isXP()
	{
		return OS_XP;
	}

	public static boolean isVista()
	{
		return OS_VISTA;
	}

	public static boolean isWindows()
	{
		return OS_WINDOWS;
	}

	public static boolean isLinux()
	{
		return OS_LINUX;
	}

	public static String getLabelFontName()
	{
		return LABEL_FONT_NAME;
	}

	public static int getLabelFontStyle()
	{
		return LABEL_FONT_STYLE;
	}

	public static int getLabelFontSize()
	{
		return LABEL_FONT_SIZE;
	}

	public static int getVectorPenVersion()
	{
		return VECTOR_PEN_VERSION;
	}
}
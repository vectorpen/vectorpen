package com.vectorpen.ui;

import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public final class UIImageDisplayScroller extends JScrollPane
{
	private static UIImageDisplayScroller instance;

	public static synchronized UIImageDisplayScroller getInstance()
	{
		if (instance == null)
			instance = new UIImageDisplayScroller();

		return instance;
	}

	private UIImageDisplayScroller()
	{
		super(UIImageDisplay.getInstance());

		setOpaque(false);
		setBackground(UIConstants.UI_COLOR_BACKGROUND);
		setWheelScrollingEnabled(true);
	}
}
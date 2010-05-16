package com.vectorpen.ui;

import java.awt.Graphics;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public final class UIMainTableScroller extends JScrollPane implements AdjustmentListener
{
	private static final URL IMAGE_PATH_BACKGROUND;

	static
	{
		IMAGE_PATH_BACKGROUND = UIMainTableScroller.class.getResource("/com/vectorpen/img/Main-Table-Background.png");
	}

	private static UIMainTableScroller instance;

	private ImageIcon background;
	private int maximum;

	public static synchronized UIMainTableScroller getInstance()
	{
		if (instance == null)
			instance = new UIMainTableScroller();

		return instance;
	}

	private UIMainTableScroller()
	{
		super(UIMainTable.getInstance());

		background = new ImageIcon(IMAGE_PATH_BACKGROUND);
		maximum = 0;

		getViewport().setOpaque(false);
		setWheelScrollingEnabled(true);
		getVerticalScrollBar().addAdjustmentListener(this);
	}

	public void adjustmentValueChanged(AdjustmentEvent event)
	{
		JScrollBar verticalScrollBar = getVerticalScrollBar();

		int maximum = verticalScrollBar.getMaximum();

		if (maximum != this.maximum)
		{
			getVerticalScrollBar().setValue(maximum);

			this.maximum = maximum;
		}
	}

	public void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);

		graphics.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
	}
}
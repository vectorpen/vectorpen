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
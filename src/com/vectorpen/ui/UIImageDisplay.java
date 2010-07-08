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

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public final class UIImageDisplay extends JPanel
{
	private Image image;

	private static UIImageDisplay instance;

	public static synchronized UIImageDisplay getInstance()
	{
		if (instance == null)
			instance = new UIImageDisplay();

		return instance;
	}

	public void setImage(BufferedImage image)
	{
		if (this.image != null)
		{
			this.image.flush();
			this.image = null;
		}

		this.image = image;

		repaint();
	}

	public void paint(Graphics graphics)
	{
		double parentWidth = getParent().getWidth();
		double parentHeight = getParent().getHeight();

		double x = 0;
		double y = 0;

		graphics.setColor(UIConstants.UI_COLOR_BACKGROUND);
		graphics.fillRect((int)x, (int)y, (int)parentWidth, (int)parentHeight);

		if (image != null)
		{	
			double imageWidth = image.getWidth(this);
			double imageHeight = image.getHeight(this);

			if (parentWidth > imageWidth)
				x = (parentWidth - imageWidth) / 2;

			if (parentHeight > imageHeight)
				y = (parentHeight - imageHeight) / 2;

			setPreferredSize(new Dimension((int)imageWidth, (int)imageHeight));

			if (parentWidth > imageWidth && parentHeight < imageHeight)
			{
				graphics.fillRect(0, 0, (int)parentWidth, (int)imageHeight);

				setPreferredSize(new Dimension(0, (int)imageHeight));
			}
			else if (parentHeight > imageHeight && parentWidth < imageWidth)
			{
				graphics.fillRect(0, 0, (int)imageWidth, (int)parentHeight);

				setPreferredSize(new Dimension((int)imageWidth, 0));
			}

			graphics.drawImage(image, (int)x, (int)y, null);
		}
		else
		{	
			setPreferredSize(new Dimension(0, 0));
		}

		revalidate();
	}
}
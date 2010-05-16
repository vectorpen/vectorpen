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
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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public final class VectorFile
{
	public static final float DEFAULT_LINE_WIDTH = 1.0f;
	public static final Color DEFAULT_LINE_COLOR = Color.BLACK;
	public static final int ZOOM_MAX = 24 * 9;
	public static final int ZOOM_MIN = 24;
	public static final int ZOOM_ACTUAL_SIZE = 72;
	public static final float LINE_WIDTH_MIN = 0.1f;

	protected static final int DHW_FILE = 0;
	protected static final int TOP_FILE = 1;
	protected static final int DNT_FILE = 2;

	private ArrayList<Path> paths;
	private float lineWidth;
	private Color lineColor;
	private boolean hasLineColor;
	private Size size;
	private String paperSizeName;
	private String title;
	private int type;
	private boolean hasChanged;
	private int zoom;

	protected VectorFile(ArrayList<Path> paths, Size size, String title, int type)
	{
		this.paths = paths;
		this.lineWidth = DEFAULT_LINE_WIDTH;
		this.lineColor = DEFAULT_LINE_COLOR;
		this.hasLineColor = false;
		this.size = size;
		this.paperSizeName = PaperSizes.getPaperSizeName(size, type);
		this.title = title;
		this.type = type;
		this.hasChanged = false;
		this.zoom = 72;
	}

	public float getLineWidth()
	{
		return this.lineWidth;
	}

	public Color getLineColor()
	{
		int redColor = this.lineColor.getRed();
		int greenColor = this.lineColor.getGreen();
		int blueColor = this.lineColor.getBlue();

		return new Color(redColor, greenColor, blueColor);
	}

	public boolean getHasLineColor()
	{
		return this.hasLineColor;
	}

	public int getAspectRatio()
	{
		return this.size.getAspectRatio();
	}

	public String getPaperSizeName()
	{
		return new String(this.paperSizeName);
	}

	public String getTitle()
	{
		return new String(this.title);
	}

	public boolean getHasChanged()
	{
		return this.hasChanged;
	}

	public int getZoom()
	{
		return this.zoom;
	}

	protected int getType()
	{
		return this.type;
	}

	public void setLineWidth(float lineWidth)
	{
		this.lineWidth = Math.abs(lineWidth);
		this.hasChanged = true;
	}

	public void setLineColor(Color lineColor)
	{
		if (lineColor != null)
		{
			this.lineColor = lineColor;
			this.hasChanged = true;
		}
	}

	public void setHasLineColor(boolean hasLineColor)
	{
		this.hasLineColor = hasLineColor;
		this.hasChanged = true;
	}

	public void setPaperSizeName(String paperSizeName)
	{
		if (PaperSizes.isValidPaperSizeName(paperSizeName))
		{
			this.paperSizeName = paperSizeName;
			this.hasChanged = true;
		}
	}

	public void setTitle(String title)
	{
		if (title != null && !title.equalsIgnoreCase(""))
		{
			this.title = title;
		}
	}

	public void setHasChanged(boolean hasChanged)
	{
		this.hasChanged = hasChanged;
	}

	public void zoomIn()
	{
		if (zoom < ZOOM_MAX)
			zoom = zoom + 24;
	}

	public void zoomOut()
	{
		if (zoom > ZOOM_MIN)
			zoom = zoom - 24;
	}

	public void zoomActualSize()
	{
		zoom = ZOOM_ACTUAL_SIZE;
	}

	public BufferedImage getZoomedImageRepresentation()
	{
		return this.getImageRepresentationByPPI(zoom, true);
	}

	public void rotateByAngle(int angle)
	{
		if (angle == -270 || angle == -180 || angle == -90 || angle == 90 || angle == 180 || angle == 270)
		{
			int count = this.paths.size();

			for (int index = 0; index < count; index++)
			{
				this.paths.get(index).rotateByAngle(angle, this.size);
			}

			this.hasChanged = true;
		}

		if (angle == -270 || angle == -90 || angle == 90 || angle == 270)
			this.size.rotate();
	}

	public BufferedImage getImageRepresentation()
	{
		return this.getImageRepresentationByPPI(ZOOM_ACTUAL_SIZE, true);
	}

	public BufferedImage getImageRepresentationByPPI(int ppi, boolean opaque)
	{
		Size paperSize = this.getPaperSize(Math.abs(ppi));
		Scale scale = new Scale(this.size, paperSize);

		float lineWidth = this.lineWidth * ((float)ppi / 72.0f);
		if (lineWidth < LINE_WIDTH_MIN) lineWidth = LINE_WIDTH_MIN;

		BasicStroke stroke = new BasicStroke(lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

		int width = (int)paperSize.getWidth() + 1;
		int height = (int)paperSize.getHeight() + 1;

		BufferedImage imageRepresentation;

		if (opaque)
		{
			imageRepresentation = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		}
		else
		{
			imageRepresentation = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		}

		Graphics2D graphics = imageRepresentation.createGraphics();
		graphics.setStroke(stroke);

		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		graphics.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

		if (opaque)
		{
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, width, height);
		}

		int count = this.paths.size();

		for (int index = 0; index < count; index++)
		{
			Path path = this.paths.get(index);

			if (this.hasLineColor)
			{
				graphics.setColor(this.lineColor);
			}
			else
			{
				graphics.setColor(path.getLineColor());
			}

			graphics.draw(path.generalPathRepresentation(scale));
		}

		imageRepresentation.flush();

		return imageRepresentation;
	}

	public BufferedImage getImageRepresentationByWidth(int width)
	{
		Size paperSize = this.getPaperSize(72);

		float height = paperSize.getHeight() / (paperSize.getWidth() / width);

		Size size = new Size(width, height);
		Scale scale = new Scale(this.size, size);

		float lineWidth = this.lineWidth * (width / paperSize.getWidth());
		if (lineWidth < LINE_WIDTH_MIN) lineWidth = LINE_WIDTH_MIN;

		BasicStroke stroke = new BasicStroke(lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

		BufferedImage imageRepresentation = new BufferedImage(width, (int)height + 1, BufferedImage.TYPE_4BYTE_ABGR);

		Graphics2D graphics = imageRepresentation.createGraphics();
		graphics.setStroke(stroke);

		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		graphics.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, width, (int)height + 1);

		int count = this.paths.size();

		for (int index = 0; index < count; index++)
		{
			Path path = this.paths.get(index);

			if (this.hasLineColor)
			{
				graphics.setColor(this.lineColor);
			}
			else
			{
				graphics.setColor(path.getLineColor());
			}

			graphics.draw(path.generalPathRepresentation(scale));
		}

		imageRepresentation.flush();

		return imageRepresentation;
	}

	public BufferedImage getImageRepresentationByHeight(int height)
	{
		Size paperSize = this.getPaperSize(72);

		float width = paperSize.getWidth() / (paperSize.getHeight() / height);

		Size size = new Size(width, height);
		Scale scale = new Scale(this.size, size);

		float lineWidth = this.lineWidth * (height / paperSize.getHeight());
		if (lineWidth < LINE_WIDTH_MIN) lineWidth = LINE_WIDTH_MIN;

		BasicStroke stroke = new BasicStroke(lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

		BufferedImage imageRepresentation = new BufferedImage((int)width + 1, height, BufferedImage.TYPE_4BYTE_ABGR);

		Graphics2D graphics = imageRepresentation.createGraphics();
		graphics.setStroke(stroke);

		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		graphics.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, (int)width + 1, height);

		int count = this.paths.size();

		for (int index = 0; index < count; index++)
		{
			Path path = this.paths.get(index);

			if (this.hasLineColor)
			{
				graphics.setColor(this.lineColor);
			}
			else
			{
				graphics.setColor(path.getLineColor());
			}

			graphics.draw(path.generalPathRepresentation(scale));
		}

		imageRepresentation.flush();

		return imageRepresentation;
	}

	public String getSVGRepresentation()
	{
		StringBuffer svgRepresentation = new StringBuffer();

		Size paperSize = this.getPaperSize(72);
		Scale scale = new Scale(this.size, paperSize);

		svgRepresentation.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r");
		svgRepresentation.append("<!-- SVG Generator: ");
		svgRepresentation.append(PDFModule.PRODUCER);
		svgRepresentation.append(" -->\r");
		svgRepresentation.append("<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.0//EN\" \"http://www.w3.org/TR/2001/REC-SVG-20010904/DTD/svg10.dtd\">\r");
		svgRepresentation.append("<svg version=\"1.0\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" viewBox=\"0 0 ");
		svgRepresentation.append(String.format("%s %s", Float.toString(paperSize.getWidth()), Float.toString(paperSize.getHeight())));
		svgRepresentation.append("\" xml:space=\"preserve\">\r");

		int count = this.paths.size();

		for (int index = 0; index < count; index++)
		{
			svgRepresentation.append(this.paths.get(index).getSVGRepresentation(scale, this));
			svgRepresentation.append("\r");
		}

		svgRepresentation.append("</svg>");

		return svgRepresentation.toString();
	}

	protected String getPDFRepresentation()
	{
		StringBuffer pdfRepresentation = new StringBuffer();

		Size paperSize = this.getPaperSize(72);
		Scale scale = new Scale(this.size, paperSize);

		pdfRepresentation.append("/Layer /MC0 BDC\r");
		pdfRepresentation.append(String.format("%s w 1 j 1 J\r", this.lineWidth));
		pdfRepresentation.append("/GS0 gs\r");

		int count = this.paths.size();

		for (int index = 0; index < count; index++)
		{
			pdfRepresentation.append(this.paths.get(index).getPDFRepresentation(scale, this));
		}

		pdfRepresentation.append("EMC\r");

		return pdfRepresentation.toString();
	}

	protected Size getPaperSize(int ppi)
	{
		Size size = PaperSizes.getInstance().getPaperSize(this.paperSizeName, ppi);

		if (this.size.getAspectRatio() == Size.LANDSCAPE)
			size.rotate();

		return size;
	}
}
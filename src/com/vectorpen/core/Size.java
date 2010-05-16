package com.vectorpen.core;

public final class Size
{
	public static final int PORTRAIT = 0;
	public static final int LANDSCAPE = 1;
	public static final int SQUARE = 2;

	private float width;
	private float height;
	private int aspectRatio;

	protected Size(float width, float height)
	{
		this.width = width;
		this.height = height;

		this.setAspectRatio();
	}

	protected float getWidth()
	{
		return this.width;
	}

	protected float getHeight()
	{
		return this.height;
	}

	protected int getAspectRatio()
	{
		return this.aspectRatio;
	}

	private void setAspectRatio()
	{
		if (this.width < this.height)
		{
			this.aspectRatio = PORTRAIT;
		}
		else if (this.width > this.height)
		{
			this.aspectRatio = LANDSCAPE;
		}
		else
		{
			this.aspectRatio = SQUARE;
		}
	}

	protected void rotate()
	{
		float buffer = this.width;

		this.width = this.height;
		this.height = buffer;

		this.setAspectRatio();
	}

	protected Size cloneByPPI(int ppi)
	{
		return new Size(this.width * ppi, this.height * ppi);
	}

	protected Size clone()
	{
		return new Size(this.width, this.height);
	}
}
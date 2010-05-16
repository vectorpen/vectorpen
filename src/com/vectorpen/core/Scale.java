package com.vectorpen.core;

public final class Scale
{
	private float scale;
	private float offsetX;
	private float offsetY;

	protected Scale(Size sourceSize, Size targetSize)
	{
		int sourceAspectRatio = sourceSize.getAspectRatio();
		int targetAspectRatio = targetSize.getAspectRatio();

		if (sourceAspectRatio == Size.PORTRAIT)
		{
			if (targetAspectRatio == Size.PORTRAIT)
			{
				this.aspectRatioByWidth(sourceSize, targetSize);
			}
			else if (targetAspectRatio == Size.LANDSCAPE)
			{
				this.aspectRatioByHeight(sourceSize, targetSize);
			}
			else if (targetAspectRatio == Size.SQUARE)
			{
				this.aspectRatioByHeight(sourceSize, targetSize);
			}
		}
		else if (sourceAspectRatio == Size.LANDSCAPE)
		{
			if (targetAspectRatio == Size.PORTRAIT)
			{
				this.aspectRatioByWidth(sourceSize, targetSize);
			}
			else if (targetAspectRatio == Size.LANDSCAPE)
			{
				this.aspectRatioByHeight(sourceSize, targetSize);
			}
			else if (targetAspectRatio == Size.SQUARE)
			{
				this.aspectRatioByWidth(sourceSize, targetSize);
			}
		}
		else if (sourceAspectRatio == Size.SQUARE)
		{
			if (targetAspectRatio == Size.PORTRAIT)
			{
				this.aspectRatioByWidth(sourceSize, targetSize);
			}
			else if (targetAspectRatio == Size.LANDSCAPE)
			{
				this.aspectRatioByHeight(sourceSize, targetSize);
			}
			else if (targetAspectRatio == Size.SQUARE)
			{
				this.aspectRatioByHeight(sourceSize, targetSize);
			}
		}
	}

	protected float getScale()
	{
		return this.scale;
	}

	protected float getOffsetX()
	{
		return this.offsetX;
	}

	protected float getOffsetY()
	{
		return this.offsetY;
	}

	private void aspectRatioByWidth(Size sourceSize, Size targetSize)
	{
		float sourceWidth = sourceSize.getWidth();
		float sourceHeight = sourceSize.getHeight();

		float targetWidth = targetSize.getWidth();
		float targetHeight = targetSize.getHeight();

		this.scale = sourceWidth / targetWidth;

		float newHeight = sourceHeight / this.scale;

		this.offsetX = 0.0f;
		this.offsetY = (targetHeight - newHeight) / 2.0f;

		if (newHeight > targetHeight)
			this.aspectRatioByHeight(sourceSize, targetSize);
	}

	private void aspectRatioByHeight(Size sourceSize, Size targetSize)
	{
		float sourceWidth = sourceSize.getWidth();
		float sourceHeight = sourceSize.getHeight();

		float targetWidth = targetSize.getWidth();
		float targetHeight = targetSize.getHeight();

		this.scale = sourceHeight / targetHeight;

		float newWidth = sourceWidth / this.scale;

		this.offsetX = (targetWidth - newWidth) / 2.0f;
		this.offsetY = 0.0f;

		if (newWidth > targetWidth)
			this.aspectRatioByWidth(sourceSize, targetSize);
	}
}
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
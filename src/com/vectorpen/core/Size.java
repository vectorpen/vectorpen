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
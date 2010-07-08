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

public final class Point
{
	private float x;
	private float y;

	protected Point(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	protected float getX()
	{
		return this.x;
	}

	protected float getY()
	{
		return this.y;
	}

	protected void rotateByAngle(int angle, Size size)
	{
		int count = Math.abs(angle) / 90;

		if (angle < 0)
		{
			for (int index = 0; index < count; index++)
			{
				size.rotate();

				float buffer = this.x;

				this.x = this.y;
				this.y = size.getHeight() - buffer;
			}
		}
		else
		{
			for (int index = 0; index < count; index++)
			{
				size.rotate();

				float buffer = this.x;

				this.x = size.getWidth() - this.y;
				this.y = buffer;
			}
		}
	}

	protected Point cloneByScale(Scale scale)
	{
		float x = scale.getOffsetX() + (this.x / scale.getScale());
		float y = scale.getOffsetY() + (this.y / scale.getScale());

		return new Point(x, y);
	}
}
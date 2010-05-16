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
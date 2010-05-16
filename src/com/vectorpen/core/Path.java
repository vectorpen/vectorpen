package com.vectorpen.core;

import java.awt.Color;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;

public final class Path
{
	private ArrayList<Point> points;
	private Color lineColor;

	protected Path(Point point, Color lineColor)
	{
		this.points = new ArrayList<Point>();
		this.points.add(point);
		this.lineColor = lineColor;
	}

	protected Color getLineColor()
	{
		return this.lineColor;
	}

	protected void addPoint(Point point)
	{
		this.points.add(point);
	}

	protected void rotateByAngle(int angle, Size size)
	{
		int count = this.points.size();

		for (int index = 0; index < count; index++)
		{
			this.points.get(index).rotateByAngle(angle, size.clone());
		}
	}

	protected GeneralPath generalPathRepresentation(Scale scale)
	{
		GeneralPath generalPath = new GeneralPath();

		Point point = this.points.get(0).cloneByScale(scale);

		generalPath.moveTo(point.getX(), point.getY());

		int count = this.points.size();

		for (int index = 1; index < count; index++)
		{
			point = this.points.get(index).cloneByScale(scale);

			generalPath.lineTo(point.getX(), point.getY());
		}

		return generalPath;
	}

	protected String getPDFRepresentation(Scale scale, VectorFile vectorFile)
	{
		StringBuffer pdfRepresentation = new StringBuffer();

		Point point = this.points.get(0).cloneByScale(scale);

		if (vectorFile.getHasLineColor())
		{
			pdfRepresentation.append(getColorString(vectorFile.getLineColor()));
		}
		else
		{
			pdfRepresentation.append(getColorString(this.lineColor));
		}

		float height = vectorFile.getPaperSize(72).getHeight();

		float x = point.getX();
		float y = height - point.getY();

		pdfRepresentation.append(String.format("q 1 0 0 1 %s %s cm\r", Float.toString(x), Float.toString(y)));
		pdfRepresentation.append("0 0 m\r");

		int count = this.points.size();

		for (int index = 1; index < count; index++)
		{
			point = this.points.get(index).cloneByScale(scale);

			float xR = point.getX() - x;
			float yR = height - point.getY() - y;

			pdfRepresentation.append(String.format("%s %s l\r", Float.toString(xR), Float.toString(yR)));
		}

		pdfRepresentation.append("S\r");
		pdfRepresentation.append("Q\r");

		return pdfRepresentation.toString();
	}

	protected String getSVGRepresentation(Scale scale, VectorFile vectorFile)
	{
		StringBuffer svgRepresentation = new StringBuffer();

		String lineColor;

		if (vectorFile.getHasLineColor())
		{
			lineColor = getHexColorString(vectorFile.getLineColor());
		}
		else
		{
			lineColor = getHexColorString(this.lineColor);
		}

		svgRepresentation.append("<polyline fill=\"none\" stroke=\"");
		svgRepresentation.append(lineColor);
		svgRepresentation.append("\" stroke-width=\"");
		svgRepresentation.append(Float.toString(vectorFile.getLineWidth()));
		svgRepresentation.append("\" stroke-linecap=\"round\" stroke-linejoin=\"round\" points=\"");

		int count = this.points.size();

		for (int index = 0; index < count; index++)
		{
			Point point = this.points.get(index).cloneByScale(scale);

			String x = Float.toString(point.getX());
			String y = Float.toString(point.getY());

			svgRepresentation.append(String.format("%s,%s", x, y));

			if (count > 1 && index < count - 1) svgRepresentation.append(" ");
		}

		svgRepresentation.append("\"/>");

		return svgRepresentation.toString();
	}

	private static String getColorString(Color color)
	{
		float rgbColorComponents[] = new float[4];

		color.getRGBColorComponents(rgbColorComponents);

		String red = Float.toString(rgbColorComponents[0]);
		String green = Float.toString(rgbColorComponents[1]);
		String blue = Float.toString(rgbColorComponents[2]);

		return String.format("%s %s %s RG\r", red, green, blue);
	}

	private static String getHexColorString(Color color)
	{
		String red = Integer.toHexString(color.getRed());
		String green = Integer.toHexString(color.getGreen());
		String blue = Integer.toHexString(color.getBlue());

		if (red.length() == 1) red = String.format("%s%s", "0", red);
		if (green.length() == 1) green = String.format("%s%s", "0", green);
		if (blue.length() == 1) blue = String.format("%s%s", "0", blue);

		return String.format("#%s%s%s", red.toUpperCase(), green.toUpperCase(), blue.toUpperCase());
	}
}
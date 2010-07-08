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

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import com.vectorpen.core.DocInfoDict;
import com.vectorpen.core.PDFModule;
import com.vectorpen.core.Size;
import com.vectorpen.core.VectorFile;

@SuppressWarnings("serial")
public final class VectorFiles extends AbstractTableModel implements TableModelListener
{
	public static final int PREVIEW_SIZE = 150;

	private static VectorFiles instance;

	private String columnNamePreview;
	private String columnNameTitle;
	private ArrayList<VectorFile> vectorFiles;
	private ArrayList<ImageIcon> previews;

	public static synchronized VectorFiles getInstance()
	{
		if (instance == null)
			instance = new VectorFiles();

		return instance;
	}

	private VectorFiles()
	{
		getLocalizedStrings();

		addTableModelListener(this);

		vectorFiles = new ArrayList<VectorFile>();
		previews = new ArrayList<ImageIcon>();
	}

	private void getLocalizedStrings()
	{
		ResourceBundle bundle = ResourceBundle.getBundle("Localizable");

		columnNamePreview = bundle.getString("VectorFiles.Column.Name.Preview");
		columnNameTitle = bundle.getString("VectorFiles.Column.Name.Title");
	}

	public String getColumnName(int column)
	{
		if (column == 0) return columnNamePreview;

		return columnNameTitle;
	}

	public boolean isCellEditable(int row, int column)
	{
		if (column == 0) return false;

		return true;
	}

	public Class<?> getColumnClass(int column)
	{
		if (column == 0) return ImageIcon.class;

		return String.class;
	}

	public Object getValueAt(int row, int column)
	{
		VectorFile vectorFile = vectorFiles.get(row);

		if (column == 0)
		{
			if (vectorFile.getHasChanged())
			{
				BufferedImage image;

				if (vectorFile.getAspectRatio() == Size.PORTRAIT)
				{
					image = vectorFile.getImageRepresentationByHeight(PREVIEW_SIZE);
				}
				else
				{
					image = vectorFile.getImageRepresentationByWidth(PREVIEW_SIZE);
				}

				ImageIcon preview = previews.remove(row);
				previews.add(row, new ImageIcon(image));

				image.flush();
				image = null;

				if (preview != null) preview = null;

				vectorFile.setHasChanged(false);
			}

			return previews.get(row);
		}

		return vectorFile.getTitle();
	}

	public void setValueAt(Object value, int row, int column)
	{
		if (column == 1)
		{
			VectorFile vectorFile = vectorFiles.get(row);

			if (FileName.check((String)value, true))
				vectorFile.setTitle((String)value);

			fireTableCellUpdated(row, column);
		}
	}

	public int getRowCount()
	{
		return vectorFiles.size();
	}

	public int getColumnCount()
	{
		return 2;
	}

	public void tableChanged(TableModelEvent event)
	{
		int firstRow = event.getFirstRow();
		int lastRow = event.getLastRow();

		switch (event.getType())
		{
		case TableModelEvent.INSERT:
			UIMainTable.getInstance().getSelectionModel().setSelectionInterval(lastRow, lastRow);

			break;
		case TableModelEvent.DELETE:
			UIMainTable.getInstance().getSelectionModel().removeIndexInterval(firstRow, lastRow);

			if (UIMainTable.getInstance().getRowCount() > 0)
			{
				lastRow = UIMainTable.getInstance().getRowCount() - 1;

				UIMainTable.getInstance().getSelectionModel().setSelectionInterval(lastRow, lastRow);
			}

			break;
		case TableModelEvent.UPDATE:
			int selectedRow = UIMainTable.getInstance().getSelectedRow();

			UIImageDisplay.getInstance().setImage(getZoomedImageRepresentation(selectedRow));
		}

		UIMainToolbar.getInstance().updateButtons();
		UIMainMenu.getInstance().updateMenuItems();
		UISecondaryToolbar.getInstance().updateComponents();
	}

	VectorFile getVectorFileAtIndex(int index)
	{
		return vectorFiles.get(index);
	}

	public float getLineWidth(int index)
	{
		return vectorFiles.get(index).getLineWidth();
	}

	public Color getLineColor(int index)
	{
		return vectorFiles.get(index).getLineColor();
	}

	public boolean getHasLineColor(int index)
	{
		return vectorFiles.get(index).getHasLineColor();
	}

	public String getPaperSizeName(int index)
	{
		return vectorFiles.get(index).getPaperSizeName();
	}

	public int getZoom(int index)
	{
		return vectorFiles.get(index).getZoom();
	}

	public void setLineWidth(float lineWidth)
	{
		int rows[] = UIMainTable.getInstance().getSelectedRows();

		int count = rows.length;

		for (int index = 0; index < count; index++)
		{
			vectorFiles.get(rows[index]).setLineWidth(lineWidth);
		}

		fireTableRowsUpdated(rows[0], rows[count - 1]);
	}

	public void setDefaultLineColor()
	{
		int rows[] = UIMainTable.getInstance().getSelectedRows();

		int count = rows.length;

		for (int index = 0; index < count; index++)
		{
			vectorFiles.get(rows[index]).setHasLineColor(false);
		}

		fireTableRowsUpdated(rows[0], rows[count - 1]);
	}

	public void setLineColor(Color lineColor)
	{
		int rows[] = UIMainTable.getInstance().getSelectedRows();

		int count = rows.length;

		for (int index = 0; index < count; index++)
		{
			vectorFiles.get(rows[index]).setHasLineColor(true);
			vectorFiles.get(rows[index]).setLineColor(lineColor);
		}

		fireTableRowsUpdated(rows[0], rows[count - 1]);
	}

	public void setPaperSizeName(String paperSizeName)
	{
		int rows[] = UIMainTable.getInstance().getSelectedRows();

		int count = rows.length;

		for (int index = 0; index < count; index++)
		{
			vectorFiles.get(rows[index]).setPaperSizeName(paperSizeName);
		}

		fireTableRowsUpdated(rows[0], rows[count - 1]);
	}

	public void add(ArrayList<VectorFile> vectorFiles)
	{
		int size = this.vectorFiles.size();
		int count = vectorFiles.size();

		for (int index = 0; index < count; index++)
		{
			VectorFile vectorFile = vectorFiles.get(index);
			BufferedImage image;

			if (vectorFile.getAspectRatio() == Size.PORTRAIT)
			{
				image = vectorFile.getImageRepresentationByHeight(PREVIEW_SIZE);
			}
			else
			{
				image = vectorFile.getImageRepresentationByWidth(PREVIEW_SIZE);
			}

			ImageIcon preview = new ImageIcon(image);

			image.flush();
			image = null;

			this.vectorFiles.add(vectorFile);
			previews.add(preview);
		}

		fireTableRowsInserted(size, size + count - 1);
	}

	public void remove()
	{
		int rows[] = UIMainTable.getInstance().getSelectedRows();

		int count = rows.length - 1;

		for (int index = count; index >= 0; index--)
		{
			VectorFile vectorFile = vectorFiles.remove(rows[index]);
			ImageIcon preview = previews.remove(rows[index]);

			if (vectorFile != null) vectorFile = null;
			if (preview != null) preview = null;
		}

		fireTableRowsDeleted(rows[0], rows[count]);
	}

	public void rotateByAngle(int angle)
	{
		int rows[] = UIMainTable.getInstance().getSelectedRows();

		int count = rows.length;

		for (int index = 0; index < count; index++)
		{
			vectorFiles.get(rows[index]).rotateByAngle(angle);
		}

		fireTableRowsUpdated(rows[0], rows[count - 1]);
	}

	public void zoomIn()
	{
		int selectedRow = UIMainTable.getInstance().getSelectedRow();

		vectorFiles.get(selectedRow).zoomIn();

		fireTableCellUpdated(selectedRow, 0);
	}

	public void zoomOut()
	{
		int selectedRow = UIMainTable.getInstance().getSelectedRow();

		vectorFiles.get(selectedRow).zoomOut();

		fireTableCellUpdated(selectedRow, 0);
	}

	public void zoomActualSize()
	{
		int selectedRow = UIMainTable.getInstance().getSelectedRow();

		vectorFiles.get(selectedRow).zoomActualSize();

		fireTableCellUpdated(selectedRow, 0);
	}

	public ArrayList<String> getTitles()
	{
		ArrayList<String> titles = new ArrayList<String>();

		int rows[] = UIMainTable.getInstance().getSelectedRows();

		int count = rows.length;

		for (int index = 0; index < count; index++)
		{
			titles.add(vectorFiles.get(index).getTitle());
		}

		return titles;
	}

	public BufferedImage getZoomedImageRepresentation(int index)
	{
		return vectorFiles.get(index).getZoomedImageRepresentation();
	}

	public BufferedImage getImageRepresentationByPPI(int ppi, int index, boolean opaque)
	{
		return vectorFiles.get(index).getImageRepresentationByPPI(ppi, opaque);
	}

	public String getPDFRepresentation(DocInfoDict docInfoDict, int index)
	{
		String pdfRepresentation = null;
		ArrayList<VectorFile> vectorFiles = new ArrayList<VectorFile>();

		try
		{
			if (index == -1)
			{
				int rows[] = UIMainTable.getInstance().getSelectedRows();

				int count = rows.length;

				for (index = 0; index < count; index++)
				{
					vectorFiles.add(this.vectorFiles.get(rows[index]));
				}

				pdfRepresentation = PDFModule.getPDFData(vectorFiles, docInfoDict);
			}
			else
			{
				vectorFiles.add(this.vectorFiles.get(index));

				pdfRepresentation = PDFModule.getPDFData(vectorFiles, docInfoDict);
			}
		}
		catch (Exception exception)
		{
			UIExceptionDialog.showDialog(exception);
		}

		vectorFiles = null;

		return pdfRepresentation;
	}

	public ArrayList<String> getSVGRepresentation()
	{
		ArrayList<String> svgRepresentation = new ArrayList<String>();

		int rows[] = UIMainTable.getInstance().getSelectedRows();

		int count = rows.length;

		for (int index = 0; index < count; index++)
		{
			svgRepresentation.add(vectorFiles.get(rows[index]).getSVGRepresentation());
		}

		return svgRepresentation;
	}

	public int size()
	{
		return vectorFiles.size();
	}
}
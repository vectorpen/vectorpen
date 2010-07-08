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
import java.awt.Component;
import java.util.ResourceBundle;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;

import com.vectorpen.core.PaperSizes;
import com.vectorpen.core.VectorFile;

@SuppressWarnings("serial")
public final class UIMainTable extends JTable implements ListSelectionListener
{
	private String columnNamePreview;
	private static UIMainTable instance;

	public static synchronized UIMainTable getInstance()
	{
		if (instance == null)
			instance = new UIMainTable();

		return instance;
	}

	private UIMainTable()
	{
		getLocalizedStrings();

		setOpaque(false);

		setModel(VectorFiles.getInstance());
		getSelectionModel().addListSelectionListener(this);
		setFocusable(false);

		setRowHeight(VectorFiles.PREVIEW_SIZE);
		getColumn(columnNamePreview).setMinWidth(VectorFiles.PREVIEW_SIZE);
		getColumn(columnNamePreview).setMaxWidth(VectorFiles.PREVIEW_SIZE);

		getTableHeader().setReorderingAllowed(false); 
		getTableHeader().setResizingAllowed(false);

		setSelectionBackground(UIConstants.UI_COLOR_SELECTION);

		setShowGrid(false);
		setShowHorizontalLines(false);
		setShowVerticalLines(false);
		setTableHeader(null);
	}

	private void getLocalizedStrings()
	{
		ResourceBundle bundle = ResourceBundle.getBundle("Localizable");

		columnNamePreview = bundle.getString("VectorFiles.Column.Name.Preview");
	}

	public void valueChanged(ListSelectionEvent event)
	{
		int row = getSelectedRow();

		if (row == -1 || row >= VectorFiles.getInstance().getRowCount())
		{
			UIImageDisplay.getInstance().setImage(null);

			UILineWidthField.getInstance().setLineWidth(VectorFile.DEFAULT_LINE_WIDTH);

			UILineColorBox.getInstance().lock();
			UILineColorBox.getInstance().setSelectedIndex(0);

			UIPaperSizesBox.getInstance().lock();
			String paperSize = PaperSizes.DEFAULT_PAPERSIZENAME;
			UIPaperSizesBox.getInstance().setPaperSize(paperSize);
		}
		else
		{
			UIImageDisplay.getInstance().setImage(VectorFiles.getInstance().getZoomedImageRepresentation(row));

			float lineWidth = VectorFiles.getInstance().getLineWidth(row);
			UILineWidthField.getInstance().setLineWidth(lineWidth);

			UILineColorBox.getInstance().unlock();
			Color lineColor = VectorFiles.getInstance().getLineColor(row);
			boolean hasLineColor = VectorFiles.getInstance().getHasLineColor(row);
			UILineColorBox.getInstance().setLineColor(lineColor, hasLineColor);

			UIPaperSizesBox.getInstance().unlock();
			String paperSize = VectorFiles.getInstance().getPaperSizeName(row);
			UIPaperSizesBox.getInstance().setPaperSize(paperSize);
		}

		UIMainToolbar.getInstance().updateButtons();
		UIMainMenu.getInstance().updateMenuItems();
		UISecondaryToolbar.getInstance().updateComponents();

		repaint();
		revalidate();
	}

	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) 
	{
		Component component = super.prepareRenderer(renderer, row, column);

		if(component instanceof JComponent)
		{
			boolean isSelected = false;

			int[] count = getSelectedRows();

			for (int index = 0; index < count.length; index++)
			{
				if (count[index] == row) isSelected = true;
			}

			if (isSelected)
			{
				((JComponent)component).setOpaque(true);
			}
			else
			{
				((JComponent)component).setOpaque(false);
			}
		}

		return component;
	}
}
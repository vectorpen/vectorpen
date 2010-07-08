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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JSeparator;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

import com.vectorpen.core.PaperSizes;

@SuppressWarnings("serial")
public final class UILineColorBox extends JComboBox
{
	private static UILineColorBox instance;

	private String textDefaultColor;
	private String textBlack;
	private String textRed;
	private String textGreen;
	private String textBlue;
	private String textCyan;
	private String textMagenta;
	private String textOrange;
	private String textPink;
	private String textYellow;
	private String textDarkGray;
	private String textGray;
	private String textLightGray;
	private String toolTip;
	private ArrayList<Color> colors;
	private ArrayList<String> colorNames;
	private boolean locked;

	public static synchronized UILineColorBox getInstance()
	{
		if (instance == null)
			instance = new UILineColorBox();

		return instance;
	}

	private UILineColorBox()
	{
		getLocalizedStrings();

		colors = new ArrayList<Color>();
		colors.add(UIPaperSizesBox.getInstance().getForeground());
		colors.add(UIPaperSizesBox.getInstance().getForeground());
		colors.add(Color.BLACK);
		colors.add(Color.RED);
		colors.add(Color.GREEN);
		colors.add(Color.BLUE);
		colors.add(Color.CYAN);
		colors.add(Color.MAGENTA);
		colors.add(Color.ORANGE);
		colors.add(Color.PINK);
		colors.add(Color.YELLOW);
		colors.add(Color.DARK_GRAY);
		colors.add(Color.GRAY);
		colors.add(Color.LIGHT_GRAY);

		colorNames = new ArrayList<String>();
		colorNames.add(textDefaultColor);
		colorNames.add(PaperSizes.LINE_SEPARATOR);
		colorNames.add(textBlack);
		colorNames.add(textRed);
		colorNames.add(textGreen);
		colorNames.add(textBlue);
		colorNames.add(textCyan);
		colorNames.add(textMagenta);
		colorNames.add(textOrange);
		colorNames.add(textPink);
		colorNames.add(textYellow);
		colorNames.add(textDarkGray);
		colorNames.add(textGray);
		colorNames.add(textLightGray);

		int count = colorNames.size();

		for (int index = 0; index < count; index++)
		{
			addItem(colorNames.get(index));
		}

		setRenderer(new MyListCellRenderer());
		addActionListener(new VP_ActionListener(this));
		setToolTipText(toolTip);
		setEnabled(false);

		locked = false;
	}

	private void getLocalizedStrings()
	{
		ResourceBundle bundle = ResourceBundle.getBundle("Localizable");

		textDefaultColor = bundle.getString("LineColorBox.Text.DefaultColor");
		textBlack = bundle.getString("LineColorBox.Text.Black");
		textRed = bundle.getString("LineColorBox.Text.Red");
		textGreen = bundle.getString("LineColorBox.Text.Green");
		textBlue = bundle.getString("LineColorBox.Text.Blue");
		textCyan = bundle.getString("LineColorBox.Text.Cyan");
		textMagenta = bundle.getString("LineColorBox.Text.Magenta");
		textOrange = bundle.getString("LineColorBox.Text.Orange");
		textPink = bundle.getString("LineColorBox.Text.Pink");
		textYellow = bundle.getString("LineColorBox.Text.Yellow");
		textDarkGray = bundle.getString("LineColorBox.Text.DarkGray");
		textGray = bundle.getString("LineColorBox.Text.Gray");
		textLightGray = bundle.getString("LineColorBox.Text.LightGray");
		toolTip = bundle.getString("LineColorBox.Text.ToolTip");
	}

	public void lock()
	{
		locked = true;
	}

	public void unlock()
	{
		locked = false;
	}

	public void setLineColor(Color lineColor, boolean hasLineColor)
	{
		if (hasLineColor)
		{
			setSelectedIndex(colors.lastIndexOf(lineColor));
		}
		else
		{
			setSelectedIndex(0);
		}
	}

	private final class MyListCellRenderer extends JLabel implements ListCellRenderer
	{
		public MyListCellRenderer()
		{
			setOpaque(true);
			setBorder(new EmptyBorder(1, 1, 1, 1));
		}

		public Component getListCellRendererComponent(JList list,
				Object value,
				int index,
				boolean isSelected,
				boolean cellHasFocus)
		{
			if (PaperSizes.LINE_SEPARATOR.equals((String)value))
				return new JSeparator(JSeparator.HORIZONTAL);

			if (isSelected)
			{
				setBackground(UIConstants.UI_COLOR_SELECTION);

				setForeground(list.getSelectionForeground());
			}
			else
			{
				setBackground(list.getBackground());

				if (index > -1)
					setForeground(colors.get(index));
			}

			setText((String)value);

			return this;
		}
	}

	private final class VP_ActionListener implements ActionListener
	{
		private UILineColorBox comboBox;
		private Object currentItem;

		public VP_ActionListener(UILineColorBox comboBox)
		{
			this.comboBox = comboBox;

			comboBox.setSelectedIndex(0);

			currentItem = comboBox.getSelectedItem();
		}

		public void actionPerformed(ActionEvent event)
		{
			String selectedItem = (String)comboBox.getSelectedItem();

			if (PaperSizes.LINE_SEPARATOR.equals(selectedItem))
			{
				comboBox.setSelectedItem(currentItem);
			}
			else
			{
				currentItem = selectedItem;

				if (!locked)
				{
					comboBox.setForeground(colors.get(comboBox.getSelectedIndex()));

					if (comboBox.getSelectedIndex() == 0)
					{
						VectorFiles.getInstance().setDefaultLineColor();
					}
					else
					{
						VectorFiles.getInstance().setLineColor(colors.get(comboBox.getSelectedIndex()));
					}
				}
			}
		}
	}
}
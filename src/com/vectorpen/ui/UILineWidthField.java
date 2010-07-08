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

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.InternationalFormatter;

import com.vectorpen.core.VectorFile;

@SuppressWarnings("serial")
public final class UILineWidthField extends JSpinner implements KeyListener
{
	private static final double LINE_WIDTH_MAX = 50.0;

	private static UILineWidthField instance;

	private String toolTip;
	private JFormattedTextField textField;

	public static synchronized UILineWidthField getInstance()
	{
		if (instance == null)
			instance = new UILineWidthField();

		return instance;
	}

	private UILineWidthField()
	{
		getLocalizedStrings();
		setModel(new SpinnerNumberModel(VectorFile.DEFAULT_LINE_WIDTH, VectorFile.LINE_WIDTH_MIN, LINE_WIDTH_MAX, VectorFile.LINE_WIDTH_MIN));
		setToolTipText(toolTip);
		setEnabled(false);

		JSpinner.DefaultEditor defaultEditor = (JSpinner.DefaultEditor)getEditor();
		textField = defaultEditor.getTextField();
		textField.addKeyListener(this);
		textField.setText(Float.toString(VectorFile.DEFAULT_LINE_WIDTH));

		textField.setSelectionColor(UIConstants.UI_COLOR_SELECTION);

		InternationalFormatter intFormatter = (InternationalFormatter)textField.getFormatter();
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
		DecimalFormat decimalFormat = (DecimalFormat)intFormatter.getFormat();
		decimalFormat.applyPattern("0.0");
		decimalFormat.setDecimalFormatSymbols(symbols);
	}

	private void getLocalizedStrings()
	{
		ResourceBundle bundle = ResourceBundle.getBundle("Localizable");

		toolTip = bundle.getString("LineWidthField.Text.ToolTip");
	}

	public void keyPressed(KeyEvent event)
	{
		if (event.getKeyCode() == KeyEvent.VK_ENTER)
		{
			try
			{
				commitEdit();

				Double.parseDouble(textField.getText());
			}
			catch (Exception exception1)
			{
				try
				{
					double value = Double.parseDouble(textField.getText());

					if (value > LINE_WIDTH_MAX)
					{
						textField.setText(Double.toString(LINE_WIDTH_MAX));
					}
					else if (value < VectorFile.LINE_WIDTH_MIN)
					{
						textField.setText(Double.toString(VectorFile.LINE_WIDTH_MIN));
					}
				}
				catch (Exception exception2)
				{
					textField.setText(Double.toString((Double)getModel().getValue()));
				}
			}

			textField.selectAll();

			float lineWidth = Float.parseFloat(textField.getText());

			VectorFiles.getInstance().setLineWidth(lineWidth);
		}
	}

	public void keyTyped(KeyEvent event)
	{
	}

	public void keyReleased(KeyEvent event)
	{
	}

	public void setLineWidth(float lineWidth)
	{
		textField.setText(Float.toString(lineWidth));
	}
}
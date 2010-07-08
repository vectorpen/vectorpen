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

import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public final class UISecondaryToolbar extends JPanel
{
	private static UISecondaryToolbar instance;

	private String labelPaperSize;
	private String labelLineColor;
	private String labelLineWidth;

	public static synchronized UISecondaryToolbar getInstance()
	{
		if (instance == null)
			instance = new UISecondaryToolbar();

		return instance;
	}

	private UISecondaryToolbar()
	{
		getLocalizedStrings();

		JPanel paperSizesPanel = new JPanel();
		paperSizesPanel.setLayout(new BoxLayout(paperSizesPanel, BoxLayout.Y_AXIS));
		paperSizesPanel.setBorder(BorderFactory.createTitledBorder(labelPaperSize));
		paperSizesPanel.add(UIPaperSizesBox.getInstance());
		paperSizesPanel.setOpaque(false);

		JPanel lineColorPanel = new JPanel();
		lineColorPanel.setLayout(new BoxLayout(lineColorPanel, BoxLayout.Y_AXIS));
		lineColorPanel.setBorder(BorderFactory.createTitledBorder(labelLineColor));
		lineColorPanel.add(UILineColorBox.getInstance());
		lineColorPanel.setOpaque(false);

		JPanel lineWidthPanel = new JPanel();
		lineWidthPanel.setLayout(new BoxLayout(lineWidthPanel, BoxLayout.Y_AXIS));
		lineWidthPanel.setBorder(BorderFactory.createTitledBorder(labelLineWidth));
		lineWidthPanel.add(UILineWidthField.getInstance());
		lineWidthPanel.setOpaque(false);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setOpaque(false);

		add(paperSizesPanel);
		add(lineColorPanel);
		add(lineWidthPanel);
		add(new JLabel(" "));
	}

	private void getLocalizedStrings()
	{
		ResourceBundle bundle = ResourceBundle.getBundle("Localizable");

		labelPaperSize = bundle.getString("SecondaryToolbar.Label.PaperSize");
		labelLineColor = bundle.getString("SecondaryToolbar.Label.LineColor");
		labelLineWidth = bundle.getString("SecondaryToolbar.Label.LineWidth");
	}

	public void updateComponents()
	{
		int selectedRow = UIMainTable.getInstance().getSelectedRow();

		if (selectedRow != -1)
		{
			UIPaperSizesBox.getInstance().setEnabled(true);
			UILineColorBox.getInstance().setEnabled(true);
			UILineWidthField.getInstance().setEnabled(true);

			UIPaperSizesBox.getInstance().setFocusable(true);
			UILineColorBox.getInstance().setFocusable(true);
			UILineWidthField.getInstance().setFocusable(true);

			UIMainTable.getInstance().setFocusable(true);
		}
		else
		{
			UIPaperSizesBox.getInstance().setEnabled(false);
			UILineColorBox.getInstance().setEnabled(false);
			UILineWidthField.getInstance().setEnabled(false);

			UIPaperSizesBox.getInstance().setFocusable(false);
			UILineColorBox.getInstance().setFocusable(false);
			UILineWidthField.getInstance().setFocusable(false);

			UIMainTable.getInstance().setFocusable(false);
		}
	}
}
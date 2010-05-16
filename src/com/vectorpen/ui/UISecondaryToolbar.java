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
package com.vectorpen.ui;

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
public final class UIPaperSizesBox extends JComboBox
{
	private static UIPaperSizesBox instance;

	private String toolTip;
	private boolean locked;

	public static synchronized UIPaperSizesBox getInstance()
	{
		if (instance == null)
			instance = new UIPaperSizesBox();

		return instance;
	}

	private UIPaperSizesBox()
	{
		getLocalizedStrings();

		ArrayList<String> paperSizeNames = PaperSizes.getPaperSizeNames();

		int count = paperSizeNames.size();

		for (int index = 0; index < count; index++)
		{
			addItem(paperSizeNames.get(index));
		}

		setRenderer(new VP_ListCellRenderer());
		addActionListener(new MyActionListener(this));
		setToolTipText(toolTip);
		setEnabled(false);

		locked = false;
	}

	private void getLocalizedStrings()
	{
		ResourceBundle bundle = ResourceBundle.getBundle("Localizable");

		toolTip = bundle.getString("PaperSizesBox.Text.ToolTip");
	}

	public void lock()
	{
		locked = true;
	}

	public void unlock()
	{
		locked = false;
	}

	public void setPaperSize(String paperSize)
	{
		setSelectedItem(paperSize);
	}

	private final class VP_ListCellRenderer extends JLabel implements ListCellRenderer
	{
		public VP_ListCellRenderer()
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
				setForeground(list.getForeground());
			}

			setText((String)value);

			return this;
		}
	}

	private final class MyActionListener implements ActionListener
	{
		private UIPaperSizesBox comboBox;
		private Object currentItem;

		public MyActionListener(UIPaperSizesBox comboBox)
		{
			this.comboBox = comboBox;

			String paperSize = PaperSizes.DEFAULT_PAPERSIZENAME;
			comboBox.setPaperSize(paperSize);

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
					VectorFiles.getInstance().setPaperSizeName(selectedItem);
				}
			}
		}
	}
}
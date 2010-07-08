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
import java.awt.Dimension;
import java.awt.Graphics;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import com.vectorpen.core.VectorFile;

@SuppressWarnings("serial")
public final class UIMainToolbar extends JToolBar
{
	private static final URL IMAGE_PATH_ADD_NORMAL;
	private static final URL IMAGE_PATH_ADD_DISABLED;
	private static final URL IMAGE_PATH_ADD_ROLLOVER;
	private static final URL IMAGE_PATH_ADD_PRESSED;
	private static final URL IMAGE_PATH_REMOVE_NORMAL;
	private static final URL IMAGE_PATH_REMOVE_DISABLED;
	private static final URL IMAGE_PATH_REMOVE_ROLLOVER;
	private static final URL IMAGE_PATH_REMOVE_PRESSED;
	private static final URL IMAGE_PATH_ROTATE_LEFT_NORMAL;
	private static final URL IMAGE_PATH_ROTATE_LEFT_DISABLED;
	private static final URL IMAGE_PATH_ROTATE_LEFT_ROLLOVER;
	private static final URL IMAGE_PATH_ROTATE_LEFT_PRESSED;
	private static final URL IMAGE_PATH_SEPARATOR;
	private static final URL IMAGE_PATH_ROTATE_RIGHT_NORMAL;
	private static final URL IMAGE_PATH_ROTATE_RIGHT_DISABLED;
	private static final URL IMAGE_PATH_ROTATE_RIGHT_ROLLOVER;
	private static final URL IMAGE_PATH_ROTATE_RIGHT_PRESSED;
	private static final URL IMAGE_PATH_ZOOM_IN_NORMAL;
	private static final URL IMAGE_PATH_ZOOM_IN_DISABLED;
	private static final URL IMAGE_PATH_ZOOM_IN_ROLLOVER;
	private static final URL IMAGE_PATH_ZOOM_IN_PRESSED;
	private static final URL IMAGE_PATH_ZOOM_ACTUAL_SIZE_NORMAL;
	private static final URL IMAGE_PATH_ZOOM_ACTUAL_SIZE_DISABLED;
	private static final URL IMAGE_PATH_ZOOM_ACTUAL_SIZE_ROLLOVER;
	private static final URL IMAGE_PATH_ZOOM_ACTUAL_SIZE_PRESSED;
	private static final URL IMAGE_PATH_ZOOM_OUT_NORMAL;
	private static final URL IMAGE_PATH_ZOOM_OUT_DISABLED;
	private static final URL IMAGE_PATH_ZOOM_OUT_ROLLOVER;
	private static final URL IMAGE_PATH_ZOOM_OUT_PRESSED;
	private static final URL IMAGE_PATH_CONVERT_NORMAL;
	private static final URL IMAGE_PATH_CONVERT_DISABLED;
	private static final URL IMAGE_PATH_CONVERT_ROLLOVER;
	private static final URL IMAGE_PATH_CONVERT_PRESSED;
	private static final URL IMAGE_PATH_BACKGROUND;

	static
	{
		IMAGE_PATH_ADD_NORMAL = UIMainToolbar.class.getResource("/com/vectorpen/img/Add-Normal.png");
		IMAGE_PATH_ADD_DISABLED = UIMainToolbar.class.getResource("/com/vectorpen/img/Add-Disabled.png");
		IMAGE_PATH_ADD_ROLLOVER = UIMainToolbar.class.getResource("/com/vectorpen/img/Add-Rollover.png");
		IMAGE_PATH_ADD_PRESSED = UIMainToolbar.class.getResource("/com/vectorpen/img/Add-Pressed.png");
		IMAGE_PATH_REMOVE_NORMAL = UIMainToolbar.class.getResource("/com/vectorpen/img/Remove-Normal.png");
		IMAGE_PATH_REMOVE_DISABLED = UIMainToolbar.class.getResource("/com/vectorpen/img/Remove-Disabled.png");
		IMAGE_PATH_REMOVE_ROLLOVER = UIMainToolbar.class.getResource("/com/vectorpen/img/Remove-Rollover.png");
		IMAGE_PATH_REMOVE_PRESSED = UIMainToolbar.class.getResource("/com/vectorpen/img/Remove-Pressed.png");
		IMAGE_PATH_ROTATE_LEFT_NORMAL = UIMainToolbar.class.getResource("/com/vectorpen/img/Rotate-Left-Normal.png");
		IMAGE_PATH_ROTATE_LEFT_DISABLED = UIMainToolbar.class.getResource("/com/vectorpen/img/Rotate-Left-Disabled.png");
		IMAGE_PATH_ROTATE_LEFT_ROLLOVER = UIMainToolbar.class.getResource("/com/vectorpen/img/Rotate-Left-Rollover.png");
		IMAGE_PATH_ROTATE_LEFT_PRESSED = UIMainToolbar.class.getResource("/com/vectorpen/img/Rotate-Left-Pressed.png");
		IMAGE_PATH_SEPARATOR = UIMainToolbar.class.getResource("/com/vectorpen/img/Separator.png");
		IMAGE_PATH_ROTATE_RIGHT_NORMAL = UIMainToolbar.class.getResource("/com/vectorpen/img/Rotate-Right-Normal.png");
		IMAGE_PATH_ROTATE_RIGHT_DISABLED = UIMainToolbar.class.getResource("/com/vectorpen/img/Rotate-Right-Disabled.png");
		IMAGE_PATH_ROTATE_RIGHT_ROLLOVER = UIMainToolbar.class.getResource("/com/vectorpen/img/Rotate-Right-Rollover.png");
		IMAGE_PATH_ROTATE_RIGHT_PRESSED = UIMainToolbar.class.getResource("/com/vectorpen/img/Rotate-Right-Pressed.png");
		IMAGE_PATH_ZOOM_IN_NORMAL = UIMainToolbar.class.getResource("/com/vectorpen/img/Zoom-In-Normal.png");
		IMAGE_PATH_ZOOM_IN_DISABLED = UIMainToolbar.class.getResource("/com/vectorpen/img/Zoom-In-Disabled.png");
		IMAGE_PATH_ZOOM_IN_ROLLOVER = UIMainToolbar.class.getResource("/com/vectorpen/img/Zoom-In-Rollover.png");
		IMAGE_PATH_ZOOM_IN_PRESSED = UIMainToolbar.class.getResource("/com/vectorpen/img/Zoom-In-Pressed.png");
		IMAGE_PATH_ZOOM_ACTUAL_SIZE_NORMAL = UIMainToolbar.class.getResource("/com/vectorpen/img/Zoom-Actual-Size-Normal.png");
		IMAGE_PATH_ZOOM_ACTUAL_SIZE_DISABLED = UIMainToolbar.class.getResource("/com/vectorpen/img/Zoom-Actual-Size-Disabled.png");
		IMAGE_PATH_ZOOM_ACTUAL_SIZE_ROLLOVER = UIMainToolbar.class.getResource("/com/vectorpen/img/Zoom-Actual-Size-Rollover.png");
		IMAGE_PATH_ZOOM_ACTUAL_SIZE_PRESSED = UIMainToolbar.class.getResource("/com/vectorpen/img/Zoom-Actual-Size-Pressed.png");
		IMAGE_PATH_ZOOM_OUT_NORMAL = UIMainToolbar.class.getResource("/com/vectorpen/img/Zoom-Out-Normal.png");
		IMAGE_PATH_ZOOM_OUT_DISABLED = UIMainToolbar.class.getResource("/com/vectorpen/img/Zoom-Out-Disabled.png");
		IMAGE_PATH_ZOOM_OUT_ROLLOVER = UIMainToolbar.class.getResource("/com/vectorpen/img/Zoom-Out-Rollover.png");
		IMAGE_PATH_ZOOM_OUT_PRESSED = UIMainToolbar.class.getResource("/com/vectorpen/img/Zoom-Out-Pressed.png");
		IMAGE_PATH_CONVERT_NORMAL = UIMainToolbar.class.getResource("/com/vectorpen/img/Convert-Normal.png");
		IMAGE_PATH_CONVERT_DISABLED = UIMainToolbar.class.getResource("/com/vectorpen/img/Convert-Disabled.png");
		IMAGE_PATH_CONVERT_ROLLOVER = UIMainToolbar.class.getResource("/com/vectorpen/img/Convert-Rollover.png");
		IMAGE_PATH_CONVERT_PRESSED = UIMainToolbar.class.getResource("/com/vectorpen/img/Convert-Pressed.png");
		IMAGE_PATH_BACKGROUND = UIMainToolbar.class.getResource("/com/vectorpen/img/Main-Toolbar-Background.png");
	}

	private static final Color COLOR_LABEL = Color.WHITE;

	private static UIMainToolbar instance;

	private String labelRotate;
	private String labelZoom;
	private String textAdd;
	private String textRemove;
	private String textConvert;
	private JButton addButton;
	private JButton removeButton;
	private JButton rotateLeftButton;
	private JButton rotateSeparatorButton;
	private JButton rotateRightButton;
	private JButton zoomInButton;
	private JButton zoomActualSizeButton;
	private JButton zoomOutButton;
	private JButton convertButton;
	private JPanel addButtonPanel;
	private JPanel removeButtonPanel;
	private JPanel rotateLeftButtonPanel;
	private JPanel rotateSeparatorButtonPanel;
	private JPanel rotateRightButtonPanel;
	private JPanel zoomInButtonPanel;
	private JPanel zoomActualSizeButtonPanel;
	private JPanel zoomOutButtonPanel;
	private JPanel convertButtonPanel;
	private ImageIcon background;

	public static synchronized UIMainToolbar getInstance()
	{
		if (instance == null)
			instance = new UIMainToolbar();

		return instance;
	}

	private UIMainToolbar()
	{
		super(JToolBar.HORIZONTAL);

		getLocalizedStrings();

		background = new ImageIcon(IMAGE_PATH_BACKGROUND);

		createAddButton();
		createRemoveButton();
		createRotateLeftButton();
		createRotateSeparatorButton();
		createRotateRightButton();
		createZoomInButton();
		createActualSizeButton();
		createZoomOutButton();
		createConvertButton();

		setFloatable(false);
		setRollover(false);
		setOpaque(false);

		Dimension bigSeparatorSize;
		Dimension smallSeparatorSize;

		if (Environment.isMac())
		{
			bigSeparatorSize = new Dimension(20, 0);
			smallSeparatorSize = new Dimension(10, 0);
		}
		else
		{
			bigSeparatorSize = new Dimension(12, 0);
			smallSeparatorSize = new Dimension(0, 0);
		}

		addSeparator(bigSeparatorSize);
		add(addButtonPanel);
		addSeparator(smallSeparatorSize);
		add(removeButtonPanel);
		addSeparator(bigSeparatorSize);
		addSeparator();
		addSeparator(bigSeparatorSize);
		add(rotateLeftButtonPanel);
		add(rotateSeparatorButtonPanel);
		add(rotateRightButtonPanel);
		addSeparator(bigSeparatorSize);
		addSeparator();
		addSeparator(bigSeparatorSize);
		add(zoomInButtonPanel);
		addSeparator(smallSeparatorSize);
		add(zoomActualSizeButtonPanel);
		addSeparator(smallSeparatorSize);
		add(zoomOutButtonPanel);
		addSeparator(bigSeparatorSize);
		addSeparator();
		addSeparator(bigSeparatorSize);
		add(convertButtonPanel);
	}

	private void getLocalizedStrings()
	{
		ResourceBundle bundle = ResourceBundle.getBundle("Localizable");

		labelRotate = bundle.getString("MainToolbar.Label.Rotate");
		labelZoom = bundle.getString("MainToolbar.Label.Zoom");
		textAdd = bundle.getString("Actions.Text.Add");
		textRemove = bundle.getString("Actions.Text.Remove");
		textConvert = bundle.getString("Actions.Text.Convert");
	}

	private void createAddButton()
	{
		URL normalPath = IMAGE_PATH_ADD_NORMAL;
		URL disabledPath = IMAGE_PATH_ADD_DISABLED;
		URL rolloverPath = IMAGE_PATH_ADD_ROLLOVER;
		URL pressedPath = IMAGE_PATH_ADD_PRESSED;

		Action action = Actions.getInstance().getAdd();

		addButton = new JButton(action);
		addButton.setIcon(new ImageIcon(normalPath));
		addButton.setDisabledIcon(new ImageIcon(disabledPath));
		addButton.setRolloverIcon(new ImageIcon(rolloverPath));
		addButton.setPressedIcon(new ImageIcon(pressedPath));
		addButton.setText(null);
		addButton.setEnabled(true);
		addButton.setBorderPainted(false);
		addButton.setContentAreaFilled(false);
		addButton.setFocusable(false);
		addButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel label = new JLabel(textAdd);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setFont(addButton.getFont());
		label.setForeground(COLOR_LABEL);

		addButtonPanel = new JPanel();
		addButtonPanel.setLayout(new BoxLayout(addButtonPanel, BoxLayout.PAGE_AXIS));
		addButtonPanel.setOpaque(false);
		addButtonPanel.add(addButton);
		addButtonPanel.add(label);
		addButtonPanel.setMinimumSize(addButtonPanel.getPreferredSize());
		addButtonPanel.setMaximumSize(addButtonPanel.getPreferredSize());
	}

	private void createRemoveButton()
	{
		URL normalPath = IMAGE_PATH_REMOVE_NORMAL;
		URL disabledPath = IMAGE_PATH_REMOVE_DISABLED;
		URL rolloverPath = IMAGE_PATH_REMOVE_ROLLOVER;
		URL pressedPath = IMAGE_PATH_REMOVE_PRESSED;

		Action action = Actions.getInstance().getRemove();

		removeButton = new JButton(action);
		removeButton.setIcon(new ImageIcon(normalPath));
		removeButton.setDisabledIcon(new ImageIcon(disabledPath));
		removeButton.setRolloverIcon(new ImageIcon(rolloverPath));
		removeButton.setPressedIcon(new ImageIcon(pressedPath));
		removeButton.setText(null);
		removeButton.setEnabled(false);
		removeButton.setBorderPainted(false);
		removeButton.setContentAreaFilled(false);
		removeButton.setFocusable(false);
		removeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel label = new JLabel(textRemove);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setFont(removeButton.getFont());
		label.setForeground(COLOR_LABEL);

		removeButtonPanel = new JPanel();
		removeButtonPanel.setLayout(new BoxLayout(removeButtonPanel, BoxLayout.PAGE_AXIS));
		removeButtonPanel.setOpaque(false);
		removeButtonPanel.add(removeButton);
		removeButtonPanel.add(label);
		removeButtonPanel.setMinimumSize(removeButtonPanel.getPreferredSize());
		removeButtonPanel.setMaximumSize(removeButtonPanel.getPreferredSize());
	}

	private void createRotateLeftButton()
	{
		URL normalPath = IMAGE_PATH_ROTATE_LEFT_NORMAL;
		URL disabledPath = IMAGE_PATH_ROTATE_LEFT_DISABLED;
		URL rolloverPath = IMAGE_PATH_ROTATE_LEFT_ROLLOVER;
		URL pressedPath = IMAGE_PATH_ROTATE_LEFT_PRESSED;

		Action action = Actions.getInstance().getRotateLeft();

		rotateLeftButton = new JButton(action);
		rotateLeftButton.setIcon(new ImageIcon(normalPath));
		rotateLeftButton.setDisabledIcon(new ImageIcon(disabledPath));
		rotateLeftButton.setRolloverIcon(new ImageIcon(rolloverPath));
		rotateLeftButton.setPressedIcon(new ImageIcon(pressedPath));
		rotateLeftButton.setText(null);
		rotateLeftButton.setEnabled(false);
		rotateLeftButton.setBorderPainted(false);
		rotateLeftButton.setContentAreaFilled(false);
		rotateLeftButton.setFocusable(false);
		rotateLeftButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel label = new JLabel(" ");
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setFont(rotateLeftButton.getFont());
		label.setForeground(COLOR_LABEL);

		rotateLeftButtonPanel = new JPanel();
		rotateLeftButtonPanel.setLayout(new BoxLayout(rotateLeftButtonPanel, BoxLayout.PAGE_AXIS));
		rotateLeftButtonPanel.setOpaque(false);
		rotateLeftButtonPanel.add(rotateLeftButton);
		rotateLeftButtonPanel.add(label);
		rotateLeftButtonPanel.setMinimumSize(rotateLeftButtonPanel.getPreferredSize());
		rotateLeftButtonPanel.setMaximumSize(rotateLeftButtonPanel.getPreferredSize());
	}

	private void createRotateSeparatorButton()
	{
		URL path = IMAGE_PATH_SEPARATOR;

		rotateSeparatorButton = new JButton();
		rotateSeparatorButton.setIcon(new ImageIcon(path));
		rotateSeparatorButton.setText(null);
		rotateSeparatorButton.setEnabled(false);
		rotateSeparatorButton.setBorderPainted(false);
		rotateSeparatorButton.setContentAreaFilled(false);
		rotateSeparatorButton.setFocusable(false);
		rotateSeparatorButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel label = new JLabel(labelRotate);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setFont(rotateSeparatorButton.getFont());
		label.setForeground(COLOR_LABEL);

		rotateSeparatorButtonPanel = new JPanel();
		rotateSeparatorButtonPanel.setLayout(new BoxLayout(rotateSeparatorButtonPanel, BoxLayout.PAGE_AXIS));
		rotateSeparatorButtonPanel.setOpaque(false);
		rotateSeparatorButtonPanel.add(rotateSeparatorButton);
		rotateSeparatorButtonPanel.add(label);
		rotateSeparatorButtonPanel.setMinimumSize(rotateSeparatorButtonPanel.getPreferredSize());
		rotateSeparatorButtonPanel.setMaximumSize(rotateSeparatorButtonPanel.getPreferredSize());
	}

	private void createRotateRightButton()
	{
		URL normalPath = IMAGE_PATH_ROTATE_RIGHT_NORMAL;
		URL disabledPath = IMAGE_PATH_ROTATE_RIGHT_DISABLED;
		URL rolloverPath = IMAGE_PATH_ROTATE_RIGHT_ROLLOVER;
		URL pressedPath = IMAGE_PATH_ROTATE_RIGHT_PRESSED;

		Action action = Actions.getInstance().getRotateRight();

		rotateRightButton = new JButton(action);
		rotateRightButton.setIcon(new ImageIcon(normalPath));
		rotateRightButton.setDisabledIcon(new ImageIcon(disabledPath));
		rotateRightButton.setRolloverIcon(new ImageIcon(rolloverPath));
		rotateRightButton.setPressedIcon(new ImageIcon(pressedPath));
		rotateRightButton.setText(null);
		rotateRightButton.setEnabled(false);
		rotateRightButton.setBorderPainted(false);
		rotateRightButton.setContentAreaFilled(false);
		rotateRightButton.setFocusable(false);
		rotateRightButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel label = new JLabel(" ");
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setFont(rotateRightButton.getFont());
		label.setForeground(COLOR_LABEL);

		rotateRightButtonPanel = new JPanel();
		rotateRightButtonPanel.setLayout(new BoxLayout(rotateRightButtonPanel, BoxLayout.PAGE_AXIS));
		rotateRightButtonPanel.setOpaque(false);
		rotateRightButtonPanel.add(rotateRightButton);
		rotateRightButtonPanel.add(label);
		rotateRightButtonPanel.setMinimumSize(rotateRightButtonPanel.getPreferredSize());
		rotateRightButtonPanel.setMaximumSize(rotateRightButtonPanel.getPreferredSize());
	}

	private void createZoomInButton()
	{
		URL normalPath = IMAGE_PATH_ZOOM_IN_NORMAL;
		URL disabledPath = IMAGE_PATH_ZOOM_IN_DISABLED;
		URL rolloverPath = IMAGE_PATH_ZOOM_IN_ROLLOVER;
		URL pressedPath = IMAGE_PATH_ZOOM_IN_PRESSED;

		Action action = Actions.getInstance().getZoomIn();

		zoomInButton = new JButton(action);
		zoomInButton.setIcon(new ImageIcon(normalPath));
		zoomInButton.setDisabledIcon(new ImageIcon(disabledPath));
		zoomInButton.setRolloverIcon(new ImageIcon(rolloverPath));
		zoomInButton.setPressedIcon(new ImageIcon(pressedPath));
		zoomInButton.setText(null);
		zoomInButton.setEnabled(false);
		zoomInButton.setBorderPainted(false);
		zoomInButton.setContentAreaFilled(false);
		zoomInButton.setFocusable(false);
		zoomInButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel label = new JLabel(" ");
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setFont(zoomInButton.getFont());
		label.setForeground(COLOR_LABEL);

		zoomInButtonPanel = new JPanel();
		zoomInButtonPanel.setLayout(new BoxLayout(zoomInButtonPanel, BoxLayout.PAGE_AXIS));
		zoomInButtonPanel.setOpaque(false);
		zoomInButtonPanel.add(zoomInButton);
		zoomInButtonPanel.add(label);
		zoomInButtonPanel.setMinimumSize(zoomInButtonPanel.getPreferredSize());
		zoomInButtonPanel.setMaximumSize(zoomInButtonPanel.getPreferredSize());
	}

	private void createActualSizeButton()
	{
		URL normalPath = IMAGE_PATH_ZOOM_ACTUAL_SIZE_NORMAL;
		URL disabledPath = IMAGE_PATH_ZOOM_ACTUAL_SIZE_DISABLED;
		URL rolloverPath = IMAGE_PATH_ZOOM_ACTUAL_SIZE_ROLLOVER;
		URL pressedPath = IMAGE_PATH_ZOOM_ACTUAL_SIZE_PRESSED;

		Action action = Actions.getInstance().getActualSize();

		zoomActualSizeButton = new JButton(action);
		zoomActualSizeButton.setIcon(new ImageIcon(normalPath));
		zoomActualSizeButton.setDisabledIcon(new ImageIcon(disabledPath));
		zoomActualSizeButton.setRolloverIcon(new ImageIcon(rolloverPath));
		zoomActualSizeButton.setPressedIcon(new ImageIcon(pressedPath));
		zoomActualSizeButton.setText(null);
		zoomActualSizeButton.setEnabled(false);
		zoomActualSizeButton.setBorderPainted(false);
		zoomActualSizeButton.setContentAreaFilled(false);
		zoomActualSizeButton.setFocusable(false);
		zoomActualSizeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel label = new JLabel(labelZoom);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setFont(zoomActualSizeButton.getFont());
		label.setForeground(COLOR_LABEL);

		zoomActualSizeButtonPanel = new JPanel();
		zoomActualSizeButtonPanel.setLayout(new BoxLayout(zoomActualSizeButtonPanel, BoxLayout.PAGE_AXIS));
		zoomActualSizeButtonPanel.setOpaque(false);
		zoomActualSizeButtonPanel.add(zoomActualSizeButton);
		zoomActualSizeButtonPanel.add(label);
		zoomActualSizeButtonPanel.setMinimumSize(zoomActualSizeButtonPanel.getPreferredSize());
		zoomActualSizeButtonPanel.setMaximumSize(zoomActualSizeButtonPanel.getPreferredSize());
	}

	private void createZoomOutButton()
	{
		URL normalPath = IMAGE_PATH_ZOOM_OUT_NORMAL;
		URL disabledPath = IMAGE_PATH_ZOOM_OUT_DISABLED;
		URL rolloverPath = IMAGE_PATH_ZOOM_OUT_ROLLOVER;
		URL pressedPath = IMAGE_PATH_ZOOM_OUT_PRESSED;

		Action action = Actions.getInstance().getZoomOut();

		zoomOutButton = new JButton(action);
		zoomOutButton.setIcon(new ImageIcon(normalPath));
		zoomOutButton.setDisabledIcon(new ImageIcon(disabledPath));
		zoomOutButton.setRolloverIcon(new ImageIcon(rolloverPath));
		zoomOutButton.setPressedIcon(new ImageIcon(pressedPath));
		zoomOutButton.setText(null);
		zoomOutButton.setEnabled(false);
		zoomOutButton.setBorderPainted(false);
		zoomOutButton.setContentAreaFilled(false);
		zoomOutButton.setFocusable(false);
		zoomOutButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel label = new JLabel(" ");
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setFont(zoomOutButton.getFont());
		label.setForeground(COLOR_LABEL);

		zoomOutButtonPanel = new JPanel();
		zoomOutButtonPanel.setLayout(new BoxLayout(zoomOutButtonPanel, BoxLayout.PAGE_AXIS));
		zoomOutButtonPanel.setOpaque(false);
		zoomOutButtonPanel.add(zoomOutButton);
		zoomOutButtonPanel.add(label);
		zoomOutButtonPanel.setMinimumSize(zoomOutButtonPanel.getPreferredSize());
		zoomOutButtonPanel.setMaximumSize(zoomOutButtonPanel.getPreferredSize());
	}

	private void createConvertButton()
	{
		URL normalPath = IMAGE_PATH_CONVERT_NORMAL;
		URL disabledPath = IMAGE_PATH_CONVERT_DISABLED;
		URL rolloverPath = IMAGE_PATH_CONVERT_ROLLOVER;
		URL pressedPath = IMAGE_PATH_CONVERT_PRESSED;

		Action action = Actions.getInstance().getConvert();

		convertButton = new JButton(action);
		convertButton.setIcon(new ImageIcon(normalPath));
		convertButton.setDisabledIcon(new ImageIcon(disabledPath));
		convertButton.setRolloverIcon(new ImageIcon(rolloverPath));
		convertButton.setPressedIcon(new ImageIcon(pressedPath));
		convertButton.setText(null);
		convertButton.setEnabled(false);
		convertButton.setBorderPainted(false);
		convertButton.setContentAreaFilled(false);
		convertButton.setFocusable(false);
		convertButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel label = new JLabel(textConvert);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setFont(convertButton.getFont());
		label.setForeground(COLOR_LABEL);

		convertButtonPanel = new JPanel();
		convertButtonPanel.setLayout(new BoxLayout(convertButtonPanel, BoxLayout.PAGE_AXIS));
		convertButtonPanel.setOpaque(false);
		convertButtonPanel.add(convertButton);
		convertButtonPanel.add(label);
		convertButtonPanel.setMinimumSize(convertButtonPanel.getPreferredSize());
		convertButtonPanel.setMaximumSize(convertButtonPanel.getPreferredSize());
	}

	public void updateButtons()
	{
		int selectedRow = UIMainTable.getInstance().getSelectedRow();

		if (selectedRow != -1)
		{
			removeButton.setEnabled(true);
			rotateLeftButton.setEnabled(true);
			rotateRightButton.setEnabled(true);

			int zoom = VectorFiles.getInstance().getZoom(selectedRow);

			if (zoom < VectorFile.ZOOM_MAX)
			{
				zoomInButton.setEnabled(true);
			}
			else
			{
				zoomInButton.setEnabled(false);
			}

			if (zoom != VectorFile.ZOOM_ACTUAL_SIZE)
			{
				zoomActualSizeButton.setEnabled(true);
			}
			else
			{
				zoomActualSizeButton.setEnabled(false);
			}

			if (zoom > VectorFile.ZOOM_MIN)
			{
				zoomOutButton.setEnabled(true);
			}
			else
			{
				zoomOutButton.setEnabled(false);
			}

			convertButton.setEnabled(true);
		}
		else
		{
			removeButton.setEnabled(false);
			rotateLeftButton.setEnabled(false);		
			rotateRightButton.setEnabled(false);
			zoomInButton.setEnabled(false);
			zoomActualSizeButton.setEnabled(false);
			zoomOutButton.setEnabled(false);
			convertButton.setEnabled(false);
		}
	}

	public void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);

		graphics.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
	}
}
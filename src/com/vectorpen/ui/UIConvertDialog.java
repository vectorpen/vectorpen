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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import com.vectorpen.core.DocInfoDict;
import com.vectorpen.core.PDFModule;
import com.vectorpen.core.VectorFile;

@SuppressWarnings("serial")
public final class UIConvertDialog extends JDialog
{
	private static final String EXCEPTION_ENGINE_CODE_CONVERT = "-1VP_M";

	private static final String TEXT1;
	private static final String TEXT2;
	private static final String TEXT3;
	private static final String TEXT4;
	private static final String TEXT5;
	private static final String TEXT6;
	private static final String TEXT7;
	private static final String TEXT8;
	private static final String TEXT9;
	private static final String TEXT10;
	private static final String TEXT11;
	private static final String TEXT12;
	private static final String TEXT13;
	private static final String TEXT14;
	private static final String TEXT15;
	private static final String TEXT16;
	private static final String TEXT17;
	private static final String TEXT18;
	private static final String TEXT19;
	private static final String TEXT20;
	private static final String TEXT21;
	private static final String TEXT22;
	private static final String TEXT23;
	private static final String TEXT24;
	private static final String TEXT25;
	private static final String TEXT26;
	private static final String TEXT27;
	private static final String TEXT28;
	private static final String TEXT29;
	private static final String TEXT30;
	private static final String TEXT31;
	private static final String TEXT32;
	private static final String TEXT33;
	private static final String TEXT34;
	private static final String TEXT35;
	private static final String TEXT36;
	private static final String TEXT37;
	private static final String TEXT38;
	private static final String TEXT39;
	private static final String TEXT40;
	private static final String TEXT41;
	private static final String TEXT42;
	private static final String TEXT43;
	private static final String TEXT44;
	private static final String TEXT45;
	private static final String TEXT46;
	private static final String TEXT47;
	private static final String TEXT48;
	private static final String TEXT49;
	private static final String TEXT50;
	private static final String TEXT51;
	private static final String TEXT52;
	private static final String TEXT53;
	private static final String TEXT54;
	private static final String TEXT55;

	static
	{
		ResourceBundle bundle = ResourceBundle.getBundle("Localizable");

		TEXT1 = bundle.getString("ConvertDialog.TEXT1");
		TEXT2 = bundle.getString("ConvertDialog.TEXT2");
		TEXT3 = bundle.getString("ConvertDialog.TEXT3");
		TEXT4 = bundle.getString("ConvertDialog.TEXT4");
		TEXT5 = bundle.getString("ConvertDialog.TEXT5");
		TEXT6 = bundle.getString("ConvertDialog.TEXT6");
		TEXT7 = bundle.getString("ConvertDialog.TEXT7");
		TEXT8 = bundle.getString("ConvertDialog.TEXT8");
		TEXT9 = bundle.getString("ConvertDialog.TEXT9");
		TEXT10 = bundle.getString("ConvertDialog.TEXT10");
		TEXT11 = bundle.getString("ConvertDialog.TEXT11");
		TEXT12 = bundle.getString("ConvertDialog.TEXT12");
		TEXT13 = bundle.getString("ConvertDialog.TEXT13");
		TEXT14 = bundle.getString("ConvertDialog.TEXT14");
		TEXT15 = bundle.getString("ConvertDialog.TEXT15");
		TEXT16 = bundle.getString("ConvertDialog.TEXT16");
		TEXT17 = bundle.getString("ConvertDialog.TEXT17");
		TEXT18 = bundle.getString("ConvertDialog.TEXT18");
		TEXT19 = bundle.getString("ConvertDialog.TEXT19");
		TEXT20 = bundle.getString("ConvertDialog.TEXT20");
		TEXT21 = bundle.getString("ConvertDialog.TEXT21");
		TEXT22 = bundle.getString("ConvertDialog.TEXT22");
		TEXT23 = bundle.getString("ConvertDialog.TEXT23");
		TEXT24 = bundle.getString("ConvertDialog.TEXT24");
		TEXT25 = bundle.getString("ConvertDialog.TEXT25");
		TEXT26 = bundle.getString("ConvertDialog.TEXT26");
		TEXT27 = bundle.getString("ConvertDialog.TEXT27");
		TEXT28 = bundle.getString("ConvertDialog.TEXT28");
		TEXT29 = bundle.getString("ConvertDialog.TEXT29");
		TEXT30 = bundle.getString("ConvertDialog.TEXT30");
		TEXT31 = bundle.getString("ConvertDialog.TEXT31");
		TEXT32 = bundle.getString("ConvertDialog.TEXT32");
		TEXT33 = bundle.getString("ConvertDialog.TEXT33");
		TEXT34 = bundle.getString("ConvertDialog.TEXT34");
		TEXT35 = bundle.getString("ConvertDialog.TEXT35");
		TEXT36 = bundle.getString("ConvertDialog.TEXT36");
		TEXT37 = bundle.getString("ConvertDialog.TEXT37");
		TEXT38 = bundle.getString("ConvertDialog.TEXT38");
		TEXT39 = bundle.getString("ConvertDialog.TEXT39");
		TEXT40 = bundle.getString("ConvertDialog.TEXT40");
		TEXT41 = bundle.getString("ConvertDialog.TEXT41");
		TEXT42 = bundle.getString("ConvertDialog.TEXT42");
		TEXT43 = bundle.getString("ConvertDialog.TEXT43");
		TEXT44 = bundle.getString("ConvertDialog.TEXT44");
		TEXT45 = bundle.getString("ConvertDialog.TEXT45");
		TEXT46 = bundle.getString("ConvertDialog.TEXT46");
		TEXT47 = bundle.getString("ConvertDialog.TEXT47");
		TEXT48 = bundle.getString("ConvertDialog.TEXT48");
		TEXT49 = bundle.getString("ConvertDialog.TEXT49");
		TEXT50 = bundle.getString("ConvertDialog.TEXT50");
		TEXT51 = bundle.getString("ConvertDialog.TEXT51");
		TEXT52 = bundle.getString("ConvertDialog.TEXT52");
		TEXT53 = bundle.getString("ConvertDialog.TEXT53");
		TEXT54 = bundle.getString("ConvertDialog.TEXT54");
		TEXT55 = bundle.getString("ConvertDialog.TEXT55");
	}

	private static UIConvertDialog instance;

	private final VPBackButton backButton;
	private final VPNextButton nextButton;
	private final VPConvertButton convertButton;
	private final VPCancelButton cancelButton;
	private final VPCaptionLabel captionLabel;
	private final VPDescriptionLabel descriptionLabel;
	private final VPFormatBox formatBox;
	private final VPPPIBox jpegPPIBox;
	private final VPPPIBox pngPPIBox;
	private final VPPPIBox bmpPPIBox;
	private final JCheckBox pngCheckBox;
	private final JCheckBox pdfCheckBox;
	private final VPQualitySlider qualitySlider;
	private final VPAuthorField authorField;
	private final VPSubjectField subjectField;
	private final VPKeywordsField keywordsField;
	private final VPTableModel tableModel;
	private final VPPDFBrowseButton pdfBrowseButton;
	private final VPPDFWhereField pdfWhereField;
	private final VPPDFSaveAsField pdfSaveAsField;
	private final VPWhereField whereField;
	private final VPBrowseButton browseButton;
	private final FileDialog macSelectFolderDialog;
	private final JFileChooser winSelectFolderDialog;
	private final JProgressBar progressBar;
	private final VPTitlePanel titlePanel;
	private final VPSelectPanel selectPanel;
	private final VPPDFPanel pdfPanel;
	private final VPJPEGPanel jpegPanel;
	private final VPPNGPanel pngPanel;
	private final VPBMPPanel bmpPanel;
	private final VPNamePanel namePanel;
	private final VPPathPanel pathPanel;
	private final VPPDFPathPanel pdfPathPanel;
	private final VPButtonPanel buttonPanel;
	private final VPProgressPanel progressPanel;

	private JPanel actualPanel;
	private int indexPanel;
	private boolean interrupted;
	private boolean saveOperationIsRunning;
	private Thread currentSaveOperation;
	boolean multiplePages;

	public static synchronized UIConvertDialog getInstance()
	{
		if (instance == null)
			instance = new UIConvertDialog();

		return instance;
	}

	private UIConvertDialog()
	{	
		backButton = new VPBackButton();
		nextButton = new VPNextButton();
		convertButton = new VPConvertButton();
		cancelButton = new VPCancelButton();
		captionLabel = new VPCaptionLabel();
		descriptionLabel = new VPDescriptionLabel();
		formatBox = new VPFormatBox();
		jpegPPIBox = new VPPPIBox();
		pngPPIBox = new VPPPIBox();
		bmpPPIBox = new VPPPIBox();
		pngCheckBox = new JCheckBox(TEXT1);
		pdfCheckBox = new JCheckBox(TEXT2);
		qualitySlider = new VPQualitySlider();
		authorField = new VPAuthorField();
		subjectField = new VPSubjectField();
		keywordsField = new VPKeywordsField();
		tableModel = new VPTableModel();
		pdfBrowseButton = new VPPDFBrowseButton();
		pdfWhereField = new VPPDFWhereField();
		pdfSaveAsField = new VPPDFSaveAsField();
		browseButton = new VPBrowseButton();
		whereField = new VPWhereField();
		macSelectFolderDialog = new FileDialog(UIConvertDialog.this, TEXT3);
		winSelectFolderDialog = new JFileChooser();
		progressBar = new JProgressBar();
		titlePanel = new VPTitlePanel();
		selectPanel = new VPSelectPanel();
		pdfPanel = new VPPDFPanel();
		jpegPanel = new VPJPEGPanel();
		pngPanel = new VPPNGPanel();
		bmpPanel = new VPBMPPanel();
		namePanel = new VPNamePanel();
		pathPanel = new VPPathPanel();
		pdfPathPanel = new VPPDFPathPanel();
		buttonPanel = new VPButtonPanel();
		progressPanel = new VPProgressPanel();

		actualPanel = null;
		interrupted = false;
		saveOperationIsRunning = false;

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(titlePanel, BorderLayout.PAGE_START);
		getContentPane().add(buttonPanel, BorderLayout.PAGE_END);
		getRootPane().setDefaultButton(convertButton);

		setResizable(false);
		setTitle(TEXT4);
		setModal(true);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent event)
			{
				close();
			}
		});
	}

	private void close()
	{
		if (saveOperationIsRunning)
		{
			interrupted = true;
		}
		else
		{
			saveOperationIsRunning = false;
			interrupted = false;

			getContentPane().remove(actualPanel);
			setVisible(false);
			cancelButton.setEnabled(true);

			for (int i = 0, n = VectorFiles.getInstance().size(); i < n; i++)
			{
				VectorFiles.getInstance().fireTableCellUpdated(i, 1);
			}

			currentSaveOperation = null;

			System.gc();
		}
	}

	public void open()
	{
		saveOperationIsRunning = false;
		interrupted = false;

		tableModel.setVectorFiles();

		nextButton.requestFocusInWindow();

		switchSelectPanel();

		pack();
		setLocationRelativeTo(null);
		getContentPane().repaint();

		setVisible(true);
	}

	private final class VPSelectPanel extends JPanel
	{
		public VPSelectPanel()
		{
			JLabel formatLabel = new JLabel(TEXT5);
			formatLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			formatLabel.setLabelFor(formatBox);

			setLayout(new SpringLayout());
			add(formatLabel);
			add(formatBox);

			makeCompactGrid(this, 1, 2, 6, 6, 6, 6);
		}
	}

	private final class VPPDFPanel extends JPanel
	{
		public VPPDFPanel()
		{
			JLabel authorLabel = new JLabel(TEXT6);
			authorLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			authorLabel.setLabelFor(authorField);

			JLabel subjectLabel = new JLabel(TEXT7);
			subjectLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			subjectLabel.setLabelFor(subjectField);

			JLabel keywordsLabel = new JLabel(TEXT8);
			keywordsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			keywordsLabel.setLabelFor(keywordsField);

			JLabel dummyLabel = new JLabel(" ");

			setLayout(new SpringLayout());
			add(authorLabel);
			add(authorField);
			add(subjectLabel);
			add(subjectField);
			add(keywordsLabel);
			add(keywordsField);
			add(dummyLabel);
			add(pdfCheckBox);

			makeCompactGrid(this, 4, 2, 6, 6, 6, 6);
		}
	}

	private final class VPJPEGPanel extends JPanel
	{
		public VPJPEGPanel()
		{
			JLabel ppiLabel = new JLabel(TEXT9);
			ppiLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			ppiLabel.setLabelFor(jpegPPIBox);

			JLabel qualityLabel = new JLabel(TEXT10);
			qualityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			qualityLabel.setLabelFor(qualitySlider);

			setLayout(new SpringLayout());
			add(ppiLabel);
			add(jpegPPIBox);
			add(qualityLabel);
			add(qualitySlider);

			makeCompactGrid(this, 2, 2, 6, 6, 6, 6);
		}
	}

	private final class VPPNGPanel extends JPanel
	{
		public VPPNGPanel()
		{
			JLabel ppiLabel = new JLabel(TEXT9);
			ppiLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			ppiLabel.setLabelFor(pngPPIBox);

			JLabel dummyLabel = new JLabel(" ");

			setLayout(new SpringLayout());
			add(ppiLabel);
			add(pngPPIBox);
			add(dummyLabel);
			add(pngCheckBox);

			makeCompactGrid(this, 2, 2, 6, 6, 6, 6);
		}
	}

	private final class VPBMPPanel extends JPanel
	{
		public VPBMPPanel()
		{
			JLabel ppiLabel = new JLabel(TEXT9);
			ppiLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			ppiLabel.setLabelFor(bmpPPIBox);

			setLayout(new SpringLayout());
			add(ppiLabel);
			add(bmpPPIBox);

			makeCompactGrid(this, 1, 2, 6, 6, 6, 6);
		}
	}

	private final class VPNamePanel extends JPanel
	{
		public VPNamePanel()
		{
			JTable table = new JTable();
			table.setModel(tableModel);
			table.getTableHeader().setReorderingAllowed(false); 
			table.getTableHeader().setResizingAllowed(false);
			table.setShowGrid(true);
			table.setShowHorizontalLines(true);
			table.setShowVerticalLines(true);
			table.setTableHeader(null);
			table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setWheelScrollingEnabled(true);
			scrollPane.setPreferredSize(new Dimension(50, 100));

			setLayout(new SpringLayout());
			add(scrollPane);

			makeCompactGrid(this, 1, 1, 6, 6, 6, 6);
		}
	}

	private final class VPPathPanel extends JPanel
	{
		public VPPathPanel()
		{
			JLabel whereLabel = new JLabel(TEXT12);
			whereLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			whereLabel.setLabelFor(pdfWhereField);

			JPanel contentPanel1 = new JPanel(new SpringLayout());
			JPanel contentPanel2 = new JPanel();

			contentPanel1.add(whereLabel);
			contentPanel1.add(whereField);

			contentPanel2.add(browseButton);

			makeCompactGrid(contentPanel1, 1, 2, 6, 6, 6, 6);

			setLayout(new BorderLayout());
			add(contentPanel1, BorderLayout.CENTER);
			add(contentPanel2, BorderLayout.EAST);
		}
	}

	private final class VPPDFPathPanel extends JPanel
	{
		public VPPDFPathPanel()
		{	
			JLabel saveAsLabel = new JLabel(TEXT13);
			saveAsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			saveAsLabel.setLabelFor(pdfSaveAsField);

			JLabel whereLabel = new JLabel(TEXT12);
			whereLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			whereLabel.setLabelFor(pdfWhereField);

			JPanel contentPanel1 = new JPanel(new SpringLayout());
			JPanel contentPanel2 = new JPanel();

			contentPanel1.add(whereLabel);
			contentPanel1.add(pdfWhereField);
			contentPanel1.add(saveAsLabel);
			contentPanel1.add(pdfSaveAsField);

			contentPanel2.add(pdfBrowseButton);

			makeCompactGrid(contentPanel1, 2, 2, 6, 6, 6, 6);

			setLayout(new BorderLayout());
			add(contentPanel1, BorderLayout.CENTER);
			add(contentPanel2, BorderLayout.EAST);
		}
	}

	private final class VPTitlePanel extends JPanel
	{
		private Image background;

		public VPTitlePanel()
		{
			URL imagePath = getClass().getResource("/com/vectorpen/img/Main-Toolbar-Background.png");
			background = new ImageIcon(imagePath).getImage();

			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);

			JPanel captionPanel = new JPanel(flowLayout);
			captionPanel.add(captionLabel);
			captionPanel.setOpaque(false);

			JPanel descriptionPanel = new JPanel(flowLayout);
			descriptionPanel.add(descriptionLabel);
			descriptionPanel.setOpaque(false);

			setLayout(new BorderLayout());
			add(captionPanel, BorderLayout.NORTH);
			add(descriptionPanel, BorderLayout.CENTER);
		}

		public void paintComponent(Graphics graphics)
		{
			super.paintComponent(graphics);

			graphics.drawImage(background, 0, 0, getWidth(), getHeight(), this);
		}
	}

	private final class VPButtonPanel extends JPanel
	{
		public VPButtonPanel()
		{
			JPanel contentPanel = new JPanel(new FlowLayout());
			contentPanel.add(backButton);
			contentPanel.add(nextButton);
			contentPanel.add(convertButton);
			contentPanel.add(cancelButton);

			setLayout(new BorderLayout());
			add(new JSeparator(), BorderLayout.PAGE_START);
			add(contentPanel, BorderLayout.EAST);
		}
	}

	private final class VPProgressPanel extends JPanel
	{
		public VPProgressPanel()
		{
			setLayout(new SpringLayout());
			add(progressBar);

			makeCompactGrid(this, 1, 1, 6, 6, 6, 6);
		}
	}

	private final class VPPDFWhereField extends JTextField implements DocumentListener
	{
		public VPPDFWhereField()
		{
			setText(System.getProperty("user.home"));
			setToolTipText(TEXT35);
			getDocument().addDocumentListener(this);
		}

		public void removeUpdate(DocumentEvent event)
		{
			boolean result = FilePath.check(pdfWhereField.getText(), false) &&
			FileName.check(pdfSaveAsField.getText(), false);

			convertButton.setEnabled(result);
		}

		public void changedUpdate(DocumentEvent event)
		{

		}

		public void insertUpdate(DocumentEvent event)
		{
			boolean result = FilePath.check(pdfWhereField.getText(), false) &&
			FileName.check(pdfSaveAsField.getText(), false);

			convertButton.setEnabled(result);
		}
	}

	private final class VPPDFSaveAsField extends JTextField implements DocumentListener
	{
		public VPPDFSaveAsField()
		{
			setToolTipText(TEXT34);
			getDocument().addDocumentListener(this);
		}

		public void removeUpdate(DocumentEvent event)
		{
			boolean result = FileName.check(pdfSaveAsField.getText(), true) &&
			FilePath.check(pdfWhereField.getText(), false);

			convertButton.setEnabled(result);
		}

		public void changedUpdate(DocumentEvent event)
		{

		}

		public void insertUpdate(DocumentEvent event)
		{
			boolean result = FileName.check(pdfSaveAsField.getText(), true) &&
			FilePath.check(pdfWhereField.getText(), false);

			convertButton.setEnabled(result);
		}
	}

	private final class VPPDFBrowseButton extends JButton implements ActionListener
	{
		public VPPDFBrowseButton()
		{
			setText("...");
			addActionListener(this);

			if (Environment.isLeopard())
			{
				putClientProperty("JButton.buttonType", "gradient");
			}
			else if (Environment.isTiger())
			{
				putClientProperty("JButton.buttonType", "toolbar");
			}
		}

		public void actionPerformed(ActionEvent event)
		{
			if (Environment.isMac())
			{
				System.setProperty("apple.awt.fileDialogForDirectories", "true");

				if (FilePath.check(pdfWhereField.getText(), false))
					macSelectFolderDialog.setDirectory(pdfWhereField.getText());

				macSelectFolderDialog.setVisible(true);

				String filePath;

				if (macSelectFolderDialog.getFile() == null)
					filePath = null;
				else
					filePath = String.format("%s%s", macSelectFolderDialog.getDirectory(), macSelectFolderDialog.getFile());

				if (FilePath.check(filePath, true))
					pdfWhereField.setText(filePath);

				System.setProperty("apple.awt.fileDialogForDirectories", "false");
			}
			else
			{
				winSelectFolderDialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				if (FilePath.check(pdfWhereField.getText(), false))
					winSelectFolderDialog.setCurrentDirectory(new File(pdfWhereField.getText()));
				else
					winSelectFolderDialog.setCurrentDirectory(null);

				winSelectFolderDialog.setMultiSelectionEnabled(false);
				winSelectFolderDialog.setDialogTitle(TEXT3);

				int returnValue = winSelectFolderDialog.showOpenDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION)
				{
					String filePath = winSelectFolderDialog.getSelectedFile().getAbsolutePath();

					if (FilePath.check(filePath, true))
						pdfWhereField.setText(filePath);
				}
			}

			pdfWhereField.requestFocusInWindow();
			pdfWhereField.selectAll();
		}
	}

	private final class VPWhereField extends JTextField implements DocumentListener
	{
		public VPWhereField()
		{
			setText(System.getProperty("user.home"));
			setToolTipText(TEXT35);
			getDocument().addDocumentListener(this);
		}

		public void removeUpdate(DocumentEvent event)
		{
			boolean result = FilePath.check(whereField.getText(), false);

			convertButton.setEnabled(result);
		}

		public void changedUpdate(DocumentEvent event)
		{

		}

		public void insertUpdate(DocumentEvent event)
		{
			boolean result = FilePath.check(whereField.getText(), false);

			convertButton.setEnabled(result);
		}
	}

	private final class VPBrowseButton extends JButton implements ActionListener
	{
		public VPBrowseButton()
		{
			setText("...");
			addActionListener(this);

			if (Environment.isLeopard())
			{
				putClientProperty("JButton.buttonType", "gradient");
			}
			else if (Environment.isTiger())
			{
				putClientProperty("JButton.buttonType", "toolbar");
			}
		}

		public void actionPerformed(ActionEvent event)
		{
			if (Environment.isMac())
			{
				System.setProperty("apple.awt.fileDialogForDirectories", "true");

				if (FilePath.check(whereField.getText(), false))
					macSelectFolderDialog.setDirectory(whereField.getText());

				macSelectFolderDialog.setVisible(true);

				String filePath;

				if (macSelectFolderDialog.getFile() == null)
					filePath = null;
				else
					filePath = String.format("%s%s", macSelectFolderDialog.getDirectory(), macSelectFolderDialog.getFile());

				if (FilePath.check(filePath, true))
					whereField.setText(filePath);

				System.setProperty("apple.awt.fileDialogForDirectories", "false");
			}
			else
			{
				winSelectFolderDialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				if (FilePath.check(whereField.getText(), false))
					winSelectFolderDialog.setCurrentDirectory(new File(whereField.getText()));
				else
					winSelectFolderDialog.setCurrentDirectory(null);

				winSelectFolderDialog.setMultiSelectionEnabled(false);
				winSelectFolderDialog.setDialogTitle(TEXT3);

				int returnValue = winSelectFolderDialog.showOpenDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION)
				{
					String filePath = winSelectFolderDialog.getSelectedFile().getAbsolutePath();

					if (FilePath.check(filePath, true))
						whereField.setText(filePath);
				}
			}

			whereField.requestFocusInWindow();
			whereField.selectAll();
		}
	}

	private final class VPQualitySlider extends JSlider
	{
		public VPQualitySlider()
		{
			Hashtable<Integer, JLabel> qualityLabelTable = new Hashtable<Integer, JLabel>();
			qualityLabelTable.put(0, new JLabel(TEXT14));
			qualityLabelTable.put(50, new JLabel(TEXT15));
			qualityLabelTable.put(100, new JLabel(TEXT16));

			setMinimum(0);
			setMaximum(100);
			setMajorTickSpacing(10);
			setPaintTicks(true);
			setPaintLabels(true);
			setSnapToTicks(true);
			setLabelTable(qualityLabelTable);
			setValue(100);

			setToolTipText(TEXT17);
		}
	}

	private final class VPFormatBox extends JComboBox
	{
		public VPFormatBox()
		{
			addItem(TEXT18);
			addItem(TEXT19);
			addItem(TEXT20);
			addItem(TEXT21);
			addItem(TEXT22);

			setToolTipText(TEXT33);
		}
	}

	private final class VPPPIBox extends JComboBox
	{
		public VPPPIBox()
		{
			addItem(TEXT23);
			addItem(TEXT24);
			addItem(TEXT25);
			addItem(TEXT26);

			setToolTipText(TEXT11);
		}
	}

	private final class VPAuthorField extends JTextField
	{
		public VPAuthorField()
		{
			setToolTipText(TEXT32);
		}
	}

	private final class VPSubjectField extends JTextField
	{
		public VPSubjectField()
		{
			setToolTipText(TEXT31);
		}
	}

	private final class VPKeywordsField extends JTextField
	{
		public VPKeywordsField()
		{
			setToolTipText(TEXT30);
		}
	}

	private final class VPCaptionLabel extends JLabel
	{
		public VPCaptionLabel()
		{
			setHorizontalAlignment(SwingConstants.LEFT);

			String name = Environment.getLabelFontName();
			int style = Font.BOLD;
			int size = Environment.getLabelFontSize();

			setFont(new Font(name, style, size));
			setForeground(Color.WHITE);
			setOpaque(false);
		}
	}

	private final class VPDescriptionLabel extends JLabel
	{
		public VPDescriptionLabel()
		{
			setHorizontalAlignment(SwingConstants.LEFT);

			String name = Environment.getLabelFontName();
			int style = Environment.getLabelFontStyle();
			int size = Environment.getLabelFontSize() - 2;

			setFont(new Font(name, style, size));
			setForeground(Color.WHITE);
			setOpaque(false);
		}

		public void setText(String text)
		{
			super.setText(String.format(" %s", text));
		}
	}

	private final class VPBackButton extends JButton implements ActionListener
	{
		public VPBackButton()
		{
			setEnabled(false);
			setText(TEXT36);
			addActionListener(this);
		}

		public void actionPerformed(ActionEvent event)
		{
			if (indexPanel == 3)
			{
				if (pdfCheckBox.isSelected() && formatBox.getSelectedIndex() == 0)
					switchPDFPanel();
				else
					switchNamePanel();
			}
			else if (indexPanel == 2)
			{
				if (formatBox.getSelectedIndex() == 0)
					switchPDFPanel();
				else if (formatBox.getSelectedIndex() == 1)
					switchSelectPanel();
				else if (formatBox.getSelectedIndex() == 2)
					switchJPEGPanel();
				else if (formatBox.getSelectedIndex() == 3)
					switchPNGPanel();
				else if (formatBox.getSelectedIndex() == 4)
					switchBMPPanel();
			}
			else if (indexPanel == 1)
			{
				switchSelectPanel();
			}

			pack();
			setLocationRelativeTo(null);
			getContentPane().repaint();
		}
	}

	private final class VPNextButton extends JButton implements ActionListener
	{
		public VPNextButton()
		{
			setEnabled(false);
			setText(TEXT37);
			addActionListener(this);
		}

		public void actionPerformed(ActionEvent event)
		{
			if (indexPanel == 0)
			{
				if (formatBox.getSelectedIndex() == 0)
					switchPDFPanel();
				else if (formatBox.getSelectedIndex() == 1)
					switchNamePanel();
				else if (formatBox.getSelectedIndex() == 2)
					switchJPEGPanel();
				else if (formatBox.getSelectedIndex() == 3)
					switchPNGPanel();
				else if (formatBox.getSelectedIndex() == 4)
					switchBMPPanel();
			}
			else if (indexPanel == 1)
			{
				if (pdfCheckBox.isSelected() && formatBox.getSelectedIndex() == 0)
					switchPDFPathPanel();
				else
					switchNamePanel();
			}
			else if (indexPanel == 2)
			{
				switchPathPanel();
			}

			pack();
			setLocationRelativeTo(null);
			getContentPane().repaint();
		}
	}

	private final class VPConvertButton extends JButton implements ActionListener
	{
		public VPConvertButton()
		{
			setEnabled(false);
			setText(TEXT29);
			addActionListener(this);
		}

		public void actionPerformed(ActionEvent event)
		{
			switchProgressPanel();

			pack();
			setLocationRelativeTo(null);
			getContentPane().repaint();

			if (formatBox.getSelectedIndex() == 0)
				savePDF(pdfCheckBox.isSelected());
			else if (formatBox.getSelectedIndex() == 1)
				saveSVG();
			else if (formatBox.getSelectedIndex() == 2)
				saveJPEG();
			else if (formatBox.getSelectedIndex() == 3)
				savePNG();
			else if (formatBox.getSelectedIndex() == 4)
				saveBMP();
		}
	}

	private final class VPCancelButton extends JButton implements ActionListener
	{
		public VPCancelButton()
		{
			setEnabled(true);
			setText(TEXT28);
			addActionListener(this);
		}

		public void actionPerformed(ActionEvent event)
		{
			cancelButton.setEnabled(false);
			close();
		}
	}

	private void switchSelectPanel()
	{
		backButton.setEnabled(false);
		nextButton.setEnabled(true);
		convertButton.setEnabled(false);

		captionLabel.setText(TEXT38);
		descriptionLabel.setText(TEXT39);

		if (actualPanel != null)
			getContentPane().remove(actualPanel);

		getContentPane().add(selectPanel, BorderLayout.CENTER);

		actualPanel = selectPanel;
		indexPanel = 0;
	}

	private void switchPDFPanel()
	{
		backButton.setEnabled(true);
		nextButton.setEnabled(true);
		convertButton.setEnabled(false);

		captionLabel.setText(TEXT40);
		descriptionLabel.setText(TEXT41);

		getContentPane().remove(actualPanel);
		getContentPane().add(pdfPanel, BorderLayout.CENTER);

		actualPanel = pdfPanel;
		indexPanel = 1;

		authorField.requestFocusInWindow();
		authorField.selectAll();
	}

	private void switchJPEGPanel()
	{
		backButton.setEnabled(true);
		nextButton.setEnabled(true);
		convertButton.setEnabled(false);

		captionLabel.setText(TEXT42);
		descriptionLabel.setText(TEXT43);

		getContentPane().remove(actualPanel);
		getContentPane().add(jpegPanel, BorderLayout.CENTER);

		actualPanel = jpegPanel;
		indexPanel = 1;
	}

	private void switchPNGPanel()
	{
		backButton.setEnabled(true);
		nextButton.setEnabled(true);
		convertButton.setEnabled(false);

		captionLabel.setText(TEXT44);
		descriptionLabel.setText(TEXT45);

		getContentPane().remove(actualPanel);
		getContentPane().add(pngPanel, BorderLayout.CENTER);

		actualPanel = pngPanel;
		indexPanel = 1;
	}

	private void switchBMPPanel()
	{
		backButton.setEnabled(true);
		nextButton.setEnabled(true);
		convertButton.setEnabled(false);

		captionLabel.setText(TEXT46);
		descriptionLabel.setText(TEXT47);

		getContentPane().remove(actualPanel);
		getContentPane().add(bmpPanel, BorderLayout.CENTER);

		actualPanel = bmpPanel;
		indexPanel = 1;
	}

	private void switchNamePanel()
	{
		tableModel.fireTableStructureChanged();

		backButton.setEnabled(true);
		nextButton.setEnabled(true);
		convertButton.setEnabled(false);

		captionLabel.setText(TEXT48);

		if (tableModel.vectorFiles.size() != 1)
			descriptionLabel.setText(TEXT50);
		else
			descriptionLabel.setText(TEXT49);

		getContentPane().remove(actualPanel);
		getContentPane().add(namePanel, BorderLayout.CENTER);

		actualPanel = namePanel;
		indexPanel = 2;
	}

	private void switchPathPanel()
	{
		boolean result = FilePath.check(whereField.getText(), false);

		backButton.setEnabled(true);
		nextButton.setEnabled(false);
		convertButton.setEnabled(result);

		captionLabel.setText(TEXT51);
		descriptionLabel.setText(TEXT52);

		getContentPane().remove(actualPanel);
		getContentPane().add(pathPanel, BorderLayout.CENTER);

		actualPanel = pathPanel;
		indexPanel = 3;

		whereField.requestFocusInWindow();
		whereField.selectAll();
	}

	private void switchPDFPathPanel()
	{	
		boolean result = FilePath.check(pdfWhereField.getText(), false) &&
		FileName.check(pdfSaveAsField.getText(), false);

		backButton.setEnabled(true);
		nextButton.setEnabled(false);
		convertButton.setEnabled(result);

		captionLabel.setText(TEXT51);
		descriptionLabel.setText(TEXT52);

		getContentPane().remove(actualPanel);
		getContentPane().add(pdfPathPanel, BorderLayout.CENTER);

		actualPanel = pdfPathPanel;
		indexPanel = 3;

		pdfSaveAsField.requestFocusInWindow();
		pdfSaveAsField.selectAll();
	}

	private void switchProgressPanel()
	{	
		progressBar.setMaximum(tableModel.vectorFiles.size() + 1);
		progressBar.setValue(1);

		backButton.setEnabled(false);
		nextButton.setEnabled(false);
		convertButton.setEnabled(false);

		captionLabel.setText(TEXT53);

		if (tableModel.vectorFiles.size() == 1 || (pdfCheckBox.isSelected() && formatBox.getSelectedIndex() == 0))
			descriptionLabel.setText(TEXT55);
		else
			descriptionLabel.setText(TEXT54);

		getContentPane().remove(actualPanel);
		getContentPane().add(progressPanel, BorderLayout.CENTER);

		actualPanel = progressPanel;
		indexPanel = 4;
	}

	private final class VPTableModel extends AbstractTableModel implements TableModelListener
	{
		private ArrayList<VectorFile> vectorFiles;

		public VPTableModel()
		{
			vectorFiles = null;
		}

		public void setVectorFiles()
		{
			ArrayList<VectorFile> vectorFiles = new ArrayList<VectorFile>();

			int[] selectedRows = UIMainTable.getInstance().getSelectedRows();

			for (int i = 0, n = selectedRows.length; i < n; i++)
			{
				vectorFiles.add(VectorFiles.getInstance().getVectorFileAtIndex(selectedRows[i]));
			}

			this.vectorFiles = vectorFiles;

			fireTableDataChanged();
		}

		public int getRowCount()
		{
			return vectorFiles.size();
		}

		public int getColumnCount()
		{
			return 1;
		}

		public Object getValueAt(int row, int column)
		{
			return vectorFiles.get(row).getTitle();
		}

		public void tableChanged(TableModelEvent event)
		{

		}

		public String getColumnName(int column)
		{
			return TEXT27;
		}

		public boolean isCellEditable(int row, int column)
		{
			return true;
		}

		public Class<?> getColumnClass(int column)
		{
			return String.class;
		}

		public void setValueAt(Object value, int row, int column)
		{
			if (FileName.check((String)value, true))
				vectorFiles.get(row).setTitle((String)value);
		}
	}

	private static SpringLayout.Constraints getConstraintsForCell(int row,
			int column,
			Container parent,
			int cols)
	{
		SpringLayout layout = (SpringLayout)parent.getLayout();
		Component component = parent.getComponent(row * cols + column);

		return layout.getConstraints(component);
	}

	public static void makeCompactGrid(Container parent,
			int rows,
			int cols,
			int initialX,
			int initialY,
			int xPad,
			int yPad)
	{
		SpringLayout layout = (SpringLayout)parent.getLayout();
		Spring x = Spring.constant(initialX);

		for (int c = 0; c < cols; c++)
		{
			Spring width = Spring.constant(0);

			for (int r = 0; r < rows; r++)
			{
				width = Spring.max(width, getConstraintsForCell(r, c, parent, cols).getWidth());
			}

			for (int r = 0; r < rows; r++)
			{
				SpringLayout.Constraints constraints = getConstraintsForCell(r, c, parent, cols);

				constraints.setX(x);
				constraints.setWidth(width);
			}

			x = Spring.sum(x, Spring.sum(width, Spring.constant(xPad)));
		}

		Spring y = Spring.constant(initialY);

		for (int r = 0; r < rows; r++)
		{
			Spring height = Spring.constant(0);

			for (int c = 0; c < cols; c++)
			{
				height = Spring.max(height, getConstraintsForCell(r, c, parent, cols).getHeight());
			}

			for (int c = 0; c < cols; c++)
			{
				SpringLayout.Constraints constraints = getConstraintsForCell(r, c, parent, cols);

				constraints.setY(y);
				constraints.setHeight(height);
			}

			y = Spring.sum(y, Spring.sum(height, Spring.constant(yPad)));
		}

		SpringLayout.Constraints pCons = layout.getConstraints(parent);

		pCons.setConstraint(SpringLayout.SOUTH, y);
		pCons.setConstraint(SpringLayout.EAST, x);
	}

	private void savePDF(boolean multiplePages)
	{
		interrupted = false;
		saveOperationIsRunning = true;
		UIConvertDialog.this.multiplePages = multiplePages;

		currentSaveOperation = new Thread(new Runnable()
		{
			public void run()
			{
				if (UIConvertDialog.this.multiplePages)
				{
					try
					{
						FileOutputStream fileOutputStream = null;

						ArrayList<String> keywords = new ArrayList<String>();

						String buffer = keywordsField.getText().replaceAll(" ", "");

						StringTokenizer stringTokenizer = new StringTokenizer(buffer, ",");

						while (stringTokenizer.hasMoreTokens())
						{ 
							keywords.add(stringTokenizer.nextToken());
						}

						String title;
						String path;
						Writer writer;

						System.gc();

						title = pdfSaveAsField.getText();
						path = FilePath.createAbsolutePath(pdfWhereField.getText(), title, "pdf");

						fileOutputStream = new FileOutputStream(path);

						DocInfoDict docInfoDict = new DocInfoDict(title,
								authorField.getText(),
								subjectField.getText(),
								keywords);

						writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream , "ISO-8859-1"));
						writer.write(PDFModule.getPDFData(tableModel.vectorFiles, docInfoDict));
						writer.close();
						writer = null;

						fileOutputStream.close();

						title = null;
						path = null;
						fileOutputStream = null;
						writer = null;

						System.gc();

						progressBar.setValue(progressBar.getValue() + 1);

						keywords = null;
						saveOperationIsRunning = false;
						close();
					}
					catch (Exception exception)
					{
						saveOperationIsRunning = false;
						close();

						IOException ioException= new IOException(EXCEPTION_ENGINE_CODE_CONVERT);

						UIExceptionDialog.showDialog(ioException);
					}
				}
				else
				{
					try
					{
						FileOutputStream fileOutputStream = null;

						ArrayList<String> keywords = new ArrayList<String>();

						String buffer = keywordsField.getText().replaceAll(" ", "");

						StringTokenizer stringTokenizer = new StringTokenizer(buffer, ",");

						while (stringTokenizer.hasMoreTokens())
						{ 
							keywords.add(stringTokenizer.nextToken());
						}

						ArrayList<VectorFile> vectorFiles;

						String title;
						String path;
						Writer writer;

						for (int i = 0, n = tableModel.vectorFiles.size(); i < n; i++)
						{
							if (interrupted)
							{
								break;
							}
							else
							{
								System.gc();

								title = tableModel.vectorFiles.get(i).getTitle();
								path = FilePath.createAbsolutePath(whereField.getText(), title, "pdf");

								fileOutputStream = new FileOutputStream(path);

								DocInfoDict docInfoDict = new DocInfoDict(title,
										authorField.getText(),
										subjectField.getText(),
										keywords);

								vectorFiles = new ArrayList<VectorFile>();
								vectorFiles.add(tableModel.vectorFiles.get(i));

								writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream , "ISO-8859-1"));
								writer.write(PDFModule.getPDFData(vectorFiles, docInfoDict));
								writer.close();
								writer = null;

								fileOutputStream.close();

								vectorFiles = null;
								title = null;
								path = null;
								fileOutputStream = null;
								writer = null;

								System.gc();

								progressBar.setValue(progressBar.getValue() + 1);
							}
						}

						vectorFiles = null;
						keywords = null;
						saveOperationIsRunning = false;
						close();
					}
					catch (Exception exception)
					{
						saveOperationIsRunning = false;
						close();

						IOException ioException= new IOException(EXCEPTION_ENGINE_CODE_CONVERT);

						UIExceptionDialog.showDialog(ioException);
					}
				}
			}
		});

		currentSaveOperation.start();
	}

	private void saveSVG()
	{
		interrupted = false;
		saveOperationIsRunning = true;

		currentSaveOperation = new Thread(new Runnable()
		{
			public void run()
			{
				try
				{
					FileOutputStream fileOutputStream = null;

					String title;
					String path;
					Writer writer;

					for (int i = 0, n = tableModel.vectorFiles.size(); i < n; i++)
					{
						if (interrupted)
						{
							break;
						}
						else
						{
							System.gc();

							title = tableModel.vectorFiles.get(i).getTitle();
							path = FilePath.createAbsolutePath(whereField.getText(), title, "svg");

							fileOutputStream = new FileOutputStream(path);

							writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream , "ISO-8859-1"));
							writer.write(tableModel.vectorFiles.get(i).getSVGRepresentation());
							writer.close();
							writer = null;

							fileOutputStream.close();

							title = null;
							path = null;
							fileOutputStream = null;
							writer = null;

							System.gc();

							progressBar.setValue(progressBar.getValue() + 1);
						}
					}

					saveOperationIsRunning = false;
					close();
				}
				catch (Exception exception)
				{
					saveOperationIsRunning = false;
					close();

					IOException ioException= new IOException(EXCEPTION_ENGINE_CODE_CONVERT);

					UIExceptionDialog.showDialog(ioException);
				}
			}
		});

		currentSaveOperation.start();
	}

	private void saveJPEG()
	{
		interrupted = false;
		saveOperationIsRunning = true;

		currentSaveOperation = new Thread(new Runnable()
		{
			public void run()
			{
				try
				{
					FileOutputStream fileOutputStream = null;
					ImageOutputStream imageOutputStream = null;

					BufferedImage image;
					String title;
					String path;
					ImageWriteParam imageWriteParam;
					ImageWriter imageWriter;

					for (int i = 0, n = tableModel.vectorFiles.size(); i < n; i++)
					{
						if (interrupted)
						{
							break;
						}
						else
						{
							System.gc();

							title = tableModel.vectorFiles.get(i).getTitle();
							path = FilePath.createAbsolutePath(whereField.getText(), title, "jpg");

							fileOutputStream = new FileOutputStream(path);
							imageOutputStream = ImageIO.createImageOutputStream(fileOutputStream);

							imageWriter = ImageIO.getImageWritersByFormatName("jpg").next();
							imageWriter.setOutput(imageOutputStream);

							image = tableModel.vectorFiles.get(i).getImageRepresentationByPPI(Integer.parseInt((String)jpegPPIBox.getSelectedItem()), true);

							Float quality = (float)qualitySlider.getValue();

							imageWriteParam = new JPEGImageWriteParam(Locale.getDefault());
							imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
							imageWriteParam.setCompressionQuality(quality / 100.0f);

							imageWriter.write(null, new IIOImage(image, null, null), imageWriteParam);

							fileOutputStream.close();
							imageOutputStream.close();

							image = null;
							title = null;
							path = null;
							fileOutputStream = null;
							imageOutputStream = null;
							imageWriteParam = null;
							imageWriter = null;

							System.gc();

							progressBar.setValue(progressBar.getValue() + 1);
						}
					}

					saveOperationIsRunning = false;
					close();
				}
				catch (Exception exception)
				{
					saveOperationIsRunning = false;
					close();

					IOException ioException= new IOException(EXCEPTION_ENGINE_CODE_CONVERT);

					UIExceptionDialog.showDialog(ioException);
				}
			}
		});

		currentSaveOperation.start();
	}

	private void savePNG()
	{
		interrupted = false;
		saveOperationIsRunning = true;

		currentSaveOperation = new Thread(new Runnable()
		{
			public void run()
			{
				try
				{
					FileOutputStream fileOutputStream = null;
					ImageOutputStream imageOutputStream = null;

					BufferedImage image;
					String title;
					String path;
					ImageWriter imageWriter;

					for (int i = 0, n = tableModel.vectorFiles.size(); i < n; i++)
					{
						if (interrupted)
						{
							break;
						}
						else
						{
							System.gc();

							title = tableModel.vectorFiles.get(i).getTitle();
							path = FilePath.createAbsolutePath(whereField.getText(), title, "png");

							fileOutputStream = new FileOutputStream(path);
							imageOutputStream = ImageIO.createImageOutputStream(fileOutputStream);

							imageWriter = ImageIO.getImageWritersByFormatName("png").next();
							imageWriter.setOutput(imageOutputStream);

							image = tableModel.vectorFiles.get(i).getImageRepresentationByPPI(Integer.parseInt((String)pngPPIBox.getSelectedItem()), !pngCheckBox.isSelected());

							imageWriter.write(image);

							fileOutputStream.close();
							imageOutputStream.close();

							image = null;
							title = null;
							path = null;
							fileOutputStream = null;
							imageOutputStream = null;
							imageWriter = null;

							System.gc();

							progressBar.setValue(progressBar.getValue() + 1);
						}
					}

					saveOperationIsRunning = false;
					close();
				}
				catch (Exception exception)
				{
					saveOperationIsRunning = false;
					close();

					IOException ioException= new IOException(EXCEPTION_ENGINE_CODE_CONVERT);

					UIExceptionDialog.showDialog(ioException);
				}
			}
		});

		currentSaveOperation.start();
	}

	private void saveBMP()
	{
		interrupted = false;
		saveOperationIsRunning = true;

		currentSaveOperation = new Thread(new Runnable()
		{
			public void run()
			{
				try
				{
					FileOutputStream fileOutputStream = null;
					ImageOutputStream imageOutputStream = null;

					BufferedImage image;
					String title;
					String path;
					ImageWriter imageWriter;

					for (int i = 0, n = tableModel.vectorFiles.size(); i < n; i++)
					{
						if (interrupted)
						{
							break;
						}
						else
						{
							System.gc();

							title = tableModel.vectorFiles.get(i).getTitle();
							path = FilePath.createAbsolutePath(whereField.getText(), title, "bmp");

							fileOutputStream = new FileOutputStream(path);
							imageOutputStream = ImageIO.createImageOutputStream(fileOutputStream);

							imageWriter = ImageIO.getImageWritersByFormatName("bmp").next();
							imageWriter.setOutput(imageOutputStream);

							image = tableModel.vectorFiles.get(i).getImageRepresentationByPPI(Integer.parseInt((String)bmpPPIBox.getSelectedItem()), true);

							imageWriter.write(image);

							fileOutputStream.close();
							imageOutputStream.close();

							image = null;
							title = null;
							path = null;
							fileOutputStream = null;
							imageOutputStream = null;
							imageWriter = null;

							System.gc();

							progressBar.setValue(progressBar.getValue() + 1);
						}
					}

					saveOperationIsRunning = false;
					close();
				}
				catch (Exception exception)
				{
					saveOperationIsRunning = false;
					close();

					IOException ioException= new IOException(EXCEPTION_ENGINE_CODE_CONVERT);

					UIExceptionDialog.showDialog(ioException);
				}
			}
		});

		currentSaveOperation.start();
	}
}
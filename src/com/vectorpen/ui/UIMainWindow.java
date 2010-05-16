package com.vectorpen.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

import com.vectorpen.core.PDFModule;

@SuppressWarnings("serial")
public final class UIMainWindow extends JFrame implements ComponentListener
{
	private static final URL IMAGE_PATH_ICON;

	static
	{
		IMAGE_PATH_ICON = UIMainWindow.class.getResource("/com/vectorpen/img/Icon.png");
	}

	private static UIMainWindow instance;

	private BasicSplitPaneDivider divider;
	private JSplitPane splitPane;
	private JPanel rightPanel;
	private int rightPanelWidth;
	private boolean init;
	private boolean resized;
	private int width;

	public static synchronized UIMainWindow getInstance()
	{
		if (instance == null)
			instance = new UIMainWindow();

		return instance;
	}

	private UIMainWindow()
	{
		/*
		com.apple.eawt.Application application = com.apple.eawt.Application.getApplication();

		application.setEnabledPreferencesMenu(false);
		application.setEnabledAboutMenu(true);

		application.addApplicationListener(new com.apple.eawt.ApplicationAdapter()
		{
			public void handleAbout(com.apple.eawt.ApplicationEvent event)
			{
				new VP_AboutDialog();

				event.setHandled(true);
			}

			public void handleQuit(com.apple.eawt.ApplicationEvent event)
			{
				System.exit(0);
			}
		});
		 */
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		setSize(screenSize);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(IMAGE_PATH_ICON));
		setLayout(new BorderLayout());
		setTitle(PDFModule.PRODUCER);
		addComponentListener(this);

		rightPanel = new JPanel(new BorderLayout());
		rightPanel.add(UIMainTableScroller.getInstance(), BorderLayout.CENTER);
		rightPanel.add(UISecondaryToolbar.getInstance(), BorderLayout.PAGE_END);

		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, UIImageDisplayScroller.getInstance(), rightPanel);
		splitPane.setContinuousLayout(true);
		splitPane.setOneTouchExpandable(false);

		divider = ((BasicSplitPaneUI)splitPane.getUI()).getDivider();
		divider.addComponentListener(this);

		init = false;
		resized = true;
		width = 0;

		add(UIMainToolbar.getInstance(), BorderLayout.PAGE_START);
		add(splitPane, BorderLayout.CENTER);

		setJMenuBar(UIMainMenu.getInstance());

		addWindowListener(new WindowAdapter()
		{
			public void windowOpened(WindowEvent event)
			{
				setExtendedState(MAXIMIZED_BOTH);
			}
		});
	}

	public void componentResized(ComponentEvent event)
	{
		Component component = (Component)event.getSource();

		if (component.getClass() == getClass())
		{
			if (!init)
			{
				double width = (double)getWidth();
				double percent = 70.0;
				double result = (width / 100.0) * percent;

				rightPanelWidth = (int)width - (int)result;

				init = true;
			}

			splitPane.setDividerLocation(getWidth() - rightPanelWidth);

			resized = true;
			width = getWidth();
		}
	}

	public void componentMoved(ComponentEvent event)
	{
		Component component = (Component)event.getSource();

		if (component.getClass() == divider.getClass())
		{
			if (getWidth() == width)
			{
				if (resized == false)
					rightPanelWidth = getWidth() - splitPane.getDividerLocation();

				resized = false;
			}
		}
	}

	public void componentShown(ComponentEvent event)
	{
	}

	public void componentHidden(ComponentEvent event)
	{
	}

	public static void main(String[] args)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception exception)
		{
		}

		getInstance().setVisible(true);
	}
}
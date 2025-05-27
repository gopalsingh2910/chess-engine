package gui;

import java.awt.*;

import javax.swing.*;

public class Frame
{

	public static JFrame createFrame(final Dimension dimension)
	{
		JFrame frame = new JFrame();
		frame.setSize(dimension);
		frame.setLocationRelativeTo(null);
		return frame;
	}
}

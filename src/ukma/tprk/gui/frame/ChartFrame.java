package ukma.tprk.gui.frame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ukma.tprk.gui.component.ChartComponent;
import ukma.tprk.model.ChartUnit;

public class ChartFrame extends JFrame {

	private static final long serialVersionUID = -2929131793529929585L;

	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;

	public ChartFrame(List<ChartUnit> units, String name) {

		final ChartComponent chartComponent = new ChartComponent(units, 10, 10, 0.05, 0.05);

		super.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {

			}

			@Override
			public void mouseMoved(MouseEvent e) {
				chartComponent.repaint();
			}
		});
		
		JPanel panel = new JPanel();
		panel.add(chartComponent);

		super.pack();
		super.setVisible(true);
		super.setSize(WIDTH, HEIGHT);
		super.setTitle(name);
		super.setContentPane(chartComponent);
	}
}

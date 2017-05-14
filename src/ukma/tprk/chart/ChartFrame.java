package ukma.tprk.chart;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChartFrame extends JFrame {

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
        super.setSize(800, 600);
        super.setTitle(name);
        super.setContentPane(chartComponent);
    }
}

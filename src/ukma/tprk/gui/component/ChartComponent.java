package ukma.tprk.gui.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.*;
import java.util.List;
import javax.swing.JComponent;

import ukma.tprk.model.ChartUnit;

public class ChartComponent extends JComponent {

	private static final long serialVersionUID = 1582016587907676232L;

	private List<ChartUnit> units;

	private double xNum;
	private double yNum;

	private double xPadding;
	private double yPadding;

	public ChartComponent(List<ChartUnit> units, double xNum, double yNum, double xPadding, double yPadding) {
		this.units = units;
		this.xNum = xNum;
		this.yNum = yNum;
		this.xPadding = xPadding;
		this.yPadding = yPadding;
	}

	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);

		double max = 0;
		double actualPaddingX = getWidth() * this.xPadding;
		double actualPaddingY = getHeight() * this.yPadding;

		for (ChartUnit unit : units) {
			if (unit.getValue() > max) {
				max = unit.getValue();
			}
		}

		Shape xLine = new Line2D.Double(0, getHeight() - actualPaddingY, getWidth(), getHeight() - actualPaddingY);
		((Graphics2D) g).draw(xLine);

		Shape yLine = new Line2D.Double(actualPaddingX, 0, actualPaddingX, getHeight());
		((Graphics2D) g).draw(yLine);

		double yCoeff = (getHeight() - actualPaddingY) / max;
		double xCoeff = (getWidth() - actualPaddingX) / units.size();

		for (int i = 0; i < units.size(); i++) {
			Shape l = new Rectangle2D.Double(actualPaddingX + i * xCoeff,
					getHeight() - units.get(i).getValue() * yCoeff - actualPaddingY, xCoeff,
					units.get(i).getValue() * yCoeff);
			((Graphics2D) g).draw(l);
		}

		double yValue = (getHeight() - actualPaddingY) / yNum;

		for (int i = 0; i < yNum; i++) {
			double value = yValue * i;
			g.drawString(String.format("%.2f", value / yCoeff), (int) (getWidth() / xNum / 10),
					(int) (getHeight() - value - actualPaddingY) - (int) (getHeight() / yNum / 10));
		}

		double xValue = (getWidth() - actualPaddingX) / xNum;

		for (int i = 0; i < xNum; i++) {

			double value = xValue * i;
			g.drawString(String.format("%.2f", (value / xCoeff) / units.size()),
					(int) (actualPaddingX + value) + (int) (getWidth() / xNum / 10),
					(int) getHeight() - (int) (getHeight() / yNum / 10));
		}

		if (getMousePosition() != null) {
			g.setColor(Color.red);
			g.drawLine(0, (int) getMousePosition().getY(), getWidth(), (int) getMousePosition().getY());
			g.drawLine((int) getMousePosition().getX(), 0, (int) getMousePosition().getX(), getHeight());

			String value = "Частота: "
					+ String.format("%.2f", ((getMousePosition().getX() - actualPaddingX) / xCoeff) / units.size());
			g.drawString(value, getWidth() / 3, getHeight() / 3);

			String value2 = "Кількість: "
					+ String.format("%.2f", ((getHeight() - getMousePosition().getY() - actualPaddingY) / yCoeff));
			g.drawString(value2, getWidth() / 3, getHeight() / 3 + 20);

			g.setColor(Color.black);
		}
	}

	public List<ChartUnit> getUnits() {
		return units;
	}

	public void setUnits(List<ChartUnit> units) {
		this.units = units;
	}

	public double getxNum() {
		return xNum;
	}

	public void setxNum(double xNum) {
		this.xNum = xNum;
	}

	public double getyNum() {
		return yNum;
	}

	public void setyNum(double yNum) {
		this.yNum = yNum;
	}

	public double getxPadding() {
		return xPadding;
	}

	public void setxPadding(double xPadding) {
		this.xPadding = xPadding;
	}

	public double getyPadding() {
		return yPadding;
	}

	public void setyPadding(double yPadding) {
		this.yPadding = yPadding;
	}
}

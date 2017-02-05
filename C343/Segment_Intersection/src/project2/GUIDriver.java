package project2;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUIDriver {

	static class ShapeItem {
		private Shape shape;
		private Color color;

		public ShapeItem(Shape shape, Color color) {
			super();
			this.shape = shape;
			this.color = color;
		}

		public Shape getShape() {
			return shape;
		}

		public void setShape(Shape shape) {
			this.shape = shape;
		}

		public Color getColor() {
			return color;
		}

		public void setColor(Color color) {
			this.color = color;
		}
	}

	private static List<ShapeItem> shapes;
	private static JFrame frame;
	public static final Color DEFAULT_COLOR = Color.GREEN;

	public static void main(String[] args) {
		shapes = new ArrayList<ShapeItem>();

		frame = new JFrame("Intersecting Lines");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		JPanel firstPanel = new JPanel();
		firstPanel.setLayout(new GridLayout(1, 1));
		firstPanel.setMaximumSize(new Dimension(500, 500));

		firstPanel.add(new JComponent() {
			private ShapeItem currentShape = null;

			{
				MouseAdapter mouseAdapter = new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						currentShape = new ShapeItem(new Line2D.Double(e.getPoint(), e.getPoint()), Color.BLACK);
						;
						shapes.add(currentShape);
						repaint();
					}

					public void mouseDragged(MouseEvent e) {
						Line2D shape = (Line2D) currentShape.getShape();
						shape.setLine(shape.getP1(), e.getPoint());
						repaint();
					}

					public void mouseReleased(MouseEvent e) {
						currentShape = null;
						repaint();
					}
				};
				addMouseListener(mouseAdapter);
				addMouseMotionListener(mouseAdapter);
			}

			protected void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
				for (ShapeItem shape : shapes) {
					g2d.setColor(shape.getColor());
					g2d.draw(shape.getShape());
				}
			}

		});

		JPanel secondPanel = new JPanel();
		secondPanel.setLayout(new GridLayout(1, 2));
		secondPanel.setMaximumSize(new Dimension(150, 50));

		JButton btnGo = new JButton("Go");
		JButton btnReset = new JButton("Reset");
		btnGo.setBounds(60, 400, 50, 30);
		btnReset.setBounds(120, 400, 100, 30);
		btnGo.setPreferredSize(new Dimension(40, 40));
		btnReset.setPreferredSize(new Dimension(100, 40));
		secondPanel.add(btnGo);
		secondPanel.add(btnReset);

		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				shapes.clear();
				frame.repaint();
			}
		});

		btnGo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				SegmentIntersection segInt = new SegmentIntersection();
				ArrayList<ShapeItem> sol = new ArrayList<ShapeItem>();
				Object[] solution = segInt.anySegmentsIntersect((ArrayList<ShapeItem>) shapes, true);

				if ((Boolean) solution[0]) {
					Line2D line1 = (Line2D) solution[1];
					Line2D line2 = (Line2D) solution[2];

					for (Iterator iterator = shapes.iterator(); iterator.hasNext();) {
						ShapeItem shapeItem = (ShapeItem) iterator.next();
						Line2D temp = (Line2D) shapeItem.getShape();
						if ((temp.getX1() == line1.getX1() && temp.getX2() == line1.getX2())
								|| (temp.getX1() == line2.getX1() && temp.getX2() == line2.getX2())) {
							shapeItem.setColor(DEFAULT_COLOR);
						}
					}
					frame.repaint();
				}
			}
		});

		mainPanel.add(firstPanel);
		mainPanel.add(secondPanel);
		frame.setContentPane(mainPanel);
		frame.setSize(520, 600);
		frame.setMinimumSize(new Dimension(520, 570));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
}

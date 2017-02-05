package project2;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiPredicate;

class Segment {
	public Point2D getPoint() {
		return point;
	}

	public void setPoint(Point2D point) {
		this.point = point;
	}

	private int side;

	public int getSide() {
		return side;
	}

	public void setSide(int side) {
		this.side = side;
	}

	private Point2D point;

}

class CustomComparator implements Comparator<Segment> {

	private int getInt(double x) {
		return Integer.parseInt("" + Math.round(x));
	}

	@Override
	public int compare(Segment o1, Segment o2) {
		// TODO Auto-generated method stub
		return Integer.valueOf(getInt(o1.getPoint().getX())).compareTo(getInt(o2.getPoint().getX()));
	}
}

public class SegmentIntersection {

	private int current_x;

	private int getInt(double x) {
		return Integer.parseInt("" + Math.round(x));
	}

	private double crossProduct(Point2D p1, Point2D p2) {
		return (p1.getX() * p2.getY()) - (p2.getX() * p1.getY());
	}

	private Point2D difference(Point2D p1, Point2D p2) {
		double x1 = p2.getX() - p1.getX();
		double y1 = p2.getY() - p1.getY();
		return new Point2D.Double(x1, y1);
	}

	private double direction(Point2D p0, Point2D p1, Point2D p2) {
		return crossProduct(difference(p2, p0), difference(p1, p0));
	}

	private Boolean straddle(Point2D p1, Point2D p2, Point2D p3, Point2D p4) {
		double d1 = direction(p3, p4, p1);
		double d2 = direction(p3, p4, p2);
		return (d1 > 0 && d2 < 0) || (d1 < 0 && d2 > 0);
	}

	private Boolean onSegment(Point2D p, Point2D q, Point2D r) {
		if (q.getX() <= Math.max(p.getX(), r.getX()) && q.getX() >= Math.min(p.getX(), r.getX())
				&& q.getY() <= Math.max(p.getY(), r.getY()) && q.getY() >= Math.min(p.getY(), r.getY()))
			return true;

		return false;
	}

	private Boolean segmentsIntersect(Point2D p1, Point2D p2, Point2D p3, Point2D p4) {
		return (straddle(p1, p2, p3, p4) && straddle(p3, p4, p1, p2))
				|| (direction(p3, p4, p1) == 0 && onSegment(p3, p4, p1))
				|| (direction(p3, p4, p2) == 0 && onSegment(p3, p4, p2))
				|| (direction(p1, p2, p3) == 0 && onSegment(p1, p2, p3))
				|| (direction(p1, p2, p4) == 0 && onSegment(p1, p2, p4));
	}

	private Segment createEndpoint(Point2D point, Point2D otherPoint) {
		Segment seg = new Segment();
		seg.setPoint(point);
		if (point.getX() < otherPoint.getX()) {
			seg.setSide(0); // Left
		} else {
			seg.setSide(1); // Right
		}
		return seg;
	}

	public Object[] anySegmentsIntersect(ArrayList<GUIDriver.ShapeItem> shapes, Boolean BST) {
		Object[] ans = new Object[3];
		List<Segment> endpoints = new ArrayList<Segment>();
		HashMap<Segment, Line2D> segmentsOf = new HashMap<Segment, Line2D>();

		for (Iterator iterator = shapes.iterator(); iterator.hasNext();) {
			GUIDriver.ShapeItem shape = (GUIDriver.ShapeItem) iterator.next();
			Line2D line = (Line2D) shape.getShape();
			Segment seg = createEndpoint(line.getP1(), line.getP2());
			segmentsOf.put(seg, line);
			endpoints.add(seg);

			seg = createEndpoint(line.getP2(), line.getP1());
			segmentsOf.put(seg, line);
			endpoints.add(seg);
		}

		BiPredicate<Line2D,Line2D> key_lt =
		    (Line2D p, Line2D q) -> p.getX1() < q.getX1();

		SearchTree<Line2D> T;
		if (BST) {
			T = new AVLTree<Line2D>(key_lt);
		} else {
			T = new AVLTree<Line2D>(key_lt);
		}

		Collections.sort(endpoints, new CustomComparator());

		for (Iterator iterator = endpoints.iterator(); iterator.hasNext();) {
			Segment p = (Segment) iterator.next();
			current_x = getInt(p.getPoint().getX());
			Line2D s = segmentsOf.get(p);

			// P is a left endpoint
			if (p.getSide() == 0) {
				Node<Line2D> sn = T.insert(s);
				Node<Line2D> above = sn.after();
				Node<Line2D> below = sn.before();
				Point2D p1 = s.getP1();
				Point2D p2 = s.getP2();

				if (above != null) {
					Line2D above_line = (Line2D) above.getKey();
					if (above_line != null && segmentsIntersect(p1, p2, above_line.getP1(), above_line.getP2())) {
						ans[0] = true;
						ans[1] = new Line2D.Double(p1, p2);
						ans[2] = new Line2D.Double(above_line.getP1(), above_line.getP2());
						return ans; // Return True,Line2D,Line2D
					}
				}
				if (below != null) {
					Line2D below_line = (Line2D) below.getKey();
					if (below_line != null && segmentsIntersect(p1, p2, below_line.getP1(), below_line.getP2())) {
						ans[0] = true;
						ans[1] = new Line2D.Double(p1, p2);
						ans[2] = new Line2D.Double(below_line.getP1(), below_line.getP2());
						return ans; // Return True,Line2D,Line2D
					}
				}

			} else {
				Node<Line2D> sn = T.search(s);
				if (sn != null) {
					Node<Line2D> above = sn.after();
					Node<Line2D> below = sn.before();
					if (above != null && below != null) {
						Line2D above_line = (Line2D) above.getKey();
						Line2D below_line = (Line2D) below.getKey();
						if (above_line != null && below_line != null && segmentsIntersect(above_line.getP1(),
								above_line.getP2(), below_line.getP1(), below_line.getP2())) {
							ans[0] = true;
							ans[1] = new Line2D.Double(above_line.getP1(), above_line.getP2());
							ans[2] = new Line2D.Double(below_line.getP1(), below_line.getP2());
							return ans; // Return True,Line2D,Line2D
						}
						T.delete(sn.getKey());
					}
				}
			}

		}
		ans[0] = false;
		ans[1] = null;
		ans[2] = null;
		return ans; // Return False,Null,Null
	}
}

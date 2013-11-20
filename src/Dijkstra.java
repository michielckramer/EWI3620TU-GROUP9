import java.awt.Point;
import java.util.ArrayList;

public abstract class Dijkstra {
	private static ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	private static ArrayList<Vertex> unvisited = new ArrayList<Vertex>();

	public static void input() {
		for (int i = 0; i < Maze.MAZE_SIZE; i++) {
			for (int j = 0; j < Maze.MAZE_SIZE; j++) {
				if (!Maze.isWall(i, j)) {
					vertices.add(new Vertex(10 * i + 5, 10 * j + 5));
				}
			}
		}
	}

	public static ArrayList<Point> path(Point s, Point t) {
		Vertex source = new Vertex(s);
		Vertex target = new Vertex(t);
		int size = (int) Maze.MAZE_SIZE;
		if (vertices.size() == 0) {
			input();
		}
		for (Vertex i : vertices) {
			i.setDistance(size * size + 1);
			i.setVisited(false);
		}
		source.setDistance(0);
		unvisited.add(source);

		int least;
		int index;

		while (unvisited.size() > 0) {
			/*
			 * for (Vertex k : unvisited) { System.out.println(k.print()); }
			 * System.out.println("-------------------");
			 */
			least = unvisited.get(0).getDistance() + 1;
			index = 0;
			for (int i = 0; i < unvisited.size(); i++) {
				if (unvisited.get(i).getDistance() < least
						&& !unvisited.get(i).getVisited()) {
					least = unvisited.get(i).getDistance();
					index = i;
				}
			}
			Vertex u = unvisited.get(index);

			// System.out.println(u.print());

			if (u.equals(target)) {
				target.setPrevious(u.getPrevious());
				break;
			}

			unvisited.remove(unvisited.get(index));
			u.setVisited(true);
			for (Vertex v : vertices) {
				if (u.isNeighbor(v)) {
					int alt = u.getDistance() + 1;
					if (alt < v.getDistance()) {
						v.setDistance(alt);
						v.setPrevious(u);
						if (v.equals(target)) {
							target.setPrevious(u);
						}
						unvisited.add(v);
					}
				}
			}
		}
		ArrayList<Vertex> invert = new ArrayList<Vertex>();
		invert.add(target);
		while (invert.get(invert.size() - 1).hasPrevious()) {
			invert.add(invert.get(invert.size() - 1).getPrevious());
		}
		ArrayList<Point> route = new ArrayList<Point>();

		for (int i = invert.size() - 1; i >= 0; i--) {
			route.add(invert.get(i).getLocation());
		}

		return route;
	}
}

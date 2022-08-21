import java.util.*;

public class Maze {

    static Map<Integer, Set<Integer>> maze = new HashMap<>();

    static {
        maze.put(0, new HashSet<>(Set.of(1, 3)));
        maze.put(1, new HashSet<>(Set.of(0, 2)));
        maze.put(2, new HashSet<>(Set.of(1)));
        maze.put(3, new HashSet<>(Set.of(0, 4, 6)));
        maze.put(4, new HashSet<>(Set.of(3, 5, 7)));
        maze.put(5, new HashSet<>(Set.of(4)));
        maze.put(6, new HashSet<>(Set.of(3)));
        maze.put(7, new HashSet<>(Set.of(4, 8)));
        maze.put(8, new HashSet<>(Set.of(7)));
    }

    static Integer start = 0;
    static Integer exit = 8;


    public static boolean solve_R(int start,
                                  int end,
                                  Deque<Integer> path,
                                  Set<Integer> visited,
                                  Map<Integer, Set<Integer>> maze) {

        if (start == end) {
            path.addFirst(start);
            return true;
        }
        visited.add(start);
        Set<Integer> paths = maze.get(start);

        for (Integer p : paths) {
            if (!visited.contains(p)) {
                if(solve_R(p, end, path, visited, maze)) {
                    path.addFirst(start);
                    return true;
                }
            }
        }
        return false;

    }

    public static void solve() {
        Deque<Integer> path = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();

        path.add(start);
        visited.add(start);

        while (path.getLast() != exit || path.size() == 0) {
            Set<Integer> paths = maze.get(path.getLast());

            int nextStep = -1;

            for (Integer p : paths) {
                nextStep = p;
                if (!visited.contains(nextStep)) break; // means nextStep has not yet been visited
            }

            System.out.println("nextStep: " + nextStep);

            if (nextStep == -1 || visited.contains(nextStep)) {
                path.removeLast();
                System.out.println(path + " -");
            } else {
//                paths.remove(nextStep);
                path.add(nextStep);
                visited.add(nextStep);
                System.out.println(path + " +");
            }
        }
        System.out.println(path);
    }

    public static void main(String[] args) {
//        solve();
        Deque<Integer> path = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();

        solve_R(0, 8, path, visited, maze);

        System.out.println(path);
    }
}

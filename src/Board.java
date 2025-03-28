public class Board {
    private int width;
    private int height;
    private int[][] mines;

    public Board(int width, int height, int numMines) {
        this.width = width;
        this.height = height;
        this.mines = new int[width][height];

        if (width * height <= numMines) {
            throw new IllegalArgumentException("Too many mines for the board");
        }

        for (int i = 0; i < numMines; i++) {
            randomMine();
        }

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (mines[x][y] != 9) {
                    mines[x][y] = countMines(x, y);
                }
            }
        }
        System.out.println("Board created");
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getValue(int x, int y) {
        return mines[x][y];
    }

    private void randomMine() {
        int x = (int) (Math.random() * width);
        int y = (int) (Math.random() * height);
        if (mines[x][y] == 0) {
            mines[x][y] = 9;
        } else {
            randomMine();
        }
    }

    private int countMines(int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int neighborX = x + i;
                int neighborY = y + j;
                if (neighborX >= 0 && neighborX < width && neighborY >= 0 && neighborY < height) {
                    if (mines[neighborX][neighborY] == 9) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}

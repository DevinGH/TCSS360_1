import static org.junit.jupiter.api.Assertions.*;

class MineSweeperTest {
    private MineSweeper testMine = new MineSweeper(2, 2);

    @org.junit.jupiter.api.Test
    void testSetGrid() {
        testMine.setGrid();
        int[][] testGrid = testMine.hintGrid;
        assertEquals(0, testGrid[0][0]);
        assertEquals(0, testGrid[0][1]);
        assertEquals(0, testGrid[1][0]);
        assertEquals(0, testGrid[1][1]);
        assertNotEquals(3, testGrid[0][0]);
        assertNotEquals(5, testGrid[0][1]);
        assertNotEquals(67, testGrid[1][0]);
        assertNotEquals(-1, testGrid[1][1]);
    }

    @org.junit.jupiter.api.Test
    void testFindMines() {
    }

    @org.junit.jupiter.api.Test
    void TestInBoundsCheck() {
        assertEquals(true, testMine.inBoundsCheck(1, 1));
        assertEquals(false, testMine.inBoundsCheck(3, 1));
        assertEquals(true, testMine.inBoundsCheck(0, 1));
    }

    @org.junit.jupiter.api.Test
    void testAddHints() {
    }

    @org.junit.jupiter.api.Test
    void testOutputGrids() {
    }
}
package core;

import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;
import java.util.Random;

public class World {
    private static final int WorldWidth = 80;
    private static final int WorldHeight = 45;
    private static final long SEED = 6578897764558030256L;
    //The room configuration.
    private static final int MIN_ROOM_WIDTH = 5;
    private static final int MAX_ROOM_WIDTH = 10;
    private static final int MIN_ROOM_HEIGHT = 5;
    private static final int MAX_ROOM_HEIGHT = 10;


    // build your own world!
    //TODO create an empty world
    public static void generateWorld() {
        Random random = new Random(SEED);
        TERenderer ter = new TERenderer();
        ter.initialize(WorldWidth, WorldHeight);

        TETile[][] world = new TETile[WorldWidth][WorldHeight];
        fillWithNothing(world);
        //Randomly place a room with walls and floors.
        for (int i = 0; i < 10; i++) {
            addRoom(world, random, WorldWidth, WorldHeight);
        }
        //rendering.
        ter.renderFrame(world);
    }

    /**
     *  Fill in the world withing nothing.
     * @param tiles
     */
    public static void fillWithNothing(TETile[][] tiles) {
        for (int x = 0; x < WorldWidth; x++) {
            for (int y = 0; y < WorldHeight; y++) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
    }

    public static void addRoom(TETile[][] world, Random random, int worldWidth, int worldHeight) {
        // randomly generate a width and height in the bound.
        int roomWidth = random.nextInt(MAX_ROOM_WIDTH - MIN_ROOM_WIDTH + 1) + MIN_ROOM_WIDTH;
        int roomHeight = random.nextInt(MAX_ROOM_HEIGHT - MIN_ROOM_HEIGHT + 1) + MIN_ROOM_HEIGHT;

        // 2. 随机生成房间的左下角 x, y 坐标
        // 确保房间能够完全放入世界中
        // x 的最大值是 worldWidth - roomWidth (因为 x 是左下角，要留 roomWidth 宽度)
        int roomX = random.nextInt(worldWidth - roomWidth); // 范围 [0, worldWidth - roomWidth - 1]
        int roomY = random.nextInt(worldHeight - roomHeight); // 范围 [0, worldHeight - roomHeight - 1]

        // 打印房间信息，方便调试
        System.out.println("Adding room at (" + roomX + ", " + roomY + ") with size " + roomWidth + "x" + roomHeight);

        // 3. 填充房间区域
        // 遍历房间的 x 范围：从 roomX 到 roomX + roomWidth - 1
        // 遍历房间的 y 范围：从 roomY 到 roomY + roomHeight - 1
        for (int x = roomX; x < roomX + roomWidth; x++) {
            for (int y = roomY; y < roomY + roomHeight; y++) {
                // 判断是否是墙壁 (边界) 还是地板 (内部)
                // 墙壁的条件：x 在最左边或最右边，或者 y 在最下边或最上边
                if (x == roomX || x == roomX + roomWidth - 1 ||
                        y == roomY || y == roomY + roomHeight - 1) {
                    world[x][y] = Tileset.WALL; // 边界填充 WALL
                } else {
                    world[x][y] = Tileset.FLOOR; // 内部填充 FLOOR
                }
            }
        }
    }

}

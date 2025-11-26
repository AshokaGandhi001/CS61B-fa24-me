package core;

import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

import java.util.Random;

public class World {
    private static final int WIDTH = 80;
    private static final int HEIGHT = 45;
    private static final long SEED = 6578897764558030256L;
    private static final Random RANDOM = new Random(SEED);

    // build your own world!
    //TODO create an empty world
    public static void boringWorld() {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        fillWithNothing(world);

        //rendering.
        ter.renderFrame(world);
    }

    /**
     *  Fill in the world withing nothing.
     * @param tiles
     */
    public static void fillWithNothing(TETile[][] tiles) {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
    }

}

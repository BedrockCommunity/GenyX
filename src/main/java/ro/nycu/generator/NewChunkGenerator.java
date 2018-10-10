package ro.nycu.generator;

import com.flowpowered.math.vector.Vector3f;
import com.nukkitx.api.Server;
import com.nukkitx.api.block.BlockState;
import com.nukkitx.api.block.BlockType;
import com.nukkitx.api.block.BlockTypes;
import com.nukkitx.api.level.Level;
import com.nukkitx.api.level.chunk.Chunk;
import com.nukkitx.api.level.chunk.generator.ChunkGenerator;
import ro.nycu.GenyMain;

import java.util.Random;

public class NewChunkGenerator implements ChunkGenerator {
    private static final Vector3f SPAWN = new Vector3f(0, 5, 0);
    private static final BlockType[] TYPES = new BlockType[] {
            BlockTypes.COAL_BLOCK, BlockTypes.DIAMOND_BLOCK, BlockTypes.GOLD_BLOCK, BlockTypes.IRON_BLOCK,
            BlockTypes.REDSTONE_BLOCK, BlockTypes.EMERALD_BLOCK, BlockTypes.LAPIS_LAZULI_BLOCK
    };
    private final Server server;
    private final BlockState BEDROCK;
    private final BlockState DIRT;
    private final BlockState WATER;

    public NewChunkGenerator(Server server) {
        this.server = server;
        BEDROCK = server.blockStateBuilder().setBlockType(BlockTypes.BEDROCK).build();
        DIRT = server.blockStateBuilder().setBlockType(BlockTypes.DIRT).build();
        WATER = server.blockStateBuilder().setBlockType(BlockTypes.WATER).build();
    }

    @Override
    public void generateChunk(Level level, Chunk chunk, Random random) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y <= 5; y++) {
                    chunk.setBlock(x, y, z, BEDROCK, false);
                    GenyMain.INSTANCE.logger.debug("Generating Bedrock Classic..");
                }
                /*for (int y = 5; y < 56; y++) {
                    chunk.setBlock(x, y, z, DIRT, false);
                    //GenyMain.INSTANCE.logger.debug("Generating Dirt Classic..");
                }
                for (int y = 56; y < 78; y++) {
                    chunk.setBlock(x, y, z, WATER, false);
                }
                for (int t = 78; t < 128; t++) {
                    if (t % 7 == 0) t += 5;
                    if (t % 2 == 0) t++;
                    if (t % 3 == 0) t--;
                    if (Math.sin(t) >= Math.toRadians(t * Math.PI)) {
                        for (int a = 78; a < 100; a++) {
                            if (a % 2 != 0) a--;
                            else a += 2;
                            chunk.setBlock(x, a, z, DIRT, false);
                            //GenyMain.INSTANCE.logger.debug("Generating Maths 1..");
                        }
                        t = 78;
                        chunk.setBlock(x, t, z, DIRT, false);
                        //GenyMain.INSTANCE.logger.debug("Generating Maths 2..");
                    } else {
                        BlockType type = TYPES[random.nextInt(TYPES.length)];
                        chunk.setBlock(x, t, z, server.blockStateBuilder().setBlockType(type).build(), false);
                        //GenyMain.INSTANCE.logger.debug("Generating Maths Water..");
                    }
                }*/
            }
        }
    }

    /*@Override
    public void generateChunk(Level level, Chunk chunk, Random random) {
        for (int x1 = 0; x1 < 16; x1++) {
            for (int z1 = 0; z1 < 16; z1++) {
                chunk.setBlock(x1, 0, z1, bedrock, false);
                BlockType type = TYPES[random.nextInt(TYPES.length)];
                chunk.setBlock(x1, 1, z1, server.blockStateBuilder().setBlockType(type).build(), false);
            }
        }

        if (chunk.getX() == 0 && chunk.getZ() == 0) {
            chunk.setBlock(0, 4, 0, bedrock, false);
        }
    }*/

    @Override
    public void populateChunk(Level level, Chunk chunk, Random random) {
        // Nothing to do... yet
    }

    @Override
    public Vector3f getDefaultSpawn() {
        return SPAWN;
    }
}

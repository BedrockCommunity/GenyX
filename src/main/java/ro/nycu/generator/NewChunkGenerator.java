package ro.nycu.generator;

import com.flowpowered.math.vector.Vector3f;
import com.nukkitx.api.block.BlockState;
import com.nukkitx.api.block.BlockTypes;
import com.nukkitx.api.level.Level;
import com.nukkitx.api.level.chunk.Chunk;
import com.nukkitx.server.block.NukkitBlockState;
import com.nukkitx.server.level.generator.FlatChunkGenerator;
import ro.nycu.GenyMain;

import java.util.Random;

public class NewChunkGenerator extends FlatChunkGenerator {
    private static final BlockState BEDROCK = new NukkitBlockState(BlockTypes.BEDROCK, null, null);
    private static final BlockState DIRT = new NukkitBlockState(BlockTypes.DIRT, null, null);
    private static final BlockState GRASS = new NukkitBlockState(BlockTypes.GRASS_BLOCK, null, null);

    private static final Vector3f SPAWN = new Vector3f(0, 7, 0);

    @Override
    public void generateChunk(Level level, Chunk chunk, Random random) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y <= 5; y++) {
                    chunk.setBlock(x, y, z, BEDROCK, false);
                    GenyMain.INSTANCE.logger.debug("Generating Bedrock Classic..");
                }
                for (int y = 0; y <= 56; y++) {
                    chunk.setBlock(x, y, z, DIRT, false);
                    GenyMain.INSTANCE.logger.debug("Generating Dirt Classic..");
                }
                for (int y = 0; y <= (int) ((Math.incrementExact(x) * Math.cosh(x) + Math.incrementExact(z) * Math.cosh(z)) / (x * z * Math.PI)); y++) {
                    for (y = 78; y <= (int) ((Math.incrementExact(x) * Math.sinh(x) + Math.incrementExact(z) * Math.sinh(z)) / (x * z * Math.PI)); y--) {
                        chunk.setBlock(x, y, z, DIRT, false);
                        GenyMain.INSTANCE.logger.debug("Generating Maths..");
                    }
                }
            }
        }
    }

    @Override
    public void populateChunk(Level level, Chunk chunk, Random random) {

    }

    @Override
    public Vector3f getDefaultSpawn() {
        return SPAWN;
    }
}

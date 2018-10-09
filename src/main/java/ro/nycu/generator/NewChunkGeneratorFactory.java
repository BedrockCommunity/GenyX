package ro.nycu.generator;

import com.nukkitx.api.Server;
import com.nukkitx.api.level.chunk.generator.ChunkGenerator;
import com.nukkitx.server.level.generator.FlatChunkGeneratorFactory;

public class NewChunkGeneratorFactory extends FlatChunkGeneratorFactory {

    @Override
    public ChunkGenerator createChunkGenerator(Server server) {
        return new NewChunkGenerator();
    }
}

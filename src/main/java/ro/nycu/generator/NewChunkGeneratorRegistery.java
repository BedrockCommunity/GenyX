package ro.nycu.generator;

import com.google.common.base.Strings;
import com.nukkitx.api.Server;
import com.nukkitx.api.level.chunk.generator.ChunkGenerator;
import com.nukkitx.api.level.chunk.generator.ChunkGeneratorFactory;
import com.nukkitx.api.level.chunk.generator.ChunkGeneratorRegistry;
import com.nukkitx.network.util.Preconditions;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class NewChunkGeneratorRegistery implements ChunkGeneratorRegistry {
    private final ConcurrentMap<String, ChunkGeneratorFactory> generators = new ConcurrentHashMap<>();
    private final Server server;

    public NewChunkGeneratorRegistery(Server server) {
        this.server = server;
        // Register default chunk generators.
        register("NEW", new NewChunkGeneratorFactory());
    }

    @Override
    public Optional<ChunkGenerator> getChunkGenerator(@Nonnull String name) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(name), "Null or empty Generator name");
        ChunkGeneratorFactory factory = generators.get(name.toUpperCase());
        if (factory == null) {
            return Optional.empty();
        } else {
            return Optional.of(factory.createChunkGenerator(server));
        }
    }

    @Override
    public ChunkGenerator ensureAndGetChunkGenerator(@Nonnull String name) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(name), "Null or empty Generator name");
        return generators.get(name.toUpperCase()).createChunkGenerator(server);
    }

    @Override
    public void register(@Nonnull String name, @Nonnull ChunkGeneratorFactory factory) throws RuntimeException {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(name), "Null or empty Generator name.");
        Preconditions.checkNotNull(factory, "generatorFactory");

        if (generators.putIfAbsent(name.toUpperCase(), factory) != null) {
            throw new IllegalStateException("Generator " + name + " has already been defined.");
        }
    }

    @Override
    public void deregister(@Nonnull String name) throws RuntimeException {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(name), "Null or empty Generator name");

        if (generators.remove(name) == null) {
            throw new IllegalStateException("No such registered chunk generator");
        }
    }
}
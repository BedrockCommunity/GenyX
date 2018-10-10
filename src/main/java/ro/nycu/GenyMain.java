package ro.nycu;

import com.nukkitx.api.Server;
import com.nukkitx.api.event.Listener;
import com.nukkitx.api.event.player.PlayerJoinEvent;
import com.nukkitx.api.event.server.ServerInitializationEvent;
import com.nukkitx.api.event.server.ServerShutdownEvent;
import com.nukkitx.api.message.TextFormat;
import com.nukkitx.api.message.TipMessage;
import com.nukkitx.api.plugin.Plugin;
import com.nukkitx.api.plugin.PluginDescription;
import org.slf4j.Logger;
import ro.nycu.generator.NewChunkGenerator;

import javax.inject.Inject;
import java.nio.file.Path;

@Plugin(id = "GenyX", authors = {"NycuRO"}, version = "1.0.0")
public class GenyMain {
    public static GenyMain INSTANCE;

    public final Logger logger;
    private final PluginDescription description;
    private final Path folder;
    private final Server server;

    @Inject
    private GenyMain(Logger logger, PluginDescription description, Path folder, Server server) {
        this.logger = logger;
        this.description = description;
        this.folder = folder;
        this.server = server;
    }

    @Listener
    public void onInitialization(ServerInitializationEvent event) {
        this.INSTANCE = this;
        server.getGeneratorRegistry().register("NEW", NewChunkGenerator::new);
        logger.info(TextFormat.DARK_GREEN + description.getId() + " initialization!");
    }

    @Listener
    public void onShutdown(ServerShutdownEvent event) {
        logger.info(TextFormat.DARK_RED + description.getId() + " shutting down!");
    }

    @Listener
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage(new TipMessage("Welcome to the test server! This is experimental server software so there may be bugs."));
    }
}

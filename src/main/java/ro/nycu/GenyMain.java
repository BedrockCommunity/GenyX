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
    
        public static double[] calculateQuadratic(double a, double b, double c) {
        double square = (b * b) - (4 * a * c);
        if (square > 0) {
            double x1 = (-b + Math.sqrt(square)) / 2;
            double x2 = (-b - Math.sqrt(square)) / 2;
            double[] list = {
                x1,
                x2
            };
            return list;
        } else if (square == 0) {
            double x = (-b / (2 * a));
            double[] list = {
                x
            };
            return list;
        } else {
            String real = String.valueOf(-b / 2);
            String x1Imaginary = String.valueOf(" + " + Math.sqrt(-square) / 2) + "i";
            String x2Imaginary = String.valueOf(" - " + Math.sqrt(-square) / 2) + "i";
            MainLogger logger = Server.getInstance().getLogger();
            logger.info("X1: " + real + x1Imaginary);
            logger.info("X2: " + real + x2Imaginary);
            double[] list = {
                0
            };
            return list;
        }
    }

    public static double[] calculateCubic(double a, double b, double c, double d) {
        // first, find clasic cases
        if (a == 0d) {
            if (b == 0d) {
                if (d == 0d) {
                    // ax^3+bx^2+cx+d = 0 is reduced to cx = 0;
                    double x = 0d;
                    double[] list = {
                            x
                    };
                    return list;
                } else {
                    // ax^3+bx^2+cx+d = 0 is reduced to cx+d = 0
                    double x1 = -d / c;
                    double[] list = {
                            x1
                    };
                    return list;
                }
            } else {
                // ax^3+bx^2+cx+d = 0 is reduced to bx^2+cx+d , a quadratic ecuation.
                return calculateQuadratic(b, c, d);
            }
        } else if (d == 0d) {
            if (c == 0d) {
                if (b == 0d) {
                    // ax^3+bx^2+cx+d = 0 is reduced to ax^3 = 0
                    double x = 0d;
                    double[] one = {
                            x
                    };
                    return one;
                } else {
                    // ax^3+bx^2+cx+D = 0 is reduced to ax^3+bx^2 = 0 who is equal with x^2(ax+b) = 0
                    double x1 = 0d;
                    double x2 = -b / a;
                    double[] list = {
                            x1,
                            x2
                    };
                    return list;
                }
            } else {
                // ax^3+bx^2+cx+d = 0 is reduced to ax^3+bx^2+cx = 0.
                // first root is 0, and others is clasic of ax^2+bx+d = 0
                double[] squareList = calculateQuadratic(a, b, c);
                double[] lists = {
                        squareList[0],
                        squareList[1],
                        squareList[2],
                        0
                };
                return lists;
            }
        } else {
            double a3 = a / 3d;
            double q = (3 * b - a * a) / 9d;
            double qcube = q * q * q;
            double r = (9 * a * b - 27 * c - 2 * a * a * a) / 54d;
            double rsquare = r * r;
            double p = qcube + rsquare;
            if (p < 0d) {
                // 3 real roots
                double theta = Math.acos(r / Math.sqrt(-qcube));
                double sqrtq = Math.sqrt(-q);
                double x1 = 2.0 * sqrtq * Math.cos(theta / 3d) - a3;
                double x2 = 2.0 * sqrtq * Math.cos((theta + (2d * Math.PI)) / 3d) - a3;
                double x3 = 2.0 * sqrtq * Math.cos((theta + (4d * Math.PI)) / 3d) - a3;
                double[] lists = {
                        x1,
                        x2,
                        x3
                };
                return lists;
            } else if (p > 0d) {
                // One real root.
                double sqrtp = Math.sqrt(p);
                double s = Math.cbrt(r + sqrtp);
                double t = Math.cbrt(r - sqrtp);
                double x1 = (s + t) - a3;
                double[] lists = {
                        x1
                };
                return lists;
            } else {
                // 3 real roots and 2 same
                double sbrtr = Math.cbrt(r);
                double x1 = 2 * sbrtr - a3;
                double x2 = sbrtr - a3;
                double[] lists = {
                        x1,
                        x2
                };
                return lists;
            }
        }
    }
}

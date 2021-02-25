package de.nosswald.game.level;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * @author Nils Osswald
 * @version 1.0
 */
public class LevelManager
{
    private final ArrayList<Level> levels = new ArrayList<>();

    /**
     * loads all levels
     */
    public LevelManager()
    {
        try (Stream<Path> paths = Files.walk(Paths.get("assets/levels")))
        {
            paths.filter(path -> path.toString().endsWith(".lvl")).forEach(path -> levels.add(new Level().fromPath(path)));
        }
        catch (IOException ignored) { }
    }

    /**
     * looks through all levels by its name
     * @param name level name
     * @return the level or null if no level was found
     */
    @Nullable
    public Level getLevel(@NotNull String name)
    {
        return levels.stream().filter(level -> level.name.equals(name)).findFirst().orElse(null);
    }

    /**
     * @return all levels
     */
    public ArrayList<Level> getLevels()
    {
        return levels;
    }
}

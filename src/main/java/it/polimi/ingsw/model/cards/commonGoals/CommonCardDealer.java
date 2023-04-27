package it.polimi.ingsw.model.cards.commonGoals;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class CommonCardDealer {
    /**
     * @return a list of all the instances of the classes that extend CommonGoalStrategy.
     */
    public static List<CommonGoalStrategy> getCardStrategies() {
        List<Class<?>> subTypes = null;
        try {
            subTypes = getClassesForPackage("it.polimi.ingsw.model.cards.commonGoals.commonGoalsStrategy");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        List<CommonGoalStrategy> instances = new ArrayList<>();
        for (Class<?> subclass : subTypes) {
            try {
                instances.add((CommonGoalStrategy)subclass.getDeclaredConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return instances;
    }

    public static List<Class<?>> getClassesForPackage(final String pkgName) throws IOException, URISyntaxException {
        final String pkgPath = pkgName.replace('.', '/');
        final URI pkg = Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource(pkgPath)).toURI();
        final ArrayList<Class<?>> allClasses = new ArrayList<Class<?>>();

        Path root;
        if (pkg.toString().startsWith("jar:")) {
            try {
                root = FileSystems.getFileSystem(pkg).getPath(pkgPath);
            } catch (final FileSystemNotFoundException e) {
                root = FileSystems.newFileSystem(pkg, Collections.emptyMap()).getPath(pkgPath);
            }
        } else {
            root = Paths.get(pkg);
        }

        final String extension = ".class";
        try (final Stream<Path> allPaths = Files.walk(root)) {
            allPaths.filter(Files::isRegularFile).forEach(file -> {
                try {
                    final String path = file.toString().replace(File.separatorChar, '.');
                    final String name = path.substring(path.indexOf(pkgName), path.length() - extension.length());
                    allClasses.add(Class.forName(name));
                } catch (final ClassNotFoundException | StringIndexOutOfBoundsException ignored) {

                }
            });
        }
        return allClasses;
    }

    /**
     * @param number: the number of CommonGoalStrategy  needed
     * @return a list of {@param number} of the instances of the classes that extend CommonGoalStrategy.
     */
    public static List<CommonGoalStrategy> pickCardStrategies(int number) {
        List<CommonGoalStrategy> strategyDeck = getCardStrategies();
        List<CommonGoalStrategy> gameCGStrategies = new ArrayList<>();

        if(number > strategyDeck.size()) number = strategyDeck.size();
        //arrayList that contains the indexes of the strategies already picked
        List<Integer> indexes = new ArrayList<>();
        int randomNumber = (int) (Math.random() * strategyDeck.size());

        for (int i = 0; i < number; i++) {
            while (indexes.contains(randomNumber)) {
                randomNumber = (int) (Math.random() * strategyDeck.size());
            }
            gameCGStrategies.add(strategyDeck.get(randomNumber));
            indexes.add(randomNumber);
        }

        return gameCGStrategies;
    }

    /**
     * @param number: the number of commonGoal cards requested
     * @return a list of {@param number} CommonGoal .
     */
    public static List<CommonGoalCard> pickCommonGoalCards(int number) {
        List<CommonGoalCard> commonGoalCards = new ArrayList<>();
        List<CommonGoalStrategy> gameCGStrategies = pickCardStrategies(number);

        for (int i = 0; i < number; i++) {
            CommonGoalCard cg = new CommonGoalCard(gameCGStrategies.get(i));
            commonGoalCards.add(cg);
        }
        return commonGoalCards;
    }
}


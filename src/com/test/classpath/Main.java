package com.test.classpath;

import com.google.common.collect.ImmutableMap;
import com.google.common.reflect.ClassPath;
import com.google.inject.name.Named;
import lombok.SneakyThrows;

import java.lang.annotation.*;
import java.util.stream.Collectors;

@Controller("mainController")
public class Main {
    /**
     *
     * @param annotation scanning classes with the given annotation
     * @param packageBase searchs recursively from the base package path
     * @return an immutable map of classes associated with their annotation instance
     */
    @SneakyThrows
    public static <A extends Annotation> ImmutableMap<Class<?>, A> find(Class<A> annotation, String packageBase) {
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();

        return ClassPath.from(loader).getTopLevelClassesRecursive(packageBase).stream()
                .filter(x -> x.load().getAnnotation(annotation) != null)
                .collect(Collectors.collectingAndThen(Collectors
                        .toMap(ClassPath.ClassInfo::load, x -> x.load().getAnnotation(annotation)), ImmutableMap::copyOf));
    }

    public static void main(String[] args) {
        find(Named.class, "classpath").forEach(
                (x, y) -> System.out.println(String.format("Class: %s, Annotation: %s", x, y.value())));
    }
}

package org.gexp.annotation;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.reflect.ClassPath;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nullable;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: romain.pillot
 * @Date: 21/07/2017
 */

public final class Providers {
    public static AnnotatedClassProvider newBased(String basePackage) {
        return new AbstractAnnotatedClassProvider() {
            @Override
            public <A extends Annotation> ImmutableMap<Class<?>, A> get(Class<A> annotation) throws IOException {
                return super.find(annotation, basePackage);
            }
        };
    }

    public static AnnotatedClassProvider newSimple() {
        return new AbstractAnnotatedClassProvider() {
            @Override
            public <A extends Annotation> ImmutableMap<Class<?>, A> get(Class<A> annotation) throws IOException {
                return super.find(annotation, null);
            }
        };
    }
}
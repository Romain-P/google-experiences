package org.gexp.annotation;

import com.google.common.collect.ImmutableMap;

import java.io.IOException;
import java.lang.annotation.Annotation;

/**
 * @Author: romain.pillot
 * @Date: 21/07/2017
 */
public interface AnnotatedClassProvider {
    <A extends Annotation> ImmutableMap<Class<?>, A> get(Class<A> annotation) throws IOException;
}

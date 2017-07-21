package org.gexp.annotation;

import java.io.IOException;
import java.util.Map;

@Controller("mainController")
public class Main {

    public static void main(String[] args) throws IOException {
        AnnotatedClassProvider provider = Providers.newBased("org.gexp.annotation");
        Map<Class<?>, Controller> classes = provider.get(Controller.class);

        classes.forEach((x, y ) -> System.out.println(String.format("Class: %s annotated with %s with value %s",
                x.getName(), y.getClass().getName(), y.value())));
    }
}

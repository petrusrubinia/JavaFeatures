package com.company.reflection.inspect;

import org.junit.jupiter.api.Test;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InspectAspects {

    /**
     * GET CLASS NAME
     */
    @Test
    public void givenObject_whenGetsClassName_thenCorrect() {
        Object goat = new Goat("goat");
        Class<?> clazz = goat.getClass();

        assertEquals("Goat", clazz.getSimpleName());
        assertEquals("com.company.reflection.inspect.Goat", clazz.getName());
        assertEquals("com.company.reflection.inspect.Goat", clazz.getCanonicalName());
    }

    /**
     * GET MODIFIERS
     * We are able to inspect modifiers of any class located in a library jar that we are importing
     * into our project.
     * @throws ClassNotFoundException
     */
    @Test
    public void givenClass_whenRecognisesModifiers_thenCorrect() throws ClassNotFoundException {
        Class<?> goatClass = Class.forName("com.company.reflection.inspect.Goat");
        Class<?> animalClass = Class.forName("com.company.reflection.inspect.Animal");

        int goatModifiers = goatClass.getModifiers();
        int animalMoifiers = animalClass.getModifiers();

        assertTrue(Modifier.isPublic(goatModifiers));
        assertTrue(Modifier.isAbstract(animalMoifiers));
        assertTrue(Modifier.isPublic(animalMoifiers));
    }

    /**
     * GET PACKAGE
     */
    @Test
    public void givenClass_whenGetsPackageInfo_thenCorrect() {
        Goat goat = new Goat("goat");
        Class<?> goatClass = goat.getClass();
        Package pkg = goatClass.getPackage();

        assertEquals("com.company.reflection.inspect", pkg.getName());
    }

    /**
     * GET SUPERCLASS
     */
    @Test
    public void givenClass_whenGetsSuperClass_thenCorrect() {
        Goat goat = new Goat("goat");
        String str = "any string";

        Class<?> goatClass = goat.getClass();
        Class<?> goatSuperClass = goatClass.getSuperclass();

        assertEquals("Animal", goatSuperClass.getSimpleName());
        assertEquals("Object", str.getClass().getSuperclass().getSimpleName());
    }

    /**
     * GET INTERFACES.
     * NOTE: only those interfaces that a class explicitly declares as implemented with the implements keyword
     * appear in the returned array. => even if a class implements interface methods because its superclass
     * implements that interface, but the subclass does not directly declare that interface with the implements
     * keyword, that interface will not appear in the array of interfaces.
     */
    @Test
    public void givenClass_whenGetsImplementedInterfaces_thenCorrect() throws ClassNotFoundException {
        Class<?> goatClass = Class.forName("com.company.reflection.inspect.Goat");
        Class<?> animalClass = Class.forName("com.company.reflection.inspect.Animal");

        Class<?>[] goatInterfaces = goatClass.getInterfaces();
        Class<?>[] animalInterfaces = animalClass.getInterfaces();

        assertEquals(1, goatInterfaces.length);
        assertEquals(1, animalInterfaces.length);
        assertEquals("Locomotion", goatInterfaces[0].getSimpleName());
        assertEquals("Eating", animalInterfaces[0].getSimpleName());
    }

    /**
     * GET CONSTRUCTOR.
     *
     */
    @Test
    public void givenClass_whenGetsParameterTypesOfConstructor_thenCorrect() throws ClassNotFoundException {
        Class<?> goatClass = Class.forName("com.company.reflection.inspect.Goat");

        Constructor<?>[] contructors = goatClass.getConstructors();

        assertEquals(String.class ,contructors[0].getParameterTypes()[0]);
     }

    /**
     * GET CONSTRUCTOR USING THE PARAM TYPES.
     */
    @Test
    public void givenClass_whenGetsEachConstructorByParamTypes_ThenCorrect() throws ClassNotFoundException {
        Class<?> goatClass = Class.forName("com.company.reflection.inspect.Goat");

        assertDoesNotThrow(() -> goatClass.getConstructor(String.class));

        assertThrows(NoSuchMethodException.class, () -> {
            goatClass.getConstructor(String.class, String.class);
        });
    }


    /**
     * GET FIELDS.
     */
    @Test
    public void givenClass_whenGetsFields_thenCorrect() throws ClassNotFoundException {
        Class<?> animalClass = Class.forName("com.company.reflection.inspect.Animal");
        Field[] fields = animalClass.getDeclaredFields();

        List<String> actualFields = getFieldName(fields);

        assertTrue(actualFields.containsAll(Arrays.asList("name", "CATEGORY")));
    }

    private List<String> getFieldName(Field[] fields) {
        return Arrays.stream(fields).map(field -> field.getName()).collect(Collectors.toList());
    }

    /**
     * GET FIELD's TYPE
     */
    @Test
    public void givenClassField_whenGetsType_thenCorrect() throws ClassNotFoundException {
        try {
            Field field = Class.forName("com.company.reflection.inspect.Animal").getField("name");
            Class<?> fieldClass = field.getType();

            assertEquals("String", fieldClass.getSimpleName());

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * GET PUBLIC METHODS of the class and SUPERclass.
     */
    @Test
    public void givenClass_whenGetsAllPublicMethods_thenCorrect() throws ClassNotFoundException {
        Class<?> goatClass = Class.forName("com.company.reflection.inspect.Goat");
        Method[] methods = goatClass.getMethods();
        List<String> methodNames = getMethodName(methods);

        assertTrue(methodNames.containsAll(Arrays.asList("eats",
                "getLocomotion", "getName", "setName", "wait",
                "equals", "toString", "hashCode", "getClass", "notify", "notifyAll")));

    }

    public List<String> getMethodName(Method[] methods) {
        return Arrays.stream(methods).map(method -> method.getName()).collect(Collectors.toList());
    }

    /**
     * GET PUBLIC METHODS of the class;
     */
    @Test
    public void givenClass_whenGetsOnlyDeclaredMethods_thenCorrect() throws ClassNotFoundException {
        Class<?> goatClass = Class.forName("com.company.reflection.inspect.Goat");
        Method[] methods = goatClass.getDeclaredMethods();
        List<String> methodNames = getMethodName(methods);

        assertTrue(methodNames.containsAll(Arrays.asList("eats",
                "getLocomotion", "getSound", "eats")));
    }

    /**\
     * GET METHOD. CHECK if a method can be accessed.
     */
    @Test
    public void givenMethodName_whenGetsMethod_thenCorrect() throws NoSuchMethodException {
        Goat goat = new Goat("goat");
        Method nameMethod = goat.getClass().getMethod("getName");
        Method setNameMethod = goat.getClass().getMethod("setName", String.class);

        assertTrue(nameMethod.canAccess(goat));
        assertTrue(setNameMethod.canAccess(goat));
    }

    /**
     * INVOKE METHOD
     */
    @Test
    public void givenMethod_whenInvokes_thenCorrect() throws NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> goatClass = Class.forName("com.company.reflection.inspect.Goat");
        Constructor<?> constructor = goatClass.getConstructor(String.class);
        Goat goat = (Goat) constructor.newInstance("goat");
        Method setNameMethod = goatClass.getMethod("setName", String.class);
        Method getNameMethod = goatClass.getMethod("getName");

        setNameMethod.invoke(goat, "new name");

        String name = (String) getNameMethod.invoke(goat);
        assertEquals("new name", name);
    }
}

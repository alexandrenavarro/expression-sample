package com.github.expressionsample;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.WatchEvent.Kind;
import java.util.Arrays;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;

/**
 * <p>JavacDealToStringFunctionCompiler.</p>
 * 
 * @author anavarro - Apr 27, 2014
 * 
 */
public class JavacDealToStringFunctionCompiler implements DealToStringFunctionCompiler {

    private static final String FUNCTION = ""
                                                 // + "package com.github.expressionsample;\n"
                                                 + "public class GeneratedDealToStringFunction implements com.github.expressionsample.DealToStringFunction { \n"
                                                 + "@Override public String applyAsString(final com.github.expressionsample.Deal deal) { \n"
                                                 + "return (deal.getQuantity() % 10 == 0) ? deal.getTrader() : \"xx\";} \n}";

    /**
     * Constructor.
     * 
     */
    public JavacDealToStringFunctionCompiler() {
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.github.expressionsample.DealToStringFunctionCompiler#compile(java.lang.String)
     */
    @Override
    public DealToStringFunction compile(final String aExpression) {
        final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        
        final JavaFileObject jsfs = new JavaSourceFromString("GeneratedDealToStringFunction", FUNCTION);
        Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(jsfs);
        
//        List<JavaFileObject> compilationUnits = new ArrayList<JavaFileObject>();
//        compilationUnits.add(new CharSequenceJavaFileObject("GeneratedDealToStringFunction", FUNCTION));
        
        final boolean isCompiled = compiler.getTask(null, null, null, null, null, compilationUnits).call();

        System.out.println("isCompiled =" + isCompiled);

        try {
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { (new File(".")).toURI().toURL() }); //root is path to class file

            // final Class<?> clazz = Class.forName("GeneratedDealToStringFunction");
            // return (DealToStringFunction) clazz.newInstance();

//            JavaFileManager fileManager = new
//                    ClassFileManager(compiler
//                        .getStandardFileManager(null, null, null));
            

            return (DealToStringFunction) classLoader
                    .loadClass("GeneratedDealToStringFunction").newInstance();
            // Object instance = fileManager.getClassLoader(null)
            // .loadClass("GeneratedDealToStringFunction").newInstance();
            // return (DealToStringFunction) instance;
            //

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.out.println("e=" + e);
            e.printStackTrace();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            
        }
        return null;
    }

    
    
    
    /**
     * <p>JavaSourceFromString.</p>
     * 
     * @author anavarro - Apr 27, 2014
     * 
     */
    class JavaSourceFromString extends SimpleJavaFileObject {
        final String code;

        JavaSourceFromString(String name, String code) {
            super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.code = code;
        }

        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return code;
        }
    }
//
//
//
//    public class JavaClassObject extends SimpleJavaFileObject {
//
//        /**
//         * Byte code created by the compiler will be stored in this ByteArrayOutputStream so that we can later get the byte array out of it and put it
//         * in the memory as an instance of our class.
//         */
//        protected final ByteArrayOutputStream bos = new ByteArrayOutputStream();
//
//        /**
//         * Registers the compiled class object under URI containing the class full name
//         * 
//         * @param name Full name of the compiled class
//         * @param kind Kind of the data. It will be CLASS in our case
//         */
//        public JavaClassObject(String name, Kind kind) {
//            super(URI.create("string:///" + name.replace('.', '/') + kind.extension), kind);
//        }
//
//        /**
//         * Will be used by our file manager to get the byte code that can be put into memory to instantiate our class
//         * 
//         * @return compiled byte code
//         */
//        public byte[] getBytes() {
//            return bos.toByteArray();
//        }
//
//        /**
//         * Will provide the compiler with an output stream that leads to our byte array. This way the compiler will write everything into the byte
//         * array that we will instantiate later
//         */
//        @Override
//        public OutputStream openOutputStream() throws IOException {
//            return bos;
//        }
//    }
//
//    public class CharSequenceJavaFileObject extends SimpleJavaFileObject {
//
//        /**
//         * CharSequence representing the source code to be compiled
//         */
//        private CharSequence content;
//
//        /**
//         * This constructor will store the source code in the internal "content" variable and register it as a source code, using a URI containing the
//         * class full name
//         * 
//         * @param className name of the public class in the source code
//         * @param content source code to compile
//         */
//        public CharSequenceJavaFileObject(String className, CharSequence content) {
//            super(URI.create("string:///" + className.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
//            this.content = content;
//        }
//
//        /**
//         * Answers the CharSequence to be compiled. It will give the source code stored in variable "content"
//         */
//        @Override
//        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
//            return content;
//        }
//    }
//    
//    public class ClassFileManager extends ForwardingJavaFileManager {
//        /**
//         * Instance of JavaClassObject that will store the compiled bytecode of our class
//         */
//        private JavaClassObject jclassObject;
//
//        /**
//         * Will initialize the manager with the specified standard java file manager
//         * 
//         * @param standardManger
//         */
//        public ClassFileManager(StandardJavaFileManager standardManager) {
//            super(standardManager);
//        }
//
//        /**
//         * Will be used by us to get the class loader for our compiled class. It creates an anonymous class extending the SecureClassLoader which uses
//         * the byte code created by the compiler and stored in the JavaClassObject, and returns the Class for it
//         */
//        @Override
//        public ClassLoader getClassLoader(Location location) {
//            return new SecureClassLoader() {
//                @Override
//                protected Class<?> findClass(String name) throws ClassNotFoundException {
//                    byte[] b = jclassObject.getBytes();
//                    return super.defineClass(name, jclassObject.getBytes(), 0, b.length);
//                }
//            };
//        }
//
//        /**
//         * Gives the compiler an instance of the JavaClassObject so that the compiler can write the byte code into it.
//         */
//        @Override
//        public JavaFileObject getJavaFileForOutput(Location location, String className, javax.tools.JavaFileObject.Kind kind, FileObject sibling) throws IOException {
//            jclassObject = new JavaClassObject(className, kind);
//            return jclassObject;
//        }
//    }
}

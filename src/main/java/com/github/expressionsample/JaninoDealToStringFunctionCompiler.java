package com.github.expressionsample;


/**
 * <p>JaninoDealToStringFunctionCompiler.</p>
 *
 * @author anavarro - Apr 27, 2014
 *
 */
public class JaninoDealToStringFunctionCompiler implements DealToStringFunctionCompiler {

    private static final String FUNCTION = ""
            // + "package com.github.expressionsample;\n"
            + "public class GeneratedDealToStringFunction implements com.github.expressionsample.DealToStringFunction { \n"
            + "@Override public String applyAsString(final com.github.expressionsample.Deal deal) { \n"
            + "return (deal.getQuantity() % 10 == 0) ? deal.getTrader() : \"xx\";} \n}";

    
    
    /**
     * {@inheritDoc}
     *
     * @see com.github.expressionsample.DealToStringFunctionCompiler#compile(java.lang.String)
     */
    @Override
    public DealToStringFunction compile(String aExpression) {
        
   
//        Scanner scanner = new Scanner(null, new ByteArrayInputStream(
//                FUNCTION.getBytes("UTF-8")), "UTF-8");
//
//                JaninoRestrictedClassLoader cl = new JaninoRestrictedClassLoader();
//                                
//                UnitCompiler unitCompiler = new UnitCompiler(
//                new Parser(scanner).parseCompilationUnit(),
//                new ClassLoaderIClassLoader(cl));
//                
//                
//                ClassFile[] classFiles = unitCompiler.compileUnit(true, true, true);
//                Class<?> clazz = cl.defineClass("GeneratedDealToStringFunction",
//                classFiles[0].toByteArray());
//
//                return (DealToStringFunction) clazz.newInstance();
        return null;
    }

}

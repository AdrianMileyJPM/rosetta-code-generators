package com.regnosys.rosetta.generator.emof2013.util ;

import com.regnosys.rosetta.generator.java.RosettaJavaPackages ;
import com.regnosys.rosetta.generator.object.ExpandedAttribute ;
import com.regnosys.rosetta.rosetta.simple.Data ;

public class ModelGeneratorUtil {
    private String generateFilename ( RosettaJavaPackages packages , Data clazz ) {
        return packages.model().directoryName() + "/" + packages.model().name() + "ecore.xml" ;
    }

    static String fileComment ( String version ) {
        return "<!--    *\n" +
            "   *   This file is auto-generated from the ISDA Common Domain Model, do not edit. \n" +
            "   *   Version: " + version + "\n" +
            "   *   -->\n" ;
    }

    static String comment ( String definition ) {
       return "comment ToDo" ;
    /* ToDo */
    }

    static String classComment ( String definition , Iterable<ExpandedAttribute> attributes ) {
        return "comment ToDo" ;
        /* ToDo */
    }
}

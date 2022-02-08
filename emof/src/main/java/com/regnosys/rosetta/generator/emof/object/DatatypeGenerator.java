package com.regnosys.rosetta.generator.emof.object ;

import com.regnosys.rosetta.generator.emof.util.XmlHelper ;
import com.regnosys.rosetta.generator.emof.util.IdentifierGenerator ;

import com.regnosys.rosetta.rosetta.RosettaEnumeration ;
import com.regnosys.rosetta.rosetta.RosettaEnumValue ;
import com.regnosys.rosetta.rosetta.simple.Data;

import java.util.List ;
import java.util.HashMap ;
import java.util.Map ;

class DatatypeGenerator {
    static final String FILENAME = "Types.ecore.xml" ;
    public DatatypeGenerator() { /* do nothing */ }

    public Map<String, ? extends CharSequence> generate ( List<Data> rosettaClasses , String version ) {
        Map result = new HashMap() ;
        String packages = generateListOfDatatypes( rosettaClasses) ;
        result.put(FILENAME, packages ) ;
        return result ;
    }

    public String generateListOfDatatypes ( List<Data> classList ) {
        StringBuilder sb = new StringBuilder() ;
        for ( Data thisClass : classList ) {
            sb.append( generateDatatype(thisClass) ) ;
        }
        return sb.toString() ;
    }

    private String generateDatatype( Data in) {
        StringBuilder sb = new StringBuilder() ;

        return sb.toString() ;
    }
}

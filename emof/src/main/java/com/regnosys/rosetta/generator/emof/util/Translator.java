package com.regnosys.rosetta.generator.emof.util ;

import com.regnosys.rosetta.rosetta.RosettaEnumeration ;
import com.regnosys.rosetta.rosetta.simple.Data ;
import com.regnosys.rosetta.types.RCalculationType ;
import com.regnosys.rosetta.types.RQualifiedType ;
import com.regnosys.rosetta.generator.object.ExpandedType ;

import java.util.Arrays ;
import java.util.List ;

public class Translator {
	
	public static String RosettaDatatypeNamespace = "com.rosetta.datatypes" ;
	public static List<String> typeList = Arrays.asList(
		"string"
		, "int"
		, "time"
		, "date"
		, "dateTime"
		, "zonedDateTime"
		, "number"
		, "boolean"
		) ;

	public static boolean isPrimitiveType ( String typeName ) {
		for ( String thisType : typeList ) {
            if ( thisType == typeName ) {
                return true ;
            }
        }
		return false ;
    }

    public static String mapToPrimitiveType ( String typeName ) {
		for ( String thisTYpe : typeList ) {
			if ( thisTYpe == typeName ) { return IdentifierGenerator.fromTwoParts ( RosettaDatatypeNamespace , typeName ) ; }
		}
		return "***Unmapped " + typeName ;
	}
}
package com.regnosys.rosetta.generator.emof.util ;

import com.regnosys.rosetta.rosetta.RosettaEnumeration ;
import com.regnosys.rosetta.rosetta.simple.Data ;

import java.util.Arrays ;
import java.util.List ;
import java.util.Objects;

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
            if (Objects.equals(thisType, typeName)) {
                return true ;
            }
        }
		return false ;
    }

    public static String mapToPrimitiveType ( String typeName ) {
		for ( String thisType : typeList ) {
			if (Objects.equals(thisType, typeName)) { return IdentifierGenerator.fromTwoParts ( RosettaDatatypeNamespace , typeName ) ; }
		}
		return "***Unmapped " + typeName ;
	}

	public static String mapToEnumType(RosettaEnumeration referencedType ) {
		return IdentifierGenerator.fromTwoParts ( referencedType.getModel().getName() , referencedType.getName() ) ;
	}

	public static String mapToModelType ( Data referencedType ) {
		return IdentifierGenerator.fromTwoParts ( referencedType.getModel().getName() , referencedType.getName() ) ;
	}

	public static String generateDatatypes() {
		StringBuilder sb = new StringBuilder()
			.append ( XmlHelper.typedTagBegin ( "packagedElement" , "Package" , false ) )
			.append ( XmlHelper.addAttribute( "xmi:id" , RosettaDatatypeNamespace ))
			.append ( XmlHelper.addAttribute( "name" , RosettaDatatypeNamespace))
			.append ( XmlHelper.closeTag() ) ;

		sb.append ( XmlHelper.addComment ( RosettaDatatypeNamespace , "Set of Rosetta Primitive Datatypes used in ISDA-CDM" )) ;

		for ( String thisType : typeList ) {
			sb.append ( XmlHelper.typedTagBegin ( "packagedElement" , "PrimitiveType" , false ) )
				.append( XmlHelper.addAttribute( "xmi:id" , IdentifierGenerator.fromTwoParts ( RosettaDatatypeNamespace , thisType )))
				.append ( XmlHelper.addAttribute ( "name" , thisType ))
				.append ( XmlHelper.endTag() ) ;
		}

		sb.append ( XmlHelper.endBlockTag ( "packagedElement")) ;
		return sb.toString() ;
	}
}
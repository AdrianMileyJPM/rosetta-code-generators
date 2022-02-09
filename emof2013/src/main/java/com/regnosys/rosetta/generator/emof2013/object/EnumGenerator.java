package com.regnosys.rosetta.generator.emof2013.object ;

import com.regnosys.rosetta.generator.emof2013.util.XmlHelper ;
import com.regnosys.rosetta.generator.emof2013.util.IdentifierGenerator ;

import com.regnosys.rosetta.rosetta.RosettaEnumeration ;
import com.regnosys.rosetta.rosetta.RosettaEnumValue ;
import com.regnosys.rosetta.rosetta.simple.Data;

import java.util.List ;
import java.util.HashMap ;
import java.util.Map ;

class EnumGenerator {
    static final String FILENAME = "Types.ecore.xml" ;
    public EnumGenerator() { /* do nothing */ }

    public Map<String, ? extends CharSequence> generate ( List<RosettaEnumeration> rosettaEnums , String version ) {
        Map result = new HashMap() ;
        String enums = generateListOfEnumerations( rosettaEnums) ;
        result.put(FILENAME, enums ) ;
        return result ;
    }

    public String generateListOfEnumerations ( List<RosettaEnumeration> enums ) {
        StringBuilder sb = new StringBuilder() ;
        for ( RosettaEnumeration thisEnum : enums ) {
            sb.append( generateEnumeration(thisEnum) ) ;
        }
        return sb.toString() ;
    }

    private String generateEnumeration ( RosettaEnumeration in ) {
        String thisElementId = IdentifierGenerator.fromTwoParts(in.getModel().getName(), in.getName()) ;
        StringBuilder sb = new StringBuilder()
                .append ( XmlHelper.typedTagBegin( "packagedElement" , "Enumeration" , false))
                .append ( XmlHelper.addAttribute( "xmi:id" , thisElementId) )
                .append ( XmlHelper.addAttribute( "name" , in.getName() ) )
                .append( XmlHelper.closeTag() ) ;
        sb.append( XmlHelper.addComment( thisElementId , in.getDefinition())) ;

        for ( RosettaEnumValue thisEnumValue : in.getEnumValues() ) {
            sb.append ( generateEnumerationLiteral(thisEnumValue)) ;
        }

        sb.append( XmlHelper.endBlockTag( "packagedElement"))
            .append(XmlHelper.LINE_SEPARATOR) ;
        return sb.toString() ;
    }

    private String generateEnumerationLiteral( RosettaEnumValue in) {
        String thisElementId = IdentifierGenerator.fromThreeParts(in.getEnumeration().getModel().getName() , in.getEnumeration().getName() , in.getName()) ;
        StringBuilder sb = new StringBuilder()
                .append ( XmlHelper.typedTagBegin( "ownedLiteral" , "EnumerationLiteral" , false))
                .append ( XmlHelper.addAttribute( "xmi:id" , thisElementId) )
                .append ( XmlHelper.addAttribute( "name" , in.getName() ) )
// ToDo:                .append ( XmlHelper.addAttribute( "literal" , in.getDisplay() ) )
                .append( XmlHelper.closeTag() ) ;

        sb.append( XmlHelper.addComment( thisElementId , in.getDefinition())) ;

        sb.append( XmlHelper.endBlockTag( "ownedLiteral"))
                .append(XmlHelper.LINE_SEPARATOR) ;
        return sb.toString() ;
    }
}
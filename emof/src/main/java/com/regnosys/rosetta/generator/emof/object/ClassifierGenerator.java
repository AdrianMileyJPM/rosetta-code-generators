package com.regnosys.rosetta.generator.emof.object ;

import com.regnosys.rosetta.generator.emof.util.XmlHelper ;
import com.regnosys.rosetta.generator.emof.util.IdentifierGenerator ;
import com.regnosys.rosetta.generator.emof.util.DatatypeHelper;

import com.regnosys.rosetta.rosetta.simple.Attribute ;
import com.regnosys.rosetta.rosetta.simple.Data ;

import java.util.List ;
import java.util.HashMap ;
import java.util.Map ;

public class ClassifierGenerator {
    static final String FILENAME = "Types.ecore.xml" ;
    public ClassifierGenerator() { /* do nothing */ }

    public Map<String, ? extends CharSequence> generate ( List<Data> rosettaClasses , String version ) {
        Map result = new HashMap() ;
        String packages = generateListOfClasses( rosettaClasses) ;
        result.put(FILENAME, packages ) ;
        return result ;
    }

    public String generateListOfClasses ( List<Data> classList ) {
        StringBuilder sb = new StringBuilder() ;
        for ( Data thisClass : classList ) {
            sb.append( generateClass(thisClass) ) ;
        }
        return sb.toString() ;
    }

    public String generateClass ( Data in ) {
        String thisElementId = IdentifierGenerator.fromTwoParts(in.getModel().getName() , in.getName()) ;

        StringBuilder sb = new StringBuilder()
                .append ( XmlHelper.typedTagBegin( "packagedElement" , "Class" , false))
                .append ( XmlHelper.addAttribute( "xmi:id" , thisElementId) )
                .append ( XmlHelper.addAttribute( "name" , in.getName() ) )
                .append( XmlHelper.closeTag() ) ;
        sb.append( XmlHelper.addComment( thisElementId , in.getDefinition())) ;

        if ( in.hasSuperType() == true ) {
            sb.append( "<generalization xmi:type=\"Generalization\"")
                .append( XmlHelper.addAttribute( "xmi:id" , IdentifierGenerator.fromMetaPartName( thisElementId , "Generalization")))
                .append( XmlHelper.addAttribute( "general"
                            , IdentifierGenerator.fromTwoParts(
                                    in.getSuperType().getModel().getName()
                                , in.getSuperType().getName()
                            )
                        )
                )
                .append(XmlHelper.closeTag()) ;
        }

        for ( Attribute thisAttribute : in.getAttributes()) {
            sb.append( generateAttribute(thisAttribute , thisElementId )) ;
        }

        sb.append( XmlHelper.endBlockTag( "packagedElement")) ;
        return sb.toString() ;
    }

    private String generateAttribute( Attribute in , String owningClassId ) {
        String thisElementId = IdentifierGenerator.fromTwoParts(owningClassId , in.getName() ) ;
/*
        String thisElementId = IdentifierGenerator.fromThreeParts
            (   in.getClass().getPackage().getName()
            ,   in.getClass().getName()
            ,   in.getName()
            ) ;
*/

        StringBuilder sb = new StringBuilder()
                .append ( XmlHelper.typedTagBegin( "ownedAttribute" , "Property" , false))
                .append ( XmlHelper.addAttribute( "xmi:id" , thisElementId) )
                .append ( XmlHelper.addAttribute( "name" , in.getName() ) ) ;

        if ( DatatypeHelper.isPrimitiveType( in.getType().getName() ) == true) {
            sb.append(XmlHelper.addAttribute("type", DatatypeHelper.mapToPrimitiveType(in.getType().getName())));
        } else {
            sb.append(XmlHelper.addAttribute("type"
               , IdentifierGenerator.fromTwoParts(
                   in.getType().getModel().getName()
               ,   in.getType().getName()
               )
            ) ) ;
        }
        sb.append(XmlHelper.closeTag()) ;
        sb.append( XmlHelper.addComment( thisElementId , in.getDefinition())) ;

        sb.append( XmlHelper.typedTagBegin( "lowerValue" , "LiteralInteger" , false )) ;
        if ( in.getCard().isIsOptional() == true ) {
            sb.append(XmlHelper.addAttribute("value", "0"));
        } else {
            sb.append(XmlHelper.addAttribute("value", "1"));
        }
        sb.append(XmlHelper.endTag()) ;

        sb.append( XmlHelper.typedTagBegin( "upperValue" , "LiteralUnlimitedNatural" , false )) ;
        if ( in.getCard().isIsMany() == true ) {
            sb.append(XmlHelper.addAttribute("value", "-1"));
        } else {
            sb.append(XmlHelper.addAttribute("value", "1"));
        }
        sb.append(XmlHelper.endTag()) ;

        sb.append( XmlHelper.endBlockTag( "ownedAttribute")) ;
        return sb.toString() ;
    }
}
package com.regnosys.rosetta.generator.emof.object;

//  import com.regnosys.rosetta.rosetta.RosettaMetaType ;
import com.regnosys.rosetta.rosetta.RosettaModel ;
import com.regnosys.rosetta.rosetta.simple.Data ;
import com.regnosys.rosetta.rosetta.RosettaEnumeration ;

import com.regnosys.rosetta.generator.emof.util.XmlHelper ;
import com.regnosys.rosetta.generator.emof.util.IdentifierGenerator ;

import java.util.List ;
import java.util.HashMap ;
import java.util.Map ;
import java.util.stream.Collectors ;

public class PackageGenerator {
    public PackageGenerator() { /* do nothing */ }

    ClassifierGenerator classGenerator = new ClassifierGenerator() ;
    EnumGenerator enumGenerator = new EnumGenerator() ;

    static final String FILENAME = "NoFile.xml" ;

    public Map<String, ? extends CharSequence> generate ( List<RosettaModel> rosettaPackages , String version ) {
        Map result = new HashMap() ;
        String packages = generateListOfPackages( rosettaPackages) ;
        result.put(FILENAME, packages ) ;
        return result ;
    }

    public String generateListOfPackages ( List<RosettaModel> packageIn ) {
        StringBuilder sb = new StringBuilder() ;
        for ( RosettaModel thisModel : packageIn ) {
            sb.append( generatePackage( thisModel ) ) ;
        }
        return sb.toString() ;
    }

    public String generatePackage ( RosettaModel elementIn ) {
        String thisElementId = IdentifierGenerator.fromOnePart(elementIn.getName()) ;
        StringBuilder sb = new StringBuilder()
            .append ( XmlHelper.typedTagBegin( "packagedElement" , "Package" , false))
            .append ( XmlHelper.addAttribute( "xmi:id" , thisElementId) )
            .append ( XmlHelper.addAttribute( "name" , elementIn.getName() ) )
            .append( XmlHelper.closeTag() ) ;
        sb.append( XmlHelper.addComment( thisElementId , elementIn.getDefinition())) ;

        List<Data> rosettaClasses = elementIn.getElements()
                .stream()
                .filter(Data.class::isInstance)
                .map(Data.class::cast)
                .collect ( Collectors.toList() );
        sb.append ( classGenerator.generateListOfClasses(rosettaClasses)) ;

        List<RosettaEnumeration> rosettaEnums = elementIn.getElements()
                .stream()
                .filter(RosettaEnumeration.class::isInstance)
                .map(RosettaEnumeration.class::cast)
                .collect ( Collectors.toList() );
        sb.append ( enumGenerator.generateListOfEnumerations(rosettaEnums)) ;

    /* ToDo : stand-alone functions */

        sb.append ( XmlHelper.endBlockTag( "packagedElement"))
                .append( XmlHelper.LINE_SEPARATOR) ;
        return sb.toString() ;
    }
}

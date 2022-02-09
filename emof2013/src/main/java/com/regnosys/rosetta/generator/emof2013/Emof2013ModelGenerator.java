package com.regnosys.rosetta.generator.emof2013 ;

import com.regnosys.rosetta.generator.emof2013.util.DatatypeHelper;
import com.regnosys.rosetta.generator.external.AbstractExternalGenerator ;
import com.regnosys.rosetta.generator.java.RosettaJavaPackages ;
import com.regnosys.rosetta.rosetta.RosettaModel ;
import com.regnosys.rosetta.rosetta.RosettaRootElement ;

import com.regnosys.rosetta.generator.emof2013.object.PackageGenerator ;
import com.regnosys.rosetta.generator.emof2013.util.XmlHelper ;

import java.util.HashMap ;
import java.util.List ;
import java.util.Map ;
import java.util.Collections ;
import java.util.Collection ;

public class Emof2013ModelGenerator extends AbstractExternalGenerator {
//    @Inject PackageGenerator packageGenerator ;
//    PackageGenerator packageGenerator ;

    public Emof2013ModelGenerator() {
        super("emof2013") ;
    }

    @Override
    public Map<String, ? extends CharSequence> generate (RosettaJavaPackages packages , List<RosettaRootElement> elements , String version ) {
        return Collections.emptyMap() ;
    }

    @Override
    public Map<String, ? extends CharSequence> afterGenerate(Collection<? extends RosettaModel> models) {
        Map<String, CharSequence> result = new HashMap() ;
        PackageGenerator packageGenerator = new PackageGenerator() ;

        String version = models.stream()
            .map(m->m.getVersion())
            .findFirst().orElse("No version") ;

        StringBuilder generatedModelData = new StringBuilder()
            .append( XmlHelper.documentHeader("ISDA Common Domain Model" , version)) ;

        generatedModelData.append( packageGenerator.generateListOfPackages( (List<RosettaModel>) models) ) ;

        generatedModelData.append(DatatypeHelper.generateDatatypes()) ;
        generatedModelData.append(XmlHelper.documentEnd()) ;
        result.put ( generatedModelData.toString() , version ) ;
        return result ;
    }
}

package com.regnosys.rosetta.generator.emof2013;

import static com.regnosys.rosetta.generators.test.TestHelper.toStringContents;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;

import java.net.URL;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.Map;
import org.junit.jupiter.api.Test;
import java.util.stream.Collectors ;

import com.google.common.io.Resources;
import com.regnosys.rosetta.generator.java.RosettaJavaPackages;
import com.regnosys.rosetta.generators.test.TestHelper;
import com.regnosys.rosetta.rosetta.RosettaModel;
import java.nio.file.Files ;
import java.io.BufferedWriter ;
import java.io.File ;
import java.io.FileWriter ;
import java.nio.charset.Charset ;

class Emof2013CodeGeneratorTest {
//    private EmofModelGenerator codeGenerator;

    @Test
    void simpleClass() throws Exception {
        TestHelper<Emof2013ModelGenerator> helper = new TestHelper<>(new Emof2013ModelGenerator());
        URL textModel = Resources.getResource("rosetta/sample.rosetta");
        RosettaModel model = helper.parse(textModel);
        Emof2013ModelGenerator generator = helper.getExternalGenerator();
        Map<String, ? extends CharSequence> files = generator.afterGenerate(Collections.singletonList(model));
        assertGenerated(Resources.getResource("sample/Foo.sample.emof"), files);

        FileWriter file = new FileWriter ( "./Foo.test.emof2013.emof" ) ;
        Map.Entry<String, ? extends CharSequence> entry = files.entrySet().iterator().next() ;
        file.write(entry.getKey());
        file.close();
    }

    private void assertGenerated(URL source, Map<String, ? extends CharSequence> map) {
        assertThat(map.entrySet(), hasSize(1));

        Map.Entry<String, ? extends CharSequence> entry = map.entrySet().iterator().next();
//        assertThat(entry.getKey(), equalToIgnoringWhiteSpace(toStringContents(source)));
    }
}

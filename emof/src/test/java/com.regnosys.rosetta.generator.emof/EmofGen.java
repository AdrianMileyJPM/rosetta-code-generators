package com.regnosys.rosetta.generator.emof;

import com.regnosys.rosetta.generators.test.TestHelper;
import com.regnosys.rosetta.rosetta.RosettaModel;
import com.regnosys.rosetta.transgest.ModelLoaderImpl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class EmofGen {

    public static void main(String[] args) throws IOException {
        if (args.length != 0) {
            System.out.println("2 args required. <dir-where-rosetta-files-are> <out-put-dir>");
        }

        Path rosettaDir = Path.of(args[0]);
        Path outDir = Files.createDirectories(Path.of(args[1]));

        List<RosettaModel> rosettaModels = loadClasses(rosettaDir);
        TestHelper<EmofModelGenerator> helper = new TestHelper<>(new EmofModelGenerator());
        EmofModelGenerator generator = helper.getExternalGenerator();
        Map<String, ? extends CharSequence> generatedCode = generator.afterGenerate(rosettaModels);
        generatedCode.forEach((fileName, contents) -> {
            try {
                Files.write(outDir.resolve(fileName), contents.toString().getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println("Done");
    }

    public static List<RosettaModel> loadClasses(Path rosettaDirPath) throws IOException {
        return new ModelLoaderImpl(Stream.concat(
                        Stream.of(EmofGen.class.getClassLoader().getResource("model/basictypes.rosetta"),
                                EmofGen.class.getClassLoader().getResource("model/annotations.rosetta")),
                        Files.list(rosettaDirPath)
                                .filter(x -> x.toString().endsWith(".rosetta"))
                                .map(x -> {
                                    try {
                                        return x.toUri().toURL();
                                    } catch (MalformedURLException e) {
                                        throw new RuntimeException(e);
                                    }
                                }))
                .toArray(URL[]::new)).models();
    }


}

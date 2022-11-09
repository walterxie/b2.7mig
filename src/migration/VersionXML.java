package migration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * BEAST 2.7 migration
 * @author Walter Xie
 */
public class VersionXML {

    final static String INDENT = "  ";
    final Path srcDir;

    public VersionXML(String projectRoot) {
        srcDir = Paths.get(projectRoot, "src");
        if (!srcDir.toFile().exists())
            throw new RuntimeException("Cannot find the src dir : " + srcDir);
    }

    public List<Path> getAllJavaFiles() throws IOException {
        return Files.find(srcDir, 999, (p, bfa) -> bfa.isRegularFile()).sorted().toList();
    }

    public void printServices(List<Path> allFiles) {
        System.out.println(INDENT + "<service type=\"beastfx.app.inputeditor.InputEditor\">");
        for (Path path : allFiles) {
            String fn = pathToPkgFile(path);
            if (fn.contains("beauti"))
                System.out.println(INDENT + INDENT + "<provider classname=\"" + fn + "\"/>");
        }
        System.out.println(INDENT + "</service>");

        System.out.println(INDENT + "<service type=\"beast.base.core.BEASTInterface\">");
        for (Path path : allFiles) {
            String fn = pathToPkgFile(path);
            if (!fn.contains("beauti"))
                System.out.println(INDENT + INDENT + "<provider classname=\"" + fn + "\"/>");
        }
        System.out.println(INDENT + "</service>");
    }

    private String pathToPkgFile(Path path) {
        String p = path.toString().replace(srcDir.toString(), "").replace(".java", "");
        if (p.startsWith("/"))
            p = p.substring(1);
        return p.replaceAll("\\/", ".");
    }

    public static void main(String[] args) throws IOException {
        if (args == null)
            throw new IllegalArgumentException("Provide the project root directory path to the args[0] !");

        VersionXML versionXML = new VersionXML(args[0]);

        List<Path> allFiles = versionXML.getAllJavaFiles();

        versionXML.printServices(allFiles);

    }

}

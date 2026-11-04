import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class DriverTreeWalker {
    public static void main(String[] args) throws IOException {
        Path startDir = Paths.get(".");
        Path reportFile = Paths.get("folder_summary_report.txt");

        System.out.println("=== Walking Folder Tree with NIO.2 (Files.walkFileTree) ===");

        try (BufferedWriter writer = Files.newBufferedWriter(reportFile)) {
            writer.write(String.format("%-35s %-12s %-25s\n", "FILE / DIRECTORY NAME", "SIZE (BYTES)", "LAST MODIFIED"));
            writer.write("--------------------------------------------------------------------------------\n");

            Files.walkFileTree(startDir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    String line = String.format("%-35s %-12d %-25s",
                            file.getFileName().toString(),
                            attrs.size(),
                            attrs.lastModifiedTime().toString());
                    writer.write(line + "\n");
                    System.out.println(line);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    String dirLine = "[DIR] " + dir.getFileName();
                    writer.write(dirLine + "\n");
                    System.out.println(dirLine);
                    return FileVisitResult.CONTINUE;
                }
            });
        }

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Summary report successfully written to: " + reportFile.toAbsolutePath());
    }
}
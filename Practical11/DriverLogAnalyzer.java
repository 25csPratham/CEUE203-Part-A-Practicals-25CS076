import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class DriverLogAnalyzer {
    public static void main(String[] args) throws IOException {
        Path logsDir = Paths.get("sample_logs");
        if (!Files.exists(logsDir)) {
            Files.createDirectory(logsDir);
        }

        // Create sample log files
        Path appLog = logsDir.resolve("app.log");
        Path authLog = logsDir.resolve("auth.log");
        Path dbLog = logsDir.resolve("db.log");

        Files.write(appLog, List.of(
            "INFO Application started successfully",
            "WARN High memory usage detected: 82%",
            "ERROR Failed to process transaction 10042",
            "INFO Server heartbeat normal"
        ));

        Files.write(authLog, List.of(
            "INFO User alice logged in",
            "ERROR Invalid credentials for user root",
            "ERROR Unauthorized access attempt from 192.168.1.50"
        ));

        Files.write(dbLog, List.of(
            "INFO Connection pool initialized with 10 connections",
            "WARN Query execution time exceeded 200ms",
            "ERROR Database timeout on connection 4",
            "INFO Backup completed successfully"
        ));

        System.out.println("=== Log Analyzer using NIO.2 (Path & Files) ===");
        String keyword = "ERROR";
        int grandTotalLines = 0;
        int keywordMatches = 0;

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(logsDir, "*.log")) {
            for (Path entry : stream) {
                BasicFileAttributes attrs = Files.readAttributes(entry, BasicFileAttributes.class);
                List<String> lines = Files.readAllLines(entry);
                int fileLines = lines.size();
                int fileKeywordCount = 0;

                for (String line : lines) {
                    if (line.contains(keyword)) {
                        fileKeywordCount++;
                    }
                }

                grandTotalLines += fileLines;
                keywordMatches += fileKeywordCount;

                System.out.println("File: " + entry.getFileName()
                    + " | Size: " + attrs.size() + " bytes"
                    + " | Total Lines: " + fileLines
                    + " | '" + keyword + "' Occurrences: " + fileKeywordCount);
            }
        }

        System.out.println("-------------------------------------------------");
        System.out.println("Grand Total Lines across all logs : " + grandTotalLines);
        System.out.println("Total '" + keyword + "' occurrences : " + keywordMatches);
    }
}
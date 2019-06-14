package io.github.thecreamedcorn;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class TestUtils {
    private static boolean running = false;
    private static File sqlToolRcFileHomeDir;
    private static File sqlToolRcFileTmp;

    public static void startDatabase() throws Exception {
        if (running) {
            return;
        }

        //move this program's sqltool.rc to home directory and temporarily relocate current one (if exists)
        File homeDir = new File(System.getenv("user.home"));
        if ((sqlToolRcFileHomeDir = new File(homeDir, "sqltool.rc")).exists()) {
            sqlToolRcFileTmp = Files.createTempFile("sqltool", ".rc.tmp").toFile();
            FileUtils.copyFile(sqlToolRcFileHomeDir, sqlToolRcFileTmp);
        }
        sqlToolRcFileHomeDir.createNewFile();
        IOUtils.copy(
                TestUtils.class.getClassLoader().getResourceAsStream("sqltool.rc"),
                new FileOutputStream(sqlToolRcFileHomeDir));

        //start the database

        //TODO implement this jawn

    }

    public static void stopDatabase() throws Exception {
        if (!running) {
            return;
        }

        //stop the database
        //TODO implement this jawn

        //delete or move back original sqltool.rc in home directory
        if (sqlToolRcFileTmp != null) {
            FileUtils.copyFile(sqlToolRcFileTmp, sqlToolRcFileHomeDir);
        } else {
            sqlToolRcFileHomeDir.delete();
        }
    }
}

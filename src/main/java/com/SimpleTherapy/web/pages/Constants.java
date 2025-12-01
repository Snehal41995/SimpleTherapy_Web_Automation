package com.SimpleTherapy.web.pages;

import java.io.File;

public class Constants {

    // Config.properties path
    public static final String ConfigFile_Path =
            System.getProperty("user.dir") + File.separator + "src" + File.separator +
                    "main" + File.separator + "java" + File.separator + "com" +
                    File.separator + "SimpleTherapy" + File.separator + "web" +
                    File.separator + "config" + File.separator + "config.properties";

    // Excel test data path
    public static final String SimpleTherapy_TestData =
            System.getProperty("user.dir") + File.separator + "Excels" +
                    File.separator + "SimpleTherapy_Details.xlsx";

    // Timeouts
    public static final long PAGE_LONG_TIMEOUT = 40;
    public static final long IMPLICIT_WAIT = 40;
    public static final long EXPLICIT_WAIT = 40;
    public static final long PAGE_LOADOUT = 60;
}

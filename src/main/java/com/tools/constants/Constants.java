package com.tools.constants;

import java.io.File;

public class Constants {
    public static final String TEST_DATA_FILE_PATH_FOR_CSV = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "testdata"
            + File.separator + "csv" + File.separator;
    public static final String WEB_DRIVERS_PATH = "src" + File.separator + "test" + File.separator + "resources"
            + File.separator + "drivers" + File.separator;
    public static final String TEST_DATA_FILE_PATH = System.getProperty("user.dir") + File.separator + "src"
            + File.separator + "test" + File.separator + "resources" + File.separator + "testdata" + File.separator;
    public static final String DOWNLOAD_FILE_PATH_FOR_ZIP = TEST_DATA_FILE_PATH + "zip"
            + File.separator;
}

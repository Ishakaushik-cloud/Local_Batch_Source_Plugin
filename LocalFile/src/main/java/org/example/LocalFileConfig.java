/*
 * Copyright © 2023 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.example;


import io.cdap.cdap.api.annotation.Description;
import io.cdap.cdap.api.annotation.Macro;
import io.cdap.cdap.api.annotation.Name;
import io.cdap.cdap.api.data.schema.Schema;
import io.cdap.cdap.api.plugin.PluginConfig;
import io.cdap.cdap.etl.api.FailureCollector;
import scala.Int;

import javax.annotation.Nullable;
import java.io.IOException;

public class LocalFileConfig extends PluginConfig {
    public static final String NAME_REFERENCE_NAME = "referenceName";
    public static final String NAME_FILE_PATH = "filePath";
    public static final String NAME_INCLUDE_HEADERS = "headers";//toggle
    public static final String NAME_DELIMITER = "delimiter";//drop down
    public static final String NAME_EMPTY_INPUT = "emptyInput";//radio button

    @Name(NAME_REFERENCE_NAME)
    @Description("This option is required for uniquely identifying the source.")

    private String referenceName;

    @Name(NAME_FILE_PATH)
    @Description("path to file(s) to read.")
    private String filePath;



    @Name(NAME_INCLUDE_HEADERS)
    @Description("Weather to use first row as header. Default value is false.")
    @Macro
    private String headers;

    @Name(NAME_DELIMITER)
    @Description("Choose delimiter symbol used in File.")
    private String delimiter;

    @Name(NAME_EMPTY_INPUT)
    @Description("Whether to allow input that does not exist.")
    @Macro
    private String emptyInput;


    public LocalFileConfig(String referenceName, String filePath, String headers, String delimiter, String emptyInput) {
        this.referenceName = referenceName;
        this.filePath = filePath;
        this.headers = headers;
        this.delimiter = delimiter;
        this.emptyInput = emptyInput;

    }
    public String getReferenceName() {
        return referenceName;
    }
    public String getFilePath() {
        return filePath;
    }


    public void validate(FailureCollector failureCollector) {
        if(referenceName==null||referenceName.isEmpty())
        {
            failureCollector.addFailure("reference name is null or empty","please enter name");
        }
        if(filePath==null||filePath.isEmpty())
        {
            failureCollector.addFailure(" path is null or empty","please give path");
        }
    }


}

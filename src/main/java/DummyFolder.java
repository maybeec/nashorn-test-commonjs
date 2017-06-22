import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.coveo.nashorn_modules.Folder;

/**
 *
 */
public class DummyFolder implements Folder {

    @SuppressWarnings("deprecation")
    @Override
    public String getFile(String arg0) {
        try {
            return FileUtils.readFileToString(new File("src/main/resources/tsmerger.js"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Folder getFolder(String arg0) {
        return this;
    }

    @Override
    public Folder getParent() {
        return this;
    }

    @Override
    public String getPath() {
        return "tsmerger.js";
    }

}

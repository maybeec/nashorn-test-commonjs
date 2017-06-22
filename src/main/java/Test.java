import java.io.File;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.commons.io.FileUtils;

import com.coveo.nashorn_modules.FilesystemFolder;
import com.coveo.nashorn_modules.Require;

import jdk.nashorn.api.scripting.NashornScriptEngine;

/**
 *
 */
public class Test {

    public static void main(String[] args) throws Exception {

        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        FilesystemFolder rootFolder = FilesystemFolder.create(new File("src/main/resources"), "UTF-8");
        Require.enable((NashornScriptEngine) engine, new DummyFolder());

        // ScriptContext context = new SimpleScriptContext();
        // context.setBindings(engine.createBindings(), ScriptContext.GLOBAL_SCOPE);
        // Bindings bindings = context.getBindings(ScriptContext.GLOBAL_SCOPE);
        // context.setBindings(bindings, ScriptContext.GLOBAL_SCOPE);
        // engine.eval(new FileReader("src/main/resources/tsmerger.js")/* , context */);

        // engine.eval(
        // "function merge(patchOverrides, baseContents, patchContents, resultFile, encoding) { " + "return
        // '';}",
        // context);

        // engine.setContext(context);

        // engine.createBindings().put("merge", "merge");
        // engine.setBindings(bindings, scope);
        // engine.eval("(function(global) { global.merge = require('tsmerger');})();");
        engine.eval("merge = require('tsmerger.js');");
        // engine.eval("import * as merge from 'tsmerger.js';");
        engine.eval("print(JSON.stringify(this));");

        Invocable invocable = (Invocable) engine;

        // Object result =
        // invocable.invokeFunction("merge", true, new
        // File("src/test/resources/class_1_base.ts").getAbsolutePath(),
        // new File("src/test/resources/class_1_patch.ts").getAbsolutePath(),
        // new File("src/test/resources/class_1_result.ts").getAbsolutePath(), "UTF-8");

        Object result = invocable.invokeFunction("merge", true,
            FileUtils.readFileToString(new File("src/test/resources/class_1_base.ts")),
            FileUtils.readFileToString(new File("src/test/resources/class_1_patch.ts")),
            new File("src/test/resources/class_1_result.ts").getAbsolutePath(), "UTF-8");

        System.out.println(result);
        System.out.println(result.getClass());

    }
}

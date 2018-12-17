package org.jboss.fuse.wsdl2rest.impl.codegen;

import java.net.URL;
import java.nio.file.Path;

import org.apache.cxf.tools.common.ToolContext;
import org.apache.cxf.tools.common.model.JavaModel;
import org.apache.cxf.tools.wsdlto.WSDLToJava;

/**
 * Generate Java Types from WSDL using Apache CXF WSDL2Java
 *
 * http://cxf.apache.org/docs/wsdl-to-java.html
 * 
 * @since 10-Nov-2016
 * @author tdiesler@redhat.com
 */
public class JavaTypeGenerator {

    private final URL wsdlURL;
    private final Path outpath;
    
    public JavaTypeGenerator(Path outpath, URL wsdlURL) {
        this.wsdlURL = wsdlURL;
        this.outpath = outpath;
    }


    public JavaModel execute() throws Exception {
        
        String[] args = new String[] {
                "-d", outpath.toString(),
                wsdlURL.toExternalForm(),
        };
        ToolContext ctx = new ToolContext();
        new WSDLToJava(args).run(ctx);
        
        return ctx.getJavaModel();
    }
}
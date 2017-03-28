package rdf;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;

/** Tutorial navigating a model
 */

public class NavigateModel {

	public static void main(String[] args) {
		
		final String inputFileName = "vc-db-1.rdf";
	    final String johnSmithURI = "http://somewhere/JohnSmith/";
	    
	    // create an empty model
        Model model = ModelFactory.createDefaultModel();
       
        // use the FileManager to find the input file
        InputStream in = FileManager.get().open(inputFileName);
        if (in == null) {
            throw new IllegalArgumentException( "File: " + inputFileName + " not found");
        }
        
        // read the RDF/XML file
        model.read(in, "");
        
        // write it to standard out
        model.write(System.out);
        
        // retrieve the Adam Smith vcard resource from the model
        Resource vcard = model.getResource(johnSmithURI);
        
        // retrieve the value of the N property
        Resource name = (Resource) vcard.getRequiredProperty(VCARD.N).getObject();
        
        // retrieve the given name property
        String fullName = vcard.getRequiredProperty(VCARD.FN).getString();
        
        // add two nick name properties to vcard
        vcard.addProperty(VCARD.NICKNAME, "Smithy")
             .addProperty(VCARD.NICKNAME, "Adman");
        
        // set up the output
        System.out.println("The nicknames of \"" + fullName + "\" are:");
        
        // list the nicknames
        StmtIterator iter = vcard.listProperties(VCARD.NICKNAME);
        while (iter.hasNext()) {
            System.out.println("    " + iter.nextStatement().getObject()
                                            .toString());
        }
	}

}

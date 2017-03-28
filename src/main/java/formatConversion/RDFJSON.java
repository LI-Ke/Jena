package rdf;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.*;

/** Tutorial 1 creating a simple model
 */

public class CreateGraph {
    
    public static void main (String args[]) {
    	// some definitions
        String personURI    = "http://somewhere/JohnSmith";
        String personURI2    = "http://somewhere/Jack";
        String fullName     = "John Smith";
        String name    = "Jack";
    	  
        // create an empty model
    	 Model model = ModelFactory.createDefaultModel();

    	// create the resource
    	Resource johnSmith = model.createResource(personURI);

    	// add the property
    	johnSmith.addProperty(VCARD.FN, fullName);
      
    	Resource johnSmith2 = model.createResource(personURI2).addProperty(VCARD.NAME, name);
    	  
        model.write(System.out);
      
    }
}

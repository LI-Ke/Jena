package rdf;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.VCARD;

/** Tutorial 2 resources as property values
 */

public class BlankNode {
	
	public static void main(String[] args) {
		// some definitions
		String personURI    = "http://somewhere/JohnSmith";
		String givenName    = "John";
		String familyName   = "Smith";
		String fullName     = givenName + " " + familyName;

		// create an empty Model
		Model model = ModelFactory.createDefaultModel();
		// create the resource
		Resource johnSmith = model.createResource(personURI).addProperty(VCARD.FN, fullName)
				.addProperty(VCARD.N, model.createResource().addProperty(VCARD.Given, givenName)
						.addProperty(VCARD.Family,familyName));
	
		model.write(System.out);
	}

}

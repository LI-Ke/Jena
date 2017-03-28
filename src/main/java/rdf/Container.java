package rdf;

import java.io.InputStream;

import org.apache.jena.rdf.model.Bag;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.NodeIterator;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.SimpleSelector;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;

public class Container {

	public static void main(String[] args) {
		
		final String inputFileName = "vc-db-1.rdf";
		
		// create an empty model
        Model model = ModelFactory.createDefaultModel();
       
        // use the class loader to find the input file
        InputStream in = FileManager.get().open( inputFileName );
        if (in == null) {
            throw new IllegalArgumentException( "File: " + inputFileName + " not found");
        }
        
        // read the RDF/XML file
        model.read(in, "");
        
        // create a bag
        Bag smiths = model.createBag();
        
        // select all the resources with a VCARD.FN property whose value ends with "Smith"
        StmtIterator iter = model.listStatements(
				new SimpleSelector(null, VCARD.FN, (RDFNode) null) {
					@Override
					public boolean selects (Statement s){
						return s.getString().endsWith("Smith");
					}
				});
        
        // add the Smith's to the bag
        while(iter.hasNext()){
        	smiths.add(iter.nextStatement().getSubject());
        }
        
        // print out the members of the bag
        //container has a iterator
        NodeIterator iter2 = smiths.iterator(); 
        if (iter2.hasNext()) {
            System.out.println("The bag contains:");
            while (iter2.hasNext()) {
            	System.out.println("  " + ((Resource) iter2.next()).getProperty(VCARD.FN)
                                        .getString());
            }
        } else {
        	System.out.println("The bag is empty");
        }

	}

}

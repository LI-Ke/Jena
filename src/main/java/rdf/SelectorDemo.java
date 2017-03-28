package rdf;

import java.io.InputStream;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.SimpleSelector;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;

public class SelectorDemo {

	public static void main(String[] args) {
		final String inputFileName = "vc-db-1.rdf";
		
		// create an empty model
		Model model = ModelFactory.createDefaultModel();
				
		// use the FileManager to find the input file
		InputStream in = FileManager.get().open(inputFileName);
				
		if (in == null){
			throw new IllegalArgumentException("File: " + inputFileName + " not found");
		}
				
		// read the RDF/XML file
		model.read( in, "");
		        
		// write it to standard out
		model.write(System.out);
		System.out.println();
		
		// select all the resources with a VCARD.FN property whose value ends with "Smith"
		StmtIterator iter = model.listStatements(
				new SimpleSelector(null, VCARD.FN, (RDFNode) null) {
					@Override
					public boolean selects (Statement s){
						return s.getString().endsWith("Smith");
					}
				});
		
		if (iter.hasNext()){
        	System.out.println("The database contains vcards for:");
        	while(iter.hasNext()){
        		System.out.println(" " + iter.nextStatement().getString());
        	}
        } else {
        	System.out.println("No vcards were found in the database");
        }

	}

}

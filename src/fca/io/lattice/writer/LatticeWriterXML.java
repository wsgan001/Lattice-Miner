package fca.io.lattice.writer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import fca.core.lattice.ConceptLattice;
import fca.exception.WriterException;
import fca.io.lattice.LatticeWriter;

/**
 * Le standard LatticeWriter pour l'ecriture de treillis dans un fichier XML
 * @author Ludovic Thomas
 * @version 1.0
 */
public abstract class LatticeWriterXML extends LatticeWriter {
	
	/**
	 * Le Document XML en cours d'ecriture
	 */
	protected Document doc;
	
	/**
	 * Constructeur de l'ecriture de treillis abstrait pour un fichier XML
	 * @param contextFile le fichier dans lequel ecrire
	 * @param lattice le treillis a ecrire
	 * @throws IOException si le fichier ne peut �tre trouv� ou est corrompu
	 * @throws WriterException si une erreur d'ecriture arrive
	 */
	public LatticeWriterXML(File contextFile, ConceptLattice lattice) throws IOException, WriterException {
		super(contextFile, lattice);
		writeLattice();
	}
	
	/*
	 * (non-Javadoc)
	 * @see fca.io.lattice.LatticeWriter#writeLattice()
	 */
	@Override
	protected void writeLattice() throws IOException {
		
		// Cr�er le document de base
		doc = new Document();
		
		// L'element "Data"
		Element root = getRootElement();
		doc.addContent(root);
		
		//On utilise ici un affichage classic avec getPrettyFormat()
		XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
		
		// S�rialise le DOM depuis le noeud "doc"
		sortie.output(doc, new FileOutputStream(latticeFile.getAbsolutePath()));
	}
	
	/**
	 * Recup�re les objets du contexte et g�n�re le noeud correspondant
	 */
	protected abstract Element getObjectsElement();
	
	/**
	 * Recup�re les attributs du contexte et g�n�re le noeud correspondant
	 */
	protected abstract Element getAttributesElement();
	
	/**
	 * Recup�re les donn�es du contexte et g�n�re le noeud correspondant
	 */
	protected abstract Element getDataElement();
	
	/**
	 * Recup�re tout le context et g�n�re le noeud de la racine du fichier XML final
	 */
	protected abstract Element getRootElement();
	
}
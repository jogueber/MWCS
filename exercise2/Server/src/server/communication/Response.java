package server.communication;

/*
 * Created by JAC (Java Asn1 Compiler)
 */

import com.turkcelltech.jac.*;
import com.chaosinmotion.asn1.Tag;

public class Response extends Sequence
{
	/**
	 * if you want to set/fill an element below, just call the setValue(..) method over its instance.
	 *
	 * To encode/decode your object, just call encode(..) decode(..) methods.
	 * See 'TestProject.java' in the project to examine encoding/decoding examples
	 */
	public ASN1Integer value = new ASN1Integer("value");
	public PrintableString randStr = new PrintableString("randStr");
	/* end of element declarations */
	
	/**
	* asn.1 SEQUENCE constructor
	*/
	public
	Response()
	{
		super();
		setUpElements();
	}

	/**
	* asn.1 SEQUENCE constructor with its name
	*/
	public
	Response(String name)
	{
		super(name);
		setUpElements();
	}
	

	protected void
	setUpElements()
	{
		super.addElement(value);
		value.setTaggingMethod(Tag.IMPLICIT);
		value.setTagClass(Tag.CONTEXT);
		value.setTagNumber(0);
		super.addElement(randStr);
		randStr.setTaggingMethod(Tag.IMPLICIT);
		randStr.setTagClass(Tag.CONTEXT);
		randStr.setTagNumber(1);
	/* end of element setup */
	}


}

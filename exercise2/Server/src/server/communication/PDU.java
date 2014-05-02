package server.communication;

/*
 * Created by JAC (Java Asn1 Compiler)
 */

import com.turkcelltech.jac.*;
import com.chaosinmotion.asn1.Tag;

public class PDU extends Choice
{
	/**
	* For encoding, just call directly encode(..) method after after setting (initializing) only 1 element in your choice object.
	* 
	* For decoding, just directly call decode(..) method. 
	* You can call the method 'getCurrentChoice()' after decoding to learn which element is decoded from byte array inside choice record.
	*
	* See 'choice example' section in 'TestProject.java' in the project for encoding/decoding examples with Choice objects.
	*/
	
	public Request request = new Request("request");
	public Response response = new Response("response");
	public Verification verification = new Verification("verification");
	/* end of element declarations */
	
	/**
	* constructor without a name
	*/
	public
	PDU()
	{
		super();
		setUpElements();
	}
	
	/**
	* constructor with a name
	*/
	public
	PDU(String name)
	{
		super(name);
		setUpElements();
	}

	protected void
	setUpElements()
	{
		super.addElement(request);
		request.setTaggingMethod(Tag.IMPLICIT);
		request.setTagClass(Tag.CONTEXT);
		request.setTagNumber(0);
		super.addElement(response);
		response.setTaggingMethod(Tag.IMPLICIT);
		response.setTagClass(Tag.CONTEXT);
		response.setTagNumber(1);
		super.addElement(verification);
		verification.setTaggingMethod(Tag.IMPLICIT);
		verification.setTagClass(Tag.CONTEXT);
		verification.setTagNumber(2);
	/* end of element setup */
	}
}

package Specfication;


/**
* Specfication/quoterOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Specfication.idl
* Donnerstag, 22. Mai 2014 11:46 Uhr MESZ
*/

public interface quoterOperations 
{
  double getStockByIsn (String isn) throws Specfication.NoSuchStock;
  double getStockByName (String name) throws Specfication.NoSuchStock;
} // interface quoterOperations

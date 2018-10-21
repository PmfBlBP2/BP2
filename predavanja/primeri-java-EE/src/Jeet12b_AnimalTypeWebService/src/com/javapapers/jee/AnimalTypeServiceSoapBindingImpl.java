/**
 * AnimalTypeServiceSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.javapapers.jee;

public class AnimalTypeServiceSoapBindingImpl implements com.javapapers.jee.AnimalTypeService{
    public String animalType(String animal) throws java.rmi.RemoteException {
    	String animalType = "";
  		if ("Lav".equals(animal)) {
  			animalType = "Divlja";
  		} else if ("Tigar".equals(animal)) {
  			animalType = "Divlja";
  		} else if ("Kokoska".equals(animal)) {
  			animalType = "Domaca";
  		} else if ("Pas".equals(animal)) {
  			animalType = "Domaca";
  		} else if ("Macka".equals(animal)) {
  			animalType = "Domaca";
  		} else {
  			animalType = "Ne znam!";
  		}
  		return animalType;
    }

}

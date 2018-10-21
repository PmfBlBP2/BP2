/**
 * AnimalTypeServiceSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.javapapers.jee;

public class AnimalTypeServiceSoapBindingSkeleton implements com.javapapers.jee.AnimalTypeService, org.apache.axis.wsdl.Skeleton {
    private com.javapapers.jee.AnimalTypeService impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://jee.javapapers.com", "animal"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("animalType", _params, new javax.xml.namespace.QName("http://jee.javapapers.com", "animalTypeReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://jee.javapapers.com", "animalType"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("animalType") == null) {
            _myOperations.put("animalType", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("animalType")).add(_oper);
    }

    public AnimalTypeServiceSoapBindingSkeleton() {
        this.impl = new com.javapapers.jee.AnimalTypeServiceSoapBindingImpl();
    }

    public AnimalTypeServiceSoapBindingSkeleton(com.javapapers.jee.AnimalTypeService impl) {
        this.impl = impl;
    }
    public java.lang.String animalType(java.lang.String animal) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.animalType(animal);
        return ret;
    }

}

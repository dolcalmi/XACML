/*
 *                        AT&T - PROPRIETARY
 *          THIS FILE CONTAINS PROPRIETARY INFORMATION OF
 *        AT&T AND IS NOT TO BE DISCLOSED OR USED EXCEPT IN
 *             ACCORDANCE WITH APPLICABLE AGREEMENTS.
 *
 *          Copyright (c) 2013 AT&T Knowledge Ventures
 *              Unpublished and Not for Publication
 *                     All Rights Reserved
 */
package com.att.research.xacml.std.datatypes;

import java.text.ParseException;

import com.att.research.xacml.api.DataTypeException;
import com.att.research.xacml.api.XACML2;

/**
 * DataTypeIpAddress extends {@link com.att.research.xacml.common.datatypes.DatatypeBase} to implement the XACML ipAddress data type.
 * 
 * @author car
 * @version $Revision: 1.1 $
 */
public class DataTypeIpAddress extends DataTypeSemanticStringBase<IPAddress> {
	private static final DataTypeIpAddress	singleInstance	= new DataTypeIpAddress();
	
	private DataTypeIpAddress() {
		super(XACML2.ID_DATATYPE_IPADDRESS, IPAddress.class);
	}
	
	public static DataTypeIpAddress newInstance() {
		return singleInstance;
	}

	@Override
	public IPAddress convert(Object source) throws DataTypeException {
		if (source == null || (source instanceof IPAddress)) {
			return (IPAddress)source;
		} else {
			String	stringValue	= this.convertToString(source);
			if (stringValue == null) {
				return null;
			}
			IPAddress	ipAddress	= null;
			try {
				ipAddress	= IPAddress.newInstance(stringValue);
			} catch (ParseException ex) {
				throw new DataTypeException(this, "Failed to convert \"" + source.getClass().getCanonicalName() + "\" with value \"" + stringValue + "\" to IPAddress", ex);
			}
			return ipAddress;
		}
	}

}

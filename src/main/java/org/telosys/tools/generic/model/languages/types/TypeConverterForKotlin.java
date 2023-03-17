/**
 *  Copyright (C) 2008-2017  Telosys project org. ( http://www.telosys.org/ )
 *
 *  Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *          http://www.gnu.org/licenses/lgpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.telosys.tools.generic.model.languages.types;

import java.util.LinkedList;
import java.util.List;

import org.telosys.tools.commons.JavaTypeUtil;
import org.telosys.tools.generic.model.types.NeutralType;

/**
 * Type converter for "KOTLIN" language
 * 
 * @author Laurent Guerin
 *
 */
public class TypeConverterForKotlin extends TypeConverter {

	private static final String KOTLIN_STRING    = "String" ;
	private static final String KOTLIN_BOOLEAN   = "Boolean" ;
	private static final String KOTLIN_BYTE      = "Byte" ;
	private static final String KOTLIN_UBYTE     = "UByte" ;  // literal with "u" : 12u
	private static final String KOTLIN_SHORT     = "Short" ;
	private static final String KOTLIN_USHORT    = "UShort" ; // literal with "u" : 12u
	private static final String KOTLIN_INT       = "Int" ;
	private static final String KOTLIN_UINT      = "UInt" ; // literal with "u" : 12u
	private static final String KOTLIN_LONG      = "Long" ; // literal (nothing special) : 12
	private static final String KOTLIN_ULONG     = "ULong" ; // literal 12u
	private static final String KOTLIN_FLOAT     = "Float" ; // literal with "f" : var ff: Float = 123.45f
	private static final String KOTLIN_DOUBLE    = "Double" ; // literal (nothing special) : var dd: Double = 123.45
	private static final String KOTLIN_BYTEARRAY = "ByteArray " ;

	
	public static final String JAVA_BIGDECIMAL    = "java.math.BigDecimal" ; // import java.math.BigDecimal
	public static final String JAVA_LOCALDATE     = "java.time.LocalDate" ;  // import java.time.LocalDateTime
	public static final String JAVA_LOCALTIME     = "java.time.LocalTime" ;
	public static final String JAVA_LOCALDATETIME = "java.time.LocalDateTime" ;	
	
	public TypeConverterForKotlin() {
		super("Kotlin");
		
		//--- Primitive types : Kotlin basic types (non Java types) are considered as "primitive types" (no import)
		declarePrimitiveType( buildPrimitiveType(NeutralType.STRING,  KOTLIN_STRING) );
		declarePrimitiveType( buildPrimitiveType(NeutralType.BOOLEAN, KOTLIN_BOOLEAN) );
		declarePrimitiveType( buildPrimitiveType(NeutralType.BYTE,    KOTLIN_BYTE) );
		declarePrimitiveType( buildPrimitiveType(NeutralType.SHORT,   KOTLIN_SHORT) );
		declarePrimitiveType( buildPrimitiveType(NeutralType.INTEGER, KOTLIN_INT) );
		declarePrimitiveType( buildPrimitiveType(NeutralType.LONG,    KOTLIN_LONG) );
		declarePrimitiveType( buildPrimitiveType(NeutralType.FLOAT,   KOTLIN_FLOAT) );
		declarePrimitiveType( buildPrimitiveType(NeutralType.DOUBLE,  KOTLIN_DOUBLE) );
		declarePrimitiveType( buildPrimitiveType(NeutralType.BINARY,  KOTLIN_BYTEARRAY) );

		//--- Primitive types for "UNSIGNED" option 
		declarePrimitiveUnsignedType( buildPrimitiveType(NeutralType.BYTE,    KOTLIN_UBYTE  ) );
		declarePrimitiveUnsignedType( buildPrimitiveType(NeutralType.SHORT,   KOTLIN_USHORT ) );
		declarePrimitiveUnsignedType( buildPrimitiveType(NeutralType.INTEGER, KOTLIN_UINT   ) );
		declarePrimitiveUnsignedType( buildPrimitiveType(NeutralType.LONG,    KOTLIN_ULONG  ) );
		
		//--- Object types : Java types used in Kotlin are considered as "object types"		
		declareObjectType( buildObjectType(NeutralType.DECIMAL,   JAVA_BIGDECIMAL) );
		declareObjectType( buildObjectType(NeutralType.DATE,      JAVA_LOCALDATE) );
		declareObjectType( buildObjectType(NeutralType.TIME,      JAVA_LOCALTIME) );
		declareObjectType( buildObjectType(NeutralType.TIMESTAMP, JAVA_LOCALDATETIME) );
	}

	private LanguageType buildPrimitiveType(String neutralType, String primitiveType)  {
		return new LanguageType(neutralType, 
				primitiveType,   // String simpleType, 
				primitiveType,   // String fullType, 
				true, // boolean isPrimitiveType, 
				primitiveType ); // String wrapperType
	}

	private LanguageType buildObjectType(String neutralType, String fullType)  {
		String simpleType = JavaTypeUtil.shortType(fullType);
		return new LanguageType(neutralType, 
				simpleType,   // String simpleType, 
				fullType,   // String fullType, 
				false, // boolean isPrimitiveType, 
				simpleType ); // String wrapperType
	}
	
	@Override
	public List<String> getComments() {
		List<String> l = new LinkedList<>();
		l.add("'@UnsignedType' :  <br>&nbsp;  has effect only for 'byte', 'short', 'int' and 'long' ");
		l.add("'@NotNull' :       <br>&nbsp;  no effect, use '$kotlin' object for 'nullable type' with '?'  ");
		l.add("'@PrimitiveType' : <br>&nbsp;  no effect ");
		l.add("'@ObjectType' :    <br>&nbsp;  no effect ");
		return l ;
	}

	@Override
	public LanguageType getType(AttributeTypeInfo attributeTypeInfo) {
		
		// Search first in primitive types (with optionally "unsigned option" )
		LanguageType lt = getPrimitiveType(attributeTypeInfo.getNeutralType(), attributeTypeInfo.isUnsignedTypeExpected() ) ; 
		if ( lt != null ) { // FOUND
			return lt ;
		}
		else { // NOT FOUND in primitive types
			// try to found in object types
			lt = getObjectType(attributeTypeInfo.getNeutralType() ) ;
			if ( lt != null ) { // FOUND
				return lt ;
			}
			else {
				// Still not found !!!
				throw new TelosysTypeNotFoundException(getLanguageName(), attributeTypeInfo);
			}		
		}
	}
	
	//--------------------------------------------------------------------------------------------
	// Collection type ( since v 3.3.0 )
	//--------------------------------------------------------------------------------------------	
	private static final String STANDARD_COLLECTION_SIMPLE_TYPE = "List" ;
	private static final String STANDARD_COLLECTION_FULL_TYPE   = "List" ; // no import required
	
	@Override
	public void setSpecificCollectionType(String specificCollectionType) {
		this.setSpecificCollectionFullType(specificCollectionType) ;
		this.setSpecificCollectionSimpleType(JavaTypeUtil.shortType(specificCollectionType));
	}

	@Override
	public String getCollectionType(String elementType) {
		return getCollectionSimpleType() + "<" + elementType + ">" ; 
	}
	
	@Override
	public String getCollectionSimpleType() {
		return getCollectionSimpleType(STANDARD_COLLECTION_SIMPLE_TYPE);
	}

	@Override
	public String getCollectionFullType() {
		return getCollectionFullType(STANDARD_COLLECTION_FULL_TYPE);
	}

}

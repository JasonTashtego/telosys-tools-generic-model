package org.telosys.tools.generic.model.types;

import static org.telosys.tools.generic.model.types.AttributeTypeInfo.NONE;
import static org.telosys.tools.generic.model.types.AttributeTypeInfo.NOT_NULL;
import static org.telosys.tools.generic.model.types.AttributeTypeInfo.OBJECT_TYPE;
import static org.telosys.tools.generic.model.types.AttributeTypeInfo.PRIMITIVE_TYPE;
import static org.telosys.tools.generic.model.types.AttributeTypeInfo.UNSIGNED_TYPE;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TypeConverterForCSharpTest  {

	private TypeConverter getTypeConverter() {
		return new TypeConverterForCSharp() ;
	}
	
	private LanguageType getType(AttributeTypeInfo typeInfo ) {
		LanguageType lt = getTypeConverter().getType(typeInfo);
		System.out.println( typeInfo + " --> " + lt );
		return lt ;
	}
	
	private LanguageType getType(String neutralType, int typeInfo ) {
		AttributeTypeInfo attributeTypeInfo = new AttributeTypeInfo(neutralType, typeInfo);
		return getType(attributeTypeInfo);
	}
	
	private void checkPrimitiveType( LanguageType lt, String primitiveType, String wrapperType) {
		assertNotNull(lt);
		assertTrue ( lt.isPrimitiveType() ) ;
		assertEquals(primitiveType, lt.getSimpleType() );
		assertEquals(primitiveType, lt.getFullType() );
		assertEquals(wrapperType, lt.getWrapperType() );
	}

	private void checkObjectType( LanguageType lt, String simpleType, String fullType) {
		assertNotNull(lt);
		assertFalse ( lt.isPrimitiveType() ) ;
		assertEquals(simpleType, lt.getSimpleType() );
		assertEquals(fullType,   lt.getFullType() );
		assertEquals(simpleType, lt.getWrapperType() );
	}
	
	@Test
	public void testString() {
		System.out.println("--- ");
		
		checkPrimitiveType( getType(NeutralType.STRING, NONE ),            "string", "System.String");
		checkPrimitiveType( getType(NeutralType.STRING, NOT_NULL ),        "string", "System.String");
		checkPrimitiveType( getType(NeutralType.STRING, PRIMITIVE_TYPE ),  "string", "System.String");
		checkPrimitiveType( getType(NeutralType.STRING, UNSIGNED_TYPE ),   "string", "System.String");
		checkPrimitiveType( getType(NeutralType.STRING, PRIMITIVE_TYPE + UNSIGNED_TYPE ), "string", "System.String");
//		checkPrimitiveType( getType(NeutralType.STRING, SQL_TYPE),         "string", "System.String" );
		
		checkObjectType( getType(NeutralType.STRING, OBJECT_TYPE),            "String", "System.String" );
//		checkObjectType( getType(NeutralType.STRING, OBJECT_TYPE + SQL_TYPE), "String", "System.String" );
	}

	@Test
	public void testBoolean() {
		System.out.println("--- ");
				
		checkPrimitiveType( getType( NeutralType.BOOLEAN, NONE ),                  "bool", "System.Boolean" );
		checkPrimitiveType( getType( NeutralType.BOOLEAN, NOT_NULL ),              "bool", "System.Boolean");
		checkPrimitiveType( getType( NeutralType.BOOLEAN, PRIMITIVE_TYPE ),        "bool", "System.Boolean");
		checkPrimitiveType( getType( NeutralType.BOOLEAN, UNSIGNED_TYPE ),         "bool", "System.Boolean");
		checkPrimitiveType( getType( NeutralType.BOOLEAN, PRIMITIVE_TYPE + UNSIGNED_TYPE ), "bool", "System.Boolean");
//		checkPrimitiveType( getType( NeutralType.BOOLEAN, SQL_TYPE),               "bool", "System.Boolean" );
//		checkPrimitiveType( getType( NeutralType.BOOLEAN, NOT_NULL + SQL_TYPE),    "bool", "System.Boolean" );
		
		checkObjectType( getType( NeutralType.BOOLEAN, OBJECT_TYPE),            "Boolean", "System.Boolean" );
//		checkObjectType( getType( NeutralType.BOOLEAN, OBJECT_TYPE + SQL_TYPE), "Boolean", "System.Boolean" );
		checkObjectType( getType( NeutralType.BOOLEAN, NOT_NULL + OBJECT_TYPE), "Boolean", "System.Boolean" );

	}

	@Test
	public void testShort() {
		System.out.println("--- ");
		checkPrimitiveType( getType( NeutralType.SHORT, NONE ),              "short",  "System.Int16" );
		checkPrimitiveType( getType( NeutralType.SHORT, UNSIGNED_TYPE ),     "ushort", "System.UInt16");
		
		checkObjectType( getType( NeutralType.SHORT, OBJECT_TYPE ),          "Int16",  "System.Int16");
	}

	@Test
	public void testDecimal() {
		System.out.println("--- ");

		checkPrimitiveType( getType( NeutralType.DECIMAL, NONE ),              "decimal",  "System.Decimal" );
		checkPrimitiveType( getType( NeutralType.DECIMAL, UNSIGNED_TYPE ),     "decimal", "System.Decimal");
		
		checkObjectType( getType( NeutralType.DECIMAL, OBJECT_TYPE ),          "Decimal",  "System.Decimal");
	}

	@Test
	public void testDate() {
		System.out.println("--- ");
		checkObjectType( getType( NeutralType.DATE, NONE ),           "DateTime",  "System.DateTime" );
		checkObjectType( getType( NeutralType.DATE, UNSIGNED_TYPE ),  "DateTime",  "System.DateTime" );
		checkObjectType( getType( NeutralType.DATE, OBJECT_TYPE ),    "DateTime",  "System.DateTime" );
		checkObjectType( getType( NeutralType.DATE, NOT_NULL ),       "DateTime",  "System.DateTime" );
	}

	@Test
	public void testTime() {
		System.out.println("--- ");
		checkObjectType( getType( NeutralType.TIME, NONE ),           "DateTime",  "System.DateTime" );
		checkObjectType( getType( NeutralType.TIME, UNSIGNED_TYPE ),  "DateTime",  "System.DateTime" );
		checkObjectType( getType( NeutralType.TIME, OBJECT_TYPE ),    "DateTime",  "System.DateTime" );
		checkObjectType( getType( NeutralType.TIME, NOT_NULL ),       "DateTime",  "System.DateTime" );
	}

	@Test
	public void testTimestamp() {
		System.out.println("--- ");
		checkObjectType( getType( NeutralType.TIMESTAMP, NONE ),           "DateTime",  "System.DateTime" );
		checkObjectType( getType( NeutralType.TIMESTAMP, UNSIGNED_TYPE ),  "DateTime",  "System.DateTime" );
		checkObjectType( getType( NeutralType.TIMESTAMP, OBJECT_TYPE ),    "DateTime",  "System.DateTime" );
		checkObjectType( getType( NeutralType.TIMESTAMP, NOT_NULL ),       "DateTime",  "System.DateTime" );
	}

}

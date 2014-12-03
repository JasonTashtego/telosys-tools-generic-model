/**
 *  Copyright (C) 2008-2014  Telosys project org. ( http://www.telosys.org/ )
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
package org.telosys.tools.generic.model;

/**
 * This interface describe an abstract attribute that must be implemented 
 * by each attribute in a concrete model 
 * 
 * @author Laurent Guerin
 * @since 3.0.0
 */
public interface Attribute {

	/**
	 * Returns the storage value for a boolean when it is 'FALSE' <br>
	 * i.e. the value to be stored in the database ( e.g. '0', 'F', 'false', etc)<br> 
	 * If not supported by the model implementation : 'null'
	 * @return
	 */
	public String getBooleanFalseValue() ;

	/**
	 * Returns the storage value for a boolean when is 'TRUE' <br>
	 * i.e. the value to be stored in the database ( e.g. '1', 'T', 'true', etc)<br> 
	 * If not supported by the model implementation : 'null'
	 * @return
	 */
	public String getBooleanTrueValue();

	/**
	 * Returns the database comment for the attribute<br> 
	 * If not supported by the model implementation : 'null'
	 * @return
	 */
	public String getDatabaseComment() ;

	/**
	 * Returns the database default value for the attribute (or null if none)<br> 
	 * If not supported by the model implementation : 'null'
	 * @return
	 */
	public String getDatabaseDefaultValue();

	/**
	 * Returns the database name for the attribute <br>
	 * Typically the column name for a relational database<br> 
	 * If not supported by the model implementation : 'null'
	 * @return
	 */
	public String getDatabaseName();

	/**
	 * Returns the database size for the attribute if any (null if none)<br> 
	 * If not supported by the model implementation : 'null'
	 * @return
	 */
	public Integer getDatabaseSize();

	/**
	 * Returns the database native type for the attribute<br>
	 * For example : INTEGER, VARCHAR, etc...<br> 
	 * If not supported by the model implementation : 'null'
	 * @return
	 */
	public String getDatabaseType();

	/**
	 * Returns the 'date after' value (for date validation)<br> 
	 * If not supported by the model implementation : 'null'
	 * @return
	 */
	public String getDateAfterValue() ;

	/**
	 * Returns the 'date before' value (for date validation)<br> 
	 * If not supported by the model implementation : 'null'
	 * @return
	 */
	public String getDateBeforeValue() ;

	/**
	 * Returns the type of the date : $const.DATE_ONLY, $const.TIME_ONLY, $const.DATE_AND_TIME<br>
	 * if any (null if none)<br> 
	 * If not supported by the model implementation : 'null'
	 * @return
	 */
	public Integer getDateType();

	/**
	 * Returns the default value for the attribute (or null if none) <br> 
	 * If not supported by the model implementation : 'null'
	 * @return
	 */
	public String getDefaultValue();

	/**
	 * Returns the entity this attribute depends on <br>
	 * This information is MENDATORY, it must be provided by all models implementations
	 * @return
	 */
	public Entity getEntity();

	/**
	 * Returns the full type ( java.math.BigDecimal, java.util.Date, .. )
	 * @return
	 */
	public String getFullType();

	/**
	 * Returns the generator for a 'generated value'<br>
	 * Typically for JPA : 'SequenceGenerator' or 'TableGenerator'<br> 
	 * If not supported by the model implementation : 'null'
	 * @return
	 */
	public String getGeneratedValueGenerator() ;

	/**
	 * Returns the strategy for a 'generated value' (or null if none) <br>
	 * e.g : 'auto', 'identity', 'sequence', 'table' <br> 
	 * If not supported by the model implementation : 'null'
	 * @return
	 */
	public String getGeneratedValueStrategy() ;

	/**
	 * Returns the initial value for the attribute (or null if none) <br> 
	 * If not supported by the model implementation : 'null'
	 * @return
	 */
	public String getInitialValue() ;

	/**
	 * Returns the "input type" defined for this attribute <br> 
	 * If not supported by the model implementation : 'null'
	 * @return
	 */
	public String getInputType();

	/**
	 * Returns the JDBC type of the attribute (the type code) if any (null if none)<br> 
	 * If not supported by the model implementation : 'null'
	 * @return
	 */
    public Integer getJdbcTypeCode();

	/**
	 * Returns the JDBC type name ('CHAR', 'VARCHAR', 'NUMERIC', ... )<br>
	 * The 'java.sql.Types' constant name for the current JDBC type code<br> 
	 * If not supported by the model implementation : 'null'
	 * @return
	 */
	public String getJdbcTypeName();

    /**
     * Returns the label defined for the attribute <br> 
	 * If not supported by the model implementation : 'null'
     * @return
     */
    public String getLabel();

	/**
	 * Returns the "maximum" length if any (null if none) <br> 
	 * If not supported by the model implementation : 'null'
	 * @return
	 */
	public Integer getMaxLength() ;

	/**
	 * Returns the "maximum" value if any (null if none)<br> 
	 * If not supported by the model implementation : 'null'
	 * @return
	 */
	public Integer getMaxValue() ;

	/**
	 * Returns the "minimum" length if any (null if none) <br> 
	 * If not supported by the model implementation : 'null'
	 * @return
	 */
	public Integer getMinLength() ;

	/**
	 * Returns the "minimum" value if any (null if none) <br> 
	 * If not supported by the model implementation : 'null'
	 * @return
	 */
	public Integer getMinValue() ;

	/**
	 * Returns the name of the attribute <br> 
	 * This information is MENDATORY, it must be provided by all models implementations
	 * @return
	 */
	public String getName();

	/**
	 * Returns the validation "pattern" (Reg Exp) if any, (null if none) <br> 
	 * If not supported by the model implementation : 'null'
	 * @return 
	 */
	public String getPattern(); 

	/**
	 * Returns the 'sequence allocation size' to be used in the 'sequence generator' definition"<br>
	 * Typically for JPA '@SequenceGenerator/allocationSize'  <br> 
	 * If not supported by the model implementation : 'null'
	 * @return
	 */
	public Integer getSequenceGeneratorAllocationSize() ;

	/**
	 * Returns the name of the 'sequence generator' <br>
	 * Typically for JPA '@SequenceGenerator/name'<br> 
	 * If not supported by the model implementation : 'null'
	 * @return
	 */
	public String getSequenceGeneratorName() ;

	/**
	 * Returns the 'sequence name' to be used in the 'sequence generator' definition <br>
	 * Typically for JPA '@SequenceGenerator/sequenceName'<br> 
	 * If not supported by the model implementation : 'null'
	 * @return
	 */
	public String getSequenceGeneratorSequenceName() ;

	/**
	 * Returns the simple type ( BigDecimal, Date, int, Integer, .. )
	 * @return
	 */
	public String getSimpleType() ;

	/**
	 * Returns the name of the 'table generator' <br>
	 * Typically for JPA '@TableGenerator/name'  <br> 
	 * If not supported by the model implementation : 'null'
	 * @return
	 */
	public String getTableGeneratorName() ;

	/**
	 * Returns the name of the Primary Key column used in the 'table generator' <br> 
	 * Typically for JPA '@TableGenerator/pkColumnName'  <br> 
	 * If not supported by the model implementation : 'null'
	 * @return
	 */
	public String getTableGeneratorPkColumnName() ;

	/**
	 * Returns the primary key value in the generator table that distinguishes this set <br>
	 * of generated values from others that may be stored in the table <br> 
	 * Typically for JPA '@TableGenerator/pkColumnValue' <br> 
	 * If not supported by the model implementation : 'null'
	 * @return
	 */
	public String getTableGeneratorPkColumnValue() ;

	/**
	 * Returns the name of the table used in the 'table generator' <br>
	 * Typically for JPA '@TableGenerator/table' <br> 
	 * If not supported by the model implementation : 'null'
	 * @return
	 */
	public String getTableGeneratorTable() ;

	/**
	 * Returns the name of the column that stores the last value generated by the 'table generator' <br>
	 * Typically for JPA '@TableGenerator/valueColumnName'   <br> 
	 * If not supported by the model implementation : 'null'
	 * @return
	 */
	public String getTableGeneratorValueColumnName() ;

	/**
	 * Returns the recommended type for the attribute <br>
	 * usually the simple type ( 'int', 'BigDecimal', 'Date' ) <br>
	 * sometimes the full type ( if the simple type is considered as ambiguous ) <br>
	 * Examples for Java : 'int', 'BigDecimal', 'Date', 'java.util.Date', 'java.sql.Date'
	 * @return
	 */
	public String getType(); // TODO useful ????

	/**
	 * Returns the Java wrapper type corresponding to the attribute's primitive type <br>
	 * Examples : 'Float' for 'float', 'Integer' for 'int', 'Boolean' for 'boolean', ... <br>
	 * The attribute's type is returned as is if it's not a primitive type
	 * @return
	 */
	public String getWrapperType(); // TODO useful ????
	
	/**
	 * Returns TRUE if the attribute is 'auto-incremented' by the database engine <br>
	 * when a new entity is inserted in the database <br>
	 * If not supported by the model implementation : 'false'
	 * @return
	 */
    public boolean isAutoIncremented();

	/**
	 * Returns TRUE if the attribute must be 'NOT NULL' when stored in the database
	 * If not supported by the model implementation : 'false'
	 * @return
	 */
    public boolean isDatabaseNotNull() ;
	
	/**
	 * Returns TRUE if the attribute's value is generated when a new entity is inserted in the database <br>
	 * It can be generated by the database ('auto-incremented') <br>
	 * or generated by the persistence layer (typically by JPA) 
	 * If not supported by the model implementation : 'false'
	 * @return
	 */
	public boolean isGeneratedValue() ;
	
	/**
	 * Returns TRUE if the attribute is the Primary Key or a part of the Primary Key in the database
	 * If not supported by the model implementation : 'false'
	 * @return
	 */
    public boolean isKeyElement() ;

	/**
	 * Returns TRUE if the attribute is a 'Long Text' <br>
	 * i.e. that cannot be transported in a classical string <br>
	 * Typically a text stored as a CLOB or a BLOB <br>
	 * If not supported by the model implementation : 'false'
	 * @return
	 */
	public boolean isLongText() ;

	/**
	 * Returns TRUE if the attribute has a 'Not Blank' validation rule <br>
	 * If not supported by the model implementation : 'false'
	 * @return
	 */
	public boolean isNotBlank() ;

	/**
	 * Returns TRUE if the attribute has a 'Not Empty' validation rule <br>
	 * If not supported by the model implementation : 'false'
	 * @return
	 */
	public boolean isNotEmpty() ;

	/**
	 * Returns TRUE if the attribute has a 'Not Null' validation rule <br>
	 * If not supported by the model implementation : 'false'
	 * @return
	 */
    public boolean isNotNull() ;

	/**
	 * Returns TRUE if the attribute is selected (check-box checked in the GUI) <br>
	 * If not supported by the model implementation : 'true' (attribute 'selected' by default)
	 * @return
	 */
	public boolean isSelected() ;

}

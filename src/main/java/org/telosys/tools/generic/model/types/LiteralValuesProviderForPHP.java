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
package org.telosys.tools.generic.model.types;

import java.math.BigDecimal;

/**
 * Literal values provider for "PHP" language <br>
 * <br>
 * Info :<br>
 * <br>
 *  https://stackoverflow.com/questions/2013848/uppercase-booleans-vs-lowercase-in-php <br>
 *  https://www.php-fig.org/psr/psr-2/ <br>
 *  https://stackoverflow.com/questions/80646/how-do-the-php-equality-double-equals-and-identity-triple-equals-comp <br>
 *    
 * @author Laurent GUERIN
 *
 */
public class LiteralValuesProviderForPHP extends LiteralValuesProvider {
	
	// According with "PSR-2: Coding Style Guide" : 
	//  "PHP keywords MUST be in lower case"
	//  "The PHP constants true, false, and null MUST be in lower case."
	private static final String NULL_LITERAL  = "null" ;   // "NULL"  or "null"
	private static final String TRUE_LITERAL  = "true" ;   // "TRUE"  or "true"
	private static final String FALSE_LITERAL = "false" ;  // "FALSE" or "false" 
	
	@Override
	public String getLiteralNull() {
		return NULL_LITERAL;
	}
	
	@Override
	public String getLiteralTrue() {
		return TRUE_LITERAL;
	}

	@Override
	public String getLiteralFalse() {
		return FALSE_LITERAL;
	}
		
	@Override
	public LiteralValue generateLiteralValue(LanguageType languageType, int maxLength, int step) {
		
		// The "neutral type" is the only information available in "LanguageType"
		String neutralType = languageType.getNeutralType(); 
		
		//--- STRING
		if ( NeutralType.STRING.equals(neutralType) ) {
			String value = buildStringValue(maxLength, step);
			return new LiteralValue("\"" + value + "\"", value) ;			
		}
		
		//--- NUMBER ( INTEGER )
		else if (  NeutralType.BYTE.equals(neutralType) 
				|| NeutralType.SHORT.equals(neutralType)
				|| NeutralType.INTEGER.equals(neutralType) 
				|| NeutralType.LONG.equals(neutralType) ) {
			Long value = buildIntegerValue(neutralType, step);  
			return new LiteralValue(value.toString(), value) ; // eg : 123
		}
		
		//--- NUMBER (NOT INTEGER)
		else if (  NeutralType.FLOAT.equals(neutralType) 
				|| NeutralType.DOUBLE.equals(neutralType) 
				|| NeutralType.DECIMAL.equals(neutralType) ) {
			BigDecimal value = buildDecimalValue(neutralType, step);
			return new LiteralValue(value.toString(), value) ; // eg :  123.77
		}
		
		//--- BOOLEAN
		else if ( NeutralType.BOOLEAN.equals(neutralType)  ) {
			boolean value = buildBooleanValue(step);
			return new LiteralValue(value ? TRUE_LITERAL : FALSE_LITERAL, Boolean.valueOf(value)) ;
		}

//		//--- DATE, TIME and TIMESTAMP :  there is no Date literal in TypeScript !
//		else if ( NeutralType.DATE.equals(neutralType)  ) {
//			return NULL_LITERAL ;
//		}
//		else if ( NeutralType.TIME.equals(neutralType)  ) {
//			return NULL_LITERAL ;
//		}
//		else if ( NeutralType.TIMESTAMP.equals(neutralType)  ) {
//			return NULL_LITERAL ;
//		}		
//		//--- BINARY
//		else if ( NeutralType.BINARY.equals(neutralType)  ) {
//			return NULL_LITERAL ;
//		}
		
		return new LiteralValue(NULL_LITERAL, null);
	}
	
	/* 
	 * Returns something like that : 
	 *   ' == 100' 
	 *   '.equals("xxx")'
	 */
	@Override
	public String getEqualsStatement(String value, LanguageType languageType) {

		// "=="   compares values (casting if necessary )
		// "==="  "Strict" (same type and sale value)
		//
		//  1 === "1": false // 1 is an integer, "1" is a string
		//  1 == "1": true // "1" gets casted to an integer, which is 1
		return " == " + value ; // Value comparison 
	}

}

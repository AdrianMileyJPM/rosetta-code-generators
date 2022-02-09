package com.regnosys.rosetta.generator.emof2013.util ;

public class IdentifierGenerator {
	IdentifierGenerator() { /* do nothing */ }
	public static String fqnDelimiter = "::" ;
	
	private static String fromStringValue ( String in ) {
	/* ToDo */
		return in ;
	}
	
	public static String fromOnePart ( String part1 ) {
		return fromStringValue ( part1 ) ;
	}

	public static String fromTwoParts ( String part1 , String part2 ) {
		return fromStringValue ( part1 + fqnDelimiter + part2 ) ;
	}

	public static String fromThreeParts ( String part1 , String part2 , String part3 ) {
		return fromStringValue ( part1 + fqnDelimiter + part2 + fqnDelimiter + part3 ) ;
	}

	public static String fromMetaPartName ( String ownerId , String partName ) {
		return fromStringValue( partName + fqnDelimiter + ownerId + fqnDelimiter + partName ) ;
	}
	
	public static String fromPartsList ( String[] partsList ) {
		String tmpFQN = "" ;
		for ( String thisPart : partsList ) {
			if ( tmpFQN.isEmpty() == false ) {
				tmpFQN = tmpFQN + fqnDelimiter + thisPart ;
			} else {
				tmpFQN = thisPart ;
			}
		}
		return fromStringValue(tmpFQN) ;
	}
}
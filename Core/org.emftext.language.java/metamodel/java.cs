//*******************************************************************************
// Copyright (c) 2006-2012 
// Software Technology Group, Dresden University of Technology
// 
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0 
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// 
// Contributors:
//   Software Technology Group - TU Dresden, Germany 
//      - initial API and implementation
// ******************************************************************************/

SYNTAXDEF java
FOR <http://www.emftext.org/java> <java.genmodel>
START containers.CompilationUnit, containers.Package, containers.EmptyModel

IMPORTS {
	annotations : <http://www.emftext.org/java/annotations>
	arrays : <http://www.emftext.org/java/arrays>
	classifiers : <http://www.emftext.org/java/classifiers>
	commons : <http://www.emftext.org/java/commons>
	containers : <http://www.emftext.org/java/containers>
	expressions : <http://www.emftext.org/java/expressions>
	generics : <http://www.emftext.org/java/generics>
	imports : <http://www.emftext.org/java/imports>
	instantiations : <http://www.emftext.org/java/instantiations>
	literals : <http://www.emftext.org/java/literals>
	members : <http://www.emftext.org/java/members>
	modifiers : <http://www.emftext.org/java/modifiers>
	operators : <http://www.emftext.org/java/operators>
	parameters : <http://www.emftext.org/java/parameters>
	references : <http://www.emftext.org/java/references>
	statements : <http://www.emftext.org/java/statements>
	types : <http://www.emftext.org/java/types>
	variables : <http://www.emftext.org/java/variables>
}

OPTIONS {	
	licenceHeader ="../../org.dropsbox/licence.txt";
	defaultTokenName = "IDENTIFIER";
	generateCodeFromGeneratorModel = "false";
	memoize = "true";
	usePredefinedTokens = "false";
	resolveProxyElementsAfterParsing = "false";
	disableLaunchSupport = "true";
	disableDebugSupport = "true";
	ignoreTypeRestrictionsForPrinting = "true";
}

TOKENS {
	DEFINE SL_COMMENT $'//'(~('\n'|'\r'|'\uffff'))* $;
	DEFINE ML_COMMENT $'/*'.*'*/'$;

	DEFINE BOOLEAN_LITERAL $'true'|'false'$;
	
	DEFINE CHARACTER_LITERAL $'\''('\\'('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')|('\\''u'('0'..'9'|'a'..'f'|'A'..'F')('0'..'9'|'a'..'f'|'A'..'F')('0'..'9'|'a'..'f'|'A'..'F')('0'..'9'|'a'..'f'|'A'..'F'))|('\\'('0'..'3')('0'..'7')('0'..'7')|'\\'('0'..'7')('0'..'7')|'\\'('0'..'7'))|~('\''|'\\'))'\''$;
	DEFINE STRING_LITERAL $'"'('\\'('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')|('\\''u'('0'..'9'|'a'..'f'|'A'..'F')('0'..'9'|'a'..'f'|'A'..'F')('0'..'9'|'a'..'f'|'A'..'F')('0'..'9'|'a'..'f'|'A'..'F'))|'\\'('0'..'7')|~('\\'|'"'))*'"'$;
	
	@SuppressWarnings(tokenOverlapping)
	DEFINE IDENTIFIER $('\u0024'|'\u0041'..'\u005a'|'\u005f'|'\u0061'..'\u007a'|'\u00a2'..'\u00a5'|'\u00aa'|'\u00b5'|'\u00ba'|'\u00c0'..'\u00d6'|'\u00d8'..'\u00f6'|'\u00f8'..'\u0236'|'\u0250'..'\u02c1'|'\u02c6'..'\u02d1'|'\u02e0'..'\u02e4'|'\u02ee'|'\u037a'|'\u0386'|'\u0388'..'\u038a'|'\u038c'|'\u038e'..'\u03a1'|'\u03a3'..'\u03ce'|'\u03d0'..'\u03f5'|'\u03f7'..'\u03fb'|'\u0400'..'\u0481'|'\u048a'..'\u04ce'|'\u04d0'..'\u04f5'|'\u04f8'..'\u04f9'|'\u0500'..'\u050f'|'\u0531'..'\u0556'|'\u0559'|'\u0561'..'\u0587'|'\u05d0'..'\u05ea'|'\u05f0'..'\u05f2'|'\u0621'..'\u063a'|'\u0640'..'\u064a'|'\u066e'..'\u066f'|'\u0671'..'\u06d3'|'\u06d5'|'\u06e5'..'\u06e6'|'\u06ee'..'\u06ef'|'\u06fa'..'\u06fc'|'\u06ff'|'\u0710'|'\u0712'..'\u072f'|'\u074d'..'\u074f'|'\u0780'..'\u07a5'|'\u07b1'|'\u0904'..'\u0939'|'\u093d'|'\u0950'|'\u0958'..'\u0961'|'\u0985'..'\u098c'|'\u098f'..'\u0990'|'\u0993'..'\u09a8'|'\u09aa'..'\u09b0'|'\u09b2'|'\u09b6'..'\u09b9'|'\u09bd'|'\u09dc'..'\u09dd'|'\u09df'..'\u09e1'|'\u09f0'..'\u09f3'|'\u0a05'..'\u0a0a'|'\u0a0f'..'\u0a10'|'\u0a13'..'\u0a28'|'\u0a2a'..'\u0a30'|'\u0a32'..'\u0a33'|'\u0a35'..'\u0a36'|'\u0a38'..'\u0a39'|'\u0a59'..'\u0a5c'|'\u0a5e'|'\u0a72'..'\u0a74'|'\u0a85'..'\u0a8d'|'\u0a8f'..'\u0a91'|'\u0a93'..'\u0aa8'|'\u0aaa'..'\u0ab0'|'\u0ab2'..'\u0ab3'|'\u0ab5'..'\u0ab9'|'\u0abd'|'\u0ad0'|'\u0ae0'..'\u0ae1'|'\u0af1'|'\u0b05'..'\u0b0c'|'\u0b0f'..'\u0b10'|'\u0b13'..'\u0b28'|'\u0b2a'..'\u0b30'|'\u0b32'..'\u0b33'|'\u0b35'..'\u0b39'|'\u0b3d'|'\u0b5c'..'\u0b5d'|'\u0b5f'..'\u0b61'|'\u0b71'|'\u0b83'|'\u0b85'..'\u0b8a'|'\u0b8e'..'\u0b90'|'\u0b92'..'\u0b95'|'\u0b99'..'\u0b9a'|'\u0b9c'|'\u0b9e'..'\u0b9f'|'\u0ba3'..'\u0ba4'|'\u0ba8'..'\u0baa'|'\u0bae'..'\u0bb5'|'\u0bb7'..'\u0bb9'|'\u0bf9'|'\u0c05'..'\u0c0c'|'\u0c0e'..'\u0c10'|'\u0c12'..'\u0c28'|'\u0c2a'..'\u0c33'|'\u0c35'..'\u0c39'|'\u0c60'..'\u0c61'|'\u0c85'..'\u0c8c'|'\u0c8e'..'\u0c90'|'\u0c92'..'\u0ca8'|'\u0caa'..'\u0cb3'|'\u0cb5'..'\u0cb9'|'\u0cbd'|'\u0cde'|'\u0ce0'..'\u0ce1'|'\u0d05'..'\u0d0c'|'\u0d0e'..'\u0d10'|'\u0d12'..'\u0d28'|'\u0d2a'..'\u0d39'|'\u0d60'..'\u0d61'|'\u0d85'..'\u0d96'|'\u0d9a'..'\u0db1'|'\u0db3'..'\u0dbb'|'\u0dbd'|'\u0dc0'..'\u0dc6'|'\u0e01'..'\u0e30'|'\u0e32'..'\u0e33'|'\u0e3f'..'\u0e46'|'\u0e81'..'\u0e82'|'\u0e84'|'\u0e87'..'\u0e88'|'\u0e8a'|'\u0e8d'|'\u0e94'..'\u0e97'|'\u0e99'..'\u0e9f'|'\u0ea1'..'\u0ea3'|'\u0ea5'|'\u0ea7'|'\u0eaa'..'\u0eab'|'\u0ead'..'\u0eb0'|'\u0eb2'..'\u0eb3'|'\u0ebd'|'\u0ec0'..'\u0ec4'|'\u0ec6'|'\u0edc'..'\u0edd'|'\u0f00'|'\u0f40'..'\u0f47'|'\u0f49'..'\u0f6a'|'\u0f88'..'\u0f8b'|'\u1000'..'\u1021'|'\u1023'..'\u1027'|'\u1029'..'\u102a'|'\u1050'..'\u1055'|'\u10a0'..'\u10c5'|'\u10d0'..'\u10f8'|'\u1100'..'\u1159'|'\u115f'..'\u11a2'|'\u11a8'..'\u11f9'|'\u1200'..'\u1206'|'\u1208'..'\u1246'|'\u1248'|'\u124a'..'\u124d'|'\u1250'..'\u1256'|'\u1258'|'\u125a'..'\u125d'|'\u1260'..'\u1286'|'\u1288'|'\u128a'..'\u128d'|'\u1290'..'\u12ae'|'\u12b0'|'\u12b2'..'\u12b5'|'\u12b8'..'\u12be'|'\u12c0'|'\u12c2'..'\u12c5'|'\u12c8'..'\u12ce'|'\u12d0'..'\u12d6'|'\u12d8'..'\u12ee'|'\u12f0'..'\u130e'|'\u1310'|'\u1312'..'\u1315'|'\u1318'..'\u131e'|'\u1320'..'\u1346'|'\u1348'..'\u135a'|'\u13a0'..'\u13f4'|'\u1401'..'\u166c'|'\u166f'..'\u1676'|'\u1681'..'\u169a'|'\u16a0'..'\u16ea'|'\u16ee'..'\u16f0'|'\u1700'..'\u170c'|'\u170e'..'\u1711'|'\u1720'..'\u1731'|'\u1740'..'\u1751'|'\u1760'..'\u176c'|'\u176e'..'\u1770'|'\u1780'..'\u17b3'|'\u17d7'|'\u17db'..'\u17dc'|'\u1820'..'\u1877'|'\u1880'..'\u18a8'|'\u1900'..'\u191c'|'\u1950'..'\u196d'|'\u1970'..'\u1974'|'\u1d00'..'\u1d6b'|'\u1e00'..'\u1e9b'|'\u1ea0'..'\u1ef9'|'\u1f00'..'\u1f15'|'\u1f18'..'\u1f1d'|'\u1f20'..'\u1f45'|'\u1f48'..'\u1f4d'|'\u1f50'..'\u1f57'|'\u1f59'|'\u1f5b'|'\u1f5d'|'\u1f5f'..'\u1f7d'|'\u1f80'..'\u1fb4'|'\u1fb6'..'\u1fbc'|'\u1fbe'|'\u1fc2'..'\u1fc4'|'\u1fc6'..'\u1fcc'|'\u1fd0'..'\u1fd3'|'\u1fd6'..'\u1fdb'|'\u1fe0'..'\u1fec'|'\u1ff2'..'\u1ff4'|'\u1ff6'..'\u1ffc'|'\u203f'..'\u2040'|'\u2054'|'\u2071'|'\u207f'|'\u20a0'..'\u20b1'|'\u2102'|'\u2107'|'\u210a'..'\u2113'|'\u2115'|'\u2119'..'\u211d'|'\u2124'|'\u2126'|'\u2128'|'\u212a'..'\u212d'|'\u212f'..'\u2131'|'\u2133'..'\u2139'|'\u213d'..'\u213f'|'\u2145'..'\u2149'|'\u2160'..'\u2183'|'\u3005'..'\u3007'|'\u3021'..'\u3029'|'\u3031'..'\u3035'|'\u3038'..'\u303c'|'\u3041'..'\u3096'|'\u309d'..'\u309f'|'\u30a1'..'\u30ff'|'\u3105'..'\u312c'|'\u3131'..'\u318e'|'\u31a0'..'\u31b7'|'\u31f0'..'\u31ff'|'\u3400'..'\u4db5'|'\u4e00'..'\u9fa5'|'\ua000'..'\ua48c'|'\uac00'..'\ud7a3'|'\ud800'..'\udbff'|'\uf900'..'\ufa2d'|'\ufa30'..'\ufa6a'|'\ufb00'..'\ufb06'|'\ufb13'..'\ufb17'|'\ufb1d'|'\ufb1f'..'\ufb28'|'\ufb2a'..'\ufb36'|'\ufb38'..'\ufb3c'|'\ufb3e'|'\ufb40'..'\ufb41'|'\ufb43'..'\ufb44'|'\ufb46'..'\ufbb1'|'\ufbd3'..'\ufd3d'|'\ufd50'..'\ufd8f'|'\ufd92'..'\ufdc7'|'\ufdf0'..'\ufdfc'|'\ufe33'..'\ufe34'|'\ufe4d'..'\ufe4f'|'\ufe69'|'\ufe70'..'\ufe74'|'\ufe76'..'\ufefc'|'\uff04'|'\uff21'..'\uff3a'|'\uff3f'|'\uff41'..'\uff5a'|'\uff65'..'\uffbe'|'\uffc2'..'\uffc7'|'\uffca'..'\uffcf'|'\uffd2'..'\uffd7'|'\uffda'..'\uffdc'|'\uffe0'..'\uffe1'|'\uffe5'..'\uffe6')('\u0000'..'\u0008'|'\u000e'..'\u001b'|'\u0024'|'\u0030'..'\u0039'|'\u0041'..'\u005a'|'\u005f'|'\u0061'..'\u007a'|'\u007f'..'\u009f'|'\u00a2'..'\u00a5'|'\u00aa'|'\u00ad'|'\u00b5'|'\u00ba'|'\u00c0'..'\u00d6'|'\u00d8'..'\u00f6'|'\u00f8'..'\u0236'|'\u0250'..'\u02c1'|'\u02c6'..'\u02d1'|'\u02e0'..'\u02e4'|'\u02ee'|'\u0300'..'\u0357'|'\u035d'..'\u036f'|'\u037a'|'\u0386'|'\u0388'..'\u038a'|'\u038c'|'\u038e'..'\u03a1'|'\u03a3'..'\u03ce'|'\u03d0'..'\u03f5'|'\u03f7'..'\u03fb'|'\u0400'..'\u0481'|'\u0483'..'\u0486'|'\u048a'..'\u04ce'|'\u04d0'..'\u04f5'|'\u04f8'..'\u04f9'|'\u0500'..'\u050f'|'\u0531'..'\u0556'|'\u0559'|'\u0561'..'\u0587'|'\u0591'..'\u05a1'|'\u05a3'..'\u05b9'|'\u05bb'..'\u05bd'|'\u05bf'|'\u05c1'..'\u05c2'|'\u05c4'|'\u05d0'..'\u05ea'|'\u05f0'..'\u05f2'|'\u0600'..'\u0603'|'\u0610'..'\u0615'|'\u0621'..'\u063a'|'\u0640'..'\u0658'|'\u0660'..'\u0669'|'\u066e'..'\u06d3'|'\u06d5'..'\u06dd'|'\u06df'..'\u06e8'|'\u06ea'..'\u06fc'|'\u06ff'|'\u070f'..'\u074a'|'\u074d'..'\u074f'|'\u0780'..'\u07b1'|'\u0901'..'\u0939'|'\u093c'..'\u094d'|'\u0950'..'\u0954'|'\u0958'..'\u0963'|'\u0966'..'\u096f'|'\u0981'..'\u0983'|'\u0985'..'\u098c'|'\u098f'..'\u0990'|'\u0993'..'\u09a8'|'\u09aa'..'\u09b0'|'\u09b2'|'\u09b6'..'\u09b9'|'\u09bc'..'\u09c4'|'\u09c7'..'\u09c8'|'\u09cb'..'\u09cd'|'\u09d7'|'\u09dc'..'\u09dd'|'\u09df'..'\u09e3'|'\u09e6'..'\u09f3'|'\u0a01'..'\u0a03'|'\u0a05'..'\u0a0a'|'\u0a0f'..'\u0a10'|'\u0a13'..'\u0a28'|'\u0a2a'..'\u0a30'|'\u0a32'..'\u0a33'|'\u0a35'..'\u0a36'|'\u0a38'..'\u0a39'|'\u0a3c'|'\u0a3e'..'\u0a42'|'\u0a47'..'\u0a48'|'\u0a4b'..'\u0a4d'|'\u0a59'..'\u0a5c'|'\u0a5e'|'\u0a66'..'\u0a74'|'\u0a81'..'\u0a83'|'\u0a85'..'\u0a8d'|'\u0a8f'..'\u0a91'|'\u0a93'..'\u0aa8'|'\u0aaa'..'\u0ab0'|'\u0ab2'..'\u0ab3'|'\u0ab5'..'\u0ab9'|'\u0abc'..'\u0ac5'|'\u0ac7'..'\u0ac9'|'\u0acb'..'\u0acd'|'\u0ad0'|'\u0ae0'..'\u0ae3'|'\u0ae6'..'\u0aef'|'\u0af1'|'\u0b01'..'\u0b03'|'\u0b05'..'\u0b0c'|'\u0b0f'..'\u0b10'|'\u0b13'..'\u0b28'|'\u0b2a'..'\u0b30'|'\u0b32'..'\u0b33'|'\u0b35'..'\u0b39'|'\u0b3c'..'\u0b43'|'\u0b47'..'\u0b48'|'\u0b4b'..'\u0b4d'|'\u0b56'..'\u0b57'|'\u0b5c'..'\u0b5d'|'\u0b5f'..'\u0b61'|'\u0b66'..'\u0b6f'|'\u0b71'|'\u0b82'..'\u0b83'|'\u0b85'..'\u0b8a'|'\u0b8e'..'\u0b90'|'\u0b92'..'\u0b95'|'\u0b99'..'\u0b9a'|'\u0b9c'|'\u0b9e'..'\u0b9f'|'\u0ba3'..'\u0ba4'|'\u0ba8'..'\u0baa'|'\u0bae'..'\u0bb5'|'\u0bb7'..'\u0bb9'|'\u0bbe'..'\u0bc2'|'\u0bc6'..'\u0bc8'|'\u0bca'..'\u0bcd'|'\u0bd7'|'\u0be7'..'\u0bef'|'\u0bf9'|'\u0c01'..'\u0c03'|'\u0c05'..'\u0c0c'|'\u0c0e'..'\u0c10'|'\u0c12'..'\u0c28'|'\u0c2a'..'\u0c33'|'\u0c35'..'\u0c39'|'\u0c3e'..'\u0c44'|'\u0c46'..'\u0c48'|'\u0c4a'..'\u0c4d'|'\u0c55'..'\u0c56'|'\u0c60'..'\u0c61'|'\u0c66'..'\u0c6f'|'\u0c82'..'\u0c83'|'\u0c85'..'\u0c8c'|'\u0c8e'..'\u0c90'|'\u0c92'..'\u0ca8'|'\u0caa'..'\u0cb3'|'\u0cb5'..'\u0cb9'|'\u0cbc'..'\u0cc4'|'\u0cc6'..'\u0cc8'|'\u0cca'..'\u0ccd'|'\u0cd5'..'\u0cd6'|'\u0cde'|'\u0ce0'..'\u0ce1'|'\u0ce6'..'\u0cef'|'\u0d02'..'\u0d03'|'\u0d05'..'\u0d0c'|'\u0d0e'..'\u0d10'|'\u0d12'..'\u0d28'|'\u0d2a'..'\u0d39'|'\u0d3e'..'\u0d43'|'\u0d46'..'\u0d48'|'\u0d4a'..'\u0d4d'|'\u0d57'|'\u0d60'..'\u0d61'|'\u0d66'..'\u0d6f'|'\u0d82'..'\u0d83'|'\u0d85'..'\u0d96'|'\u0d9a'..'\u0db1'|'\u0db3'..'\u0dbb'|'\u0dbd'|'\u0dc0'..'\u0dc6'|'\u0dca'|'\u0dcf'..'\u0dd4'|'\u0dd6'|'\u0dd8'..'\u0ddf'|'\u0df2'..'\u0df3'|'\u0e01'..'\u0e3a'|'\u0e3f'..'\u0e4e'|'\u0e50'..'\u0e59'|'\u0e81'..'\u0e82'|'\u0e84'|'\u0e87'..'\u0e88'|'\u0e8a'|'\u0e8d'|'\u0e94'..'\u0e97'|'\u0e99'..'\u0e9f'|'\u0ea1'..'\u0ea3'|'\u0ea5'|'\u0ea7'|'\u0eaa'..'\u0eab'|'\u0ead'..'\u0eb9'|'\u0ebb'..'\u0ebd'|'\u0ec0'..'\u0ec4'|'\u0ec6'|'\u0ec8'..'\u0ecd'|'\u0ed0'..'\u0ed9'|'\u0edc'..'\u0edd'|'\u0f00'|'\u0f18'..'\u0f19'|'\u0f20'..'\u0f29'|'\u0f35'|'\u0f37'|'\u0f39'|'\u0f3e'..'\u0f47'|'\u0f49'..'\u0f6a'|'\u0f71'..'\u0f84'|'\u0f86'..'\u0f8b'|'\u0f90'..'\u0f97'|'\u0f99'..'\u0fbc'|'\u0fc6'|'\u1000'..'\u1021'|'\u1023'..'\u1027'|'\u1029'..'\u102a'|'\u102c'..'\u1032'|'\u1036'..'\u1039'|'\u1040'..'\u1049'|'\u1050'..'\u1059'|'\u10a0'..'\u10c5'|'\u10d0'..'\u10f8'|'\u1100'..'\u1159'|'\u115f'..'\u11a2'|'\u11a8'..'\u11f9'|'\u1200'..'\u1206'|'\u1208'..'\u1246'|'\u1248'|'\u124a'..'\u124d'|'\u1250'..'\u1256'|'\u1258'|'\u125a'..'\u125d'|'\u1260'..'\u1286'|'\u1288'|'\u128a'..'\u128d'|'\u1290'..'\u12ae'|'\u12b0'|'\u12b2'..'\u12b5'|'\u12b8'..'\u12be'|'\u12c0'|'\u12c2'..'\u12c5'|'\u12c8'..'\u12ce'|'\u12d0'..'\u12d6'|'\u12d8'..'\u12ee'|'\u12f0'..'\u130e'|'\u1310'|'\u1312'..'\u1315'|'\u1318'..'\u131e'|'\u1320'..'\u1346'|'\u1348'..'\u135a'|'\u1369'..'\u1371'|'\u13a0'..'\u13f4'|'\u1401'..'\u166c'|'\u166f'..'\u1676'|'\u1681'..'\u169a'|'\u16a0'..'\u16ea'|'\u16ee'..'\u16f0'|'\u1700'..'\u170c'|'\u170e'..'\u1714'|'\u1720'..'\u1734'|'\u1740'..'\u1753'|'\u1760'..'\u176c'|'\u176e'..'\u1770'|'\u1772'..'\u1773'|'\u1780'..'\u17d3'|'\u17d7'|'\u17db'..'\u17dd'|'\u17e0'..'\u17e9'|'\u180b'..'\u180d'|'\u1810'..'\u1819'|'\u1820'..'\u1877'|'\u1880'..'\u18a9'|'\u1900'..'\u191c'|'\u1920'..'\u192b'|'\u1930'..'\u193b'|'\u1946'..'\u196d'|'\u1970'..'\u1974'|'\u1d00'..'\u1d6b'|'\u1e00'..'\u1e9b'|'\u1ea0'..'\u1ef9'|'\u1f00'..'\u1f15'|'\u1f18'..'\u1f1d'|'\u1f20'..'\u1f45'|'\u1f48'..'\u1f4d'|'\u1f50'..'\u1f57'|'\u1f59'|'\u1f5b'|'\u1f5d'|'\u1f5f'..'\u1f7d'|'\u1f80'..'\u1fb4'|'\u1fb6'..'\u1fbc'|'\u1fbe'|'\u1fc2'..'\u1fc4'|'\u1fc6'..'\u1fcc'|'\u1fd0'..'\u1fd3'|'\u1fd6'..'\u1fdb'|'\u1fe0'..'\u1fec'|'\u1ff2'..'\u1ff4'|'\u1ff6'..'\u1ffc'|'\u200c'..'\u200f'|'\u202a'..'\u202e'|'\u203f'..'\u2040'|'\u2054'|'\u2060'..'\u2063'|'\u206a'..'\u206f'|'\u2071'|'\u207f'|'\u20a0'..'\u20b1'|'\u20d0'..'\u20dc'|'\u20e1'|'\u20e5'..'\u20ea'|'\u2102'|'\u2107'|'\u210a'..'\u2113'|'\u2115'|'\u2119'..'\u211d'|'\u2124'|'\u2126'|'\u2128'|'\u212a'..'\u212d'|'\u212f'..'\u2131'|'\u2133'..'\u2139'|'\u213d'..'\u213f'|'\u2145'..'\u2149'|'\u2160'..'\u2183'|'\u3005'..'\u3007'|'\u3021'..'\u302f'|'\u3031'..'\u3035'|'\u3038'..'\u303c'|'\u3041'..'\u3096'|'\u3099'..'\u309a'|'\u309d'..'\u309f'|'\u30a1'..'\u30ff'|'\u3105'..'\u312c'|'\u3131'..'\u318e'|'\u31a0'..'\u31b7'|'\u31f0'..'\u31ff'|'\u3400'..'\u4db5'|'\u4e00'..'\u9fa5'|'\ua000'..'\ua48c'|'\uac00'..'\ud7a3'|'\ud800'..'\udfff'|'\uf900'..'\ufa2d'|'\ufa30'..'\ufa6a'|'\ufb00'..'\ufb06'|'\ufb13'..'\ufb17'|'\ufb1d'..'\ufb28'|'\ufb2a'..'\ufb36'|'\ufb38'..'\ufb3c'|'\ufb3e'|'\ufb40'..'\ufb41'|'\ufb43'..'\ufb44'|'\ufb46'..'\ufbb1'|'\ufbd3'..'\ufd3d'|'\ufd50'..'\ufd8f'|'\ufd92'..'\ufdc7'|'\ufdf0'..'\ufdfc'|'\ufe00'..'\ufe0f'|'\ufe20'..'\ufe23'|'\ufe33'..'\ufe34'|'\ufe4d'..'\ufe4f'|'\ufe69'|'\ufe70'..'\ufe74'|'\ufe76'..'\ufefc'|'\ufeff'|'\uff04'|'\uff10'..'\uff19'|'\uff21'..'\uff3a'|'\uff3f'|'\uff41'..'\uff5a'|'\uff65'..'\uffbe'|'\uffc2'..'\uffc7'|'\uffca'..'\uffcf'|'\uffd2'..'\uffd7'|'\uffda'..'\uffdc'|'\uffe0'..'\uffe1'|'\uffe5'..'\uffe6'|'\ufff9'..'\ufffb')*$;
	
	DEFINE HEX_FLOAT_LITERAL $'0'('x'|'X')('0'..'9'|'a'..'f'|'A'..'F')*  (('.' ('0'..'9'|'a'..'f'|'A'..'F')+ (('p'|'P') ('+'|'-')? ('0'..'9')+ ('f'|'F') )?) | ((('p'|'P') ('+'|'-')? ('0'..'9')+ ('f'|'F') )))$;
	@SuppressWarnings(tokenOverlapping)
	DEFINE HEX_DOUBLE_LITERAL $'0'('x'|'X')('0'..'9'|'a'..'f'|'A'..'F')* (('.' ('0'..'9'|'a'..'f'|'A'..'F')+ (('p'|'P') ('+'|'-')? ('0'..'9')+ ('d'|'D')?)?) | ((('p'|'P') ('+'|'-')? ('0'..'9')+ ('d'|'D')?)))$;
	DEFINE HEX_LONG_LITERAL $'0'('x'|'X')('0'..'9'|'a'..'f'|'A'..'F')+ ('l'|'L')$;
	DEFINE HEX_INTEGER_LITERAL $'0'('x'|'X')('0'..'9'|'a'..'f'|'A'..'F')+$;
	
	DEFINE DECIMAL_FLOAT_LITERAL $('0'..'9')+ '.' ('0'..'9')* (('e'|'E'|'p'|'P') ('+'|'-')? ('0'..'9')+)? ('f'|'F') | ('.' ('0'..'9')+ (('e'|'E'|'p'|'P') ('+'|'-')? ('0'..'9')+)?) ('f'|'F') | (('0'..'9')+ (('e'|'E'|'p'|'P') ('+'|'-')? ('0'..'9')+) ('f'|'F') | ('0'..'9')+ ('f'|'F'))$;
	DEFINE DECIMAL_DOUBLE_LITERAL $('0'..'9')+ '.' ('0'..'9')* (('e'|'E'|'p'|'P') ('+'|'-')? ('0'..'9')+)? ('d'|'D')? | ('.' ('0'..'9')+ (('e'|'E'|'p'|'P') ('+'|'-')? ('0'..'9')+)?) ('d'|'D')? | (('0'..'9')+ (('e'|'E'|'p'|'P') ('+'|'-')? ('0'..'9')+) ('d'|'D')? | ('0'..'9')+ ('d'|'D'))$;
	DEFINE DECIMAL_LONG_LITERAL $('0'|'1'..'9''0'..'9'*)('l'|'L')$;
	DEFINE DECIMAL_INTEGER_LITERAL $('0'|'1'..'9''0'..'9'*)$;
	
	DEFINE OCTAL_LONG_LITERAL $'0'('0'..'7')+('l'|'L')$;
	DEFINE OCTAL_INTEGER_LITERAL $'0'('0'..'7')+$;

	@SuppressWarnings(unusedToken)
	DEFINE WHITESPACE $(' '|'\t'|'\f'|'\r'|'\n')+$;
}

TOKENSTYLES {
	"ML_COMMENT" COLOR #008000, ITALIC;
	"SL_COMMENT" COLOR #000080, ITALIC;
	"STRING_LITERAL" COLOR #2A00FF;
	"IDENTIFIER" COLOR #000000;
	
	"abstract",	"assert",
	"boolean", "byte",
	"case", "catch", "class", "continue", "char",
	"default", "double", "do",
	"enum",	"extends", "else",
	"for", "float", "final",
	"if", "import", "implements", "int", "interface", "instanceof",
	"package", "private", "protected", "public",
	"super", "switch", "strictfp", "static", "synchronized",
	"this", "try", "throws", "transient",
	"new", "null",
	"while",
	"void",
	"long",
	"return" COLOR #7F0055, BOLD;
}

RULES {

@SuppressWarnings(optionalKeyword)
@SuppressWarnings(featureWithoutSyntax) //name is set by JavaSourceOrClassFileResource.load()
containers.EmptyModel 
   ::= (imports  ";" !0 )* (";")*
   ;

@SuppressWarnings(optionalKeyword)
@SuppressWarnings(featureWithoutSyntax) //subpackages is filled by JavaSourceOrClassFileResource.load()
containers.Package
   ::=  annotations* "package" (namespaces[]  "."  )* name[]  ";" 
        (";")? //TODO this is required to let T7312 of JacksTest pass... not sure if this is correct or if it should be * instead of ?
        !0 !0
        (imports !0 )* (";")*
   ;

@SuppressWarnings(optionalKeyword)
@SuppressWarnings(featureWithoutSyntax) //name is set by JavaSourceOrClassFileResource or ClassFileModelLoader 
containers.CompilationUnit 
   ::=	("package" namespaces[] ( "."  namespaces[])*  ";" )?
        !0 !0
        (imports !0 )*
        (";" !0)*
        !0
        (classifiers (";")* !0 !0)+
        ("\u001a")?
	;
	
imports.ClassifierImport
	::= "import" (namespaces[]  "."  )* classifier[]  ";";

@SuppressWarnings(minOccurenceMismatch) //the minimal occurence of namespaces[] is in other cases 0
imports.PackageImport
	::= "import" (namespaces[]  "."  )+  "*"  ";";

imports.StaticMemberImport
	::= "import" static (namespaces[]  "."  )* staticMembers[]  ";";

@SuppressWarnings(minOccurenceMismatch) //the minimal occurence of namespaces[] is in other cases 0
imports.StaticClassifierImport
	::= "import" static (namespaces[]  "."  )+  "*"  ";";

@SuppressWarnings(featureWithoutSyntax) //defaultExtends is filled by post processor
classifiers.Class
	::=	annotationsAndModifiers*
	    "class" name[] ("<" typeParameters ("," typeParameters)* ">")?
        ("extends" extends)?
        ("implements" (implements ("," implements)*))?
        #1 "{" 
        	(!1 members)* !0
        "}"
	;

@SuppressWarnings(featureWithoutSyntax) //defaultMembers is filled by post processor
classifiers.AnonymousClass
	::= #1 "{" 
			(!1 members)* !0
		"}"
	;

@SuppressWarnings(featureWithoutSyntax) //defaultMembers is set during reference resolving
classifiers.Interface
	::=	annotationsAndModifiers*
	    "interface" name[] ("<"  typeParameters ( "," typeParameters)*  ">")?
		("extends" (extends ("," extends)*))? 
	    #1 "{"
        	(!1 members)* !0
		"}"
	;

@SuppressWarnings(featureWithoutSyntax) //defaultMembers is set during reference resolving
@SuppressWarnings(optionalKeyword)
classifiers.Enumeration
    ::= annotationsAndModifiers*
        "enum" name[] 
    	("implements" (implements ("," implements)*))? 
    	#1 "{" 
    		(!1 constants ("," !1 constants)*)? (",")? 
    		(";" (!1 members)* !0)?
    	"}"
    ;

@SuppressWarnings(featureWithoutSyntax) //defaultMembers is set during reference resolving
classifiers.Annotation
	::=	annotationsAndModifiers*
	    "@" "interface" name[]
	    #1 "{" 
	    	(!1 members)* !0
	    "}"
	;

@SuppressWarnings(featureWithoutSyntax) //typeArguments
annotations.AnnotationInstance
	::=	"@" (namespaces[]  "."  )* annotation[]
		(parameter)? 
	;

annotations.SingleAnnotationParameter
	::= "(" value:arrays.ArrayInitializer,expressions.ConditionalExpression ")"
	;

annotations.AnnotationParameterList
	::= "(" (settings ("," settings)*)? ")"
	;
	
annotations.AnnotationAttributeSetting
	::= attribute[] #1 "=" #1 
	    value:arrays.ArrayInitializer,expressions.ConditionalExpression
	;

generics.TypeParameter
	::= name[] ("extends" extendTypes ("&" extendTypes)*)?
	;

@SuppressWarnings(optionalKeyword)
members.EnumConstant
    ::= annotations* name[] (#1 "(" (arguments:expressions.AssignmentExpression ("," arguments:expressions.AssignmentExpression)*)? ")" )?
    	(anonymousClass)?
    ;

@SuppressWarnings(featureWithoutSyntax) //name is set by JavaModelCompletion.setBlockName()
statements.Block
	::=	modifiers* #1 "{" (!1 statements)* !0 "}"
	;

members.Constructor
	::=	annotationsAndModifiers* ("<" typeParameters ("," typeParameters)* ">")? name[]
	"(" (parameters ("," parameters)* )? ")" 
	("throws" exceptions ("," exceptions)*)? #1 "{" (!2 statements)* !1 "}"
	;

members.InterfaceMethod
	::=	annotationsAndModifiers* ("<"  typeParameters ( "," typeParameters)*  ">")? (typeReference arrayDimensionsBefore*) name[]  
	"("  (parameters ("," parameters)* )? ")" arrayDimensionsAfter*
	("throws" exceptions ("," exceptions)*)? ";"
	;

members.ClassMethod
	::=	annotationsAndModifiers* ("<" typeParameters ("," typeParameters)* ">")? (typeReference arrayDimensionsBefore*) name[]  
	"(" (parameters ("," parameters)* )? ")" arrayDimensionsAfter*
	("throws" exceptions ("," exceptions)*)? #1 "{" (!2 statements)* !1 "}"
	;
	
annotations.AnnotationAttribute
	::=	annotationsAndModifiers* ("<" typeParameters ("," typeParameters)* ">")? (typeReference arrayDimensionsBefore*) name[]  
	"(" (parameters ("," parameters)* )? ")" arrayDimensionsAfter*
	("throws" exceptions ("," exceptions)*)? "default" defaultValue:expressions.AssignmentExpression ";"
	;

parameters.OrdinaryParameter
	::= annotationsAndModifiers* typeReference arrayDimensionsBefore* ("<" typeArguments ("," typeArguments)* ">")? name[] arrayDimensionsAfter*
	;

@SuppressWarnings(featureWithoutSyntax)
parameters.VariableLengthParameter
	::= annotationsAndModifiers* typeReference arrayDimensionsBefore* ("<" typeArguments ("," typeArguments)* ">")? "..." name[] 
	;

variables.LocalVariable
	::= annotationsAndModifiers* typeReference arrayDimensionsBefore* ("<" typeArguments ("," typeArguments)* ">")? name[] arrayDimensionsAfter* (#1 "=" #1 initialValue:expressions.AssignmentExpression)? ("," additionalLocalVariables)*
	;

statements.LocalVariableStatement
	::= variable ";" ;

@SuppressWarnings(featureWithoutSyntax)
variables.AdditionalLocalVariable
	::= name[] arrayDimensionsAfter* (#1 "=" #1 initialValue:expressions.AssignmentExpression)?
	;

members.Field
	::= annotationsAndModifiers* typeReference arrayDimensionsBefore* ("<" typeArguments ("," typeArguments)* ">")? name[] arrayDimensionsAfter* (#1 "=" #1 initialValue:expressions.AssignmentExpression)? ("," additionalFields)* ";"
	;

@SuppressWarnings(featureWithoutSyntax)
members.AdditionalField
	::= name[] arrayDimensionsAfter* (#1 "=" #1 initialValue:expressions.AssignmentExpression)?
	;

@SuppressWarnings(featureWithoutSyntax) //name is set by JavaModelCompletion.setEmptyMemberName()
members.EmptyMember
	::= ";"
	;

// INSTANTIATIONS
@SuppressWarnings(featureWithoutSyntax) //arraySelectors
instantiations.NewConstructorCall 
	::= "new" 
		// these are the arguments for the constructor type parameters
		("<" typeArguments ("," typeArguments)* ">")?
		typeReference 
		// these are the arguments for the class type parameters
		("<" callTypeArguments ("," callTypeArguments)* ">")?
		"(" (arguments:expressions.AssignmentExpression ("," arguments:expressions.AssignmentExpression)* )? ")"
		anonymousClass?
		("." next)? 
     ;

@SuppressWarnings(featureWithoutSyntax) //arraySelectors 
instantiations.ExplicitConstructorCall 
	::= ("<" typeArguments ("," typeArguments)* ">")?
		callTarget "(" (arguments:expressions.AssignmentExpression ("," arguments:expressions.AssignmentExpression)* )? ")"
		("." next)? 
     ;

@SuppressWarnings(featureWithoutSyntax) //arrayDimensionsAfter
@SuppressWarnings(minOccurenceMismatch) //arrayDimensionsBefore required here
arrays.ArrayInstantiationByValuesTyped
	::= "new" typeReference arrayDimensionsBefore+ arrayInitializer
		arraySelectors* ("." next)? 
	;

@SuppressWarnings(featureWithoutSyntax) //typeArguments not applicable
arrays.ArrayInstantiationByValuesUntyped
	::= arrayInitializer
		arraySelectors* ("." next)? 
	;

@SuppressWarnings(featureWithoutSyntax)
arrays.ArrayInstantiationBySize 
	::= "new" typeReference 
		("[" sizes:expressions.AssignmentExpression "]")+
		arrayDimensionsBefore*
		("." next)?
	;

@SuppressWarnings(optionalKeyword)
arrays.ArrayInitializer
    ::= #1 "{" (initialValues:expressions.AssignmentExpression,arrays.ArrayInitializer
            ("," initialValues:expressions.AssignmentExpression,arrays.ArrayInitializer )*)? (",")? "}"    
    ;

arrays.ArraySelector
	::= "[" position:expressions.AssignmentExpression? "]"
	;
	
types.NamespaceClassifierReference
	::= (namespaces[]  ".")* (classifierReferences ".")* classifierReferences
	;
	
types.ClassifierReference
	::= target[] 
		("<" typeArguments ("," typeArguments)* ">")?
	;
	

references.MethodCall
	::= ("<" callTypeArguments ("," callTypeArguments)* ">")?
	    target[]
		("<" typeArguments ("," typeArguments)* ">")?
		"(" (arguments:expressions.AssignmentExpression ("," arguments:expressions.AssignmentExpression)* )? ")"
		arraySelectors* ("." next)? 
	;
	
references.IdentifierReference
	::= target[]
		("<" typeArguments ("," typeArguments)* ">")?
		arraySelectors* ("." next)? 
	;

@SuppressWarnings(featureWithoutSyntax) //typeArguments	
references.ReflectiveClassReference ::= "class"
		("." next)? 
	;

@SuppressWarnings(featureWithoutSyntax) //typeArguments
references.SelfReference ::= self
		("." next)? 
	;

@SuppressWarnings(featureWithoutSyntax) //typeArguments
references.PrimitiveTypeReference ::= primitiveType
		arraySelectors* ("." next)? 
	;
			
literals.This ::= "this";
literals.Super ::= "super";

@SuppressWarnings(featureWithoutSyntax) //typeArguments
references.StringReference 
	::= value[STRING_LITERAL]
		("." next)? 
	;

@SuppressWarnings(featureWithoutSyntax)
generics.QualifiedTypeArgument
	::= typeReference arrayDimensionsBefore*
	;

@SuppressWarnings(featureWithoutSyntax)
generics.UnknownTypeArgument
	::= "?"
	;

@SuppressWarnings(featureWithoutSyntax)
generics.ExtendsTypeArgument
	::= "?" "extends" extendTypes ("&" extendTypes)* arrayDimensionsBefore*
	;

@SuppressWarnings(featureWithoutSyntax)
generics.SuperTypeArgument
	::= "?" "super" superType arrayDimensionsBefore*
	;

@SuppressWarnings(minOccurenceMismatch) //condition can be empty in other cases
statements.Assert
	::= "assert" condition:expressions.AssignmentExpression (":" errorMessage:expressions.AssignmentExpression)? ";" ;

@SuppressWarnings(minOccurenceMismatch) //condition can be empty in other cases
statements.Condition 
	::= "if" #1 "(" condition:expressions.AssignmentExpression ")" statement ("else" elseStatement)? ;
	
statements.ForLoop
	::= "for" #1 "(" init? ";" condition:expressions.AssignmentExpression? ";" (updates:expressions.AssignmentExpression ("," updates:expressions.AssignmentExpression)* )? ")" statement;

statements.ForEachLoop
	::= "for" #1 "(" next ":" collection:expressions.AssignmentExpression ")" statement;
	
statements.WhileLoop
	::= "while" #1 "(" condition:expressions.AssignmentExpression ")" statement;
	
statements.DoWhileLoop	
	::= "do" statement "while" #1 "(" condition:expressions.AssignmentExpression ")" ";" ;
	
statements.EmptyStatement	
	::= ";" ;
	
statements.SynchronizedBlock
	::= "synchronized" #1 "(" lockProvider:expressions.AssignmentExpression ")" #1 "{" (!1 statements)* !0 "}" ;
	
statements.TryBlock
	::= "try" #1 "{" (!1 statements)* !0 "}"
		catcheBlocks* 
		("finally" finallyBlock)?;

statements.CatchBlock
	::=	"catch" #1 "(" parameter ")" #1 "{" (!1 statements)* !0 "}"
	;

statements.Switch
	::= "switch" #1 "(" variable:expressions.AssignmentExpression ")" #1 "{" (cases*) "}";

@SuppressWarnings(minOccurenceMismatch) //condition can be empty in other cases
statements.NormalSwitchCase
	::= "case" condition:expressions.AssignmentExpression ":" (!1 statements)* !0 ;
	
statements.DefaultSwitchCase
	::= "default" ":" (!1 statements)* !0 ;
	
statements.Return
	::= "return" returnValue:expressions.AssignmentExpression? ";" ;
	
statements.Throw
	::= "throw" throwable:expressions.AssignmentExpression ";" ;
	
statements.Break
	::= "break" (target[])? ";" ;
	
statements.Continue
	::= "continue" (target[])? ";" ;
	
statements.JumpLabel
	::= name[] ":" statement ;

statements.ExpressionStatement 
	::= expression:expressions.AssignmentExpression ";" 
	;

@SuppressWarnings(minOccurenceMismatch) //the expression simplifier removes the cases where min occurrence does not match
expressions.ExpressionList
    ::= expressions:expressions.AssignmentExpression ("," expressions:expressions.AssignmentExpression)*
    ;

@SuppressWarnings(minOccurenceMismatch) //the expression simplifier removes the cases where min occurrence does not match
expressions.AssignmentExpression
	::= child:expressions.ConditionalExpression (#1 assignmentOperator #1 value:expressions.AssignmentExpression)?
    ;

@SuppressWarnings(minOccurenceMismatch) //the expression simplifier removes the cases where min occurrence does not match   	
expressions.ConditionalExpression
    ::= child:expressions.ConditionalOrExpression ("?" expressionIf:expressions.AssignmentExpression ":" expressionElse:expressions.ConditionalExpression)?
    ;
    
expressions.ConditionalOrExpression
    ::= children:expressions.ConditionalAndExpression ( "||" children:expressions.ConditionalAndExpression )*
    ;
    
expressions.ConditionalAndExpression
    ::= children:expressions.InclusiveOrExpression ( "&&" children:expressions.InclusiveOrExpression )*
    ;
       
expressions.InclusiveOrExpression
    ::= children:expressions.ExclusiveOrExpression ( "|" children:expressions.ExclusiveOrExpression )*
    ;

expressions.ExclusiveOrExpression
    ::= children:expressions.AndExpression ( "^" children:expressions.AndExpression )*
    ;

expressions.AndExpression
    ::= children:expressions.EqualityExpression ( "&" children:expressions.EqualityExpression )*
    ;

@SuppressWarnings(minOccurenceMismatch) //the expression simplifier removes the cases where min occurrence does not match
expressions.EqualityExpression
    ::= children:expressions.InstanceOfExpression ( #1 equalityOperators #1 children:expressions.InstanceOfExpression )*
    ;

@SuppressWarnings(featureWithoutSyntax)
@SuppressWarnings(minOccurenceMismatch) //the expression simplifier removes the cases where min occurrence does not match 
expressions.InstanceOfExpression
    ::= child:expressions.RelationExpression ("instanceof" typeReference arrayDimensionsBefore*)?
    ;

@SuppressWarnings(minOccurenceMismatch) //the expression simplifier removes the cases where min occurrence does not match   
expressions.RelationExpression
	::= children:expressions.ShiftExpression ( #1 relationOperators #1 children:expressions.ShiftExpression)*
	;

@SuppressWarnings(minOccurenceMismatch) //the expression simplifier removes the cases where min occurrence does not match	
expressions.ShiftExpression
	::= children:expressions.AdditiveExpression ( #1 shiftOperators #1 children:expressions.AdditiveExpression)*
	;

@SuppressWarnings(minOccurenceMismatch) //the expression simplifier removes the cases where min occurrence does not match
expressions.AdditiveExpression
    ::= children:expressions.MultiplicativeExpression ( #1 additiveOperators #1 children:expressions.MultiplicativeExpression )*
    ;

@SuppressWarnings(minOccurenceMismatch) //the expression simplifier removes the cases where min occurrence does not match
expressions.MultiplicativeExpression
    ::=	children:expressions.UnaryExpression ( #1 multiplicativeOperators #1 children:expressions.UnaryExpression )*
    ;

@SuppressWarnings(minOccurenceMismatch) //the expression simplifier removes the cases where min occurrence does not match 
expressions.UnaryExpression
    ::= operators* child:expressions.UnaryModificationExpression
    // TODO why does UnaryExpression have multiple operators?
    ;

@SuppressWarnings(minOccurenceMismatch) //the expression simplifier removes the cases where min occurrence does not match 
expressions.SuffixUnaryModificationExpression
	::= child (operator)?
	;

@SuppressWarnings(minOccurenceMismatch) //the expression simplifier removes the cases where min occurrence does not match 	
expressions.PrefixUnaryModificationExpression
	::= (operator)? child
	;

@SuppressWarnings(featureWithoutSyntax)
expressions.CastExpression
    ::= "(" typeReference arrayDimensionsBefore* ")" #1 child:expressions.UnaryExpression
    ;

@SuppressWarnings(featureWithoutSyntax) //typeArguments
expressions.NestedExpression ::= "(" expression:expressions.AssignmentExpression ")"  arraySelectors* ("." next)? 
    ;	

       

    
    
operators.Assignment                   ::= "=";
operators.AssignmentPlus               ::= "+=";
operators.AssignmentMinus              ::= "-=";
operators.AssignmentMultiplication     ::= "*=";
operators.AssignmentDivision           ::= "/=";
operators.AssignmentAnd                ::= "&=";
operators.AssignmentOr                 ::= "|=";
operators.AssignmentExclusiveOr        ::= "^=";
operators.AssignmentModulo             ::= "%=";
operators.AssignmentLeftShift          ::= "<" "<" "=";
operators.AssignmentRightShift         ::= ">" ">" "=";
operators.AssignmentUnsignedRightShift ::= ">" ">" ">" "=";

operators.Addition              ::= "+";
operators.Subtraction           ::= "-";

operators.Multiplication        ::= "*" ;
operators.Division              ::= "/" ;
operators.Remainder             ::= "%" ;

operators.LessThan 			    ::= "<";
operators.LessThanOrEqual		::= "<" "=";
operators.GreaterThan			::= ">";
operators.GreaterThanOrEqual	::= ">" "=";

operators.LeftShift 			::= "<" "<" ;
operators.RightShift 			::= ">" ">" ;
operators.UnsignedRightShift	::= ">" ">" ">" ;

operators.Equal		::= "==";	
operators.NotEqual	::= "!=";
operators.PlusPlus 	::= "++" ;
operators.MinusMinus 	::= "--" ;
operators.Complement 	::= "~" ;
operators.Negate 		::= "!" ;

arrays.ArrayDimension ::= ("[" "]");

literals.NullLiteral ::= "null";

modifiers.Public ::= "public";
modifiers.Abstract ::= "abstract";
modifiers.Protected ::= "protected";
modifiers.Private ::= "private";
modifiers.Final ::= "final";
modifiers.Static ::= "static";

modifiers.Native ::= "native";
modifiers.Synchronized ::= "synchronized";
modifiers.Transient ::= "transient";
modifiers.Volatile ::= "volatile";
modifiers.Strictfp ::= "strictfp";

types.Void ::= "void";
types.Boolean ::= "boolean";
types.Char ::= "char";
types.Byte ::= "byte";
types.Short ::= "short";
types.Int ::= "int";
types.Long ::= "long";
types.Float ::= "float";
types.Double ::= "double";

// do not change the order of the literals!
literals.DecimalLongLiteral 
	::= decimalValue[DECIMAL_LONG_LITERAL];

literals.DecimalFloatLiteral 
	::= decimalValue[DECIMAL_FLOAT_LITERAL];

literals.DecimalIntegerLiteral 
	::= decimalValue[DECIMAL_INTEGER_LITERAL];

literals.DecimalDoubleLiteral 
	::= decimalValue[DECIMAL_DOUBLE_LITERAL];

literals.HexLongLiteral 
	::= hexValue[HEX_LONG_LITERAL];

literals.HexFloatLiteral 
	::= hexValue[HEX_FLOAT_LITERAL];

literals.HexDoubleLiteral 
	::= hexValue[HEX_DOUBLE_LITERAL];

literals.HexIntegerLiteral 
	::= hexValue[HEX_INTEGER_LITERAL];

literals.OctalLongLiteral 
	::= octalValue[OCTAL_LONG_LITERAL];

literals.OctalIntegerLiteral 
	::= octalValue[OCTAL_INTEGER_LITERAL];

literals.CharacterLiteral 
	::= value[CHARACTER_LITERAL];

literals.BooleanLiteral 
	::= value[BOOLEAN_LITERAL];
	
}

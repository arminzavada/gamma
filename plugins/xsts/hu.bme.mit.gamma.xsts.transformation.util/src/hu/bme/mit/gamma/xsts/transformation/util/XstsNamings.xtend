package hu.bme.mit.gamma.xsts.transformation.util

class XstsNamings {
	
	static def String getTypeName(String lowlevelName) '''«lowlevelName»'''
	static def String getVariableName(String lowlevelName) '''«lowlevelName»'''
	static def String getEventName(String lowlevelName) '''«lowlevelName»'''
	
	static def String getStateEnumLiteralName(String lowlevelName) '''«lowlevelName»'''
	static def String getRegionTypeName(String lowlevelName) '''«lowlevelName.toFirstUpper»'''
	static def String getRegionVariableName(String lowlevelName) '''«lowlevelName.toFirstLower»'''
	
	// Split
	public static val String PC_VAR_NAME = "__pc"
	public static val String TRANS_VAR_NAME = "__trans"
}
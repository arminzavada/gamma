package hu.bme.mit.gamma.xsts.transformation.serializer;

import hu.bme.mit.gamma.xsts.model.XSTS
import hu.bme.mit.gamma.xsts.model.NoEnvAnnotation
import hu.bme.mit.gamma.xsts.model.SplittedAnnotation
import hu.bme.mit.gamma.xsts.model.XstsAnnotation

class AnnotationSerializer {
	// Singleton
	public static final AnnotationSerializer INSTANCE = new AnnotationSerializer
	protected new() {}
	
	protected static final String ANNOTATION_PREFIX = "//@"
	
	def String serializeAnnotations(XSTS xSts) '''
		«FOR annotation : xSts.annotations»
			«ANNOTATION_PREFIX»«annotation.serialize»
		«ENDFOR»
	''' 
	
	def dispatch String serialize(NoEnvAnnotation noenv) '''noenv'''
	
	def dispatch String serialize(SplittedAnnotation splitted) '''splitted'''
	
	def dispatch String serialize(XstsAnnotation annotation) {
		throw new IllegalArgumentException("Not known annotation: " + annotation)
	}
}

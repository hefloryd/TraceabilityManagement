package org.amalthea4public.metamodel.trace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.amalthea4public.generic.tracecreation.metamodel.trace.adapter.TraceCreationHelper;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

public class TraceMetamodelAdapter
		implements org.amalthea4public.generic.tracecreation.metamodel.trace.adapter.TraceMetamodelAdapter {


	@Override
	public Collection<EClass> getAvailableTraceTypes(Object... selection) {
		
		Collection<EClass> traceTypes = new ArrayList<>(); 
		
		if (selection.length == 2 && TraceCreationHelper.isEMFSelection(Arrays.asList(selection))) {
			traceTypes.add(TracePackage.eINSTANCE.getTrace());
		}
		
		return traceTypes;
	}

	@Override
	public EObject createTrace(EClass traceType, Optional<EObject> traceModel, Object... selection) {
		TraceModel root = (TraceModel) traceModel.orElse(TraceFactory.eINSTANCE.createTraceModel());
		
		Trace trace = (Trace) TraceFactory.eINSTANCE.create(traceType);
		
		trace.setSource((EObject) selection[0]);
		trace.setTarget((EObject) selection[1]);
		
		root.getTraces().add(trace);
		
		return root;
	}

}
 
package org.openlca.ilcd.flows.epd;

import java.util.ArrayList;
import java.util.List;

import org.openlca.ilcd.Vocab;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.Extension;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.flows.epd.matml.MaterialDoc;
import org.openlca.ilcd.util.Val;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
	"genericFlow",
	"materialDoc",
	"any"
})
public class EpdInfoExtension implements Copyable<EpdInfoExtension>, Extension {

	@XmlElement(name = "isA", namespace = Vocab.EPD_2013)
	private Ref genericFlow;

	@XmlElement(name = "MatML_Doc", namespace = Vocab.MATML)
	private MaterialDoc materialDoc;

	@XmlAnyElement(lax = true)
	private List<Object> any;

	// region getters


	public Ref getGenericFlow() {
		return genericFlow;
	}

	public MaterialDoc getMaterialDoc() {
		return materialDoc;
	}

	public List<Object> getAny() {
		return any != null ? any : List.of();
	}

	// endregion

	// region setters


	public EpdInfoExtension withGenericFlow(Ref genericFlow) {
		this.genericFlow = genericFlow;
		return this;
	}

	public EpdInfoExtension withMaterialDoc(MaterialDoc materialDoc) {
		this.materialDoc = materialDoc;
		return this;
	}

	public Ref withGenericFlow() {
		if (genericFlow == null) {
			genericFlow = new Ref();
		}
		return genericFlow;
	}

	public MaterialDoc withMaterialDoc() {
		if (materialDoc == null) {
			materialDoc = new MaterialDoc();
		}
		return materialDoc;
	}

	public EpdInfoExtension withAny(List<Object> any) {
		this.any = any;
		return this;
	}

	public List<Object> withAny() {
		if (any == null) {
			any = new ArrayList<>();
		}
		return any;
	}

	// endregion

	@Override
	public EpdInfoExtension copy() {
		var copy = new EpdInfoExtension();
		Val.copy(genericFlow, copy::withGenericFlow);
		Val.copy(materialDoc, copy::withMaterialDoc);
		Val.copyAny(any, copy::withAny);
		return copy;
	}

}

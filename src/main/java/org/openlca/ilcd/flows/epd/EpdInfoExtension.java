package org.openlca.ilcd.flows.epd;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlElement;
import org.openlca.ilcd.Vocab;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.Extension;
import org.openlca.ilcd.flows.epd.matml.MaterialDoc;
import org.openlca.ilcd.util.Val;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class EpdInfoExtension implements Copyable<EpdInfoExtension>, Extension {

	@XmlElement(name = "MatML_Doc", namespace = Vocab.MATML)
	private MaterialDoc materialDoc;

	@XmlAnyElement(lax = true)
	private List<Object> any;

	// region getters

	public MaterialDoc getMaterialDoc() {
		return materialDoc;
	}

	public List<Object> getAny() {
		return any != null ? any : List.of();
	}

	// endregion

	// region setters

	public EpdInfoExtension withMaterialDoc(MaterialDoc materialDoc) {
		this.materialDoc = materialDoc;
		return this;
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
		Val.copy(materialDoc, copy::withMaterialDoc);
		Val.copyAny(any, copy::withAny);
		return copy;
	}

}

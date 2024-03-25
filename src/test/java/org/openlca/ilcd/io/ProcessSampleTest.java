package org.openlca.ilcd.io;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.openlca.ilcd.Tests;
import org.openlca.ilcd.commons.Compliance;
import org.openlca.ilcd.commons.DataSetType;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.ModellingPrinciple;
import org.openlca.ilcd.commons.ProcessType;
import org.openlca.ilcd.commons.PublicationStatus;
import org.openlca.ilcd.commons.UncertaintyDistribution;
import org.openlca.ilcd.processes.AllocationFactor;
import org.openlca.ilcd.processes.DataEntry;
import org.openlca.ilcd.processes.DataSetInfo;
import org.openlca.ilcd.processes.Exchange;
import org.openlca.ilcd.processes.ImpactResult;
import org.openlca.ilcd.processes.Location;
import org.openlca.ilcd.processes.Parameter;
import org.openlca.ilcd.processes.ParameterModel;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.processes.Review;
import org.openlca.ilcd.processes.Time;
import org.openlca.ilcd.util.Processes;

public class ProcessSampleTest {

	private final Process p = Tests.read(Process.class, "sdk_sample_process.xml");

	@Test
	public void testAdminInfo() {
		var pub = Processes.getPublication(p);
		assertNotNull(pub);
		assertEquals("00.00", pub.getVersion());
		assertEquals(2, pub.getPrecedingVersions().size());
		assertEquals(
			"http://www.ilcd-network.org/data/processes/sample_process.xml",
			pub.getUri().trim());
		assertEquals(PublicationStatus.WORKING_DRAFT, pub.getStatus());
		assertEquals(DataSetType.SOURCE, pub.getRepublication().getType());
		assertEquals(DataSetType.CONTACT, pub.getRegistrationAuthority().getType());
		assertEquals(DataSetType.CONTACT, pub.getOwner().getType());
		assertEquals(2, pub.getAccessRestrictions().size());

		DataEntry e = p.getAdminInfo().getDataEntry();
		assertNotNull(e.getTimeStamp());
		assertEquals(2, e.getFormats().size());
		assertEquals(DataSetType.SOURCE, e.getOriginalDataSet().getType());
		assertEquals(DataSetType.CONTACT, e.getDocumentor().getType());
		assertEquals(2, e.getUseApprovals().size());
	}

	@Test
	public void testDataSetInfo() {
		DataSetInfo info = p.getProcessInfo().getDataSetInfo();
		assertEquals(2, info.getComplementingProcesses().size());
		assertEquals("identifierOfSubDataSet0", info.getSubIdentifier());
		assertEquals(2, info.getClassifications().size());
		assertEquals("baseName0", LangString.get(info.getProcessName().getBaseName(), "en"));
	}

	@Test
	public void testTime() {
		Time time = p.getProcessInfo().getTime();
		assertEquals(1234, time.getReferenceYear().intValue());
		assertEquals(1234, time.getValidUntil().intValue());
		assertEquals(2, time.getDescription().size());
	}

	@Test
	public void testGeography() {
		Location loc = p.getProcessInfo().getGeography().getLocation();
		assertEquals("EU-28", loc.getCode());
		assertEquals(2, loc.getDescription().size());
	}

	@Test
	public void testParameters() {
		ParameterModel section = p.getProcessInfo().getParameterModel();
		assertEquals(2, section.getDescription().size());
		assertEquals(2, section.getParameters().size());
		Parameter param = section.getParameters().get(0);
		assertEquals("formula0", param.getFormula());
		assertEquals(0.0, param.getMean(), 0);
		assertEquals(0.0, param.getMin(), 0);
		assertEquals(0.0, param.getMax(), 0);
		assertEquals(12.123, param.getDispersion(), 1e-16);
		assertEquals(UncertaintyDistribution.UNDEFINED, param.getDistribution());
		assertEquals(2, param.getComment().size());
	}

	@Test
	public void testCompliance() {
		assertEquals(2, p.getModelling().getComplianceDeclarations().size());
		var c = p.getModelling().getComplianceDeclarations().get(0);
		assertNotNull(c.getSystem());
		var v = Compliance.FULLY_COMPLIANT;
		assertEquals(v, c.getApproval());
		assertEquals(v, c.getNomenclature());
		assertEquals(v, c.getMethod());
		assertEquals(v, c.getReview());
		assertEquals(v, c.getDocumentation());
		assertEquals(v, c.getQuality());
	}

	@Test
	public void testMethod() {
		var method = p.getModelling().getInventoryMethod();
		assertEquals(ProcessType.UNIT_PROCESS, method.getProcessType());
		assertEquals(ModellingPrinciple.ATTRIBUTIONAL, method.getPrinciple());
		assertEquals(2, method.getApproaches().size());
		assertEquals(2, method.getApproachDeviations().size());
		assertEquals(2, method.getConstants().size());
		assertEquals(2, method.getConstantsDeviations().size());
		assertEquals(2, method.getSources().size());
		assertEquals(2, method.getPrincipleDeviations().size());
	}

	@Test
	public void testReviews() {
		List<Review> reviews = p.getModelling().getValidation().getReviews();
		assertEquals(2, reviews.size());
		for (Review r : reviews) {
			assertEquals(2, r.getScopes().size());
			assertEquals(2, r.getDetails().size());
			assertEquals(2, r.getIndicators().size());
		}
	}

	@Test
	public void testAllocation() {
		Exchange e = p.getExchanges().get(0);
		assertEquals(2, e.getAllocations().size());
		AllocationFactor f = e.getAllocations().get(1);
		assertEquals(57.98, f.getFraction(), 1e-10);
		assertEquals(1, f.getProductExchangeId());
	}

	@Test
	public void testLCIAResults() {
		assertEquals(2, p.getImpactResults().size());
		ImpactResult r1 = p.getImpactResults().get(0);
		assertTrue(r1.getMethod().isValid());
		assertEquals(DataSetType.IMPACT_METHOD, r1.getMethod().getType());
	}
}

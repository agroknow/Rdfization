package com.agroknow.rdfization.service;

import com.agroknow.rdfization.model.bdg.BDGObservableProperty;
import com.agroknow.rdfization.model.bdg.BDGObservation;
import com.agroknow.rdfization.model.bdg.BDGObservationResult;
import com.agroknow.rdfization.model.bdg.BDGSensor;
import com.agroknow.rdfization.model.bdg.qudt.AbsoluteHumidity;
import com.agroknow.rdfization.model.bdg.qudt.DegreeCelsius;
import com.agroknow.rdfization.model.bdg.qudt.Percent;
import com.agroknow.rdfization.model.bdg.qudt.Unit;
import com.agroknow.rdfization.model.request.bdg.BDGConversionRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.cyberborean.rdfbeans.RDFBeanManager;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;

@Service
public class BDGRdfFileGenerationService {

    @Value("${storage.dir}")
    private String storageDir;

    public String generateRdf(BDGConversionRequest request) throws Exception {


        org.eclipse.rdf4j.repository.Repository repo =
                new org.eclipse.rdf4j.repository.sail.SailRepository(new org.eclipse.rdf4j.sail.memory.MemoryStore());
        repo.initialize();
        RepositoryConnection con = repo.getConnection();
        RDFBeanManager manager = new RDFBeanManager(con);

        String filePath = storageDir + String.format("%s_%s.%s", RandomStringUtils.randomAlphanumeric(8), System.currentTimeMillis(), "rdf");

        BDGSensor sensor = new BDGSensor();
        sensor.setLatitude(request.getSensor().getLatitude());
        sensor.setLongitude(request.getSensor().getLongitude());
        sensor.setName(request.getSensor().getName());

        request.getObservations().forEach(
                o -> {
                    BDGObservation observation = new BDGObservation();
                    observation.setResultTime(o.getDate());
                    observation.setSensor(sensor);

                    BDGObservationResult result = new BDGObservationResult();
                    result.setNumericValue(o.getResult().getValue());

                    BDGObservableProperty property = new BDGObservableProperty();
                    property.setSensor(sensor);
                    Unit unit = null;
                    if ("Humidity".equals(o.getResult().getUnit())) {
                        unit = new Percent();
                    } else if ("AirTemperature".equals(o.getResult().getUnit()) || "DiscomfortIndex".equals(o.getResult().getUnit())) {
                        unit = new DegreeCelsius();
                    }
                    property.setObserves(o.getResult().getUnit());
                    result.setUnit(unit);
                    observation.setResult(result);
                    observation.setObservableProperty(property);
//                    if (!sensor.getProperties().contains(property)) {
//                        sensor.getProperties().add(property);
//                    }
//                    sensor.getObservations().add(observation);
                    try {
                        manager.add(result);
                        manager.add(observation);
                    } catch (Exception ignored) {
                    }
                }
        );
        manager.add(sensor);

        manager.getRepositoryConnection().export(
                org.eclipse.rdf4j.rio.Rio.createWriter(org.eclipse.rdf4j.rio.RDFFormat.RDFXML, new FileOutputStream(filePath)));
        con.close();
        repo.shutDown();
        return filePath;

    }
}

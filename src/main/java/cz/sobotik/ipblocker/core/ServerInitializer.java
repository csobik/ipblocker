package cz.sobotik.ipblocker.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cz.sobotik.ipblocker.core.util.CsvLineReader;

/**
 * run before server starts to fill DB from given resources
 */
@Component
@Transactional
public class ServerInitializer implements ApplicationRunner {

  @Autowired
  ApplicationDataResourceProvider dataResourceProvider;

  @Autowired
  CsvLineReader csvLineReader;

  @Override
  public void run(ApplicationArguments applicationArguments) throws Exception {
    List<Resource> data = dataResourceProvider.getDataFileResources();
    data.stream().forEach(this::readAndParseResource);
  }

  private void readAndParseResource(Resource data) {
    try (GZIPInputStream stream = new GZIPInputStream(data.getInputStream());
         BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream))) {

      CSVParser parser = new CSVParser(bufferedReader, CSVFormat.DEFAULT.withDelimiter(','));

      for (CSVRecord record : parser) {
        csvLineReader.mapLine(record);
      }
      parser.close();

    } catch (IOException e) {
      // FIXME log it
      e.printStackTrace();
    }
  }
}

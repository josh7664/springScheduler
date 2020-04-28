package com.codenotfound.batch;

import com.codenotfound.model.RetailerDetails;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class CapitalizeNamesJobConfig {

  @Bean
  public Job convertNamesJob(JobBuilderFactory jobBuilders,
      StepBuilderFactory stepBuilders) {
    return jobBuilders.get("capitalizeNamesJob")
        .start(convertNamesStep(stepBuilders)).build();
  }

  @Bean
  public Step convertNamesStep(StepBuilderFactory stepBuilders) {
    return stepBuilders.get("capitalizeNamesStep")
        .<RetailerDetails, RetailerDetails>chunk(100).reader(itemReader())
        .processor(itemProcessor()).writer(itemWriter()).build();
  }

  @Bean
  public ItemReader<? extends RetailerDetails> itemReader() {
    return new FlatFileItemReaderBuilder<RetailerDetails>()
        .name("retailerDetailsItemReader")
        .resource(new ClassPathResource("csv/Retailerinfo.csv"))
        .delimited().names(new String[] {"retailer_id","retailer_name","retailer_location"})
        .targetType(RetailerDetails.class).build();
  }

  @Bean
  public RetailerDetailsItemProcessor itemProcessor() {
    return new RetailerDetailsItemProcessor();
  }

  @Bean
  public ItemWriter<RetailerDetails> itemWriter() {
      StaxEventItemWriter<RetailerDetails> xmlFileWriter = new StaxEventItemWriter<>();

      String exportFilePath = "target/test-outputs/RetailerDetails.xml";
      xmlFileWriter.setResource(new FileSystemResource(exportFilePath));

      xmlFileWriter.setRootTagName("RetailerDetails");

      Jaxb2Marshaller RetailerDetailsMarshaller = new Jaxb2Marshaller();
      RetailerDetailsMarshaller.setClassesToBeBound(RetailerDetails.class);
      xmlFileWriter.setMarshaller(RetailerDetailsMarshaller);

      return xmlFileWriter;
  }
}

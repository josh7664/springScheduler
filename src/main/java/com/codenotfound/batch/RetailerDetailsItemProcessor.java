package com.codenotfound.batch;

import com.codenotfound.model.RetailerDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class RetailerDetailsItemProcessor
        implements ItemProcessor<RetailerDetails, RetailerDetails> {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(RetailerDetailsItemProcessor.class);

    @Override
    public RetailerDetails process(RetailerDetails retailerDetails) throws Exception {
        RetailerDetails result = new RetailerDetails();
        result.setRetailer_id(retailerDetails.getRetailer_id());
        result.setRetailer_name(retailerDetails.getRetailer_name());
        result.setRetailer_location(retailerDetails.getRetailer_location());

        LOGGER.info("converting '{}' into '{}'", retailerDetails, result);
        return result;
    }
}
package com.nice.order.center.common.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

/**
 * TODO Any problem with ModelMapper + Lombok?
 */
public class ModelMapperUtil {

    /**
     * Get modelMapper instance with MatchingStrategies=STANDARD and
     * FieldMatchingEnabled=true<br>
     * <br>
     * Note: enable "FieldMatchingEnabled=true" can help to successfully match
     * obsolete fields in A against A1 class, which A1 have
     * setter & getter while another don't
     *
     * @return
     */
    // TODO If A has getter but no setter, and setFieldMatchingEnabled = false, can it work?
    public static ModelMapper getModelMapperWithFieldMatching() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD).setFieldMatchingEnabled(true).setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        return modelMapper;
    }

}

package com.epam.courses.warehouse.web_app;

import com.epam.courses.warehouse.model.filter.ProductRecordDateInterval;
import com.epam.courses.warehouse.service_rest.ProductRecordDtoServiceRest;
import com.epam.courses.warehouse.service_rest.ProductRecordServiceRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class RecordController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecordController.class);

    private final ProductRecordDtoServiceRest productRecordDtoServiceRest;

    private final ProductRecordServiceRest productRecordServiceRest;

    public RecordController(ProductRecordServiceRest productRecordService,
                            ProductRecordDtoServiceRest productRecordDtoService){
        this.productRecordServiceRest = productRecordService;
        this.productRecordDtoServiceRest = productRecordDtoService;
    }

    @GetMapping("/circulation")
    public final String records(Model model){
        LOGGER.debug("RecordController:records");

        ProductRecordDateInterval interval = new ProductRecordDateInterval();

        model.addAttribute("interval", interval);
        model.addAttribute("records", productRecordDtoServiceRest.getAll());
        return "circulation";
    }

    @GetMapping("/filter")
    public final String filterRecords(@ModelAttribute("interval") ProductRecordDateInterval interval,
                               Model model) {
        model.addAttribute("interval", interval);
        model.addAttribute("records", productRecordDtoServiceRest.getAllInTimeInterval(
                interval.getStartInterval(),
                interval.getEndInterval()));
        return "circulation";
    }



}
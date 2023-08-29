package rsm.project.OkxDemoCommunication.controller;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.service.publicData.PublicDataAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicDataService {
    @Autowired
    private PublicDataAPIService publicDataAPIService;

    @GetMapping("/display_instrumentation_details")
    public ResponseEntity<Object> showInstrumentationDetails() {
        JSONObject jsonData = publicDataAPIService.getInstruments("SWAP", null, null, null);
        return new ResponseEntity<>(jsonData, HttpStatus.OK);
    }
}

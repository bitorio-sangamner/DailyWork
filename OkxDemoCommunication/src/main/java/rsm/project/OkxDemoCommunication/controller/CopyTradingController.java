package rsm.project.OkxDemoCommunication.controller;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.service.copytrading.CopytradingAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.http.Query;

@RestController()
public class CopyTradingController {
    @Autowired
    CopytradingAPIService copytradingAPIService;

    @GetMapping("/copy_trading/get_instruments")
    public ResponseEntity<JSONObject> getInstruments() {
        JSONObject jsonObject = copytradingAPIService.getInstruments();
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/copy_trading/current_subpositions/{instId}")
    public ResponseEntity<JSONObject> currentSubpositions(@PathVariable String instId) {
        JSONObject jsonObject = copytradingAPIService.currentSubpositions(instId);
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/copy_trading/get_subposition_history")
    public ResponseEntity<JSONObject> subpositionsHistory(@RequestBody JSONObject jsonData) {
        String instId = jsonData.getString("instId");
        String after = jsonData.getString("after");
        String before = jsonData.getString("before");
        String limit = jsonData.getString("limit");
        JSONObject jsonObject = copytradingAPIService.subpositionsHistory(instId, after, before, limit);
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

}
